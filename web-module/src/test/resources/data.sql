/*Create Secure API User*/
INSERT INTO `user` (email, password, `role`) 
	VALUES ('api@test.com', '$2y$12$Y2OF1/j1OFoNTbq/p899TuFJ8EXQh4rnr3QfOc/aTTjDOKUPSHJCe', 'SECURE_API_USER');
/*Create Secure API User Details*/
INSERT INTO user_details (user_id, `name`, surname, patronymic, address, phone) 
	VALUES (SELECT id FROM `user` WHERE `user`.email='api@test.com', 'Name', 'Surname', 'Patronymic', 'Address', '+375290000000');

/*Create Sale User*/
INSERT INTO `user` (email, password, `role`) 
	VALUES ('sale@test.com', 'pass', 'SALE_USER');
/*Create Sale User Details*/
INSERT INTO user_details (user_id, `name`, surname, patronymic, address, phone) 
	VALUES (SELECT id FROM `user` WHERE `user`.email='sale@test.com', 'Name', 'Surname', 'Patronymic', 'Address', '+375290000000');

/*Create Customer User*/
INSERT INTO `user` (email, password, `role`) 
	VALUES ('customer@test.com', 'pass', 'CUSTOMER_USER');
/*Create Customer User Details*/
INSERT INTO user_details (user_id, `name`, surname, patronymic, address, phone) 
	VALUES (SELECT id FROM `user` WHERE `user`.email='customer@test.com', 'Name', 'Surname', 'Patronymic', 'Address', '+375290000000');

/*Create Article 1*/
INSERT INTO article (user_id, title, `date`, content) 
VALUES (SELECT id FROM `user` WHERE `user`.email='sale@test.com', 'article test', '2020-04-29 13:28:53.0', 'Some text');
/*Create Article 2*/
INSERT INTO article (user_id, title, `date`, content) 
VALUES (SELECT id FROM `user` WHERE `user`.email='sale@test.com', 'article test 2', '2020-04-29 13:28:53.0', 'Some text 2');

/*Creeate Comment to article 1 by customet user*/
INSERT INTO comment (user_id, comment, creation_date, is_visible, article_id) 
	VALUES (SELECT id FROM `user` WHERE `user`.email='customer@test.com', 'comment', '2020-04-29 19:26:34.0', true, SELECT id FROM article WHERE article.title='article test');
