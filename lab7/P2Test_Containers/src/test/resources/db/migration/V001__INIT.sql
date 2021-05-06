CREATE TABLE tqs_employee (
    id BIGSERIAL PRIMARY KEY,
    name varchar(255) not null,
    email varchar(255) not null
);

insert into tqs_employee(name, email) values ('Pedro', 'pedro@something.com');