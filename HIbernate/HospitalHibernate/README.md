# Hospital Hibernate Project
**Package:** `com.klef.fsad.exam`  
**Database:** `fsadexam`

---

## Project Structure

```
HospitalHibernate/
├── pom.xml
├── README.md
└── src/
    └── main/
        ├── java/
        │   └── com/klef/fsad/exam/
        │       ├── Hospital.java       ← Entity class
        │       └── ClientDemo.java     ← Main / operations class
        └── resources/
            └── hibernate.cfg.xml       ← DB connection config
```

---

## Hospital Entity — Fields

| Field         | Column            | Type        | Notes                     |
|---------------|-------------------|-------------|---------------------------|
| id            | hospital_id       | int (PK)    | Auto-generated (IDENTITY) |
| name          | hospital_name     | varchar(150)| Required                  |
| description   | description       | varchar(500)|                           |
| date          | established_date  | date        |                           |
| status        | status            | varchar(50) | e.g. Active / Inactive    |
| location      | location          | varchar(200)|                           |
| contactNumber | contact_number    | varchar(15) |                           |
| email         | email             | varchar(100)|                           |
| numberOfBeds  | number_of_beds    | int         |                           |
| hospitalType  | hospital_type     | varchar(80) | General / Multispeciality |

---

## Prerequisites

| Tool    | Version  | Download |
|---------|----------|---------- |
| JDK     | 11+      | https://adoptium.net |
| Maven   | 3.6+     | https://maven.apache.org/download.cgi |
| MySQL   | 8.0+     | https://dev.mysql.com/downloads/mysql/ |

Verify installations:
```cmd
java -version
mvn -version
mysql --version
```

---

## Step 1 — Configure Database Credentials

Open `src/main/resources/hibernate.cfg.xml` and update:

```xml
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">YOUR_MYSQL_PASSWORD</property>
```

> The database `fsadexam` will be **created automatically** if it does not exist
> (thanks to `createDatabaseIfNotExist=true` in the JDBC URL).

---

## Step 2 — Run from Eclipse IDE

1. Open Eclipse → **File → Import → Existing Maven Projects**
2. Browse to the extracted `HospitalHibernate` folder → **Finish**
3. Wait for Maven to download dependencies (watch bottom-right progress bar)
4. Right-click `ClientDemo.java` → **Run As → Java Application**
5. Output appears in the **Console** tab

---

## Step 3 — Run from Command Prompt (CMD) — Recommended ✅

Open CMD and navigate to the project folder:

```cmd
cd path\to\HospitalHibernate
```

### Build the project
```cmd
mvn clean package -DskipTests
```
This compiles code and creates `target/HospitalHibernate-1.0-SNAPSHOT.jar`

### Run the program
```cmd
mvn exec:java -Dexec.mainClass="com.klef.fsad.exam.ClientDemo"
```

**OR** run the fat JAR directly:
```cmd
java -jar target/HospitalHibernate-1.0-SNAPSHOT.jar
```

---

## Step 4 — Full CMD Workflow (copy-paste ready)

```cmd
REM 1. Go to project directory
cd C:\Users\YourName\Desktop\HospitalHibernate

REM 2. Clean and build
mvn clean package -DskipTests

REM 3. Run
mvn exec:java -Dexec.mainClass="com.klef.fsad.exam.ClientDemo"
```

---

## Expected Console Output

```
========================================================
   Maven Hibernate Demo — Hospital Entity
   Package  : com.klef.fsad.exam
   Database : fsadexam
========================================================

--- Operation I : Insert a new Hospital record ---
Hibernate: insert into hospital (...) values (?, ?, ...)
[SUCCESS] Hospital record inserted successfully.
[INFO]    Auto-generated Hospital ID = 1

--- Operation II : View Hospital record by ID ---
Hibernate: select hospital0_.hospital_id as ... from hospital ...
[SUCCESS] Hospital record found:

============================================================
  Hospital Record
============================================================
  ID            : 1
  Name          : KLEF General Hospital
  Description   : A 500-bed multi-speciality hospital ...
  Estd. Date    : 2005-06-15
  Status        : Active
  Location      : Vaddeswaram, Guntur District, Andhra Pradesh
  Contact No.   : 0863-2344567
  Email         : hospital@kluniversity.in
  No. of Beds   : 500
  Hospital Type : Multispeciality
============================================================
```

---

## Verify in MySQL

```sql
mysql -u root -p

USE fsadexam;
SHOW TABLES;
SELECT * FROM hospital;
```

---

## Common Errors & Fixes

| Error | Fix |
|-------|-----|
| `Access denied for user 'root'` | Update username/password in `hibernate.cfg.xml` |
| `Communications link failure` | Make sure MySQL service is running |
| `java.lang.ClassNotFoundException` | Run `mvn clean package` first |
| `Unknown database 'fsadexam'` | Add `createDatabaseIfNotExist=true` to JDBC URL (already included) |

---

## IDE vs CMD

| | Eclipse | CMD |
|---|---|---|
| Good for | Debugging, editing | Submission, automation |
| Setup needed | Import Maven project | Just `mvn` commands |
| Run command | Right-click → Run As | `mvn exec:java` |

Both work perfectly for this project.
