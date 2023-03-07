create table client (
	id_client INT NOT NULL AUTO_INCREMENT,
	firstname VARCHAR(50),
	lastname VARCHAR(50),
	company_address VARCHAR(50),
	company_name VARCHAR(50),
	phone_number VARCHAR(50),
	email_address VARCHAR(50),
	SIRET_number VARCHAR(50),
	PRIMARY KEY (`id_client`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


insert into client (id_client, firstname, lastname, company_address, company_name, phone_number, email_address, SIRET_number) values (1, 'Auguste', 'Minocchi', '6 Brickson Park Road', 'Bluejam', '4396805268', 'aminocchi0@discovery.com', '47-702-1527');
insert into client (id_client, firstname, lastname, company_address, company_name, phone_number, email_address, SIRET_number) values (2, 'Dominique', 'Tacey', '71 Comanche Trail', 'Yozio', '1099217243', 'dtacey1@google.de', '61-525-1056');
insert into client (id_client, firstname, lastname, company_address, company_name, phone_number, email_address, SIRET_number) values (3, 'Angelle', 'Denziloe', '0 Nevada Crossing', 'Twinder', '4759807810', 'adenziloe2@webmd.com', '73-809-1257');
insert into client (id_client, firstname, lastname, company_address, company_name, phone_number, email_address, SIRET_number) values (4, 'Berte', 'Perrinchief', '519 7th Trail', 'Zoombox', '3083613126', 'bperrinchief3@irs.gov', '69-441-1977');
insert into client (id_client, firstname, lastname, company_address, company_name, phone_number, email_address, SIRET_number) values (5, 'Brandyn', 'Le Fevre', '5256 Westend Hill', 'Edgeblab', '9803298026', 'blefevre4@mozilla.org', '07-683-0873');
insert into client (id_client, firstname, lastname, company_address, company_name, phone_number, email_address, SIRET_number) values (6, 'Clarence', 'Smuth', '50 Bartillon Plaza', 'Quimm', '8247544864', 'csmuth5@imgur.com', '92-062-0157');
insert into client (id_client, firstname, lastname, company_address, company_name, phone_number, email_address, SIRET_number) values (7, 'Melina', 'MacMenamin', '12683 6th Lane', 'Leenti', '7658158938', 'mmacmenamin6@ocn.ne.jp', '25-109-2814');
insert into client (id_client, firstname, lastname, company_address, company_name, phone_number, email_address, SIRET_number) values (8, 'Colette', 'Stuke', '128 Granby Way', 'Fanoodle', '8541451281', 'cstuke7@jalbum.net', '68-825-6339');
insert into client (id_client, firstname, lastname, company_address, company_name, phone_number, email_address, SIRET_number) values (9, 'Audrie', 'Gellion', '4553 Mayfield Trail', 'Wikivu', '2331807458', 'agellion8@china.com.cn', '94-934-4365');
insert into client (id_client, firstname, lastname, company_address, company_name, phone_number, email_address, SIRET_number) values (10, 'Courtnay', 'Tidy', '05391 Mandrake Court', 'Reallinks', '3533130942', 'ctidy9@abc.net.au', '27-368-5911');
insert into client (id_client, firstname, lastname, company_address, company_name, phone_number, email_address, SIRET_number) values (11, 'Evie', 'Scholfield', '2 Anzinger Crossing', 'Brainverse', '7756048636', 'escholfielda@abc.net.au', '50-304-3282');
insert into client (id_client, firstname, lastname, company_address, company_name, phone_number, email_address, SIRET_number) values (12, 'Thom', 'Dulling', '4 Judy Alley', 'Realmix', '9214266829', 'tdullingb@drupal.org', '04-404-1893');
insert into client (id_client, firstname, lastname, company_address, company_name, phone_number, email_address, SIRET_number) values (13, 'Joshua', 'Janes', '14 Gale Crossing', 'Buzzster', '7656432916', 'jjanesc@yandex.ru', '43-848-5586');


create table products (
	id_product INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(50),
	availability VARCHAR(50),
	price INT,
	stock INT,
	PRIMARY KEY (`id_product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;




insert into products (id_product, name, availability, price, stock) values (1, 'Wine - Pinot Noir Stoneleigh', true, 64, 74);
insert into products (id_product, name, availability, price, stock) values (2, 'Lid - 10,12,16 Oz', true, 15, 83);
insert into products (id_product, name, availability, price, stock) values (3, 'Brocolinni - Gaylan, Chinese', false, 91, 81);
insert into products (id_product, name, availability, price, stock) values (4, 'Bacardi Raspberry', true, 39, 100);
insert into products (id_product, name, availability, price, stock) values (5, 'Tart - Pecan Butter Squares', false, 80, 73);
insert into products (id_product, name, availability, price, stock) values (6, 'Cheese - Gouda Smoked', true, 32, 46);
insert into products (id_product, name, availability, price, stock) values (7, 'Tea - Mint', false, 55, 19);
insert into products (id_product, name, availability, price, stock) values (8, 'Flour - Corn, Fine', true, 84, 27);
insert into products (id_product, name, availability, price, stock) values (9, 'Dehydrated Kelp Kombo', true, 3, 62);
insert into products (id_product, name, availability, price, stock) values (10, 'Wine - Chablis 2003 Champs', false, 24, 90);
insert into products (id_product, name, availability, price, stock) values (11, 'Ezy Change Mophandle', false, 90, 61);
insert into products (id_product, name, availability, price, stock) values (12, 'Orange - Canned, Mandarin', true, 24, 55);
insert into products (id_product, name, availability, price, stock) values (13, 'Sausage - Andouille', true, 40, 3);
insert into products (id_product, name, availability, price, stock) values (14, 'Soup - Campbellschix Stew', true, 80, 62);
insert into products (id_product, name, availability, price, stock) values (15, 'Wine - Magnotta - Bel Paese White', false, 9, 60);
insert into products (id_product, name, availability, price, stock) values (16, 'Pepper - White, Whole', true, 71, 61);
insert into products (id_product, name, availability, price, stock) values (17, 'Carrots - Purple, Organic', true, 85, 17);
insert into products (id_product, name, availability, price, stock) values (18, 'Bar Nature Valley', false, 94, 90);


create table ordered_item (
    id_bidon INT NOT NULL AUTO_INCREMENT,
	id_order INT,
	client_id INT,
	product_id INT,
    quantity INT,
	total INT, 
	date DATE,
	PRIMARY KEY (`id_bidon`),
	KEY `lien_entre_commande_et_client` (`client_id`),
	KEY `produit_commande` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


ALTER TABLE `ordered_item`
	ADD CONSTRAINT `lien_entre_commande_et_client` FOREIGN KEY (`client_id`) REFERENCES `client` (`id_client`),
	ADD CONSTRAINT `produit_commande` FOREIGN KEY (`product_id`) REFERENCES `products` (`id_product`);
