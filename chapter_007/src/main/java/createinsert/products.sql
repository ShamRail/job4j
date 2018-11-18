create table _type(
	id serial primary key,
	name varchar(100)
);

create table products(
	id serial primary key,
	name varchar(100),
	type_id int references _type(id),
	expired_date timestamp not null,
	price real
);

insert into _type(name) values('cheese');
insert into _type(name) values('ice cream');
insert into _type(name) values('milk');

insert into products(name, type_id, expired_date, price) values(
	'melted cheese',
	1,
	'2019-02-18 01:00:00',
	52.5
); 

insert into products(name, type_id, expired_date, price) values(
	'parmesan cheese',
	1,
	'2019-01-15 01:00:00',
	121.0
);

insert into products(name, type_id, expired_date, price) values(
	'dutch cheese',
	1,
	'2018-12-31 01:00:00',
	113.3
);


insert into products(name, type_id, expired_date, price) values(
	'cream',
	2,
	'2018-12-31 01:00:00',
	41
);

insert into products(name, type_id, expired_date, price) values(
	'pistachio ice cream',
	2,
	'2019-01-31 01:00:00',
	55
);

insert into products(name, type_id, expired_date, price) values(
	'chocolate ice-cream',
	2,
	'2019-02-28 01:00:00',
	73
);


insert into products(name, type_id, expired_date, price) values(
	'baked milk',
	3,
	'2018-11-21 01:00:00',
	56
);

insert into products(name, type_id, expired_date, price) values(
	'milk 2.5%',
	3,
	'2019-02-20 01:00:00',
	33
);

insert into products(name, type_id, expired_date, price) values(
	'milk 3.2%',
	3,
	'2019-02-22 01:00:00',
	47
);

--1.запрос получение всех продуктов с типом "СЫР"
select * from products as p inner join _type as t on p.type_id = t.id and t.name = 'cheese';

--2.запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select * from products where name like '%ice cream%';

--3.запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select * from products where extract(month from expired_date) = extract(month from now()) + 1;

--4.запрос, который выводит самый дорогой продукт
select * from products where price = (select max(price) from products);

--запрос, который выводит количество всех продуктов определенного типа.
select type_id, count(id) from products group by type_id;

--6..запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from products as p inner join _type as t on p.type_id = t.id and (t.name = 'milk' or t.name = 'cheese');

--7.запрос, который выводит тип продуктов, которых осталось меньше 10 штук
select name from _type where (select count(p.id) from products as p where p.type_id = _type.id) < 10;

--8.вывести все продукты и их тип
select * from products as p inner join _type as t on p.type_id = t.id


