BEGIN TRANSACTION;

DROP TABLE IF EXISTS maintenance_requests;
DROP TABLE IF EXISTS invoices;
DROP TABLE IF EXISTS renters;
DROP TABLE IF EXISTS properties;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	name varchar(100) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE properties (
	property_id SERIAL,
	address varchar(200) NOT NULL,
	rent_amount int NOT NULL,
	property_description varchar(500) NOT NULL,
	picture_link varchar(500),
	landlord_user_id int NOT NULL,
	is_rented boolean NOT NULL,
	CONSTRAINT PK_property PRIMARY KEY (property_id),
	CONSTRAINT FK_landlord_users FOREIGN KEY (landlord_user_id) REFERENCES users(user_id)

);

CREATE TABLE renters (
	renter_id SERIAL,
	lease_start_date date NOT NULL,
	lease_end_date date NOT NULL,
	user_id int NOT NULL,
	CONSTRAINT PK_renters PRIMARY KEY (renter_id),
	CONSTRAINT FK_renters_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE invoices (
	invoice_number SERIAL,
	is_paid boolean NOT NULL,
	due_date date NOT NULL,
	amount_due int NOT NULL,
	renter_id int,
	property_id int,
	CONSTRAINT PK_invoice PRIMARY KEY (invoice_number),
	CONSTRAINT FK_invoices_renter FOREIGN KEY (renter_id) REFERENCES renters(renter_id),
	CONSTRAINT FK_invoices_properties FOREIGN KEY (property_id) REFERENCES properties(property_id)
	
	
);

CREATE TABLE maintenance_requests (
	maint_request_id SERIAL,
	property_id int,
	issue_description varchar(500) NOT NULL,
	date_submitted date NOT NULL,
	is_resolved boolean NOT NULL,
	assigned_to int,
	submitted_by int,
	CONSTRAINT PK_maintenance PRIMARY KEY (maint_request_id),
	CONSTRAINT FK_maintenance_requests_properties FOREIGN KEY (property_id) REFERENCES properties(property_id),
	CONSTRAINT FK_maintenance_requests_users_assigned FOREIGN KEY (assigned_to) REFERENCES users(user_id),
	CONSTRAINT FK_maintenance_requests_users_submitted FOREIGN KEY (submitted_by) REFERENCES users(user_id)

);
COMMIT TRANSACTION;




