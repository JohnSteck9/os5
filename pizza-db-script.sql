CREATE DATABASE IF NOT EXISTS pizza_project;
USE pizza_project;
#DELIMITER //
#//
#DELIMITER ;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS courier;
DROP TABLE IF EXISTS courier_status;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS delivery_area;

DROP TABLE IF EXISTS drink;
DROP TABLE IF EXISTS drink_has_order_info; 

DROP TABLE IF EXISTS order_info;
DROP TABLE IF EXISTS order_status;

DROP TABLE IF EXISTS pizza;
DROP TABLE IF EXISTS pizza_size;
DROP TABLE IF EXISTS ingredients;
DROP TABLE IF EXISTS pizza_has_ingredients;
DROP TABLE IF EXISTS pizza_has_order_info;

DROP TABLE IF EXISTS salads;
DROP TABLE IF EXISTS salads_has_order_info;
SET FOREIGN_KEY_CHECKS = 1;

#------------------------------------------------
CREATE TABLE courier (
id INT AUTO_INCREMENT NOT NULL,
first_name VARCHAR(45) NOT NULL,
last_name VARCHAR(45) NOT NULL,
phone VARCHAR(45) NOT NULL,
email VARCHAR(100) NULL,
courier_status_id INT NOT NULL DEFAULT 1,
PRIMARY KEY (id),
INDEX (first_name)
) ENGINE = INNODB;

CREATE TABLE courier_status (
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
status VARCHAR(45) NOT NULL
) ENGINE = INNODB;

CREATE TABLE customer (
id INT AUTO_INCREMENT NOT NULL,
first_name VARCHAR(45) NOT NULL,
last_name VARCHAR(45) NOT NULL,
phone VARCHAR(20) NOT NULL,
email VARCHAR(100) NULL,
PRIMARY KEY (id)
) ENGINE = INNODB;

CREATE TABLE delivery_area (
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
zone VARCHAR(45) NOT NULL,
delivery_time VARCHAR(50) NULL
) ENGINE = INNODB;

CREATE TABLE drink (
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
name VARCHAR(45) NOT NULL,
prise DECIMAL(10, 2) NOT NULL,
liter DECIMAL(10, 2) NOT NULL
) ENGINE = INNODB;

CREATE TABLE drink_has_order_info (
drink_id INT NOT NULL,
order_info_id INT NOT NULL,
quantity INT NULL,
PRIMARY KEY (drink_id, order_info_id)
) ENGINE = INNODB;

CREATE TABLE order_info (
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
comment VARCHAR(255) NULL,
price_product DECIMAL(10, 2) NOT NULL,
price_delivery DECIMAL(10, 2) NOT NULL,
expected_time VARCHAR(50) NULL,
actual_time VARCHAR(50) NULL,
delivery_area_id INT NOT NULL,
order_status_id INT NOT NULL,
customer_id INT NOT NULL,
courier_id INT NOT NULL
# INDEX (prise_total)
) ENGINE = INNODB;

CREATE TABLE order_status (
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
status VARCHAR(100) NOT NULL
) ENGINE = INNODB;

CREATE TABLE pizza (
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
name VARCHAR(45) NOT NULL UNIQUE,
price DECIMAL(10, 2) NOT NULL,
weight VARCHAR(45) NOT NULL,
pizza_size_id INT NOT NULL
# ingredients VARCHAR(45) NOT NULL,
# size VARCHAR(45) NOT NULL,
) ENGINE = INNODB;

CREATE TABLE pizza_size (
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
name VARCHAR(45) NOT NULL UNIQUE,
diameter_cm INT NOT NULL
# pizza_id INT NOT NULL,
# FOREIGN KEY(pizza_id) REFERENCES pizza(id)
) ENGINE = INNODB;

CREATE TABLE ingredients (
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
name VARCHAR(100) NOT NULL UNIQUE
# price DECIMAL(10,2) NOT NULL,
# weight VARCHAR(45) NOT NULL
) ENGINE = INNODB;

CREATE TABLE pizza_has_ingredients (
pizza_id INT NOT NULL,
ingredients_id INT NOT NULL,
PRIMARY KEY (pizza_id, ingredients_id)
) ENGINE = INNODB;

