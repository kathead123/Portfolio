BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role,name) VALUES ('renter','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_RENTER','mr renter');
INSERT INTO users (username,password_hash,role,name) VALUES ('landlord','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_LANDLORD','ms landlord');
INSERT INTO users (username,password_hash,role,name) VALUES ('maintenance','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_MAINTENANCE','mr maintenance');
INSERT INTO users (username,password_hash,role,name) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ADMIN','ms admin');
INSERT INTO users (username,password_hash,role,name) VALUES ('wednesday','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_RENTER','Wednesday Addams');
INSERT INTO users (username,password_hash,role,name) VALUES ('paulsheldonsnum1fan','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_RENTER','Annie Wilkes');
INSERT INTO users (username,password_hash,role,name) VALUES ('michael','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_RENTER','Michael Meyers');



INSERT INTO properties (address, rent_amount, property_description, picture_link, landlord_user_id, is_rented) VALUES ('4019 Highway 88, White Pine Bay, OR 97702', 
																													   666, 'There are four bedrooms, a full kitchen, a large living room, dining room, mud/laundry room, one full bathroom, a walk-up attic and a full basement with a walk-in freezer.', 
																													  'https://slack-imgs.com/?c=1&o1=ro&url=https%3A%2F%2Fwww.thesfnews.com%2Fwp-content%2Fuploads%2F2015%2F03%2FBates-Motel.jpg', (SELECT user_id FROM users WHERE username='landlord'), TRUE);
INSERT INTO properties (address, rent_amount, property_description, picture_link, landlord_user_id, is_rented) VALUES ('112 Ocean Ave, North Massapequa, NY 11758', 1500, 'Built in 1927, the three-story waterfront home boasts five bedrooms, 3½ bathrooms and a basement',
																													   'https://imgs.6sqft.com/wp-content/uploads/2016/06/07130558/Amityville-Horror-House-2.jpg', (SELECT user_id FROM users WHERE username='landlord'), FALSE);
INSERT INTO properties (address, rent_amount, property_description, picture_link, landlord_user_id, is_rented) VALUES ('430 Keysburg Rd, Adams, TN 37010', 999, 'This rustic cabin is 800 square feet with 3 bedrooms and 1 outhouse and lots of charm.',
																													   'https://bloximages.chicago2.vip.townnews.com/crossville-chronicle.com/content/tncms/assets/v3/editorial/2/b1/2b10f74e-fa89-11e9-93c1-23eeed9cd7e7/5db89e6925fff.image.jpg', (SELECT user_id FROM users WHERE username='landlord'), FALSE);
INSERT INTO properties (address, rent_amount, property_description, picture_link, landlord_user_id, is_rented) VALUES ('4421 Woodward Avenue Detroit, MI 48201', 3000, 'Completed in 1894, this sprawling home has 9 bedrooms, 12 bathrooms, and 3 full kitchens. The Whitney now provides several unique features within the historical property: the fine dining restaurant, menu dining throughout the Mansion, The Ghostbar and Gardens',
																													  'https://slack-imgs.com/?c=1&o1=ro&url=https%3A%2F%2Feventective-media.azureedge.net%2F2218593_lg.jpg', (SELECT user_id FROM users WHERE username='landlord'), FALSE);
INSERT INTO properties (address, rent_amount, property_description, picture_link, landlord_user_id, is_rented) VALUES ('1000 Mission St, South Pasadena, CA 91030', 2500, 'Built in 1888, this historic colonial will bring a slice of thrills and chills to your life. Newly renovated with 4 bedrooms and 2 full baths.',
																													  'https://static.wixstatic.com/media/d03ef6_b5504fb4c2bf4cc49527939cce13707d~mv2.jpg/v1/fill/w_960,h_642,al_c,q_85,enc_auto/d03ef6_b5504fb4c2bf4cc49527939cce13707d~mv2.jpg', (SELECT user_id FROM users WHERE username='landlord'), FALSE);																													  
INSERT INTO properties (address, rent_amount, property_description, picture_link, landlord_user_id, is_rented) VALUES ('4308 Franklin Blvd, Cleveland, OH 44113', 4966, 'Vicorian stone house built in the American Queen Anne style. The building has 4 stories and 23 total rooms.',
																													  'https://www.ableroof.com/wp-content/uploads/2021/10/r93n4keiwxh61.jpg', (SELECT user_id FROM users WHERE username='landlord'), FALSE);																													  
