CREATE DATABASE pets;

use pets;

CREATE TABLE owner (
    owner_id INT PRIMARY KEY,
    name VARCHAR(45),
    surname VARCHAR(45),
    street_address VARCHAR(60),
    city VARCHAR(20),
    state VARCHAR(20),
    state_full VARCHAR(20),
    zip_code INT
);

CREATE TABLE pet (
    pet_id VARCHAR(100) PRIMARY KEY,
    name VARCHAR(45),
    kind VARCHAR(50),
    gender VARCHAR(20),
    age INT,
    owner_id INT,
    FOREIGN KEY (owner_id)
        REFERENCES owner (owner_id)
);

CREATE TABLE procedure_detail (
    procedure_type VARCHAR(50) NOT NULL,
    procedure_subcode INT NOT NULL,
    description VARCHAR(255),
    price DECIMAL,
    CONSTRAINT proc_detail PRIMARY KEY (procedure_type , procedure_subcode)
);

CREATE TABLE procedure_history (
    pet_id VARCHAR(100),
    date DATE,
    procedure_type VARCHAR(50),
    procedure_subcode INT,
    FOREIGN KEY (procedure_type)
        REFERENCES procedure_detail (procedure_type),
    -- FOREIGN KEY (procedure_subcode)
--         REFERENCES procedure_detail (procedure_subcode),
    FOREIGN KEY (pet_id)
        REFERENCES pet (pet_id)
);

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/owner.csv' INTO TABLE owner FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\n' IGNORE 1 ROWS;

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/pet.csv' INTO TABLE pet FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\n' IGNORE 1 ROWS;

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/procedure_detail.csv' INTO TABLE procedure_detail FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\n' IGNORE 1 ROWS;

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/procedure_history.csv' INTO TABLE procedure_history FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\n' IGNORE 1 ROWS;

SELECT 
    procedure_type
FROM
    procedure_detail
WHERE
    price > 150;
    
SELECT 
    name
FROM
    owner
WHERE
    owner_id IN (SELECT 
            owner_id
        FROM
            pet
        WHERE
            kind = 'parrot');

SELECT 
    COUNT(DISTINCT O.name)
FROM
    owner AS O,
    owner AS N
WHERE
    O.zip_code = N.zip_code;
    
select date from procedure_history;

SELECT 
    name
FROM
    pet
WHERE
    pet_id IN (SELECT 
            pet_id
        FROM
            procedure_history
        WHERE
            MONTH(date) = 02 AND YEAR(date) = 2016);
            
SELECT 
    SUM(procedure_detail.price)
FROM
    (procedure_detail
    INNER JOIN procedure_history ON procedure_detail.procedure_type = procedure_history.procedure_type
        AND procedure_detail.procedure_subcode = procedure_history.procedure_subcode)
WHERE
    procedure_history.pet_id IN (SELECT 
            pet.pet_id
        FROM
            (pet
            INNER JOIN owner USING (owner_id))
        WHERE
            owner.zip_code = 49503);