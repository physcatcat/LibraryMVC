create table Person(
    id int generated by default as identity primary key,
    full_name varchar(80) not null,
    phone_number varchar(11) not null unique,
    date_of_birth date not null,
    email varchar(80) not null unique
);

create table Book(
    id int generated by default as identity primary key,
    title varchar(150) not null,
    author varchar(80)  not null,
    year int not null check (year >1000),
    person_id int references person(id) on delete set null
);