create table car_body(
	id serial primary key,
	name varchar(100)
);

create table transmission(
	id serial primary key,
	name varchar(100)
);

create table engine(
	id serial primary key,
	name varchar(100)
);

create table car(
	id serial primary key,
	name varchar(100),
	car_body_id int references car_body(id),
	transmission_id int references transmission(id),
	engine_id int references engine(id)
);

insert into car_body(name) values('car_body 1');
insert into car_body(name) values('car_body 2');
insert into car_body(name) values('car_body 3');
insert into car_body(name) values('car_body 4');
insert into car_body(name) values('car_body 5');

insert into transmission(name) values('transmission 1');
insert into transmission(name) values('transmission 2');
insert into transmission(name) values('transmission 3');

insert into engine(name) values('engine 1');
insert into engine(name) values('engine 2');
insert into engine(name) values('engine 3'); 

insert into car(name, car_body_id, transmission_id, engine_id) values('car 1', 1, 1, 1);
insert into car(name, car_body_id, transmission_id, engine_id) values('car 2', 2, 2, 2);
insert into car(name, car_body_id, transmission_id, engine_id) values('car 3', 3, 3, 3);