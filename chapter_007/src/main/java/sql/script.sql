CREATE TABLE IF NOT EXISTS company (
	id serial PRIMARY KEY,
	name VARCHAR(100)
);


CREATE TABLE IF NOT EXISTS person (
	id serial PRIMARY KEY,
	name VARCHAR(100),	
	company_id INTEGER REFERENCES company(id)
);

INSERT INTO company(name) VALUES('IBM');
INSERT INTO company(name) VALUES('Microsoft');
INSERT INTO company(name) VALUES('Apple');
INSERT INTO company(name) VALUES('Google');
INSERT INTO company(name) VALUES('Intel');

INSERT INTO person(name, company_id) VALUES('Jack', 1);
INSERT INTO person(name, company_id) VALUES('Mike', 2);
INSERT INTO person(name, company_id) VALUES('Daniel', 3);
INSERT INTO person(name, company_id) VALUES('Georg', 4);
INSERT INTO person(name, company_id) VALUES('Henry', 5);
INSERT INTO person(name, company_id) VALUES('Mitchal', 5);

-- task 1

SELECT person.name, company.name FROM person 
JOIN company ON person.id = company.id AND company.id != 5;

-- task 2

SELECT company.name, COUNT(company_id) FROM company 
JOIN person ON company.id = person.company_id 
GROUP BY company.name ORDER BY COUNT(company_id) DESC LIMIT 1;