
CREATE TABLE table_Role (
role_Id SERIAL PRIMARY KEY,
role_Name VARCHAR(100)
);

CREATE TABLE table_user (
user_Id SERIAL PRIMARY KEY,
user_Login varchar(50) not null,
user_Pasword varchar(50),
role_Id integer references table_Role(role_Id)  
);


CREATE TABLE table_Rules (
rules_Id SERIAL PRIMARY KEY,
rules_Name VARCHAR(100) 
);

CREATE TABLE table_Rules_to_Role (

rules_Id integer references table_Rules(rules_Id) ,
role_Id integer references table_Role(role_Id) 
);

CREATE TABLE table_State (
state_id SERIAL PRIMARY KEY,
state_desc VARCHAR(50)
);

CREATE TABLE table_Category (
Category_id SERIAL PRIMARY KEY,
Category_desc VARCHAR(50)
); 

CREATE TABLE table_Item (
item_Id SERIAL PRIMARY KEY UNIQUE,
item_Desc VARCHAR(200),
user_id integer references table_user(user_id) UNIQUE,
state_id integer references table_State(state_id),
category_id integer references table_Category(Category_id)
);

CREATE TABLE table_Comments (
comments_id SERIAL PRIMARY KEY,
comments_Desc VARCHAR(200),
item_id integer references table_Item(item_Id)
);


CREATE TABLE table_Attachs (
attachs_id SERIAL PRIMARY KEY,
attach_date timestamp,
file_name VARCHAR(255),
item_id integer references table_Item(item_Id)
);



