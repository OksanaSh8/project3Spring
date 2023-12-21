CREATE TABLE groups (
    group_name varchar(50)  PRIMARY KEY
);

CREATE TABLE students(
                         id int(11) auto_increment PRIMARY KEY,
                         full_name varchar(100) NOT NULL UNIQUE,
                         year_of_birth int NOT NULL,
                         group_name varchar(50) references groups(group_name)
);

CREATE TABLE subject(
                        id int auto_increment PRIMARY KEY ,
                        subject_name varchar(50) NOT NULL
);

CREATE TABLE groups_subjects(
                                group_id varchar(100) references groups(group_name),
                                subject_id int references subject(id),
                                PRIMARY KEY (group_id, subject_id)
);

INSERT INTO students(full_name, year_of_birth, group_name) VALUES ('Ivanov Ivan Vsevolodovich', 2001, 'IT');
INSERT INTO students(full_name, year_of_birth, group_name) VALUES ('Petrov Oleg Sergeevich', 2001, 'AC');
INSERT INTO students(full_name, year_of_birth, group_name) VALUES ('Sidorova Marina Olegovna', 2001, 'IT');
INSERT INTO students(full_name, year_of_birth, group_name) VALUES ('Savchenko Ekaterina Petrovna', 2001, 'AC');
INSERT INTO students(full_name, year_of_birth, group_name) VALUES ('Gorbacheva Vera Mikhailovna', 2001, 'IT');
INSERT INTO students(full_name, year_of_birth, group_name) VALUES ('Gladchenko Vladimir Olegovich', 2001, 'IT');
INSERT INTO students(full_name, year_of_birth, group_name) VALUES ('Solovey Anna Nikolaevna', 2001,'IT');
INSERT INTO students(full_name, year_of_birth, group_name) VALUES ('Lisichkina Yana Igorevna', 2001,'IT');

INSERT INTO groups(group_name) values ('IT');
INSERT INTO groups(group_name) values ('AC');

INSERT INTO subject(subject_name) values ('mathematics');
INSERT INTO subject(subject_name) values ('physics');
INSERT INTO subject(subject_name) values ('accounting');
INSERT INTO subject(subject_name) values ('programming languages');
INSERT INTO subject(subject_name) values ('database');
INSERT INTO subject(subject_name) values ('English language');

INSERT INTO groups_subjects(group_id, subject_id) values ('IT',1);
INSERT INTO groups_subjects(group_id, subject_id) values ('IT',2);
INSERT INTO groups_subjects(group_id, subject_id) values ('IT',4);
INSERT INTO groups_subjects(group_id, subject_id) values ('IT',5);
INSERT INTO groups_subjects(group_id, subject_id) values ('AC',1);
INSERT INTO groups_subjects(group_id, subject_id) values ('AC',2);
INSERT INTO groups_subjects(group_id, subject_id) values ('AC',3);
INSERT INTO groups_subjects(group_id, subject_id) values ('AC',6);
INSERT INTO groups_subjects(group_id, subject_id) values ('IT',6);
