INSERT INTO roles (role_id, role_name)
VALUES (1, 'Admin'),
       (2, 'HumanResources'),
       (3, 'Moderator'),
       (4, 'Employee');

INSERT INTO departments (department_id, department_name)
VALUES (1, 'Department of Strategic Planning'),
       (2, 'Human Resources Division'),
       (3, 'Finance and Accounting Department'),
       (4, 'Research and Development Team'),
       (5, 'Marketing and Communications Bureau'),
       (6, 'Information Technology Division'),
       (7, 'Operations and Logistics Unit'),
       (8, 'Customer Relations Department'),
       (9, 'Quality Assurance and Compliance Team'),
       (10, 'Procurement and Supply Chain Division');

INSERT INTO categories (category_id, category_name)
VALUES (1, 'Physical Product'),
       (2, 'Graphic Design'),
       (3, 'Marketing'),
       (4, 'Social Media'),
       (5, 'Branding'),
       (6, 'Web Design'),
       (7, 'App Design'),
       (8, 'Advertising'),
       (9, 'Packaging'),
       (10, 'Promotional Material'),
       (11, 'UI/UX'),
       (12, 'Events'),
       (13, 'Sales'),
       (14, 'Merchandise');

INSERT INTO stages (stage_id, stage_name)
VALUES (1, 'Submitted'),
       (2, 'Voting'),
       (3, 'Rejected'),
       (4, 'Approved'),
       (5, 'NotApproved');

INSERT INTO statuses (status_id, status_name)
VALUES (1, 'active'),
       (2, 'closed');


INSERT INTO users (user_name, user_email, user_password, role_id, department_id)
VALUES ('Jowita', 'jowita@test.com', '123', 1, 5),
       ('Alex', 'alex@test.com', '321', 4, 6),
       ('Tomek', 'tomek@test.com', '555', 3, 3),
       ('Piotr', 'piotr@test.com', '987', 2, 4),
       ('test', 'a@a.pl', '000', 1, 1),
       ('admin', 'admin@admin.pl', '$2a$10$0fN5YqsNup0xeB.u6Q4QU.neP4XZPp8zNcwvU6AVxlxn2MGTNSLf.', 1, 1);


INSERT INTO threads (thread_date, thread_title, thread_description, thread_justification, thread_photo, thread_points,
                     user_id,
                     category_id, stage_id, status_id)
VALUES ('2023-05-10', 'Monday Thread 1', 'Description for Thread 1', 'Justification for Thread 1', 'photo1.jpg', 10, 1,
        1, 1, 1),
       ('2023-05-10', 'Monday Thread 2', 'Description for Thread 2', 'Justification for Thread 2', 'photo2.jpg', 20, 2,
        2, 2, 1),
       ('2023-05-10', 'Tuesday Thread 3', 'Description for Thread 3', 'Justification for Thread 3', 'photo3.jpg', 15, 3,
        1, 1, 2),
       ('2023-05-10', 'Tuesday Thread 4', 'Description for Thread 4', 'Justification for Thread 4', 'photo4.jpg', 25, 4,
        2, 2, 2),
       ('2023-05-10', 'Tuesday Thread 5', 'Description for Thread 5', 'Justification for Thread 5', 'photo5.jpg', 18, 1,
        1, 1, 1),
       ('2023-05-10', 'Wednesday Thread 6', 'Description for Thread 6', 'Justification for Thread 6', 'photo6.jpg', 12,
        2, 2, 2, 1),
       ('2023-05-10', 'Wednesday Thread 7', 'Description for Thread 7', 'Justification for Thread 7', 'photo7.jpg', 30,
        3, 1, 1, 2),
       ('2023-05-10', 'Wednesday Thread 8', 'Description for Thread 8', 'Justification for Thread 8', 'photo8.jpg', 22,
        4, 2, 2, 2),
       ('2023-05-10', 'Wednesday Thread 9', 'Description for Thread 9', 'Justification for Thread 9', 'photo9.jpg', 17,
        1, 1, 1, 1),
       ('2023-05-10', 'Wednesday Thread 10', 'Description for Thread 10', 'Justification for Thread 10', 'photo10.jpg',
        28, 2, 2, 2, 1);

INSERT INTO admissions (admission_id, admission_author, admission_text, admission_date)
VALUES (1, 1, 'Admission 1', '2023-07-26'),
       (2, 2, 'Admission 2', '2023-07-27'),
       (3, 3, 'Admission 3', '2023-07-28'),
       (4, 1, 'Admission 4', '2023-07-28'),
       (5, 2, 'Admission 5', '2023-07-29'),
       (6, 3, 'Admission 6', '2023-07-30'),
       (7, 4, 'Admission 7', '2023-07-30'),
       (8, 1, 'Admission 8', '2023-07-31'),
       (9, 2, 'Admission 9', '2023-07-31'),
       (10, 3, 'Admission 10', '2023-08-01');


INSERT INTO conclusions (conclusion_id, conclusion_author, conclusion_text, conclusion_date)
VALUES (1, 1, 'Conclusion 1', '2023-07-26'),
       (2, 2, 'Conclusion 2', '2023-07-27'),
       (3, 3, 'Conclusion 3', '2023-07-28'),
       (4, 1, 'Conclusion 4', '2023-07-28'),
       (5, 2, 'Conclusion 5', '2023-07-29'),
       (6, 3, 'Conclusion 6', '2023-07-30'),
       (7, 4, 'Conclusion 7', '2023-07-30'),
       (8, 1, 'Conclusion 8', '2023-07-31'),
       (9, 2, 'Conclusion 9', '2023-07-31'),
       (10, 3, 'Conclusion 10', '2023-08-01');

INSERT INTO comments (author_id, comment_date, comment_text, thread_id)
VALUES (1, '2023-07-26', 'Comment 1 for Thread 1', 1),
       (2, '2023-07-27', 'Comment 2 for Thread 1', 1),
       (3, '2023-07-28', 'Comment 1 for Thread 2', 1),
       (1, '2023-07-28', 'Comment 1 for Thread 3', 2),
       (2, '2023-07-29', 'Comment 1 for Thread 4', 2),
       (3, '2023-07-30', 'Comment 2 for Thread 4', 3),
       (4, '2023-07-30', 'Comment 1 for Thread 5', 4),
       (1, '2023-07-31', 'Comment 1 for Thread 6', 5),
       (2, '2023-07-31', 'Comment 1 for Thread 7', 6),
       (3, '2023-08-01', 'Comment 1 for Thread 8', 7);

-- UPDATE threads
-- SET admission_id = (SELECT admission_id FROM admissions WHERE thread_id = threads.thread_id),
--     conclusion_id = (SELECT conclusion_id FROM conclusions WHERE thread_id = threads.thread_id);

