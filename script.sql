create table article(
id serial primary key,
name varchar(80) NOT NULL,
description text,
size char(1),
price double,
gender varchar(10),
category varchar(80),
availabilty boolean default false,
stock int
);

create table customers(
id serial primary key,
name varchar(200) NOT NULL,
address varchar (100),
gender char(1),
phone_number int,
email varchar(20),
CIN varchar(50)
);

create table promotion(
id serial primary key,
promotion_type text,
promotion_end_date date,
promotion_start_date date,
promotion_value double
);

create table settlement(
id serial primary key,
payment float,
payment_methode varchar(50),
payment_date date
);

create table pannier(
id serial primary key,
quantity int,
id_article int REFERENCES article(id),
id_customers int REFERENCES customers(id)
);

