package com.klef.fsad.exam;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Hospital — JPA/Hibernate Entity
 *
 * Table will be auto-created in the "fsadexam" database as "hospital".
 */
@Entity
@Table(name = "hospital")
public class Hospital {

    // ----------------------------------------------------------------
    // Primary Key — auto-incremented by the database
    // ----------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id")
    private int id;

    // ----------------------------------------------------------------
    // Required attributes
    // ----------------------------------------------------------------

    @Column(name = "hospital_name", nullable = false, length = 150)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "established_date")
    private LocalDate date;

    @Column(name = "status", length = 50)
    private String status;

    // ----------------------------------------------------------------
    // Additional relevant attributes
    // ----------------------------------------------------------------

    @Column(name = "location", length = 200)
    private String location;

    @Column(name = "contact_number", length = 15)
    private String contactNumber;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "number_of_beds")
    private int numberOfBeds;

    @Column(name = "hospital_type", length = 80)
    private String hospitalType;     // e.g. General, Speciality, Multispeciality

    // ----------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------

    /** No-arg constructor required by Hibernate */
    public Hospital() {}

    /** Convenience constructor (ID is generated automatically) */
    public Hospital(String name, String description, LocalDate date,
                    String status, String location, String contactNumber,
                    String email, int numberOfBeds, String hospitalType) {
        this.name          = name;
        this.description   = description;
        this.date          = date;
        this.status        = status;
        this.location      = location;
        this.contactNumber = contactNumber;
        this.email         = email;
        this.numberOfBeds  = numberOfBeds;
        this.hospitalType  = hospitalType;
    }

    // ----------------------------------------------------------------
    // Getters & Setters
    // ----------------------------------------------------------------

    public int getId()                        { return id; }
    public void setId(int id)                 { this.id = id; }

    public String getName()                   { return name; }
    public void setName(String name)          { this.name = name; }

    public String getDescription()            { return description; }
    public void setDescription(String d)      { this.description = d; }

    public LocalDate getDate()                { return date; }
    public void setDate(LocalDate date)       { this.date = date; }

    public String getStatus()                 { return status; }
    public void setStatus(String status)      { this.status = status; }

    public String getLocation()               { return location; }
    public void setLocation(String location)  { this.location = location; }

    public String getContactNumber()              { return contactNumber; }
    public void setContactNumber(String c)        { this.contactNumber = c; }

    public String getEmail()                      { return email; }
    public void setEmail(String email)            { this.email = email; }

    public int getNumberOfBeds()                  { return numberOfBeds; }
    public void setNumberOfBeds(int n)            { this.numberOfBeds = n; }

    public String getHospitalType()               { return hospitalType; }
    public void setHospitalType(String t)         { this.hospitalType = t; }

    // ----------------------------------------------------------------
    // toString — used when printing a record
    // ----------------------------------------------------------------
    @Override
    public String toString() {
        return "\n============================================================"
             + "\n  Hospital Record"
             + "\n============================================================"
             + "\n  ID            : " + id
             + "\n  Name          : " + name
             + "\n  Description   : " + description
             + "\n  Estd. Date    : " + date
             + "\n  Status        : " + status
             + "\n  Location      : " + location
             + "\n  Contact No.   : " + contactNumber
             + "\n  Email         : " + email
             + "\n  No. of Beds   : " + numberOfBeds
             + "\n  Hospital Type : " + hospitalType
             + "\n============================================================\n";
    }
}
