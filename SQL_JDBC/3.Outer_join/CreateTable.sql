CREATE TABLE transmission (
tr_id SERIAL primary KEY,
tr_name VARCHAR(200) NOT NULL
);

INSERT INTO transmission (tr_name) VALUES ('Механическая'), ('Электрическая'), ('Комбинированная');


CREATE TABLE engine (
  eng_id   SERIAL PRIMARY KEY,
  eng_name VARCHAR(200) NOT NULL
);

INSERT INTO engine (eng_name) VALUES ('Бензиновый'), ('Дизельный'), ('Газовый'), ('Электрический');

CREATE TABLE gearbox (
  gear_id   SERIAL PRIMARY KEY,
  gear_name VARCHAR(200) NOT NULL
);

INSERT INTO gearbox (gear_name) VALUES ('Механическая'), ('Автоматическая'), ('Роторная');

CREATE TABLE car (
  car_id   SERIAL PRIMARY KEY,
  car_name VARCHAR(200) NOT NULL,
  tr_id integer references transmission(tr_id),
  eng_id integer references engine(eng_id) ,
  gear_id integer references gearbox(gear_id)
  
);

INSERT INTO car (car_name, tr_id, gear_id, eng_id)
VALUES ('Рено', 2,1, 1), ('Шкода', 1, 2, 4), ('Форд', 2, 1, 3), ('Мерседес', 2,3, 1), ('Ауди', 1,3,2),
   ('Ситроен', 2, 2, 1), ('Фольксваген', 1, 2, 2);