CREATE TABLE pizza_has_order_info (
pizza_id INT NOT NULL,
order_info_id INT NOT NULL,
quantity INT NULL,
PRIMARY KEY (pizza_id, order_info_id)
) ENGINE = INNODB;

CREATE TABLE salads (
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
name VARCHAR(45) NOT NULL UNIQUE,
ingredients VARCHAR(45) NOT NULL,
price DECIMAL(10, 2) NOT NULL,
weight INT NOT NULL
) ENGINE = INNODB;

CREATE TABLE salads_has_order_info (
salads_id INT NOT NULL,
order_info_id INT NOT NULL,
quantity INT NULL,
PRIMARY KEY (salads_id, order_info_id)
) ENGINE = INNODB;

# ========================================================

ALTER TABLE courier
	ADD CONSTRAINT FK_courier_courier_status
    FOREIGN KEY (courier_status_id)
		REFERENCES courier_status (id);
        
ALTER TABLE drink_has_order_info
	ADD CONSTRAINT FK_drink_id
		FOREIGN KEY (drink_id)
		REFERENCES drink (id),
	ADD CONSTRAINT FK_order_info_id
		FOREIGN KEY (order_info_id)
		REFERENCES order_info (id);

ALTER TABLE order_info
	ADD CONSTRAINT FK_delivery_area_id
		FOREIGN KEY (delivery_area_id)
		REFERENCES delivery_area (id),
	ADD CONSTRAINT FK_order_status_id
		FOREIGN KEY (order_status_id)
		REFERENCES order_status (id),
	ADD CONSTRAINT FK_customer_id
		FOREIGN KEY (customer_id)
		REFERENCES customer (id),
	ADD CONSTRAINT FK_courier_id
		FOREIGN KEY (courier_id)
		REFERENCES courier (id);

ALTER TABLE pizza_has_order_info
	ADD CONSTRAINT FK_pizza_id_order_info
		FOREIGN KEY (pizza_id)
		REFERENCES pizza (id),
	ADD CONSTRAINT FK_order_info_id_pizza
		FOREIGN KEY (order_info_id)
		REFERENCES order_info (id);

/*
ALTER TABLE pizza_additions_has_pizza_has_order_info
	ADD CONSTRAINT FK_pizza_additions__pizza_has_order_info
		FOREIGN KEY (pizza_additions_id)
		REFERENCES pizza_additions (id),
	ADD CONSTRAINT FK_pizza_has_order_info
		FOREIGN KEY (pizza_has_order_info_pizza_id)
		REFERENCES pizza_has_order_info (pizza_id),
	ADD CONSTRAINT FK_pizza_has_order_info_order_info
		FOREIGN KEY (pizza_has_order_info_order_info_id)
		REFERENCES pizza_has_order_info (order_info_id);
*/

ALTER TABLE salads_has_order_info
	ADD CONSTRAINT FK_salads_id
		FOREIGN KEY (salads_id)
		REFERENCES salads (id),
	ADD CONSTRAINT FK_order_info_id_salads
		FOREIGN KEY (order_info_id)
		REFERENCES order_info (id);
        
ALTER TABLE pizza_has_ingredients
	ADD CONSTRAINT FK_pizza_id_ingradients
		FOREIGN KEY (pizza_id)
		REFERENCES pizza (id),
	ADD CONSTRAINT FK_ingredients_id_pizza
		FOREIGN KEY (ingredients_id)
		REFERENCES ingredients (id);

ALTER TABLE pizza
	ADD CONSTRAINT FK_pizza_size
    FOREIGN KEY (pizza_size_id)
		REFERENCES pizza_size (id);
/*        
ALTER TABLE size
	ADD CONSTRAINT FK_size_id_pizza
		FOREIGN KEY (pizza_id)
		REFERENCES pizza (id);
*/    

