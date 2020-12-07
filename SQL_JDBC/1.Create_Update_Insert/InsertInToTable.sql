INSERT INTO table_role (role_id, role_name) VALUES
	(1,'Root role'),
	(2,'user role');
	
INSERT INTO table_rules (rules_id, rules_name) VALUES
    (1,'Root rules'),
    (2,'user rules');

INSERT INTO table_rules_to_role (rules_id, role_id) VALUES
       (1,1),
       (1,2),
       (2,1);


INSERT INTO table_user (user_login, user_pasword, role_id) VALUES
      ('root','paswordRoot',1),
      ('User1','paswordUser1',2),
      ('User2', 'PaswordUser2',2);


INSERT INTO table_state (state_id, state_desc) VALUES
	(1,'заявка в работе'),
	(2,'заявка закрыта');


INSERT INTO table_category (category_id, category_desc) VALUES
	(1,'заявка по ремонту техники'),
	(2,'заявка на установку');


INSERT INTO table_item (item_desc, user_id, state_id,category_id) VALUES
	('Заявка № 1',1,2,2),
	('Заявка № 2',2,2,1);

INSERT INTO table_attachs (attach_date, file_name, item_id) VALUES
	('2017-12-11 09:15:36','picture.jpg',1),
	('2017-12-11 08:00:00','masage.txt',2);

INSERT INTO table_comments (comments_desc, item_id) VALUES
	('замечания к заявке',1),
	('',2);