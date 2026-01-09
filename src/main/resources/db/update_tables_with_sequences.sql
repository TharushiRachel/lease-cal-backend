-- Update table definitions to use sequences with triggers
-- This script updates the tables to use sequences for primary key generation

-- Drop existing tables if they exist (be careful in production!)
-- DROP TABLE t_comp_income_sources CASCADE CONSTRAINTS;
-- DROP TABLE t_comp_related_parties CASCADE CONSTRAINTS;
-- DROP TABLE t_comp_party_addresses CASCADE CONSTRAINTS;
-- DROP TABLE t_comp_party_identifications CASCADE CONSTRAINTS;
-- DROP TABLE t_comp_parties CASCADE CONSTRAINTS;
-- DROP TABLE t_comp_lead CASCADE CONSTRAINTS;

-- Create tables with sequences (if creating fresh)
-- Note: If tables already exist, you may need to alter them or recreate

-- Create triggers to auto-increment primary keys using sequences

-- Trigger for t_comp_lead
CREATE OR REPLACE TRIGGER trg_comp_lead_id
    BEFORE INSERT ON t_comp_lead
    FOR EACH ROW
BEGIN
    IF :NEW.comp_lead_id IS NULL THEN
        :NEW.comp_lead_id := seq_comp_lead_id.NEXTVAL;
    END IF;
END;
/

-- Trigger for t_comp_parties
CREATE OR REPLACE TRIGGER trg_comp_party_id
    BEFORE INSERT ON t_comp_parties
    FOR EACH ROW
BEGIN
    IF :NEW.comp_party_id IS NULL THEN
        :NEW.comp_party_id := seq_comp_party_id.NEXTVAL;
    END IF;
END;
/

-- Trigger for t_comp_party_identifications
CREATE OR REPLACE TRIGGER trg_party_identification_id
    BEFORE INSERT ON t_comp_party_identifications
    FOR EACH ROW
BEGIN
    IF :NEW.identification_id IS NULL THEN
        :NEW.identification_id := seq_party_identification_id.NEXTVAL;
    END IF;
END;
/

-- Trigger for t_comp_party_addresses
CREATE OR REPLACE TRIGGER trg_party_address_id
    BEFORE INSERT ON t_comp_party_addresses
    FOR EACH ROW
BEGIN
    IF :NEW.addresses_id IS NULL THEN
        :NEW.addresses_id := seq_party_address_id.NEXTVAL;
    END IF;
END;
/

-- Trigger for t_comp_related_parties
CREATE OR REPLACE TRIGGER trg_related_party_id
    BEFORE INSERT ON t_comp_related_parties
    FOR EACH ROW
BEGIN
    IF :NEW.related_party_id IS NULL THEN
        :NEW.related_party_id := seq_related_party_id.NEXTVAL;
    END IF;
END;
/

-- Trigger for t_comp_income_sources
CREATE OR REPLACE TRIGGER trg_income_source_id
    BEFORE INSERT ON t_comp_income_sources
    FOR EACH ROW
BEGIN
    IF :NEW.income_source_id IS NULL THEN
        :NEW.income_source_id := seq_income_source_id.NEXTVAL;
    END IF;
END;
/