#==================================================================
insert into drink (id, name, prise, liter) values (1, 1, 567.48, 1);
insert into drink (id, name, prise, liter) values (2, 2, 234.61, 3);
insert into drink (id, name, prise, liter) values (3, 3, 531.98, 2);
insert into drink (id, name, prise, liter) values (4, 4, 738.53, 2);
insert into drink (id, name, prise, liter) values (5, 5, 951.38, 3);
insert into drink (id, name, prise, liter) values (6, 6, 494.61, 1);
insert into drink (id, name, prise, liter) values (7, 7, 159.17, 1);
insert into drink (id, name, prise, liter) values (8, 8, 415.87, 2);
insert into drink (id, name, prise, liter) values (9, 9, 234.52, 1);
insert into drink (id, name, prise, liter) values (10, 10, 991.60, 3);

insert into salads (id, name, ingredients, price, weight) values (1, 'Cuphea aspera Chapm.', 'Cook''s tree boa', 68.17, 175);
insert into salads (id, name, ingredients, price, weight) values (2, 'Dicranodontium asperulum (Mitt.) Broth.', 'White-lipped peccary', 78.69, 238);
insert into salads (id, name, ingredients, price, weight) values (3, 'Adenophorus oahuensis (Copeland) Bishop', 'Lion, california sea', 47.53, 113);
insert into salads (id, name, ingredients, price, weight) values (4, 'Mollugo nudicaulis Lam.', 'Suricate', 23.23, 127);
insert into salads (id, name, ingredients, price, weight) values (5, 'Asclepias nivea L.', 'Penguin, fairy', 40.11, 203);
insert into salads (id, name, ingredients, price, weight) values (6, 'Trichothelium Müll. Arg.', 'Screamer, southern', 80.86, 216);
insert into salads (id, name, ingredients, price, weight) values (7, 'Ocimum basilicum L.', 'Black kite', 18.65, 136);
insert into salads (id, name, ingredients, price, weight) values (8, 'Cerastium beeringianum Cham.', 'Dog, black-tailed prairie', 67.27, 134);
insert into salads (id, name, ingredients, price, weight) values (9, 'Castilleja puberula Rydb.', 'Orca', 95.12, 176);
insert into salads (id, name, ingredients, price, weight) values (10, 'Stillingia linearifolia S. Watson', 'Blue wildebeest', 94.79, 166);

insert into pizza_size (id, name, diameter_cm) values (1, 'small', 15);
insert into pizza_size (id, name, diameter_cm) values (2, 'medium', 30);
insert into pizza_size (id, name, diameter_cm) values (3, 'big', 60);
insert into pizza_size (id, name, diameter_cm) values (4, 'MEGA', 100);

insert into ingredients (id, name) values (1, 'Fontinalis');
insert into ingredients (id, name) values (2, 'Paronychia virginica');
insert into ingredients (id, name) values (3, 'Piper');
insert into ingredients (id, name) values (4, 'Watson');
insert into ingredients (id, name) values (5, 'Clarkia');
insert into ingredients (id, name) values (6, 'Jarava plumosa');
insert into ingredients (id, name) values (7, 'Sterculia setigera Delile');
insert into ingredients (id, name) values (8, 'Tephroseris atropurpurea');
insert into ingredients (id, name) values (9, 'Cerastium alpinum');
insert into ingredients (id, name) values (10, 'Malcolmia maritima');

/*
insert into pizza_additions (id, name, price, weight) values (1, 'Fontinalis flaccida Renauld & Cardot', '88.29', 70);
insert into pizza_additions (id, name, price, weight) values (2, 'Paronychia virginica Spreng.', '88.82', 68);
insert into pizza_additions (id, name, price, weight) values (3, 'Townsendia alpigena Piper var. alpigena', '98.64', 32);
insert into pizza_additions (id, name, price, weight) values (4, 'Polemonium pauciflorum S. Watson ssp. hinckleyi (Standl.) Wherry', '33.35', 51);
insert into pizza_additions (id, name, price, weight) values (5, 'Sterculia setigera Delile', '13.28', 44);
insert into pizza_additions (id, name, price, weight) values (6, 'Clarkia biloba (Durand) A. Nelson & J.F. Macbr. ssp. australis F.H. Lewis & M.E. Lewis', '31.63', 74);
insert into pizza_additions (id, name, price, weight) values (7, 'Jarava plumosa (Spreng.) S.L.W. Jacobs & J. Everett', '63.68', 25);
insert into pizza_additions (id, name, price, weight) values (8, 'Tephroseris atropurpurea (Ledeb.) Holub ssp. frigida (Richardson) Á. Löve & D. Löve', '57.05', 27);
insert into pizza_additions (id, name, price, weight) values (9, 'Cerastium alpinum L. ssp. lanatum (Lam.) Ces.', '88.44', 54);
insert into pizza_additions (id, name, price, weight) values (10, 'Malcolmia maritima (L.) W.T. Aiton', '94.44', 35);
*/

