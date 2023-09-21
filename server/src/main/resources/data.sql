INSERT INTO roles (role_id, role_name)
VALUES (1, 'Admin'),
       (2, 'HumanResources'),
       (3, 'Moderator'),
       (4, 'Employee');

INSERT INTO departments (department_id, department_name)
VALUES (1, 'Department of Strategic'),
       (2, 'Human Resources Division'),
       (3, 'Finance and Accounting'),
       (4, 'Research and Development'),
       (5, 'Marketing and Communications'),
       (6, 'Information Technology'),
       (7, 'Operations and Logistics'),
       (8, 'Customer Relations'),
       (9, 'Quality Assurance and Compliance'),
       (10, 'Procurement and Supply Chain');

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
('Admin', 'admin@admin.pl', '$2a$10$DTdSPv25HDVaQEwSnOVsm.9mg1v6y0142jArgVgfziG2gjbU/WD1i', 1, 6);


INSERT INTO threads (thread_date, thread_title, thread_description, thread_justification, thread_photo, thread_points, user_id,
                     category_id, stage_id, status_id)
VALUES ('2023-09-15','Employee Wellness Program', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Morbi ultrices ornare nisi, eget dapibus ante facilisis at', 'photo1.jpg', 5, 1, 1, 1, 1),
       ('2023-09-09','Remote Work Optimization', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Etiam luctus sodales neque eget volutpat. Maecenas posuere consectetur orci', 'photo2.jpg', 20, 2, 2, 1, 1),
       ('2023-09-20','Green Initiative', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Etiam luctus sodales neque eget volutpat. Maecenas posuere consectetur orci', 'photo3.jpg', 15, 3, 1, 1, 2),
       ('2023-09-11','Customer Loyalty Program', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Etiam luctus sodales neque eget volutpat. Maecenas posuere consectetur orci', 'photo4.jpg', 25, 4, 2, 1, 2),
       ('2023-09-01','Cross-Training', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Etiam luctus sodales neque eget volutpat. Maecenas posuere consectetur orci', 'photo5.jpg', 18, 1, 1, 1, 1),
       ('2023-09-10','Diversity and Inclusion Training', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Etiam luctus sodales neque eget volutpat. Maecenas posuere consectetur orci', 'photo6.jpg', 12, 2, 2, 2, 1),
       ('2023-09-05','Digital Marketing Upgrade', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Etiam luctus sodales neque eget volutpat. Maecenas posuere consectetur orci', 'photo7.jpg', 30, 3, 1, 2, 2),
       ('2023-09-17','Cost-Cutting Analysis', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Etiam luctus sodales neque eget volutpat. Maecenas posuere consectetur orci', 'photo8.jpg', 22, 4, 2, 2, 2),
       ('2023-08-30','Innovation Challenge', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Etiam luctus sodales neque eget volutpat. Maecenas posuere consectetur orci', 'photo9.jpg', 17, 1, 1, 2, 1),
       ('2023-08-25','Supply Chain Optimization', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Etiam luctus sodales neque eget volutpat. Maecenas posuere consectetur orci', 'photo10.jpg', 28, 2, 2, 2, 1),
       ('2023-09-09','Customer Feedback Loop', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Etiam luctus sodales neque eget volutpat. Maecenas posuere consectetur orci', 'photo2.jpg', 20, 2, 2, 2, 1),
       ('2023-09-20','Knowledge Sharing Platform', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Etiam luctus sodales neque eget volutpat. Maecenas posuere consectetur orci', 'photo3.jpg', 15, 3, 1, 2, 2),
       ('2023-09-11','Cybersecurity Enhancements', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Etiam luctus sodales neque eget volutpat. Maecenas posuere consectetur orci', 'photo4.jpg', 25, 4, 2, 2, 2),
       ('2023-09-01','Flexible Work Arrangements', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Etiam luctus sodales neque eget volutpat. Maecenas posuere consectetur orci', 'photo5.jpg', 18, 1, 1, 2, 1),
       ('2023-09-10','Corporate Social Responsibility', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Etiam luctus sodales neque eget volutpat. Maecenas posuere consectetur orci', 'photo6.jpg', 12, 2, 2, 2, 1),
       ('2023-09-05','Streamlined Onboarding', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Etiam luctus sodales neque eget volutpat. Maecenas posuere consectetur orci', 'photo7.jpg', 30, 3, 1, 2, 2),
       ('2023-09-17','Market Expansion', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Etiam luctus sodales neque eget volutpat. Maecenas posuere consectetur orci', 'photo8.jpg', 22, 4, 2, 2, 2),
       ('2023-08-30','Collaborative Tools', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Etiam luctus sodales neque eget volutpat. Maecenas posuere consectetur orci', 'photo9.jpg', 17, 1, 1, 2, 1),
       ('2023-08-25','Skill Development Initiatives', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Etiam luctus sodales neque eget volutpat. Maecenas posuere consectetur orci', 'photo10.jpg', 28, 2, 2, 2, 1);

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
VALUES (2, 1, 'Aliquam tempor ligula neque, eget bibendum ligula dapibus et. Aenean vulputate mi vehicula est finibus tempor. Sed hendrerit auctor ligula id blandit. Praesent venenatis quis dui nec fermentum. Praesent aliquam enim in purus porta vehicula. ', '2023-07-26'),
       (3, 2, 'Aliquam tempor ligula neque, eget bibendum ligula dapibus et. Aenean vulputate mi vehicula est finibus tempor. Sed hendrerit auctor ligula id blandit. Praesent venenatis quis dui nec fermentum. Praesent aliquam enim in purus porta vehicula. ', '2023-07-27'),
       (4, 3, 'Aliquam tempor ligula neque, eget bibendum ligula dapibus et. Aenean vulputate mi vehicula est finibus tempor. Sed hendrerit auctor ligula id blandit. Praesent venenatis quis dui nec fermentum. Praesent aliquam enim in purus porta vehicula. ', '2023-07-28'),
       (5, 1, 'Aliquam tempor ligula neque, eget bibendum ligula dapibus et. Aenean vulputate mi vehicula est finibus tempor. Sed hendrerit auctor ligula id blandit. Praesent venenatis quis dui nec fermentum. Praesent aliquam enim in purus porta vehicula. ', '2023-07-28'),
       (6, 2, 'Aliquam tempor ligula neque, eget bibendum ligula dapibus et. Aenean vulputate mi vehicula est finibus tempor. Sed hendrerit auctor ligula id blandit. Praesent venenatis quis dui nec fermentum. Praesent aliquam enim in purus porta vehicula. ', '2023-07-29'),
       (7, 3, 'Aliquam tempor ligula neque, eget bibendum ligula dapibus et. Aenean vulputate mi vehicula est finibus tempor. Sed hendrerit auctor ligula id blandit. Praesent venenatis quis dui nec fermentum. Praesent aliquam enim in purus porta vehicula. ', '2023-07-30'),
       (8, 4, 'Aliquam tempor ligula neque, eget bibendum ligula dapibus et. Aenean vulputate mi vehicula est finibus tempor. Sed hendrerit auctor ligula id blandit. Praesent venenatis quis dui nec fermentum. Praesent aliquam enim in purus porta vehicula. ', '2023-07-30'),
       (9, 1, 'Aliquam tempor ligula neque, eget bibendum ligula dapibus et. Aenean vulputate mi vehicula est finibus tempor. Sed hendrerit auctor ligula id blandit. Praesent venenatis quis dui nec fermentum. Praesent aliquam enim in purus porta vehicula. ', '2023-07-31'),
       (10, 2, 'Aliquam tempor ligula neque, eget bibendum ligula dapibus et. Aenean vulputate mi vehicula est finibus tempor. Sed hendrerit auctor ligula id blandit. Praesent venenatis quis dui nec fermentum. Praesent aliquam enim in purus porta vehicula. ', '2023-07-31'),
       (11, 3, 'Aliquam tempor ligula neque, eget bibendum ligula dapibus et. Aenean vulputate mi vehicula est finibus tempor. Sed hendrerit auctor ligula id blandit. Praesent venenatis quis dui nec fermentum. Praesent aliquam enim in purus porta vehicula. ', '2023-08-01');

INSERT INTO comments (author_id, comment_date, comment_text, thread_id)
VALUES (1, '2023-07-26', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque tristique risus eu ipsum lobortis posuere.', 1),
       (2, '2023-07-27', 'In congue nibh et enim condimentum, vel gravida turpis posuere. Morbi efficitur ligula sapien. Suspendisse fringilla feugiat ante, ac finibus sem molestie in. ', 1),
       (3, '2023-07-28', 'Suspendisse congue semper ullamcorper. Fusce imperdiet ac dui id feugiat. Sed placerat efficitur mauris non iaculis. Donec pellentesque et tortor a pellentesque.', 1),
       (1, '2023-07-28', 'Pellentesque efficitur nulla neque, eget consectetur urna aliquam scelerisque. Cras vel diam eu felis blandit egestas. Mauris mauris nisi, vestibulum eget suscipit scelerisque, congue vitae ligula.', 1),
       (2, '2023-07-29', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque tristique risus eu ipsum lobortis posuere. In congue nibh et enim condimentum, vel gravida turpis posuere. Morbi efficitur ligula sapien', 2),
       (3, '2023-07-30', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque tristique risus eu ipsum lobortis posuere. In congue nibh et enim condimentum, vel gravida turpis posuere. Morbi efficitur ligula sapien', 3),
       (4, '2023-07-30', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque tristique risus eu ipsum lobortis posuere. In congue nibh et enim condimentum, vel gravida turpis posuere. Morbi efficitur ligula sapien', 4),
       (1, '2023-07-31', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque tristique risus eu ipsum lobortis posuere. In congue nibh et enim condimentum, vel gravida turpis posuere. Morbi efficitur ligula sapien', 5),
       (2, '2023-07-31', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque tristique risus eu ipsum lobortis posuere. In congue nibh et enim condimentum, vel gravida turpis posuere. Morbi efficitur ligula sapien', 6),
       (3, '2023-08-01', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque tristique risus eu ipsum lobortis posuere. In congue nibh et enim condimentum, vel gravida turpis posuere. Morbi efficitur ligula sapien', 7);

INSERT INTO votes (vote_type, thread_id, user_id)
VALUES ('LIKE', 1, 1),
       ('LIKE', 1, 2),
       ('LIKE', 1, 3),
       ('LIKE', 1, 4),
       ('LIKE', 1, 5);

-- UPDATE threads
-- SET admission_id = (SELECT admission_id FROM admissions WHERE thread_id = threads.thread_id),
--     conclusion_id = (SELECT conclusion_id FROM conclusions WHERE thread_id = threads.thread_id);

