
INSERT INTO address_branch (country, region, district, location, street, house_num, litter, building, room, apartment)
values ('RUSSIA', 'SAINT PETERSBURG REGION', 'SAINT PETERSBURG DISTRICT', 'SAINT PETERSBURG',
        'PETERBURGSKAYA STREET',
        '30', null, null, '2', null);
INSERT INTO address_branch(country, region, district, location, street, house_num, litter, building, room, apartment)
values ('RUSSIA', 'MOSCOW REGION', 'MOSCKOVSKIY DISTRICT', 'MOSCOW', 'MOSCKOVSKAYA STREET', '8', null, null, null,
        '33');
INSERT INTO address_branch (country, region, district, location, street, house_num, litter, building, room, apartment)
values ('TAJIKISTAN', 'DUSHANBE REGION', 'DUSHANBE DISTRICT', 'DUSHANBE', 'DUSHANBE STREET', '2', null, null, null,
        '22');
INSERT INTO address_branch (country, region, district, location, street, house_num, litter, building, room, apartment)
values ('RUSSIA', 'TYUMEN REGION', 'TYUMEN DISTRICT', 'TYUMEN', 'TYUMENSKAYA STREET', '32', null, null, null, '332');
INSERT INTO address_branch (country, region, district, location, street, house_num, litter, building, room, apartment)
values ('RUSSIA', 'MOSCOW REGION', 'MOSCOW DISTRICT', 'MOSCOW', 'VAVILOVA STREET', '19', 'a', null, null, null);
INSERT INTO address_branch (country, region, district, location, street, house_num, litter, building, room, apartment)
values ('RUSSIA', 'TULA REGION', 'TULA DISTRICT', 'TULA', 'TULA STREET', '55', null, null, null, 33);
INSERT INTO address_branch (country, region, district, location, street, house_num, litter, building, room, apartment)
values ('RUSSIA', 'MOSCOW REGION', 'MOSCOW DISTRICT', 'MOSCOW', 'HUTORSKAYA STREET', '38', 'a', null, null, null);
INSERT INTO address_branch (country, region, district, location, street, house_num, litter, building, room, apartment)
values ('USA', 'CALIFORNIA REGION', 'CALIFORNIA DISTRICT', 'CALIFORNIA', 'CALIFORNIA STREET', '4', null, null, null,
        null);
INSERT INTO address_branch (country, region, district, location, street, house_num, litter, building, room, apartment)
values ('USA', 'NEW YORK REGION', 'NEW YORK DISTRICT', 'NEW YORK', 'NEW YORK STREET', '233', 'b', null, null,
        null);

INSERT INTO bank_branch (department_number, head_branch_id, name, address_branch_id)
VALUES (3115125, null, 'VTB MOSCOW', null);
INSERT INTO bank_branch (department_number, head_branch_id, name, address_branch_id)
VALUES (12515, null, 'ALIF BANK', null);
INSERT INTO bank_branch (department_number, head_branch_id, name, address_branch_id)
VALUES (34144, null, 'SBERBANK TYUMEN', null);
INSERT INTO bank_branch (department_number, head_branch_id, name, address_branch_id)
VALUES (35255, null, 'TINKOFF TULA', null);
INSERT INTO bank_branch (department_number, head_branch_id, name, address_branch_id)
VALUES (112424, null, 'BANK OF AMERICA', null);


INSERT INTO bank_account (number, bank_card_id, bank_client_id, opening_date, closing_date, balance, currency,
                          bank_branch_id)
VALUES (52555, null, null, '2000-10-31', '2005-10-31', 10000.00, 'RUB', null);
INSERT INTO bank_account (number, bank_card_id, bank_client_id, opening_date, closing_date, balance, currency,
                          bank_branch_id)
VALUES (15152, null, null, '2010-12-20', '2015-12-20', 550.00, 'USD', null);
INSERT INTO bank_account (number, bank_card_id, bank_client_id, opening_date, closing_date, balance, currency,
                          bank_branch_id)
VALUES (34347347, null, null, '2007-04-23', '2008-03-25', 300000.00, 'RUB', null);
INSERT INTO bank_account (number, bank_card_id, bank_client_id, opening_date, closing_date, balance, currency,
                          bank_branch_id)
VALUES (551251255, null, null, '2013-11-30', '2022-01-30', 5555555.00, 'RUB', null);
INSERT INTO bank_account (number, bank_card_id, bank_client_id, opening_date, closing_date, balance, currency,
                          bank_branch_id)
VALUES (125125, null, null, '2020-10-31', '2040-10-31', 6611233.00, 'USD', null);