/*
insert into pizza (id, name, ingredients, price, size, weight) values (1, 'Asteraceae1', 'Scrophulariaceae', '127.00', 60, 343);
insert into pizza (id, name, ingredients, price, size, weight) values (2, 'Asteraceae2', 'Poaceae', '657.00', 37, 161);
insert into pizza (id, name, ingredients, price, size, weight) values (3, 'Poaceae', 'Polygonaceae', '153.12', 46, 678);
insert into pizza (id, name, ingredients, price, size, weight) values (4, 'Iridaceae', 'Arecaceae', '255.25', 37, 376);
insert into pizza (id, name, ingredients, price, size, weight) values (5, 'Rosaceae1', 'Pteridaceae', '325.98', 42, 469);
insert into pizza (id, name, ingredients, price, size, weight) values (6, 'Rosaceae2', 'Juglandaceae', '671.63', 45, 664);
insert into pizza (id, name, ingredients, price, size, weight) values (7, 'Fabaceae', 'Brassicaceae', '454.74', 54, 367);
insert into pizza (id, name, ingredients, price, size, weight) values (8, 'Arecaceae3', 'Cryphaeaceae', '235.56', 58, 485);
insert into pizza (id, name, ingredients, price, size, weight) values (9, 'Loasaceae', 'Sphagnaceae', '158.33', 39, 749);
insert into pizza (id, name, ingredients, price, size, weight) values (10, 'Ranunculaceae', 'Saxifragaceae', '568.43', 39, 491);
*/
insert into pizza (id, name, price, weight, pizza_size_id) values (1, 'Asteraceae1', 127.00, 500, 1);
insert into pizza (id, name, price, weight, pizza_size_id) values (2, 'Asteraceae2', 657.00, 550, 2);
insert into pizza (id, name, price, weight, pizza_size_id) values (3, 'Poaceae', 153.12, 650, 3);
insert into pizza (id, name, price, weight, pizza_size_id) values (4, 'Iridaceae', 255.25, 300, 1);
insert into pizza (id, name, price, weight, pizza_size_id) values (5, 'Rosaceae1', 325.98, 250, 2);
insert into pizza (id, name, price, weight, pizza_size_id) values (6, 'Rosaceae2', 671.63, 250, 3);
insert into pizza (id, name, price, weight, pizza_size_id) values (7, 'Fabaceae', 454.74, 350, 1);
insert into pizza (id, name, price, weight, pizza_size_id) values (8, 'Arecaceae3', 235.56, 450, 2);
insert into pizza (id, name, price, weight, pizza_size_id) values (9, 'Loasaceae', 158.33, 750, 3);
insert into pizza (id, name, price, weight, pizza_size_id) values (10, 'Ranunculaceae', 568.43, 600, 3);

insert into pizza_has_ingredients (pizza_id, ingredients_id) values (1, 1);
insert into pizza_has_ingredients (pizza_id, ingredients_id) values (2, 2);
insert into pizza_has_ingredients (pizza_id, ingredients_id) values (3, 3);
insert into pizza_has_ingredients (pizza_id, ingredients_id) values (4, 4);
insert into pizza_has_ingredients (pizza_id, ingredients_id) values (5, 5);
insert into pizza_has_ingredients (pizza_id, ingredients_id) values (6, 6);
insert into pizza_has_ingredients (pizza_id, ingredients_id) values (7, 7);
insert into pizza_has_ingredients (pizza_id, ingredients_id) values (8, 8);
insert into pizza_has_ingredients (pizza_id, ingredients_id) values (9, 9);
insert into pizza_has_ingredients (pizza_id, ingredients_id) values (10, 10);




