-- Complete Database Schema with Sequences and Triggers
-- This script creates all tables, sequences, and triggers

-- ============================================
-- CREATE SEQUENCES
-- ============================================

CREATE SEQUENCE seq_comp_lead_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE seq_comp_party_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE seq_party_identification_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE seq_party_address_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE seq_related_party_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE seq_income_source_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- ============================================
-- CREATE TABLES
-- ============================================

CREATE TABLE t_comp_lead (
    comp_lead_id NUMBER PRIMARY KEY,
    lead_name VARCHAR2(255) NOT NULL,
    creation_type VARCHAR2(50),
    CREATED_DATE DATE,
    CREATED_BY VARCHAR2(100),
    MODIFIED_DATE DATE,
    MODIFIED_BY VARCHAR2(100)
);

CREATE TABLE t_comp_parties (
    comp_party_id NUMBER PRIMARY KEY,
    comp_lead_id NUMBER,
    party_type VARCHAR2(20),
    personal_name VARCHAR2(255) NOT NULL,
    email VARCHAR2(255),
    mobile_number VARCHAR2(20),
    date_of_birth DATE,
    civil_status VARCHAR2(20),
    preferred_branch VARCHAR2(100),
    CREATED_DATE DATE,
    CREATED_BY VARCHAR2(100),
    MODIFIED_DATE DATE,
    MODIFIED_BY VARCHAR2(100),
    CONSTRAINT fk_parties_lead FOREIGN KEY (comp_lead_id) REFERENCES t_comp_lead(comp_lead_id)
);

CREATE TABLE t_comp_party_identifications (
    identification_id NUMBER PRIMARY KEY,
    comp_party_id NUMBER,
    identification_type VARCHAR2(50) NOT NULL,
    identification_number VARCHAR2(100) NOT NULL,
    CREATED_DATE DATE,
    CREATED_BY VARCHAR2(100),
    MODIFIED_DATE DATE,
    MODIFIED_BY VARCHAR2(100),
    CONSTRAINT fk_identifications_party FOREIGN KEY (comp_party_id) REFERENCES t_comp_parties(comp_party_id)
);

CREATE TABLE t_comp_party_addresses (
    addresses_id NUMBER PRIMARY KEY,
    comp_party_id NUMBER,
    address VARCHAR2(1000),
    CONSTRAINT fk_addresses_party FOREIGN KEY (comp_party_id) REFERENCES t_comp_parties(comp_party_id)
);

CREATE TABLE t_comp_related_parties (
    related_party_id NUMBER PRIMARY KEY,
    comp_lead_id NUMBER,
    main_partner_id NUMBER,
    related_partner_id NUMBER,
    relationship_description VARCHAR2(500),
    consider_crib VARCHAR2(5),
    consider_advance_analysis VARCHAR2(5),
    CONSTRAINT fk_related_lead FOREIGN KEY (comp_lead_id) REFERENCES t_comp_lead(comp_lead_id),
    CONSTRAINT fk_related_main_party FOREIGN KEY (main_partner_id) REFERENCES t_comp_parties(comp_party_id),
    CONSTRAINT fk_related_related_party FOREIGN KEY (related_partner_id) REFERENCES t_comp_parties(comp_party_id)
);

CREATE TABLE t_comp_income_sources (
    income_source_id NUMBER PRIMARY KEY,
    comp_party_id NUMBER,
    income_type VARCHAR2(50) NOT NULL,
    consider_for_repayment VARCHAR2(5),
    CONSTRAINT fk_income_party FOREIGN KEY (comp_party_id) REFERENCES t_comp_parties(comp_party_id)
);

-- ============================================
-- CREATE TRIGGERS FOR AUTO-INCREMENT
-- ============================================

CREATE OR REPLACE TRIGGER trg_comp_lead_id
    BEFORE INSERT ON t_comp_lead
    FOR EACH ROW
BEGIN
    IF :NEW.comp_lead_id IS NULL THEN
        :NEW.comp_lead_id := seq_comp_lead_id.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_comp_party_id
    BEFORE INSERT ON t_comp_parties
    FOR EACH ROW
BEGIN
    IF :NEW.comp_party_id IS NULL THEN
        :NEW.comp_party_id := seq_comp_party_id.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_party_identification_id
    BEFORE INSERT ON t_comp_party_identifications
    FOR EACH ROW
BEGIN
    IF :NEW.identification_id IS NULL THEN
        :NEW.identification_id := seq_party_identification_id.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_party_address_id
    BEFORE INSERT ON t_comp_party_addresses
    FOR EACH ROW
BEGIN
    IF :NEW.addresses_id IS NULL THEN
        :NEW.addresses_id := seq_party_address_id.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_related_party_id
    BEFORE INSERT ON t_comp_related_parties
    FOR EACH ROW
BEGIN
    IF :NEW.related_party_id IS NULL THEN
        :NEW.related_party_id := seq_related_party_id.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_income_source_id
    BEFORE INSERT ON t_comp_income_sources
    FOR EACH ROW
BEGIN
    IF :NEW.income_source_id IS NULL THEN
        :NEW.income_source_id := seq_income_source_id.NEXTVAL;
    END IF;
END;
/

-- ============================================
-- CREATE INDEXES (Optional but recommended)
-- ============================================

CREATE INDEX idx_parties_lead_id ON t_comp_parties(comp_lead_id);
CREATE INDEX idx_identifications_party_id ON t_comp_party_identifications(comp_party_id);
CREATE INDEX idx_addresses_party_id ON t_comp_party_addresses(comp_party_id);
CREATE INDEX idx_related_lead_id ON t_comp_related_parties(comp_lead_id);
CREATE INDEX idx_income_party_id ON t_comp_income_sources(comp_party_id);

