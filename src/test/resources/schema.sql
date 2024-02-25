-- Create company table
CREATE TABLE IF NOT EXISTS company (company_id BIGINT NOT NULL AUTO_INCREMENT,name VARCHAR(255),
    created DATETIME,
    updated DATETIME,
    PRIMARY KEY (company_id)
    );

-- Create address table
CREATE TABLE IF NOT EXISTS address (address_id BIGINT NOT NULL AUTO_INCREMENT, city VARCHAR(255),street VARCHAR(255),
    postal_code VARCHAR(20),
    created DATETIME,
    updated DATETIME,
    PRIMARY KEY (address_id)
    );

-- Create employee table
CREATE TABLE IF NOT EXISTS employee (employee_id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(255),
    salary FLOAT,
    date_of_birth DATE,
    address_id BIGINT,
    joining_date DATE,
    is_active BOOLEAN,
    gender VARCHAR(10),
    contract_type VARCHAR(20),
    created DATETIME,
    updated DATETIME,
    PRIMARY KEY (employee_id),
    FOREIGN KEY (department_id) REFERENCES department(department_id),
    FOREIGN KEY (address_id) REFERENCES address(address_id)
    );

-- Create department table
CREATE TABLE IF NOT EXISTS department (department_id BIGINT NOT NULL AUTO_INCREMENT,name VARCHAR(255),
    employee_id BIGINT,
    company_id BIGINT,
    created DATETIME,
    updated DATETIME,
    PRIMARY KEY (department_id),
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
    FOREIGN KEY (company_id) REFERENCES company(company_id)
    );

-- Create project table
CREATE TABLE IF NOT EXISTS project (project_id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(255),
    created DATETIME,
    updated DATETIME,
    PRIMARY KEY (project_id)
    );

-- Create employee_project table
CREATE TABLE IF NOT EXISTS employee_project (project_id BIGINT NOT NULL, employee_id BIGINT NOT NULL, PRIMARY KEY (project_id, employee_id),
    FOREIGN KEY (project_id) REFERENCES project(project_id),
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
    );

-- Create meeting table
CREATE TABLE IF NOT EXISTS meeting (id BIGINT NOT NULL AUTO_INCREMENT, title VARCHAR(255),
    start_time DATETIME,
    created DATETIME,
    updated DATETIME,
    PRIMARY KEY (id)
    );

-- Create work_calendar table
CREATE TABLE IF NOT EXISTS  work_calendar (id_WorkCalendar BIGINT NOT NULL AUTO_INCREMENT, employee_id BIGINT, tag VARCHAR(255),
    created DATETIME,
    updated DATETIME,
    PRIMARY KEY (id_WorkCalendar),
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
    );

-- Create report table
CREATE TABLE IF NOT EXISTS `report`(`id` int not null AUTO_INCREMENT,`title` varchar(200) default null,`content` varchar(200) default null,
    `created` date DEFAULT NULL,
    `updated` date DEFAULT current_timestamp(),  PRIMARY KEY (`id`));

-- Create task table
CREATE TABLE IF NOT EXISTS task (id_Task BIGINT NOT NULL AUTO_INCREMENT,name VARCHAR(255),
    description TEXT,
    employee_id BIGINT,
    project_id BIGINT,
    priority VARCHAR(50),
    taskStatus VARCHAR(50),
    created DATETIME,
    updated DATETIME,
    PRIMARY KEY (id_Task),
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
    FOREIGN KEY (project_id) REFERENCES project(project_id)
    );







