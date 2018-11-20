select c.name, cb.name, t.name, e.name from car as c
	left join car_body as cb on c.car_body_id = cb.id
	left join transmission as t on c.transmission_id = t.id
	left join engine as e on c.engine_id = e.id;

select cb.name from car_body as cb left join car as c on cb.id = c.car_body_id where c.id is null;
select t.name from transmission as t left join car as c on t.id = c.car_body_id where c.id is null;
select e.name from engine as e left join car as c on e.id = c.car_body_id where c.id is null;

