
--Написать запрос получение всех продуктов с типом "СЫР"
SELECT products.product_name,typeprod.type_name,products.expired_date,products.price FROM table_type AS typeprod 
LEFT JOIN table_product as products ON typeprod.type_id = products.type_id
WHERE (typeprod.type_name = 'Сыр');

--Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT products.product_name,products.type_id,products.expired_date,products.price FROM table_product as products
WHERE (products.product_name like '%мороженое%');

-- Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT products.product_name,products.type_id,products.expired_date,products.price FROM table_product as products
WHERE (products.expired_date > '2017-12-31 23:59:59');

--Написать запрос, который вывод самый дорогой продукт.
SELECT products.product_name,products.price  FROM table_product as products 
WHERE products.price = (SELECT MAX(price) FROM table_product);

-- Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT typeprod.type_name, COUNT(typeprod.type_name) as cout_type FROM table_type AS typeprod 
LEFT JOIN table_product as products ON typeprod.type_id = products.type_id
GROUP BY typeprod.type_name;

--Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT products.product_name,typeprod.type_name,products.expired_date,products.price 
FROM table_product AS products 
INNER JOIN table_type AS typeprod ON products.type_id = typeprod.type_id
WHERE (typeprod.type_name = 'Сыр' OR typeprod.type_name = 'Молоко');

--Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук. 
SELECT typeprod.type_name, COUNT(typeprod.type_name) as count_type
FROM table_type AS typeprod
INNER JOIN table_product AS products ON (typeprod.type_id = products.type_id)
GROUP BY type_name
HAVING count(type_name) < 10;

--Вывести все продукты и их тип.
SELECT typeprod.type_name,products.product_name,products.expired_date,products.price FROM table_product AS products
INNER JOIN table_type as typeprod ON typeprod.type_id = products.type_id;