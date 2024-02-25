-- Insert initial data into Company table
INSERT INTO company (name, created, updated)
VALUES
    ('Company', '2021-03-14','2024-09-25'),
    ('Company1', '2021-03-14','2024-09-25');

-- Insert initial data into Address table
INSERT INTO address ( city,street, postal_code, created, updated)
VALUES
    ('city', 'street', '12345', '2021-03-14','2024-09-25'),
    ('city1', 'street', '54321','2021-03-14','2024-09-25');

-- Insert initial data into Employee table
INSERT INTO employee (name, salary, date_of_birth,  address_id, joining_date, is_active, gender, contract_type, created, updated)
VALUES
    ('Oumaima', 50000, '1990-05-15', 1, '2023-01-01', 1, 'MALE', ' CDI', '2021-03-14','2024-09-25'),
    ('Mayssa', 60000, '1988-10-20', 2, '2022-06-15', 1, 'FEMALE', ' CDI', '2021-03-14','2024-09-25');

-- Insert initial data into Department table
INSERT INTO department (name, company_id, created, updated)
VALUES
    ('Engineering', 1, '2021-03-14','2024-09-25'),
    ('Human Resources', 2, '2021-03-14','2024-09-25');

-- Insert initial data into Project table
INSERT INTO project (name, created, updated)
VALUES
    ('Project A', '2021-03-14','2024-09-25'),
    ('Project B', '2021-03-14','2024-09-25'),
    ('Project C', '2021-03-14','2024-09-25');

-- Insert initial data into Employee_project table
INSERT INTO employee_project (project_id, employee_id)
VALUES
    (1, 1),
    (1, 2);

-- Insert initial data into Employee_project table
INSERT INTO meeting (title, start_time, created, updated)
VALUES
    ('Team Meeting', '2024-02-22 10:00:00', '2021-03-14','2024-09-25'),
    ('Project Discussion', '2024-02-23 14:30:00', '2021-03-14','2024-09-25');

-- Insert initial data into Work_calendar table
INSERT INTO work_calendar (employee_id,tag, created, updated)
VALUES
    (1,  'Meeting','2021-03-14','2024-09-25'),
    (2,  'Training', '2021-03-14','2024-09-25');
-- Insert initial data into Report table
INSERT INTO report (title,content,created,updated)
VALUES
    ('test','test','2021-03-14','2024-09-25'),
    ('report','test','2021-03-14','2024-09-25');

-- Insert initial data into Task table
INSERT INTO task (name, description, priority,  created, updated)
VALUES
    ('Task 1', 'Description for Task 1', 'HIGH', '2021-03-14','2024-09-25'),
    ('Task 2', 'Description for Task 2',  'HIGH', '2021-03-14','2024-09-25'),
    ('Task 3', 'Description for Task 3', 'HIGH','2021-03-14','2024-09-25');