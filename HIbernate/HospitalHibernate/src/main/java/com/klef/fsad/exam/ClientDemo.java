package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

/**
 * ClientDemo
 *
 * Demonstrates two Hibernate operations against the Hospital entity:
 *   I.  Insert a new record into the database.
 *   II. View (retrieve) a record by its primary key (ID).
 *
 * Package : com.klef.fsad.exam
 * Database: fsadexam
 */
public class ClientDemo {

    // ----------------------------------------------------------------
    // Shared SessionFactory — created once, reused across operations
    // ----------------------------------------------------------------
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")   // loads from classpath
                    .buildSessionFactory();
            System.out.println("\n[INFO] Hibernate SessionFactory initialised successfully.");
        } catch (Exception e) {
            System.err.println("[ERROR] SessionFactory creation failed: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    // ================================================================
    //  I. INSERT — persist a new Hospital record
    // ================================================================

    /**
     * Inserts a Hospital object into the database.
     *
     * @param hospital the transient Hospital object to persist
     * @return the auto-generated primary key assigned by the database
     */
    public static int insertHospital(Hospital hospital) {

        Session     session     = sessionFactory.openSession();
        Transaction transaction = null;
        int generatedId = -1;

        try {
            transaction = session.beginTransaction();

            // save() persists the object and returns the generated ID
            generatedId = (int) session.save(hospital);

            transaction.commit();
            System.out.println("\n[SUCCESS] Hospital record inserted successfully.");
            System.out.println("[INFO]    Auto-generated Hospital ID = " + generatedId);

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("[ERROR] Insert failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }

        return generatedId;
    }

    // ================================================================
    //  II. VIEW — retrieve a Hospital record by ID
    // ================================================================

    /**
     * Retrieves a Hospital record from the database using its primary key.
     *
     * @param hospitalId the ID of the hospital to look up
     * @return the Hospital object, or null if not found
     */
    public static Hospital viewHospitalById(int hospitalId) {

        Session  session  = sessionFactory.openSession();
        Hospital hospital = null;

        try {
            // get() returns null when the row does not exist (no exception)
            hospital = session.get(Hospital.class, hospitalId);

            if (hospital != null) {
                System.out.println("\n[SUCCESS] Hospital record found:");
                System.out.println(hospital);
            } else {
                System.out.println("\n[INFO] No Hospital found with ID = " + hospitalId);
            }

        } catch (Exception e) {
            System.err.println("[ERROR] View failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }

        return hospital;
    }

    // ================================================================
    //  MAIN — entry point
    // ================================================================

    public static void main(String[] args) {

        System.out.println("\n========================================================");
        System.out.println("   Maven Hibernate Demo — Hospital Entity");
        System.out.println("   Package  : com.klef.fsad.exam");
        System.out.println("   Database : fsadexam");
        System.out.println("========================================================");

        // ---- Operation I : INSERT --------------------------------
        System.out.println("\n--- Operation I : Insert a new Hospital record ---");

        Hospital h1 = new Hospital(
                "KLEF General Hospital",                          // name
                "A 500-bed multi-speciality hospital serving " +
                "students and staff of KL University.",           // description
                LocalDate.of(2005, 6, 15),                        // established date
                "Active",                                          // status
                "Vaddeswaram, Guntur District, Andhra Pradesh",   // location
                "0863-2344567",                                    // contact number
                "hospital@kluniversity.in",                        // email
                500,                                               // number of beds
                "Multispeciality"                                  // hospital type
        );

        int newId = insertHospital(h1);

        // ---- Operation II : VIEW BY ID ---------------------------
        System.out.println("\n--- Operation II : View Hospital record by ID ---");

        if (newId != -1) {
            // Retrieve the record we just inserted
            viewHospitalById(newId);
        }

        // Also demonstrate retrieval of a non-existent ID
        System.out.println("\n--- Attempting to view a non-existent record (ID = 9999) ---");
        viewHospitalById(9999);

        // ---- Cleanup ---------------------------------------------
        if (sessionFactory != null) {
            sessionFactory.close();
        }

        System.out.println("\n[INFO] Program completed. SessionFactory closed.");
    }
}
