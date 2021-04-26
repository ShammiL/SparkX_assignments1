CREATE DATABASE pets;

use pets;

CREATE TABLE owner (
	owner_id INT(5) PRIMARY KEY,
    name VARCHAR(45) not null,
    surname VARCHAR(45) not null,
    street_address VARCHAR(60) not null,
    city VARCHAR(20) not null,
    state VARCHAR(20) not null,
    state_full VARCHAR(20) not null,
    zip_code INT(7) not null
);

CREATE TABLE pet (
    pet_id VARCHAR(100) PRIMARY KEY,
    name VARCHAR(45) not null,
    kind VARCHAR(50) not null,
    gender VARCHAR(20) not null,
    age INT(3) not null,
    owner_id INT(5) not null,
    FOREIGN KEY (owner_id)
		REFERENCES owner (owner_id)
);

CREATE TABLE procedure_detail (
    procedure_type VARCHAR(50) NOT NULL,
    procedure_subcode INT(5) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (procedure_type , procedure_subcode)
);

CREATE TABLE procedure_history (
    pet_id VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    procedure_type VARCHAR(50) NOT NULL,
    procedure_subcode INT(5) NOT NULL,
    FOREIGN KEY (procedure_type , procedure_subcode)
        REFERENCES procedure_detail (procedure_type , procedure_subcode),
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
    
SELECT DISTINCT
    owner.name
FROM
    pet
        JOIN
    owner ON pet.owner_id = owner.owner_id
WHERE
    kind = 'parrot';

SELECT 
    zip_code, COUNT(*)
FROM
    owner
group by
    zip_code;
    
SELECT DISTINCT
    pet.name
FROM
    pet
        JOIN
    procedure_history ON pet.pet_id = procedure_history.pet_id
WHERE
    MONTH(date) = 02 AND YEAR(date) = 2016;
            
SELECT 
    SUM(procedure_detail.price)
FROM
    procedure_detail
        JOIN
    procedure_history ON procedure_detail.procedure_type = procedure_history.procedure_type
        AND procedure_detail.procedure_subcode = procedure_history.procedure_subcode
        JOIN
    pet ON procedure_history.pet_id = pet.pet_id
        JOIN
    owner ON pet.owner_id = owner.owner_id
WHERE
    owner.zip_code = 49503
        AND MONTH(date) = 03;
