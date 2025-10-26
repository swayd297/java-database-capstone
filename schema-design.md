# MySQL Database Design – Java Database Capstone Project

## 1. Database Name
`healthcare_system`

---

## 2. Tables Overview
The system contains four main entities:
- **Doctor** – Information about doctors and their specialties.
- **Patient** – Information about registered patients.
- **Appointment** – Tracks patient appointments with doctors.
- **Admin** – Manages the overall system.

---

## 3. Entity Relationship Diagram (ERD)

Doctor (1) ───< Appointment >─── (1) Patient  
Admin manages both Doctor and Patient entities.

---

## 4. Tables and Fields

### 4.1 Doctor Table
| Field | Type | Description |
|-------|------|--------------|
| doctor_id | INT (PK, AUTO_INCREMENT) | Unique ID for each doctor |
| name | VARCHAR(100) | Doctor's full name |
| specialization | VARCHAR(100) | Medical specialization |
| email | VARCHAR(100) | Contact email |
| phone | VARCHAR(20) | Contact phone number |

---

### 4.2 Patient Table
| Field | Type | Description |
|-------|------|-------------|
| patient_id | INT (PK, AUTO_INCREMENT) | Unique ID for each patient |
| name | VARCHAR(100) | Patient’s full name |
| email | VARCHAR(100) | Contact email |
| phone | VARCHAR(20) | Contact phone number |
| address | VARCHAR(255) | Residential address |

---

### 4.3 Appointment Table
| Field | Type | Description |
|-------|------|-------------|
| appointment_id | INT (PK, AUTO_INCREMENT) | Unique appointment ID |
| doctor_id | INT (FK) | References `doctor(doctor_id)` |
| patient_id | INT (FK) | References `patient(patient_id)` |
| appointment_date | DATETIME | Date and time of appointment |
| notes | TEXT | Additional remarks |

**Relationships:**
- Each appointment belongs to one doctor and one patient.

---

### 4.4 Admin Table
| Field | Type | Description |
|-------|------|-------------|
| admin_id | INT (PK, AUTO_INCREMENT) | Unique ID for admin |
| username | VARCHAR(50) | Admin login username |
| password | VARCHAR(100) | Encrypted password |
| email | VARCHAR(100) | Admin contact email |

---

## 5. SQL Script Example

```sql
CREATE DATABASE healthcare_system;
USE healthcare_system;

CREATE TABLE Doctor (
  doctor_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  specialization VARCHAR(100),
  email VARCHAR(100),
  phone VARCHAR(20)
);

CREATE TABLE Patient (
  patient_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100),
  phone VARCHAR(20),
  address VARCHAR(255)
);

CREATE TABLE Appointment (
  appointment_id INT AUTO_INCREMENT PRIMARY KEY,
  doctor_id INT,
  patient_id INT,
  appointment_date DATETIME,
  notes TEXT,
  FOREIGN KEY (doctor_id) REFERENCES Doctor(doctor_id),
  FOREIGN KEY (patient_id) REFERENCES Patient(patient_id)
);

CREATE TABLE Admin (
  admin_id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50),
  password VARCHAR(100),
  email VARCHAR(100)
);
