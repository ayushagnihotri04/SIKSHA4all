create database education;

use education;

create table teachers( 
    teacher_id int auto_increment primary key,
    name char(45) not null,
    email char(255) not null unique,
    password char(255) not null,
    contact char(10),
    department char(50),
    dob date
);


create table students( 
    student_id int auto_increment primary key,
    name char(45) not null,
    email char(255) not null unique,
    password char(255) not null
);

CREATE TABLE attendance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(100),
    enrollment_number VARCHAR(100),
    institution VARCHAR(100),
    class VARCHAR(100),
    subject VARCHAR(100),
    lecture_number INT(10),
    status VARCHAR(20),
    date DATE
);
