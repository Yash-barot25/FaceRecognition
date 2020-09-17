INSERT IGNORE INTO dean(dean_id,first_name,last_name, dean_email, dean_contact_number)
values ('25252525', 'ak-47', 'akm', 'm416@gmail.com','528529688');

INSERT IGNORE INTO institute(institute_code, institute_name,institute_address, institute_email, dean_id)
VALUES (007, 'sheridan college', '7899 McLaughlin Rd, Brampton, ON L6Y 5H9', 'sheridancollege@sheridan.ca', '25252525');

INSERT IGNORE INTO department(department_id,department_name,department_description, institute_id)
VALUES (123, 'science & technology', 'department of science & tech. provides various technical courses', 007),
 (234, 'art & culture', 'department of art & culture. provides various culture related courses', 007);

INSERT IGNORE INTO program(program_code,program_name, num_of_semester , program_description, campus,department_id)
VALUES ('sydney', 'software engineering', 6, 'all concept of software engineering', 'DAVIS', 123),
 ('sydoun', 'computer programming', 4, 'teaches various programming languages', 'TRAFALGAR', 123),
('CULTO25', 'master in history', 8, 'art & history topics', 'HAZEL_MCCALLION',234);

INSERT IGNORE INTO student(student_id,student_GPA,student_address,campus, student_email, first_name, last_name, student_contact_number, student_current_semester, program_id)
VALUES (991494010, 4.0, '430 mcmurchy ave south','DAVIS','baroty@sheridancollege.ca','yashkumar','barot','2899466420',6,'sydney'),
        (991494011, 3.0, '440 mcmurchy ave south','TRAFALGAR','panchoja@sheridancollege.ca','jaydat','pancholi','9654589623',4,'sydoun'),
        (991494012, 3.9, '450 mcmurchy ave south','DAVIS','dipu@sheridancollege.ca','dipu','patel','8546985225',6, 'sydoun'),
		(991494013, 2.0, '460 mcmurchy ave south','DAVIS','parth@sheridancollege.ca','parth','patel','2364521245',4,'CULTO25' );


INSERT IGNORE INTO professor(professor_id,first_name,last_name,professor_email,campus, department_id)
VALUES ('2500', 'jaweed','seikh', 'jaweed@gmail.com', 'DAVIS', 123 ),
       ('2501', 'nicolas','ivanov', 'ivanov@gmail.com', 'DAVIS' , 123),
       ('2502', 'harleen','kaur', 'kaur@gmail.com', 'TRAFALGAR' , 234),
       ('2503', 'walid','belal', 'walid@gmail.com', 'OAKVILLE' , 234);

INSERT IGNORE INTO experties(experty, professor_id)
VALUES ('JAVA', '2500'), ('C#', '2501'), ('History', '2502'), ('Architect', '2503');

INSERT IGNORE INTO course(course_code,course_name ,course_credit, course_offered_in_semester,course_description, campus, coordinator_id)
VALUES ('SYST100', 'java',6.0, 3, 'various concept of java technology', 'DAVIS', '2500' ),
 ('SYST099', 'c#',4.0, 4, 'various concept of c# technology', 'DAVIS', '2500' ),
 ('SYST0999', 'SQL',3.0, 2, 'various concept of SQL technology', 'TRAFALGAR', '2500' ),
('SYST2563', 'linux',3.0, 3, 'various concept of linux technology', 'DAVIS' , '2501'),
 ('CULT889', 'history',3.0, 1, 'history of canada', 'HAZEL_MCCALLION', '2500' ),
 ('INFO889', 'drawing',2.0, 2, 'drawing classes', 'HAZEL_MCCALLION' ,'2502');


INSERT IGNORE INTO program_course(program_id, course_id)
VALUES ('sydney','SYST100' ),
 ('sydoun','SYST099' ),
 ('sydney','SYST0999' ),
 ('sydoun','SYST2563' ),
  ('CULTO25','CULT889' ),
   ('CULTO25','INFO889' );

INSERT IGNORE INTO professor_course(professor_id, course_id)
VALUES ('2500','SYST100' ),
 ('2501','SYST099' ),
 ('2500','SYST0999' ),
 ('2502','SYST2563' ),
  ('2503','CULT889' ),
   ('2502','INFO889' );



INSERT IGNORE INTO professor_program(professor_id, program_id)
VALUES ('2500','sydney' ),
 ('2501','sydoun' ),
 ('2502','CULTO25' ),
 ('2503','sydney' );