/*
insert into menu (id, menu_item) values (1, 'Asteraceae');
insert into menu (id, menu_item) values (2, 'Grammitidaceae');
insert into menu (id, menu_item) values (3, 'Polemoniaceae');
insert into menu (id, menu_item) values (4, 'Parmeliaceae');
insert into menu (id, menu_item) values (5, 'Poaceae');
insert into menu (id, menu_item) values (6, 'Cyperaceae');
insert into menu (id, menu_item) values (7, 'Hydrocharitaceae');
insert into menu (id, menu_item) values (8, 'Cyperaceae');
insert into menu (id, menu_item) values (9, 'Asteraceae');
insert into menu (id, menu_item) values (10, 'Convolvulaceae');
*/

insert into customer (id, first_name, last_name, phone) values (1, 'Ganny', 'Chasmor', '229-902-0924');
insert into customer (id, first_name, last_name, phone) values (2, 'Terri', 'Lodin', '270-117-3746');
insert into customer (id, first_name, last_name, phone) values (3, 'Wilbert', 'Cottier', '914-468-1878');
insert into customer (id, first_name, last_name, phone) values (4, 'Arlene', 'Woolstenholmes', '912-708-7749');
insert into customer (id, first_name, last_name, phone) values (5, 'Chery', 'Escalero', '366-847-2842');
insert into customer (id, first_name, last_name, phone) values (6, 'Fanchon', 'Watkins', '173-280-0533');
insert into customer (id, first_name, last_name, phone) values (7, 'Eleanor', 'Dinneen', '282-946-7866');
insert into customer (id, first_name, last_name, phone) values (8, 'Savina', 'Boissieux', '542-900-4814');
insert into customer (id, first_name, last_name, phone) values (9, 'Rona', 'Fullard', '300-142-3905');
insert into customer (id, first_name, last_name, phone) values (10, 'Jaquelin', 'Leteurtre', '136-983-9752');

insert into order_status (id, status) values (1, 'Violet');
insert into order_status (id, status) values (2, 'Fuscia');
insert into order_status (id, status) values (3, 'Mauv');
insert into order_status (id, status) values (4, 'Teal');
insert into order_status (id, status) values (5, 'Green');
insert into order_status (id, status) values (6, 'Turquoise');
insert into order_status (id, status) values (7, 'Turquoise');
insert into order_status (id, status) values (8, 'Green');
insert into order_status (id, status) values (9, 'Puce');
insert into order_status (id, status) values (10, 'Green');

insert into delivery_area (id, zone) values (1, 'Khaki');
insert into delivery_area (id, zone) values (2, 'Mauv');
insert into delivery_area (id, zone) values (3, 'Yellow');
insert into delivery_area (id, zone) values (4, 'Aquamarine');
insert into delivery_area (id, zone) values (5, 'Crimson');
insert into delivery_area (id, zone) values (6, 'Puce');
insert into delivery_area (id, zone) values (7, 'Teal');
insert into delivery_area (id, zone) values (8, 'Indigo');
insert into delivery_area (id, zone) values (9, 'Goldenrod');
insert into delivery_area (id, zone) values (10, 'Puce');

insert into courier_status (id, status) values (1, 'Orange');
insert into courier_status (id, status) values (2, 'Blue');
insert into courier_status (id, status) values (3, 'Indigo');
insert into courier_status (id, status) values (4, 'Turquoise');
insert into courier_status (id, status) values (5, 'Fuscia');
insert into courier_status (id, status) values (6, 'Indigo');
insert into courier_status (id, status) values (7, 'Blue');
insert into courier_status (id, status) values (8, 'Goldenrod');
insert into courier_status (id, status) values (9, 'Orange');
insert into courier_status (id, status) values (10, 'Violet');

