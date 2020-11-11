INSERT IGNORE INTO roles(id,name) VALUES ('1','ADMIN');
INSERT IGNORE INTO roles(id,name) VALUES ('2','STUDENT');

# use face_recognition;
INSERT IGNORE INTO users (id, useremail, password, enabled, role_id)
VALUES ('1','stealth@sheridan.com','$2a$10$a3A0JC757FLZqHYVvEJq7uNaYz3AW8320IHORzmljQN43Hkmi4T7C', true,'1');

-- INSERT IGNORE INTO dean(dean_id,first_name,last_name, dean_email, dean_contact_number)
-- values (25252525, 'ak-47', 'akm', 'm416@gmail.com','528529688');
--
-- INSERT IGNORE INTO institute(institute_code, institute_name,institute_address, institute_email, dean_id)
-- VALUES (007, 'sheridan college', '7899 McLaughlin Rd, Brampton, ON L6Y 5H9', 'sheridancollege@sheridan.ca', 25252525);
--
-- INSERT IGNORE INTO department(department_id,department_name,department_description, institute_id)
-- VALUES (123, 'science & technology', 'department of science & tech. provides various technical courses', 007),
--  (234, 'art & culture', 'department of art & culture. provides various culture related courses', 007);
--
-- INSERT IGNORE INTO program(program_code,program_name, num_of_semester , program_description, campus,department_id)
-- VALUES (85, 'software engineering', 6, 'all concept of software engineering', 'DAVIS', 123),
--  (89, 'computer programming', 4, 'teaches various programming languages', 'TRAFALGAR', 123),
-- (78, 'master in history', 8, 'art & history topics', 'HAZEL_MCCALLION',234);
--
-- INSERT IGNORE INTO student(student_id,student_GPA,student_address,campus, student_email, first_name, last_name, student_contact_number, student_current_semester, program_id)
-- VALUES (991494010, 4.0, '430 mcmurchy ave south','DAVIS','baroty@sheridancollege.ca','yashkumar','barot','2899466420',6,85),
--         (991494011, 3.0, '440 mcmurchy ave south','TRAFALGAR','panchoja@sheridancollege.ca','jaydat','pancholi','9654589623',4,89),
--         (991494012, 3.9, '450 mcmurchy ave south','DAVIS','dipu@sheridancollege.ca','dipu','patel','8546985225',6, 78),
-- 		(991494013, 2.0, '460 mcmurchy ave south','DAVIS','parth@sheridancollege.ca','parth','patel','2364521245',4,85 ),
--  (991494014, 4.0, '430 mcmurchy ave south','DAVIS','baroty@sheridancollege.ca','yashkumar','barot','2899466420',6,89),
--         (991494015, 3.0, '440 mcmurchy ave south','TRAFALGAR','panchoja@sheridancollege.ca','jaydat','pancholi','9654589623',4,78),
--         (991494016, 3.9, '450 mcmurchy ave south','DAVIS','dipu@sheridancollege.ca','dipu','patel','8546985225',6, 85),
-- 		(991494017, 2.0, '460 mcmurchy ave south','DAVIS','parth@sheridancollege.ca','parth','patel','2364521245',4,89 ),
--  (991494018, 4.0, '430 mcmurchy ave south','DAVIS','baroty@sheridancollege.ca','yashkumar','barot','2899466420',6,78),
--         (991494019, 3.0, '440 mcmurchy ave south','TRAFALGAR','panchoja@sheridancollege.ca','jaydat','pancholi','9654589623',4,78),
--         (991494020, 3.9, '450 mcmurchy ave south','DAVIS','dipu@sheridancollege.ca','dipu','patel','8546985225',6, 85),
-- 		(991494021, 2.0, '460 mcmurchy ave south','DAVIS','parth@sheridancollege.ca','parth','patel','2364521245',4,89 );
--
--
-- INSERT IGNORE INTO professor(professor_id,first_name,last_name,professor_email,campus, department_id)
-- VALUES (2500, 'jaweed','seikh', 'jaweed@gmail.com', 'DAVIS', 123 ),
--        (2501, 'nicolas','ivanov', 'ivanov@gmail.com', 'DAVIS' , 123),
--        (2502, 'harleen','kaur', 'kaur@gmail.com', 'TRAFALGAR' , 234),
--        (2503, 'walid','belal', 'walid@gmail.com', 'HAZEL_MCCALLION' , 234);
--
-- INSERT IGNORE INTO experties(experty, professor_id)
-- VALUES ('JAVA', '2500'), ('C#', 2501), ('History', 2502), ('Architect', 2503);
--
-- INSERT IGNORE INTO course(course_code,course_name ,course_credit, course_offered_in_semester,course_description, campus, coordinator_id)
-- VALUES (789, 'java',6.0, 3, 'various concept of java technology', 'DAVIS', 2500 ),
--  (456, 'c#',4.0, 4, 'various concept of c# technology', 'DAVIS', 2500 ),
--  (132, 'SQL',3.0, 2, 'various concept of SQL technology', 'TRAFALGAR', 2500 ),
-- (963, 'linux',3.0, 3, 'various concept of linux technology', 'DAVIS' , 2501),
--  (741, 'history',3.0, 1, 'history of canada', 'HAZEL_MCCALLION', 2500 ),
--  (7523, 'drawing',2.0, 2, 'drawing classes', 'HAZEL_MCCALLION' ,2502);
--
--
-- INSERT IGNORE INTO program_course(program_id, course_id)
-- VALUES (85,789 ),
--  (89,132 ),
--  (78,132 ),
--  (78,741 ),
--   (89,963 ),
--    (85,456 );
--
-- INSERT IGNORE INTO professor_course(professor_id, course_id)
-- VALUES (2500,456 ),
--  (2501,132 ),
--  (2500,456 ),
--  (2502,132 ),
--   (2503,456 ),
--    (2502,456 );
--
--
--
-- INSERT IGNORE INTO professor_program(professor_id, program_id)
-- VALUES (2500,85),
--  (2501,89 ),
--  (2502,78 ),
--  (2503,85 );

--
-- insert ignore into student(first_name, department_id, program_id) values ('yash', null, null);
# INSERT IGNORE INTO users(useremail,username, encrypted_Password, enabled) VALUES ('stealth@gmail.com','Stealth Security', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1);
# INSERT IGNORE INTO roles(rolename) VALUES ('ADMIN');
# INSERT IGNORE INTO role_users(users_id, roles_id) VALUES(1, 1);
drop database if exists face_recognition;