INSERT INTO card_tariff (name, start_date, end_date, subscription_amount, subscription_periodicity,
                         cash_withdrawal_limit_bank, cash_withdrawal_limit_atm, transfer_limit, currency)
VALUES ('ECONOM', '2000-10-31', '2005-10-31', 100.00, 'YEARLY', 1000.00, 1000.00, 100.00, 'RUB');
INSERT INTO card_tariff (name, start_date, end_date, subscription_amount, subscription_periodicity,
                         cash_withdrawal_limit_bank, cash_withdrawal_limit_atm, transfer_limit, currency)
VALUES ('ECONOM', '2010-12-20', '2015-12-20', 5.00, 'WEEKLY', 5.00, 5.00, 5.00, 'USD');
INSERT INTO card_tariff (name, start_date, end_date, subscription_amount, subscription_periodicity,
                         cash_withdrawal_limit_bank, cash_withdrawal_limit_atm, transfer_limit, currency)
VALUES ('PREMIUM', '2007-04-23', '2008-03-25', 5000.00, 'YEARLY', 5000.00, 5000.00, 5000.00, 'RUB');
INSERT INTO card_tariff (name, start_date, end_date, subscription_amount, subscription_periodicity,
                         cash_withdrawal_limit_bank, cash_withdrawal_limit_atm, transfer_limit, currency)
VALUES ('STANDART', '2013-11-30', '2022-01-30', 500.00, 'MONTHLY', 50000.00, 50000.00, 50000.00, 'RUB');
INSERT INTO card_tariff (name, start_date, end_date, subscription_amount, subscription_periodicity,
                         cash_withdrawal_limit_bank, cash_withdrawal_limit_atm, transfer_limit, currency)
VALUES ('GOLDEN', '2020-10-31', '2040-10-31', 100.00, 'YEARLY', 1000000.00, 1000000.00, 10000.00, 'USD');


INSERT INTO bank_service (name, subscription_amount, subscription_periodicity, currency, start_date, end_date, valid)
VALUES ('APP VTB', 5.00, 'MONTHLY', 'RUB', '2000-10-31', '2005-10-31', false);
INSERT INTO bank_service (name, subscription_amount, subscription_periodicity, currency, start_date, end_date, valid)
VALUES ('APP ALIF', 10.00, 'YEARLY', 'USD', '2010-12-20', '2015-12-20', false);
INSERT INTO bank_service (name, subscription_amount, subscription_periodicity, currency, start_date, end_date, valid)
VALUES ('APP SBERBANK', 7.00, 'WEEKLY', 'RUB', '2007-04-23', '2008-03-25', false);
INSERT INTO bank_service (name, subscription_amount, subscription_periodicity, currency, start_date, end_date, valid)
VALUES ('APP TINKOFF', 3.00, 'YEARLY', 'RUB', '2013-11-30', '2022-01-30', true);
INSERT INTO bank_service (name, subscription_amount, subscription_periodicity, currency, start_date, end_date, valid)
VALUES ('APP AMERICA', 5.00, 'WEEKLY', 'USD', '2020-10-31', '2040-10-31', true);


INSERT INTO bank_card (number, account_id, card_type, date_of_issue, validity, pay_system, tariff_id, ccv, pin_code,
                       bank_service_id)
VALUES (5404360257096197, null, 'DEBIT', '2000-10-31', '2005-10-31', 'VISA', null, 333, 1772, null);
INSERT INTO bank_card (number, account_id, card_type, date_of_issue, validity, pay_system, tariff_id, ccv, pin_code,
                       bank_service_id)
VALUES (5404362880365437, null, 'DEBIT', '2010-12-20', '2015-12-20', 'UNIONPAY', null, 255, 7790, null);
INSERT INTO bank_card (number, account_id, card_type, date_of_issue, validity, pay_system, tariff_id, ccv, pin_code,
                       bank_service_id)
VALUES (4865746588183214, null, 'CREDIT', '2007-04-23', '2008-03-25', 'JCF', null, 257, 8902, null);
INSERT INTO bank_card (number, account_id, card_type, date_of_issue, validity, pay_system, tariff_id, ccv, pin_code,
                       bank_service_id)
VALUES (4393182716615383, null, 'DEBIT', '2013-11-30', '2022-01-30', 'VISA', null, 257, 2357, null);
INSERT INTO bank_card (number, account_id, card_type, date_of_issue, validity, pay_system, tariff_id, ccv, pin_code,
                       bank_service_id)
VALUES (4384899958827597, null, 'CREDIT', '2020-10-31', '2040-10-31', 'MASTERCARD', null, 777, 2552, null);
