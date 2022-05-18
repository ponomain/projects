INSERT INTO client_info (last_name, first_name, patronymic, birth_date, email, phone_number, inn, resident)
VALUES ('IVANOV', 'IVAN', 'IVANOVICH', '1971-02-20', 'ivanov@mail.com', '+79100223049', '123-681-552 52', false);
INSERT INTO client_info (last_name, first_name, patronymic, birth_date, email, phone_number, inn, resident)
VALUES ('PETROV', 'PETR', 'PETROVICH', '1986-06-01', 'petrov@mail.com', '+79228622953', '471-582-682 15', true);
INSERT INTO client_info (last_name, first_name, patronymic, birth_date, email, phone_number, inn, resident)
VALUES ('ARTEMOV', 'ARTEM', 'ARTEMOVICH', '1993-02-20', 'artem@mail.ru', '+7940208182', '042-657-373 90', false);
INSERT INTO client_info (last_name, first_name, patronymic, birth_date, email, phone_number, inn, resident)
VALUES ('SERGEEV', 'SERGEY', 'SERGEEVICH', '1966-12-13', 'sergey@rambler.ru', '+79206950172', '164-638-465 52',
        false);
INSERT INTO client_info (last_name, first_name, patronymic, birth_date, email, phone_number, inn, resident)
VALUES ('NICKOLAEV', 'NICKOLAY', 'NICKOLAEVICH', '2000-04-07', 'nick@gmail.com', '+79049167297', '254-561-352 54',
        true);

INSERT INTO address_client (country, region, district, location, street, house_num, litter, building, room, apartment)
values ('RUSSIA', 'MOSCOW REGION', 'MOSCKOVSKIY DISTRICT', 'MOSCOW', 'MOSCKOVSKAYA STREET', '8', null, '1', null,
        '32');
INSERT INTO address_client (country, region, district, location, street, house_num, litter, building, room, apartment)
values ('TAJIKISTAN', 'DUSHANBE REGION', 'DUSHANBE DISTRICT', 'DUSHANBE', 'DUSHANBE STREET', '2', null, '4', null,
        '52');
INSERT INTO address_client (country, region, district, location, street, house_num, litter, building, room, apartment)
values ('RUSSIA', 'TYUMEN REGION', 'TYUMEN DISTRICT', 'TYUMEN', 'TYUMENSKAYA STREET', '32', 'b', '5', null, '22');
INSERT INTO address_client (country, region, district, location, street, house_num, litter, building, room, apartment)
values ('RUSSIA', 'TULA REGION', 'TULA DISTRICT', 'TULA', 'TULA STREET', '55', null, '2', null, '55');
INSERT INTO address_client (country, region, district, location, street, house_num, litter, building, room, apartment)
values ('USA', 'CALIFORNIA REGION', 'CALIFORNIA DISTRICT', 'CALIFORNIA', 'CALIFORNIA STREET', '4', 'c', '1', null,
        '55');

INSERT INTO identity_document (client_info_id, serial, number, date_of_issue, organization, organization_code,
                               validity_date)
VALUES (null, 1714, 564295, '1992-12-10', 'МРО УФМС РОССИИ', '002-220', '2020-12-10');
INSERT INTO identity_document (client_info_id, serial, number, date_of_issue, organization, organization_code,
                               validity_date)
VALUES (null, 0401, 902192, '2002-11-01', 'DIA in AYNI', '330-100', '2032-11-01');
INSERT INTO identity_document (client_info_id, serial, number, date_of_issue, organization, organization_code,
                               validity_date)
VALUES (null, 5021, 256111, '2019-02-20', 'МРО УФМС РОССИИ', '265-559', '2039-02-20');
INSERT INTO identity_document (client_info_id, serial, number, date_of_issue, organization, organization_code,
                               validity_date)
VALUES (null, 9012, 591027, '1988-03-19', 'МРО УФМС РОССИИ', '225-545', '2018-03-19');
INSERT INTO identity_document (client_info_id, serial, number, date_of_issue, organization, organization_code,
                               validity_date)
VALUES (null, 5671, 694321, '2021-04-07', 'UNiTED STATES DEPARTMENT OF STATE', '665-445', '2041-04-07');