INSERT INTO USER(name, email, pass) VALUES('admin', 'admin@email.com', '$2a$10$Z4tw9BwTra3kHso.1KZdiuLd5Skuarw1DByQl8VzImWmcXgq2SDeW');

INSERT INTO PROJECT(name, description) VALUES('projectA', 'Back-end project');
INSERT INTO PROJECT(name, description) VALUES('projectB', 'Front-end project');

INSERT INTO TASK(summary, description, date_create, status,priority, author_id, project_id) VALUES('task 1', 'create project', '2019-09-03 09:00:00', 'PENDING','MAJOR', 1, 1);
INSERT INTO TASK(summary, description, date_create, status,priority, author_id, project_id) VALUES('task 2', 'create core structure', '2019-09-03 10:00:00', 'PENDING','MAJOR', 1, 1);
INSERT INTO TASK(summary, description, date_create, status,priority, author_id, project_id) VALUES('task 3', 'Tag HTML', '2019-09-03 11:00:00', 'PENDING','MAJOR', 1, 2);