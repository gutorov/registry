CREATE TABLE farmer
(
    id                                     BIGSERIAL PRIMARY KEY,
    organisation_name                      VARCHAR(128) NOT NULL,
    legal_form                             VARCHAR(64),
    taxpayer_identification_number_inn     BIGINT       NOT NULL,
    tax_registration_reason_code_kpp       BIGINT       NOT NULL,
    primary_state_registration_number_ogrn BIGINT       NOT NULL,
    district_registered_at_id              BIGINT,
    is_archived                            BOOLEAN     DEFAULT FALSE NOT NULL,
    registered_at                          TIMESTAMPTZ DEFAULT current_timestamp,
    updated_at                             TIMESTAMPTZ DEFAULT current_timestamp,

    CONSTRAINT fk_district_registered_at FOREIGN KEY (district_registered_at_id) REFERENCES district (id),

    CONSTRAINT inn_length_check CHECK (CHAR_LENGTH(CAST(taxpayer_identification_number_inn AS VARCHAR)) >= 10 AND
                                       CHAR_LENGTH(CAST(taxpayer_identification_number_inn AS VARCHAR)) <= 12),

    CONSTRAINT kpp_length_check CHECK (CHAR_LENGTH(CAST(tax_registration_reason_code_kpp AS VARCHAR)) = 9),

    CONSTRAINT ogrn_length_check CHECK (CHAR_LENGTH(CAST(primary_state_registration_number_ogrn AS VARCHAR)) = 13)
);

CREATE TABLE farmer_district
(
    farmer_id   BIGINT,
    district_id BIGINT,

    PRIMARY KEY (farmer_id, district_id),
    FOREIGN KEY (farmer_id) REFERENCES farmer (id),
    FOREIGN KEY (district_id) REFERENCES district (id)
);

CREATE INDEX idx_farmer_registered_at ON farmer (registered_at);

CREATE INDEX idx_farmer_district_registered_at_id ON farmer (district_registered_at_id);

CREATE INDEX idx_farmer_taxpayer_identification_number_inn ON farmer (taxpayer_identification_number_inn);

CREATE INDEX idx_farmer_organisation_name ON farmer (organisation_name);
