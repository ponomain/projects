CREATE TABLE IF NOT EXISTS address_branch
(
    id             UUID    default gen_random_uuid() PRIMARY KEY,
    country        VARCHAR(255) NOT NULL,
    region         VARCHAR(255) NOT NULL,
    district       VARCHAR(255) NOT NULL,
    location       VARCHAR(255) NOT NULL,
    street         VARCHAR(255) NOT NULL,
    house_num      VARCHAR(255) NOT NULL,
    litter         VARCHAR(10),
    building       VARCHAR(10),
    room           VARCHAR(10),
    apartment      VARCHAR(10),
    bank_branch_id UUID,
    deleted        BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS bank_branch
(
    id                UUID    default gen_random_uuid() PRIMARY KEY,
    department_number VARCHAR(255) NOT NULL,
    head_branch_id    UUID,
    name              VARCHAR(255) NOT NULL,
    address_branch_id        UUID,
    deleted           BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (head_branch_id) REFERENCES bank_branch (id),
    FOREIGN KEY (address_branch_id) REFERENCES address_branch (id)
);

CREATE TABLE IF NOT EXISTS bank_account
(
    id             UUID    default gen_random_uuid() PRIMARY KEY,
    number         VARCHAR(255)          NOT NULL UNIQUE,
    bank_card_id   UUID,
    bank_client_id UUID,
    opening_date   DATE                  NOT NULL,
    closing_date   DATE,
    balance        DECIMAL(36, 2)        NOT NULL,
    currency       VARCHAR(3)            NOT NULL,
    bank_branch_id UUID,
    blocked        BOOLEAN DEFAULT FALSE NOT NULL,
    blocking_date  DATE,
    deleted        BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS card_tariff
(
    id                         UUID    default gen_random_uuid() PRIMARY KEY,
    name                       VARCHAR(255)                                                                      NOT NULL,
    start_date                 DATE                                                                              NOT NULL,
    end_date                   DATE                                                                              NOT NULL,
    subscription_amount        DECIMAL(36, 2)                                                                    NOT NULL,
    subscription_periodicity   VARCHAR(255) check ( subscription_periodicity IN ('YEARLY', 'MONTHLY', 'WEEKLY')) NOT NULL,
    cash_withdrawal_limit_bank DECIMAL(36, 2)                                                                    NOT NULL,
    cash_withdrawal_limit_atm  DECIMAL(36, 2)                                                                    NOT NULL,
    transfer_limit             DECIMAL(36, 2)                                                                    NOT NULL,
    currency                   VARCHAR(3)                                                                        NOT NULL,
    deleted                    BOOLEAN DEFAULT FALSE
);


CREATE TABLE IF NOT EXISTS bank_service
(
    id                           UUID    default gen_random_uuid() PRIMARY KEY,
    name                         VARCHAR(255)                                                                       NOT NULL,
    subscription_amount          DECIMAL(36, 2)                                                                     NOT NULL,
    subscription_periodicity     VARCHAR(255) check ( subscription_periodicity IN ('YEARLY', 'MONTHLY', 'WEEKLY') ) NOT NULL,
    currency                     VARCHAR(3)                                                                         NOT NULL,
    valid                        BOOLEAN DEFAULT TRUE                                                               NOT NULL,
    start_date                   DATE                                                                               NOT NULL,
    end_date                     DATE                                                                               NOT NULL,
    service_usage_information_id UUID,
    deleted                      BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS service_usage_information
(
    id                  UUID    default gen_random_uuid() PRIMARY KEY,
    bank_client_id      UUID,
    bank_service_id     UUID,
    starting_usage_date DATE NOT NULL,
    valid               BOOLEAN DEFAULT TRUE,
    deleted             BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (bank_service_id) REFERENCES bank_service (id)
);


CREATE TABLE IF NOT EXISTS bank_card
(
    id                      UUID    default gen_random_uuid() PRIMARY KEY,
    number                  VARCHAR(255)                                                                          NOT NULL,
    account_id              UUID,
    card_type               VARCHAR(255) check ( card_type in ('DEBIT', 'CREDIT') )                               NOT NULL,
    date_of_issue           DATE                                                                                  NOT NULL,
    validity                DATE                                                                                  NOT NULL,
    pay_system              VARCHAR(255) check ( pay_system IN ('VISA', 'MASTERCARD', 'МИР', 'UNIONPAY', 'JCF') ) NOT NULL,
    tariff_id               UUID,
    ccv                     varchar(5)                                                                            NOT NULL,
    pin_code                VARCHAR(4)                                                                            NOT NULL,
    bank_service_id         UUID,
    blocked                 BOOLEAN DEFAULT FALSE,
    temporary_blocking_date DATE    DEFAULT NULL,
    permanent_blocking_date DATE    DEFAULT NULL,
    deleted                 BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (tariff_id) REFERENCES card_tariff (id),
    FOREIGN KEY (bank_service_id) references bank_service (id)
);

ALTER TABLE bank_service
    ADD FOREIGN KEY (service_usage_information_id) references service_usage_information (id);

ALTER TABLE address_branch
    ADD FOREIGN KEY (bank_branch_id) references bank_branch (id);

ALTER TABLE bank_account
    ADD FOREIGN KEY (bank_card_id) REFERENCES bank_card (id);

ALTER TABLE bank_card
    ADD FOREIGN KEY (account_id) REFERENCES bank_account (id);

ALTER TABLE bank_account
    ADD FOREIGN KEY (bank_branch_id) REFERENCES bank_branch (id);

