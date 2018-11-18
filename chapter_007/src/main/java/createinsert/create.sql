create database job4j;

create table role(
	id serial primary key,
	name varchar(100)
);

create table rule(
	id serial primary key,
	name varchar(100)
);

create table role_rule(
	id serial primary key,
	role_id int references role(id),
	rule_id int references rule(id)
);
create table _user(
	id serial primary key,
	name varchar(100),
	login varchar(100),
	password varchar(100),
	role_id int references role(id)
);

create table category(
	id serial primary key,
	name varchar(100)
);

create table _state(
	id serial primary key,
	name varchar(100)
);

create table item(
	id serial primary key,
	name varchar(100),
	_date timestamp default now(),
	foreign key(id) references _user(id),
	category_id int references category(id),
	state_id int references _state(id)
);

create table comments(
	id serial primary key,
	_comment text,
	item_id int references item(id)
);

create table attaches(
	id serial primary key,
	name varchar(100),
	item_id int references item(id)
);

insert into role(name) values('Role 1');
insert into rule(name) values('Rule 1');
insert into role_rule(role_id, rule_id) values(1, 1);

insert into _user(name, login, password, role_id) values(
	'username',
	'login',
	'password',
	1
);

insert into category(name) values('Category 1');
insert into _state(name) values('State 1');

insert into item(name, category_id, state_id) values(
	'item 1',
	1,
	1
);

insert into comments(_comment, item_id) values('Some text', 1);
insert into attaches(name, item_id) values('attach1', 1);












