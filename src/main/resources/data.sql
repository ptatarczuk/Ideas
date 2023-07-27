INSERT INTO users (user_id, user_name, user_email, user_password, role_id, department_id) VALUES
(1, 'Jowita', 'jowita@test.com', '123', 1, 5),
(2, 'Alex', 'alex@test.com', '321', 4, 6),
(3, 'Tomek', 'tomek@test.com', '555', 3, 3),
(4, 'Piotr', 'piotr@test.com', '987', 2, 4);

INSERT INTO threads (thread_id, justification, description, photo, points, title, admission_id, category_id, stage_id, user_id) VALUES

(1,'Poprawa motywacji pracowników', 'Owoce w każdym dziale', './sadkfas/sdfa/aa.jpg', 10, 'Owocowy poniedziałek', 1, 12, 1, 1),
(2,'Zwiększenie atrakcyjności firmy', 'Proponuję nowy profil na Facebooku', './sadkfas/sdfa/aa.jpg', 10, 'Nowy profil na Facebooku', 1, 10, 1, 1),
(3,'Klienci wolą treści w internecie', 'Większość klientów nie korzysta z gazetek reklamowych', './sadkfas/sdfa/aa.jpg', 10, 'Rezygnacja z gazetek reklamowych', 1, 10, 1, 1);


INSERT INTO comments (comment_id, author_id, comment_date, comment_text, thread_id) VALUES
(1, 1, '2023-07-26', 'To jest pierwszy komentarz.', 1),
(2, 2, '2023-07-26', 'Świetna idea, zgadzam się!', 1),
(3, 3, '2023-07-27', 'Czekam na więcej informacji.', 2),
(4, 4, '2023-07-28', 'Dobrze, że zwróciliście uwagę na ten problem.', 2),
(5, 1, '2023-07-28', 'Co myślicie o wprowadzeniu tej funkcji?', 3),
(6, 3, '2023-07-29', 'Mam pytanie dotyczące tego tematu.', 3),
(7, 2, '2023-07-29', 'Brawo dla autora za kreatywność!', 4),
(8, 4, '2023-07-30', 'Niestety, nie zgadzam się z tym podejściem.', 4),
(9, 3, '2023-07-30', 'Cieszę się, że jesteście zainteresowani moim projektem.', 5),
(10, 1, '2023-07-30', 'Dajcie mi znać, jeśli macie jakieś sugestie.', 5);

INSERT INTO admissions (admission_id, admission_author, admission_text, admission_date) VALUES
(1, 'Adam', 'To jest pierwsze przyznanie.', '2023-07-26'),
(2, 'Ewa', 'To jest drugie przyznanie.', '2023-07-27'),
(3, 'Piotr', 'To jest trzecie przyznanie.', '2023-07-28'),
(4, 'Anna', 'To jest czwarte przyznanie.', '2023-07-29'),
(5, 'Jan', 'To jest piąte przyznanie.', '2023-07-30');

INSERT INTO conclusions (conclusion_id, conclusion_author, conclusion_text, conclusion_date, conclusion_points) VALUES
(1, 'Marta', 'To jest pierwsze podsumowanie.', '2023-07-26', 7),
(2, 'Kamil', 'To jest drugie podsumowanie.', '2023-07-27', 5),
(3, 'Aleksandra', 'To jest trzecie podsumowanie.', '2023-07-28', 9),
(4, 'Bartosz', 'To jest czwarte podsumowanie.', '2023-07-29', 8),
(5, 'Natalia', 'To jest piąte podsumowanie.', '2023-07-30', 6);

INSERT INTO roles (role_id, role_name) VALUES
(1, 'Admin'),
(2, 'HumanResources'),
(3, 'Moderator'),
(4, 'Employee');

INSERT INTO departments (department_id, department_name) VALUES
(1, 'Department of Strategic Planning'),
(2, 'Human Resources Division'),
(3, 'Finance and Accounting Department'),
(4, 'Research and Development Team'),
(5, 'Marketing and Communications Bureau'),
(6, 'Information Technology Division'),
(7, 'Operations and Logistics Unit'),
(8, 'Customer Relations Department'),
(9, 'Quality Assurance and Compliance Team'),
(10, 'Procurement and Supply Chain Division');

INSERT INTO categories (category_id, category_name) VALUES
(1, 'Physical Product'),
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

INSERT INTO stages (stage_id, stage_name) VALUES
(1, 'Submitted'),
(2, 'Voting'),
(3, 'Rejected'),
(4, 'Approved'),
(5, 'NotApproved');

INSERT INTO statuses (status_id, status_name) VALUES
(1, 'closed'),
(2, 'open');