insert into courier (id, first_name, last_name, phone, email, courier_status_id) values (1, 'Robinett', 'Fuggles', '585-189-0897', 'rfuggles0@earthlink.net', 1);
insert into courier (id, first_name, last_name, phone, email, courier_status_id) values (2, 'Thedric', 'Debold', '796-187-9813', 'tdebold1@blogtalkradio.com', 2);
insert into courier (id, first_name, last_name, phone, email, courier_status_id) values (3, 'Chev', 'Son', '714-228-8468', 'cson2@constantcontact.com', 3);
insert into courier (id, first_name, last_name, phone, email, courier_status_id) values (4, 'Ernesto', 'Fulop', '216-364-4731', 'efulop3@storify.com', 4);
insert into courier (id, first_name, last_name, phone, email, courier_status_id) values (5, 'Alfreda', 'Grigoroni', '106-854-2628', 'agrigoroni4@cisco.com', 5);
insert into courier (id, first_name, last_name, phone, email, courier_status_id) values (6, 'Kordula', 'Schimpke', '178-115-9000', 'kschimpke5@abc.net.au', 6);
insert into courier (id, first_name, last_name, phone, email, courier_status_id) values (7, 'Dynah', 'Ruddell', '591-213-6037', 'druddell6@shop-pro.jp', 7);
insert into courier (id, first_name, last_name, phone, email, courier_status_id) values (8, 'Jeannette', 'Conibeer', '196-885-0554', 'jconibeer7@shop-pro.jp', 8);
insert into courier (id, first_name, last_name, phone, email) values (9, 'Curcio', 'D''Ambrogi', '304-931-4618', 'cdambrogi8@macromedia.com');
insert into courier (id, first_name, last_name, phone, email) values (10, 'Tarah', 'Slott', '497-915-4406', 'tslott9@bbc.co.uk');

insert into order_info (id, comment, price_product, price_delivery, delivery_area_id, order_status_id, customer_id, courier_id) values (1, 'Uraeginthus granatina', 360, 50, 1, 1, 1, 1);
insert into order_info (id, comment, price_product, price_delivery, delivery_area_id, order_status_id, customer_id, courier_id) values (2, 'Bubulcus ibis', 964, 44, 2, 2, 2, 2);
insert into order_info (id, comment, price_product, price_delivery, delivery_area_id, order_status_id, customer_id, courier_id) values (3, 'Eumetopias jubatus', 1913.00, 60, 3, 3, 3, 3);
insert into order_info (id, comment, price_product, price_delivery, delivery_area_id, order_status_id, customer_id, courier_id) values (4, 'Thamnolaea cinnmomeiventris', 2632, 84, 4, 4, 4, 4);
insert into order_info (id, comment, price_product, price_delivery, delivery_area_id, order_status_id, customer_id, courier_id) values (5, 'Neophron percnopterus', 326, 31, 5, 5, 5, 5);
insert into order_info (id, comment, price_product, price_delivery, delivery_area_id, order_status_id, customer_id, courier_id) values (6, 'Bison bison', 1654, 68, 6, 6, 6, 6);
insert into order_info (id, comment, price_product, price_delivery, delivery_area_id, order_status_id, customer_id, courier_id) values (7, 'Phasianus colchicus', 1733, 72, 7, 7, 7, 7);
insert into order_info (id, comment, price_product, price_delivery, delivery_area_id, order_status_id, customer_id, courier_id) values (8, 'Choloepus hoffmani', 467, 43, 8, 8, 8, 8);
insert into order_info (id, comment, price_product, price_delivery, delivery_area_id, order_status_id, customer_id, courier_id) values (9, 'Aegypius occipitalis', 503, 29, 9, 9, 9, 9);
insert into order_info (id, comment, price_product, price_delivery, delivery_area_id, order_status_id, customer_id, courier_id) values (10, 'Spermophilus armatus', 2, 36, 10, 10, 10, 10);