INSERT INTO properties (address, rent_amount, property_description, picture_link, landlord_user_id, is_rented) VALUES ('001 Cemetary Lane, Death Valley, CA 90210', 200, 'This house is a museum, when people come to see em, they really are a screamin. Come see this for an open house on Wednesdays!',
																													  'https://sites.google.com/site/theaddamsfamilyproperty/_/rsrc/1368637956703/home/tumblr_mbopeoZJK01r7arxb.jpg', (SELECT user_id FROM users WHERE username='landlord'), FALSE);																													  
INSERT INTO properties (address, rent_amount, property_description, picture_link, landlord_user_id, is_rented) VALUES ('4 Ocean Ave, Salem, MA 01970', 1111, 'A truly magical home, it will cast a spell on you! 3 Bedrooms and 1 bathroom in this quaint witch friendly town!',
																													  'https://ew77mymd4fj.exactdn.com/wp-content/uploads/2022/06/Dani-and-Max-Dennisons-House-from-Hocus-Pocus-in-Salem-MA.jpg?strip=all&lossy=1&ssl=1', (SELECT user_id FROM users WHERE username='landlord'), FALSE);																													  
INSERT INTO properties (address, rent_amount, property_description, picture_link, landlord_user_id, is_rented) VALUES ('83 Tower Road, Sidewinder, CO 24697', 1919, 'Beautiful secluded farmhouse with a mountain view. Follow the cobbled path for a scenic hike. You will be obsessed with the story this charming home creates',
																													  'https://4.bp.blogspot.com/-1h-9gECce1o/WCEBso16q1I/AAAAAAAAVlY/nIEnwzC14w4Gdj2DUVOJbBl50Pnai1dUwCLcB/s1600/Misery-Wilkes-Farmhouse-1990.JPG', (SELECT user_id FROM users WHERE username='landlord'), FALSE);																													  
																																			  																														  
INSERT INTO renters (lease_start_date, lease_end_date, user_id) VALUES ('01/01/2023', '01/01/2024', 1);

INSERT INTO invoices(is_paid, due_date, amount_due, renter_id, property_id)
VALUES (false, '01-01-2023', (SELECT rent_amount FROM properties WHERE property_id=1), 1, 1);

INSERT INTO invoices(is_paid, due_date, amount_due, renter_id, property_id)
VALUES (false, '02-01-2023', (SELECT rent_amount FROM properties WHERE property_id=1), 1, 1);

INSERT INTO invoices(is_paid, due_date, amount_due, renter_id, property_id)
VALUES (false, '03-01-2023', (SELECT rent_amount FROM properties WHERE property_id=1), 1, 1);

INSERT INTO invoices(is_paid, due_date, amount_due, renter_id, property_id)
VALUES (false, '04-01-2023', (SELECT rent_amount FROM properties WHERE property_id=1), 1, 1);

INSERT INTO invoices(is_paid, due_date, amount_due, renter_id, property_id)
VALUES (false, '05-01-2023', (SELECT rent_amount FROM properties WHERE property_id=1), 1, 1);

INSERT INTO invoices(is_paid, due_date, amount_due, renter_id, property_id)
VALUES (false, '06-01-2023', (SELECT rent_amount FROM properties WHERE property_id=1), 1, 1);

INSERT INTO invoices(is_paid, due_date, amount_due, renter_id, property_id)
VALUES (false, '07-01-2023', (SELECT rent_amount FROM properties WHERE property_id=1), 1, 1);

INSERT INTO invoices(is_paid, due_date, amount_due, renter_id, property_id)
VALUES (false, '08-01-2023', (SELECT rent_amount FROM properties WHERE property_id=1), 1, 1);

INSERT INTO invoices(is_paid, due_date, amount_due, renter_id, property_id)
VALUES (false, '09-01-2023', (SELECT rent_amount FROM properties WHERE property_id=1), 1, 1);

INSERT INTO invoices(is_paid, due_date, amount_due, renter_id, property_id)
VALUES (false, '10-01-2023', (SELECT rent_amount FROM properties WHERE property_id=1), 1, 1);

INSERT INTO invoices(is_paid, due_date, amount_due, renter_id, property_id)
VALUES (false, '11-01-2023', (SELECT rent_amount FROM properties WHERE property_id=1), 1, 1);

INSERT INTO invoices(is_paid, due_date, amount_due, renter_id, property_id)
VALUES (false, '12-01-2023', (SELECT rent_amount FROM properties WHERE property_id=1), 1, 1);
																													   

COMMIT TRANSACTION;
rollback

