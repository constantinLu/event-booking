create connection event_booking;

use event_booking;

create table user(
id BIGINT PRIMARY KEY auto_increment,
first_name varchar(100),
last_name varchar(100),
email varchar(500),
username varchar(100),
password varchar(100),
role varchar (100)
);

create table user_event(
id bigint primary key auto_increment,
user_id BIGINT,
event_id BIGINT
);

create table event(
id bigint primary key auto_increment,
title varchar(100),
description varchar(2000),
is_online char,
location varchar(100),
start_date datetime,
end_date datetime,
constraints varchar(2000),
is_booking_allowed char,
organiser_id bigint
);

create table booking(
id bigint primary key auto_increment,
booking_date datetime,
cancel_date datetime,
user_id bigint,
event_id bigint
);



