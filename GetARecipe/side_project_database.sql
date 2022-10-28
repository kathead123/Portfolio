START TRANSACTION;

DROP TABLE IF EXISTS recipe_ingredients;
DROP TABLE IF EXISTS ingredients;
DROP TABLE IF EXISTS recipe_instructions;
DROP TABLE IF EXISTS recipes;

CREATE TABLE recipes(
	recipe_id serial,
	title varchar (100) NOT NULL UNIQUE,
	picture_url varchar (100),
	description varchar (500) NOT NULL,
	vegetarian_or_vegan varchar(10) NOT NULL,
	CONSTRAINT pk_recipes PRIMARY KEY (recipe_id)
);

CREATE TABLE recipe_instructions(
	step_number int NOT NULL,
	recipe_id int NOT NULL,
	step_description varchar(10000) NOT NULL,
	CONSTRAINT pk_recipe_steps PRIMARY KEY (step_number, recipe_id),
	CONSTRAINT fk_recipe_steps_recipe FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id)
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
	measurement_style varchar(20) DEFAULT NULL,
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
	

INSERT INTO recipes (title, picture_url, description, vegetarian_or_vegan) VALUES
	('Classic Fried Rice', 'https://dinnerthendessert.com/wp-content/uploads/2016/04/Classic-Fried-Rice-2-680x453.jpg', 'The vegetarian version of a Chinese food staple. This simple wok recipe takes no more than 30 minutes from start to finish to cook.', 'vegetarian');
	
	SELECT * FROM recipes;
	
INSERT INTO recipe_instructions(step_number, recipe_id, step_description) VALUES
	(1, (SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), 'Cook 2 cups of rice to 2 cups of water in a pressure cooker or rice maker prior to starting the actual recipe steps.'),
	(2, (SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), 'Combine the salt, water, olive oil, sesame oil, cornstarch, dark soy sauce, soy sauce, and pepper in a bowl so that it is ready to go before you start cooking.'),
	(3, (SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), 'Fluff your cookied rice with a fork.'),
	(4, (SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), 'Heat your wok over medium high heat. Add olive oil to grease the pan and then add your beaten eggs. Cook almost all the way through and then dump back into bowl.'),
	(5, (SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), 'Add some more olive oil and begin sauteing onions until translucent (about 5 minutes).'),
	(6, (SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), 'With the onions still in the wok, add the rice and stir until combined. Next add the sauce mixture and stir around until all rice is covered. Add your eggs and peas.'),
	(7, (SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), 'Push all the rice into the middle of the wok. After 20 seconds pour shaoxing wine around perimeter and let sit for another 20 seconds. Stir to combine. Add salt and pepper to taste.');
	
	
	


INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measurement, measurement_type) VALUES
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Salt'), 0.25, 'tsp'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Pepper'), 0.25, 'tsp'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Water'), 3.00, 'tbsps'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Olive Oil'), 1.00, 'tsp'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Sesame Oil'), 1.00, 'tsp'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Soy Sauce'), 3.00, 'tbsps'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Dark Soy Sauce'), 1.00, 'tbsp'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Cornstarch'), 1.00, 'tsp'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Frozen Peas'), 0.75, 'cups'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Shaoxing Wine'), 2.00, 'tbsps');

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measurement, measurement_type, measurement_style) VALUES
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Eggs'), 2.00, 'whole', 'beaten'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'Onion'), 1.00, 'whole', 'diced'),
	((SELECT recipe_id FROM recipes WHERE title= 'Classic Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name= 'White Rice'), 6.00, 'cups', 'cooked');
	
	
	
	SELECT measurement, measurement_type, measurement_style, ingredient_name, title, step_description
	FROM recipe_ingredients
	JOIN ingredients ON recipe_ingredients.ingredient_id= ingredients.ingredient_id
	JOIN recipes ON recipe_ingredients.recipe_id= recipes.recipe_id
	JOIN recipe_instructions ON recipes.recipe_id= recipe_instructions.recipe_id;
	
	
	SELECT *
	FROM recipe_instructions;



COMMIT;


