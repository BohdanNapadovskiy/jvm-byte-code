--Вывести все машины.
SELECT car.car_name, engine.eng_name,trans.tr_name,gearbox.gear_name FROM CAR as car
LEFT JOIN engine AS engine ON car.eng_id = engine.eng_id
LEFT JOIN transmission AS trans ON car.tr_id = trans.tr_id
LEFT JOIN gearbox AS gearbox ON car.gear_id = gearbox.gear_id

--Вывести все неиспользуемые детали.
SELECT car.car_name, engine.eng_name,trans.tr_name,gearbox.gear_name FROM CAR as car
FULL JOIN engine AS engine ON car.eng_id = engine.eng_id
FULL  JOIN transmission AS trans ON car.tr_id = trans.tr_id
FULL JOIN gearbox AS gearbox ON car.gear_id = gearbox.gear_id
WHERE car.car_id IS NULL
