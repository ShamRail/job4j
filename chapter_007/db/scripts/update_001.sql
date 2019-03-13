create table if not exists item (
  id int primary key,
  name varchar(255),
  description varchar(255),
  create_date bigint
);

create table if not exists comments (
  id serial primary key,
  content text,
  item_id int references item(id)
);