CREATE TABLE IF NOT EXISTS address_client
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
    client_info_id UUID,
    deleted        BOOLEAN DEFAULT FALSE
);


CREATE TABLE IF NOT EXISTS identity_document
(
    id                UUID    default gen_random_uuid() PRIMARY KEY,
    client_info_id    UUID,
    serial            VARCHAR(255) NOT NULL,
    number            VARCHAR(255) NOT NULL,
    date_of_issue     DATE         NOT NULL,
    organization      VARCHAR(255) NOT NULL,
    organization_code VARCHAR(255) NOT NULL,
    validity_date     DATE         NOT NULL,
    deleted           BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS client_info
(
    id                   UUID    default gen_random_uuid() PRIMARY KEY,
    first_name           VARCHAR(100) NOT NULL,
    last_name            VARCHAR(100) NOT NULL,
    patronymic           VARCHAR(100) NOT NULL,
    phone_number         VARCHAR(30)  NOT NULL UNIQUE,
    birth_date           DATE         NOT NULL,
    email                VARCHAR(100) NOT NULL UNIQUE,
    inn                  VARCHAR(30)  NOT NULL UNIQUE,
    resident             BOOLEAN,
    deleted              BOOLEAN DEFAULT FALSE,
    identity_document_id UUID,
    address_client_id    UUID
);

ALTER TABLE address_client
    ADD FOREIGN KEY (client_info_id) references client_info (id);

ALTER TABLE identity_document
    ADD FOREIGN KEY (client_info_id) references client_info (id);

ALTER TABLE client_info
    ADD FOREIGN KEY (address_client_id) references address_client (id);

ALTER TABLE client_info
    ADD FOREIGN KEY (identity_document_id) references identity_document (id);



