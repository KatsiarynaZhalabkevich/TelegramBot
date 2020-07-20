DELETE FROM city;
INSERT INTO city (id,name, info) VALUES
        (1,'Брест', 'Посетите брестскую крепость'),
        (2,'Пекин', 'Попробуйте утку'),
        (3,'Барселона', 'Архитектура Гауди вас покорит'),
        (4,'Слуцк', 'Отличное место для шопинга! Купите себе слуцкий пояс!');

ALTER SEQUENCE hibernate_sequence restart WITH 1;