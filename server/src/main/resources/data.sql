INSERT INTO roles (role_name) VALUES
('ADMIN'),
('HUMAN_RESOURCES'),
('MODERATOR'),
('EMPLOYEE');

INSERT INTO departments (department_name) VALUES
('Department of Strategic Planning'),
('Human Resources Division'),
('Finance and Accounting Department'),
('Research and Development Team'),
('Marketing and Communications Bureau'),
('Information Technology Division'),
('Operations and Logistics Unit'),
('Customer Relations Department'),
('Quality Assurance and Compliance Team'),
('Procurement and Supply Chain Division');

INSERT INTO categories (category_name) VALUES
('Physical Product'),
('Graphic Design'),
('Marketing'),
('Social Media'),
('Branding'),
('Web Design'),
('App Design'),
('Advertising'),
('Packaging'),
('Promotional Material'),
('UI/UX'),
('Events'),
('Sales'),
('Merchandise');

INSERT INTO stages (stage_name) VALUES
('Submitted'),
('Voting'),
('Rejected'),
('Approved'),
('NotApproved');

INSERT INTO statuses (status_name) VALUES
('closed'),
('open');

INSERT INTO users (user_name, user_email, user_password, role_id, department_id) VALUES
('Jowita', 'jowita@test.com', '123', 1, 5),
('Alex', 'alex@test.com', '321', 4, 6),
('Tomek', 'tomek@test.com', '555', 3, 3),
('Piotr', 'piotr@test.com', '987', 2, 4),
('test', 'a@a.pl', '000', 1 ,1);

INSERT INTO threads (thread_title, thread_description, thread_justification, thread_photo, thread_points, user_id, category_id, stage_id, status_id) VALUES
('Thread 1', 'Description for Thread 1', 'Justification for Thread 1', 'photo1.jpg', 10, 1, 1, 1, 1),
('Thread 2', 'Description for Thread 2', 'Justification for Thread 2', 'photo2.jpg', 20, 2, 2, 2, 1),
('Thread 3', 'Description for Thread 3', 'Justification for Thread 3', 'photo3.jpg', 15, 3, 1, 1, 2),
('Thread 4', 'Description for Thread 4', 'Justification for Thread 4', 'photo4.jpg', 25, 4, 2, 2, 2),
('Thread 5', 'Description for Thread 5', 'Justification for Thread 5', 'photo5.jpg', 18, 1, 1, 1, 1),
('Thread 6', 'Description for Thread 6', 'Justification for Thread 6', 'photo6.jpg', 12, 2, 2, 2, 1),
('Thread 7', 'Description for Thread 7', 'Justification for Thread 7', 'photo7.jpg', 30, 3, 1, 1, 2),
('Thread 8', 'Description for Thread 8', 'Justification for Thread 8', 'photo8.jpg', 22, 4, 2, 2, 2),
('Thread 9', 'Description for Thread 9', 'Justification for Thread 9', 'photo9.jpg', 17, 1, 1, 1, 1),
('Thread 10', 'Description for Thread 10', 'Justification for Thread 10', 'photo10.jpg', 28, 2, 2, 2, 1);

INSERT INTO admissions (admission_author, admission_text, admission_date) VALUES

(1, 'Admission 1', '2023-07-26'),
(2, 'Admission 2', '2023-07-27'),
(3, 'Admission 3', '2023-07-28'),
(1, 'Admission 4', '2023-07-28'),
(2, 'Admission 5', '2023-07-29'),
(3, 'Admission 6', '2023-07-30'),
(4, 'Admission 7', '2023-07-30'),
(1, 'Admission 8', '2023-07-31'),
(2, 'Admission 9', '2023-07-31'),
(3, 'Admission 10', '2023-08-01');


INSERT INTO conclusions (conclusion_author, conclusion_text, conclusion_date, conclusion_points) VALUES
(1, 'Conclusion 1', '2023-07-26', 5),
(2, 'Conclusion 2', '2023-07-27', 8),
(3, 'Conclusion 3', '2023-07-28', 6),
(1, 'Conclusion 4', '2023-07-28', 9),
(2, 'Conclusion 5', '2023-07-29', 7),
(3, 'Conclusion 6', '2023-07-30', 6),
(4, 'Conclusion 7', '2023-07-30', 8),
(1, 'Conclusion 8', '2023-07-31', 7),
(2, 'Conclusion 9', '2023-07-31', 9),
(3, 'Conclusion 10', '2023-08-01', 6);

INSERT INTO comments (comment_date, comment_text, thread_id) VALUES
('2023-07-26', 'Comment 1 for Thread 1', 1),
('2023-07-27', 'Comment 2 for Thread 1', 1),
('2023-07-28', 'Comment 1 for Thread 2', 1),
('2023-07-28', 'Comment 1 for Thread 3', 2),
('2023-07-29', 'Comment 1 for Thread 4', 2),
('2023-07-30', 'Comment 2 for Thread 4', 3),
('2023-07-30', 'Comment 1 for Thread 5', 4),
('2023-07-31', 'Comment 1 for Thread 6', 5),
('2023-07-31', 'Comment 1 for Thread 7', 6),
('2023-08-01', 'Comment 1 for Thread 8', 7);

-- UPDATE threads
-- SET admission_id = (SELECT admission_id FROM admissions WHERE thread_id = threads.thread_id),
--     conclusion_id = (SELECT conclusion_id FROM conclusions WHERE thread_id = threads.thread_id);

