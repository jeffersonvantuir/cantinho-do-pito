CREATE DATABASE cantinho_do_pito_db;
USE cantinho_do_pito_db;

CREATE TABLE states (
	id INTEGER AUTO_INCREMENT NOT NULL,
	name VARCHAR(255) NOT NULL,
	uf CHAR(2) NOT NULL,
	CONSTRAINT pk_states PRIMARY KEY (id)
);

CREATE TABLE cities (
	id INTEGER AUTO_INCREMENT NOT NULL,
	name VARCHAR(255) NOT NULL,
	state_id INTEGER NOT NULL,
	CONSTRAINT pk_cities PRIMARY KEY (id),
	CONSTRAINT fk_states_cities FOREIGN KEY (state_id) REFERENCES states(id)
);

CREATE TABLE address (
	id INTEGER AUTO_INCREMENT NOT NULL,
	address VARCHAR(255) NOT NULL,
	zipcode VARCHAR(20) NOT NULL,
	complement VARCHAR(255),
	district VARCHAR(255),
	city_id INTEGER NOT NULL,
	CONSTRAINT pk_address PRIMARY KEY (id),
	CONSTRAINT fk_cities_address FOREIGN KEY (city_id) REFERENCES cities(id)
);

CREATE TABLE clients (
	id INTEGER AUTO_INCREMENT NOT NULL,
	email VARCHAR(255) NOT NULL,
	name VARCHAR(255) NOT NULL,
	cpf CHAR(11) NOT NULL,
	cellphone VARCHAR(20) NOT NULL,
	password VARCHAR(255) NOT NULL,
	birthday DATE,
	address VARCHAR(255) NOT NULL,
	zipcode VARCHAR(20) NOT NULL,
	home_number INTEGER,
	complement VARCHAR(255),
	district VARCHAR(255),
	city_id INTEGER NOT NULL,
	is_admin BOOLEAN DEFAULT FALSE,
	CONSTRAINT pk_clients PRIMARY KEY (id),
	CONSTRAINT fk_cities_clients FOREIGN KEY (city_id) REFERENCES cities(id)
);

CREATE TABLE categories (
	id INTEGER AUTO_INCREMENT NOT NULL,
	name VARCHAR(255) NOT NULL,
	description VARCHAR(255) NOT NULL,
	CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE brands (
    id INTEGER AUTO_INCREMENT NOT NULL,
	name VARCHAR(255) NOT NULL,
	CONSTRAINT pk_brands PRIMARY KEY (id)
);

CREATE TABLE products (
	id INTEGER AUTO_INCREMENT NOT NULL,
	name VARCHAR(255) NOT NULL,
        description TEXT NOT NULL,
        price DECIMAL(7,2) NOT NULL,
        stock INTEGER NOT NULL,
        image VARCHAR(255) NOT NULL,
        category_id INTEGER NOT NULL,
    	brand_id INTEGER NOT NULL,
	CONSTRAINT pk_products PRIMARY KEY (id),
	CONSTRAINT fk_categories_products FOREIGN KEY (category_id) REFERENCES categories(id),
	CONSTRAINT fk_brands_products FOREIGN KEY (brand_id) REFERENCES brands(id)
);

CREATE TABLE buy (
	id CHAR(36) NOT NULL,
	total_price DECIMAL(7,2) NOT NULL,
	date DATE NOT NULL,
	client_id INTEGER NOT NULL,
	address_id INTEGER NOT NULL,
	CONSTRAINT pk_buy PRIMARY KEY (id),
	CONSTRAINT fk_clients_buy FOREIGN KEY (client_id) REFERENCES clients(id),
  	CONSTRAINT fk_address_buy FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE buy_products (
	id INTEGER AUTO_INCREMENT NOT NULL,
    	amount INTEGER NOT NULL,
	total_price DECIMAL(7,2) NOT NULL,
	product_id INTEGER NOT NULL,
    	buy_id CHAR(36) NOT NULL,
	CONSTRAINT pk_buy_produtcs PRIMARY KEY (id),
  	CONSTRAINT fk_products_buy_products FOREIGN KEY (product_id) REFERENCES products(id),
	CONSTRAINT fk_buy_buy_products FOREIGN KEY (buy_id) REFERENCES buy(id)
);

CREATE TABLE requests (
	id INTEGER AUTO_INCREMENT NOT NULL,
	status BOOLEAN DEFAULT FALSE,
	product_id INTEGER NOT NULL,
    	client_id INTEGER NOT NULL,
	CONSTRAINT pk_requests PRIMARY KEY (id),
	CONSTRAINT fk_products_requests FOREIGN KEY (product_id) REFERENCES products(id),
	CONSTRAINT fk_clients_requests FOREIGN KEY (client_id) REFERENCES clients(id)
);

DELIMITER $$
CREATE TRIGGER request_update AFTER UPDATE ON products
FOR EACH ROW
BEGIN
	IF (NEW.stock > 0)
		THEN
		UPDATE requests re
			SET re.status = TRUE
				WHERE re.product_id = NEW.id
				AND re.status = FALSE;
	ELSE
		UPDATE requests re
			SET re.status = FALSE
				WHERE re.product_id = NEW.id
				AND re.status = TRUE;
	END IF;
END;
$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER stock_update AFTER INSERT ON buy_products
FOR EACH ROW
BEGIN
	IF ((SELECT stock FROM products WHERE id=NEW.product_id) >= NEW.amount)
	THEN
		UPDATE products pr
			SET pr.stock = pr.stock - NEW.amount
				WHERE pr.id = NEW.product_id;
	ELSE 
		DELETE FROM buy_products WHERE id = NEW.id;
        UPDATE buy b 
			SET b.total_price = b.total_price - NEW.total_price 
				WHERE id = NEW.buy_id;
		SIGNAL SQLSTATE '02000' SET MESSAGE_TEXT = 'Error';
	END IF;
END;
$$
DELIMITER ;
