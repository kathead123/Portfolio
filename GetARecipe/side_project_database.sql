START TRANSACTION;

DROP TABLE IF EXISTS recipe_ingredients;
DROP TABLE IF EXISTS ingredients;
DROP TABLE IF EXISTS recipes;

CREATE TABLE recipes(
	recipe_id serial,
	title varchar (100) NOT NULL UNIQUE,
	picture_url varchar (100),
	description varchar (500) NOT NULL,
	CONSTRAINT pk_recipes PRIMARY KEY (recipe_id)
);

CREATE TABLE ingredients(
	ingredient_id serial,
	ingredient_name varchar(100) NOT NULL UNIQUE,
	CONSTRAINT pk_ingredients PRIMARY KEY (ingredient_id)
);

CREATE TABLE recipe_ingredients(
	recipe_id int,
	ingredient_id int,
	measurement numeric(3,2) NOT NULL,
	measurement_type varchar(20) NOT NULL,
	CONSTRAINT pk_recipe_ingredients PRIMARY KEY (recipe_id, ingredient_id),
	CONSTRAINT fk_recipe_ingredients_recipes FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id),
	CONSTRAINT fk_recipe_ingredients_ingredients FOREIGN KEY (ingredient_id) REFERENCES ingredients(ingredient_id)
);

INSERT INTO ingredients (ingredient_name) VALUES 
	('Salt'),
	('Pepper'),
	('Water'),
	('Cornstarch'),
	('Dark Soy Sauce'),
	('Soy Sauce'),
	('Olive Oil'),
	('Sesame Oil'),
	('Eggs'),
	('Onion'),
	('Frozen Peas'),
	('White Rice'),
	('Shaoxing Wine');
	SELECT * FROM ingredients;
	

INSERT INTO recipes (title, picture_url, description) VALUES
	('Classic Fried Rice', 'https://dinnerthendessert.com/wp-content/uploads/2016/04/Classic-Fried-Rice-2-680x453.jpg', 'The vegetarian version of a Chinese food staple. This simple wok recipe takes no more than 30 minutes from start to finish to cook.');
	
	SELECT * FROM recipes;

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measurement, measurement_type) VALUES
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Salt'), 0.25, 'tsp'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Pepper'), 0.25, 'tsp'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Water'), 3.00, 'tbsps'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Olive Oil'), 1.00, 'tsp'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Sesame Oil'), 1.00, 'tsp'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Soy Sauce'), 3.00, 'tbsps'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Dark Soy Sauce'), 1.00, 'tbsp'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Cornstarch'), 1.00, 'tsp'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'White Rice'), 5.00, 'cups'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Eggs'), 2.00, 'whole'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Onion'), 1.00, 'whole'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Frozen Peas'), 0.75, 'cups'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Shaoxing Wine'), 2.00, 'tbsps');
	
	
	
	SELECT measurement, measurement_type, ingredient_name, title
	FROM recipe_ingredients
	JOIN ingredients ON recipe_ingredients.ingredient_id= ingredients.ingredient_id
	JOIN recipes ON recipe_ingredients.recipe_id= recipes.recipe_id;



COMMIT;


