START TRANSACTION;

DROP TABLE IF EXISTS recipes;

CREATE TABLE recipes(
	id serial,
	title varchar (100) NOT NULL UNIQUE,
	picture_url varchar (100),
	description varchar (500) NOT NULL,
	CONSTRAINT pk_recipes PRIMARY KEY (id)
);

CREATE TABLE recipe_ingredients(
	recipe_id int,
	ingredient
	phone money NOT NULL,
	internet money NOT NULL,
	CONSTRAINT pk_first_of_month_expenses PRIMARY KEY (month_id)
);




COMMIT;
