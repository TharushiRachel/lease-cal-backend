-- Sequences for t_comp_lead table
CREATE SEQUENCE seq_comp_lead_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Sequences for t_comp_parties table
CREATE SEQUENCE seq_comp_party_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Sequences for t_comp_party_identifications table
CREATE SEQUENCE seq_party_identification_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Sequences for t_comp_party_addresses table
CREATE SEQUENCE seq_party_address_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Sequences for t_comp_related_parties table
CREATE SEQUENCE seq_related_party_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Sequences for t_comp_income_sources table
CREATE SEQUENCE seq_income_source_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Grant permissions (if needed)
-- GRANT SELECT ON seq_comp_lead_id TO your_user;
-- GRANT SELECT ON seq_comp_party_id TO your_user;
-- GRANT SELECT ON seq_party_identification_id TO your_user;
-- GRANT SELECT ON seq_party_address_id TO your_user;
-- GRANT SELECT ON seq_related_party_id TO your_user;
-- GRANT SELECT ON seq_income_source_id TO your_user;