insert into pizza_has_order_info (pizza_id, order_info_id, quantity) values (1, 1, 5);
insert into pizza_has_order_info (pizza_id, order_info_id, quantity) values (2, 2, null);
insert into pizza_has_order_info (pizza_id, order_info_id, quantity) values (3, 3, null);
insert into pizza_has_order_info (pizza_id, order_info_id, quantity) values (4, 4, 9);
insert into pizza_has_order_info (pizza_id, order_info_id, quantity) values (5, 5, 1);
insert into pizza_has_order_info (pizza_id, order_info_id, quantity) values (6, 6, 5);
insert into pizza_has_order_info (pizza_id, order_info_id, quantity) values (7, 7, null);
insert into pizza_has_order_info (pizza_id, order_info_id, quantity) values (8, 8, 2);
insert into pizza_has_order_info (pizza_id, order_info_id, quantity) values (9, 9, null);
insert into pizza_has_order_info (pizza_id, order_info_id, quantity) values (10, 10, null);
/*
insert into pizza_additions_has_pizza_has_order_info (pizza_additions_id, pizza_has_order_info_pizza_id, pizza_has_order_info_order_info_id) values (1, 1, 1);
insert into pizza_additions_has_pizza_has_order_info (pizza_additions_id, pizza_has_order_info_pizza_id, pizza_has_order_info_order_info_id) values (2, 2, 2);
insert into pizza_additions_has_pizza_has_order_info (pizza_additions_id, pizza_has_order_info_pizza_id, pizza_has_order_info_order_info_id) values (3, 3, 3);
insert into pizza_additions_has_pizza_has_order_info (pizza_additions_id, pizza_has_order_info_pizza_id, pizza_has_order_info_order_info_id) values (4, 4, 4);
insert into pizza_additions_has_pizza_has_order_info (pizza_additions_id, pizza_has_order_info_pizza_id, pizza_has_order_info_order_info_id) values (5, 5, 5);
insert into pizza_additions_has_pizza_has_order_info (pizza_additions_id, pizza_has_order_info_pizza_id, pizza_has_order_info_order_info_id) values (6, 6, 6);
insert into pizza_additions_has_pizza_has_order_info (pizza_additions_id, pizza_has_order_info_pizza_id, pizza_has_order_info_order_info_id) values (7, 7, 7);
insert into pizza_additions_has_pizza_has_order_info (pizza_additions_id, pizza_has_order_info_pizpizza_projectza_id, pizza_has_order_info_order_info_id) values (8, 8, 8);
insert into pizza_additions_has_pizza_has_order_info (pizza_additions_id, pizza_has_order_info_pizza_id, pizza_has_order_info_order_info_id) values (9, 9, 9);
insert into pizza_additions_has_pizza_has_order_info (pizza_additions_id, pizza_has_order_info_pizza_id, pizza_has_order_info_order_info_id) values (10, 10, 10);
*/
insert into salads_has_order_info (salads_id, order_info_id, quantity) values (1, 1, 9);
insert into salads_has_order_info (salads_id, order_info_id, quantity) values (2, 2, 1);
insert into salads_has_order_info (salads_id, order_info_id, quantity) values (3, 3, 4);
insert into salads_has_order_info (salads_id, order_info_id, quantity) values (4, 4, 3);
insert into salads_has_order_info (salads_id, order_info_id, quantity) values (5, 5, 10);
insert into salads_has_order_info (salads_id, order_info_id, quantity) values (6, 6, 8);
insert into salads_has_order_info (salads_id, order_info_id, quantity) values (7, 7, 4);
insert into salads_has_order_info (salads_id, order_info_id, quantity) values (8, 8, 4);
insert into salads_has_order_info (salads_id, order_info_id, quantity) values (9, 9, 9);
insert into salads_has_order_info (salads_id, order_info_id, quantity) values (10, 10, 4);

insert into drink_has_order_info (drink_id, order_info_id, quantity) values (1, 1, 3);
insert into drink_has_order_info (drink_id, order_info_id, quantity) values (2, 2, 8);
insert into drink_has_order_info (drink_id, order_info_id, quantity) values (3, 3, 3);
insert into drink_has_order_info (drink_id, order_info_id, quantity) values (4, 4, 5);
insert into drink_has_order_info (drink_id, order_info_id, quantity) values (5, 5, 4);
insert into drink_has_order_info (drink_id, order_info_id, quantity) values (6, 6, 8);
insert into drink_has_order_info (drink_id, order_info_id, quantity) values (7, 7, 3);
insert into drink_has_order_info (drink_id, order_info_id, quantity) values (8, 8, 2);
insert into drink_has_order_info (drink_id, order_info_id, quantity) values (9, 9, 9);
insert into drink_has_order_info (drink_id, order_info_id, quantity) values (10, 10, 5);

