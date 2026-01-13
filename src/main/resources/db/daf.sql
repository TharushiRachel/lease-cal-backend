-- =========================================================
-- APPLICATION TABLES LINKED TO COMPREHENSIVE LEAD
-- Note: This schema uses comp_lead_id to link with t_comp_lead table
-- =========================================================

-- =========================================================
-- 1. INDIVIDUAL APPLICANT DETAILS
-- =========================================================
CREATE TABLE applicant_individual (
    applicant_id NUMBER PRIMARY KEY,
    comp_lead_id NUMBER,
    full_name VARCHAR2(255),
    date_of_birth DATE,
    nic_passport_no VARCHAR2(50),
    permanent_address VARCHAR2(2000),
    communication_address VARCHAR2(2000),
    residence_type VARCHAR2(20), -- 'OWN','RENTED','OTHER'
    years_at_address NUMBER,
    nationality VARCHAR2(50),
    is_pep VARCHAR2(1), -- 'Y' or 'N'
    pep_details VARCHAR2(2000),
    civil_status VARCHAR2(50),
    dependents_count NUMBER,
    highest_qualification VARCHAR2(255),
    professional_qualification VARCHAR2(255),
    CREATED_DATE DATE,
    CREATED_BY VARCHAR2(100),
    MODIFIED_DATE DATE,
    MODIFIED_BY VARCHAR2(100),
    CONSTRAINT fk_applicant_individual_lead FOREIGN KEY (comp_lead_id) REFERENCES t_comp_lead(comp_lead_id)
);

-- Sequence for applicant_individual
CREATE SEQUENCE seq_applicant_individual_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Trigger for applicant_individual
CREATE OR REPLACE TRIGGER trg_applicant_individual_id
    BEFORE INSERT ON applicant_individual
    FOR EACH ROW
BEGIN
    IF :NEW.applicant_id IS NULL THEN
        :NEW.applicant_id := seq_applicant_individual_id.NEXTVAL;
    END IF;
END;
/

-- =========================================================
-- 2. COMPANY APPLICANT DETAILS
-- =========================================================
CREATE TABLE applicant_company (
    company_id NUMBER PRIMARY KEY,
    comp_lead_id NUMBER,
    name VARCHAR2(255),
    address VARCHAR2(2000),
    registration_no VARCHAR2(100),
    incorporation_date DATE,
    business_type VARCHAR2(255),
    constitution VARCHAR2(50), -- 'PRIVATE LTD','PUBLIC LTD','CORPORATION','OFF-SHORE'
    no_of_employees NUMBER,
    paid_up_capital NUMBER(18,2),
    issued_capital NUMBER(18,2),
    CREATED_DATE DATE,
    CREATED_BY VARCHAR2(100),
    MODIFIED_DATE DATE,
    MODIFIED_BY VARCHAR2(100),
    CONSTRAINT fk_applicant_company_lead FOREIGN KEY (comp_lead_id) REFERENCES t_comp_lead(comp_lead_id)
);

-- Sequence for applicant_company
CREATE SEQUENCE seq_applicant_company_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Trigger for applicant_company
CREATE OR REPLACE TRIGGER trg_applicant_company_id
    BEFORE INSERT ON applicant_company
    FOR EACH ROW
BEGIN
    IF :NEW.company_id IS NULL THEN
        :NEW.company_id := seq_applicant_company_id.NEXTVAL;
    END IF;
END;
/

-- =========================================================
-- 3. DIRECTORS / PARTNERS
-- =========================================================
CREATE TABLE company_director (
    director_id NUMBER PRIMARY KEY,
    company_id NUMBER,
    name VARCHAR2(255),
    address VARCHAR2(2000),
    nic_no VARCHAR2(20),
    age NUMBER,
    shareholding_percent NUMBER(5,2),
    CREATED_DATE DATE,
    CREATED_BY VARCHAR2(100),
    MODIFIED_DATE DATE,
    MODIFIED_BY VARCHAR2(100),
    CONSTRAINT fk_company_director_company FOREIGN KEY (company_id) REFERENCES applicant_company(company_id)
);

-- Sequence for company_director
CREATE SEQUENCE seq_company_director_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Trigger for company_director
CREATE OR REPLACE TRIGGER trg_company_director_id
    BEFORE INSERT ON company_director
    FOR EACH ROW
BEGIN
    IF :NEW.director_id IS NULL THEN
        :NEW.director_id := seq_company_director_id.NEXTVAL;
    END IF;
END;
/

-- =========================================================
-- 4. EMPLOYMENT DETAILS (FOR INDIVIDUALS)
-- =========================================================
CREATE TABLE employment_details (
    employment_id NUMBER PRIMARY KEY,
    applicant_id NUMBER,
    employer_name VARCHAR2(255),
    employment_type VARCHAR2(50),
    designation VARCHAR2(255),
    years_with_employer NUMBER,
    business_nature VARCHAR2(255),
    employer_address VARCHAR2(2000),
    office_phone VARCHAR2(50),
    avg_income_growth VARCHAR2(50), -- '>10%','>5%','<=5%','POSITIVE_ONE_YEAR','NEGATIVE_BOTH'
    CREATED_DATE DATE,
    CREATED_BY VARCHAR2(100),
    MODIFIED_DATE DATE,
    MODIFIED_BY VARCHAR2(100),
    CONSTRAINT fk_employment_applicant FOREIGN KEY (applicant_id) REFERENCES applicant_individual(applicant_id)
);

-- Sequence for employment_details
CREATE SEQUENCE seq_employment_details_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Trigger for employment_details
CREATE OR REPLACE TRIGGER trg_employment_details_id
    BEFORE INSERT ON employment_details
    FOR EACH ROW
BEGIN
    IF :NEW.employment_id IS NULL THEN
        :NEW.employment_id := seq_employment_details_id.NEXTVAL;
    END IF;
END;
/

-- =========================================================
-- 5. FACILITY REQUEST DETAILS
-- =========================================================
CREATE TABLE facility_request (
    facility_id NUMBER PRIMARY KEY,
    comp_lead_id NUMBER,
    type VARCHAR2(100),
    purpose VARCHAR2(2000),
    amount NUMBER(18,2),
    repayment_years NUMBER,
    repayment_type VARCHAR2(100),
    deduction_date VARCHAR2(2), -- '04' or '26'
    applicant_contribution NUMBER(18,2),
    advance_payment NUMBER(18,2),
    CREATED_DATE DATE,
    CREATED_BY VARCHAR2(100),
    MODIFIED_DATE DATE,
    MODIFIED_BY VARCHAR2(100),
    CONSTRAINT fk_facility_lead FOREIGN KEY (comp_lead_id) REFERENCES t_comp_lead(comp_lead_id)
);

-- Sequence for facility_request
CREATE SEQUENCE seq_facility_request_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Trigger for facility_request
CREATE OR REPLACE TRIGGER trg_facility_request_id
    BEFORE INSERT ON facility_request
    FOR EACH ROW
BEGIN
    IF :NEW.facility_id IS NULL THEN
        :NEW.facility_id := seq_facility_request_id.NEXTVAL;
    END IF;
END;
/

-- =========================================================
-- 6. ASSETS
-- =========================================================
CREATE TABLE assets (
    asset_id NUMBER PRIMARY KEY,
    comp_lead_id NUMBER,
    asset_type VARCHAR2(50), -- 'LAND_BUILDING','VEHICLE','SHARE_TBILL','INSURANCE','OTHER'
    description VARCHAR2(2000),
    owner_name VARCHAR2(255),
    market_value NUMBER(18,2),
    mortgage_details VARCHAR2(2000),
    CREATED_DATE DATE,
    CREATED_BY VARCHAR2(100),
    MODIFIED_DATE DATE,
    MODIFIED_BY VARCHAR2(100),
    CONSTRAINT fk_assets_lead FOREIGN KEY (comp_lead_id) REFERENCES t_comp_lead(comp_lead_id)
);

-- Sequence for assets
CREATE SEQUENCE seq_assets_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Trigger for assets
CREATE OR REPLACE TRIGGER trg_assets_id
    BEFORE INSERT ON assets
    FOR EACH ROW
BEGIN
    IF :NEW.asset_id IS NULL THEN
        :NEW.asset_id := seq_assets_id.NEXTVAL;
    END IF;
END;
/

-- =========================================================
-- 7. LIABILITIES
-- =========================================================
CREATE TABLE liabilities (
    liability_id NUMBER PRIMARY KEY,
    comp_lead_id NUMBER,
    creditor_name VARCHAR2(255),
    reference_no VARCHAR2(100),
    purpose VARCHAR2(255),
    amount_borrowed NUMBER(18,2),
    balance_payable NUMBER(18,2),
    repayment_period_months NUMBER,
    security VARCHAR2(2000),
    CREATED_DATE DATE,
    CREATED_BY VARCHAR2(100),
    MODIFIED_DATE DATE,
    MODIFIED_BY VARCHAR2(100),
    CONSTRAINT fk_liabilities_lead FOREIGN KEY (comp_lead_id) REFERENCES t_comp_lead(comp_lead_id)
);

-- Sequence for liabilities
CREATE SEQUENCE seq_liabilities_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Trigger for liabilities
CREATE OR REPLACE TRIGGER trg_liabilities_id
    BEFORE INSERT ON liabilities
    FOR EACH ROW
BEGIN
    IF :NEW.liability_id IS NULL THEN
        :NEW.liability_id := seq_liabilities_id.NEXTVAL;
    END IF;
END;
/

-- =========================================================
-- 8. BANK ACCOUNTS
-- =========================================================
CREATE TABLE bank_accounts (
    bank_account_id NUMBER PRIMARY KEY,
    comp_lead_id NUMBER,
    bank_name VARCHAR2(255),
    branch_name VARCHAR2(255),
    account_no VARCHAR2(50),
    account_type VARCHAR2(50),
    date_opened DATE,
    balance NUMBER(18,2),
    holder_name VARCHAR2(255),
    CREATED_DATE DATE,
    CREATED_BY VARCHAR2(100),
    MODIFIED_DATE DATE,
    MODIFIED_BY VARCHAR2(100),
    CONSTRAINT fk_bank_accounts_lead FOREIGN KEY (comp_lead_id) REFERENCES t_comp_lead(comp_lead_id)
);

-- Sequence for bank_accounts
CREATE SEQUENCE seq_bank_accounts_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Trigger for bank_accounts
CREATE OR REPLACE TRIGGER trg_bank_accounts_id
    BEFORE INSERT ON bank_accounts
    FOR EACH ROW
BEGIN
    IF :NEW.bank_account_id IS NULL THEN
        :NEW.bank_account_id := seq_bank_accounts_id.NEXTVAL;
    END IF;
END;
/

-- =========================================================
-- 9. INCOME & EXPENDITURE
-- =========================================================
CREATE TABLE income_expenditure (
    record_id NUMBER PRIMARY KEY,
    applicant_id NUMBER,
    income_source VARCHAR2(100),
    monthly_income NUMBER(18,2),
    expense_type VARCHAR2(100),
    monthly_expense NUMBER(18,2),
    CREATED_DATE DATE,
    CREATED_BY VARCHAR2(100),
    MODIFIED_DATE DATE,
    MODIFIED_BY VARCHAR2(100),
    CONSTRAINT fk_income_expenditure_applicant FOREIGN KEY (applicant_id) REFERENCES applicant_individual(applicant_id)
);

-- Sequence for income_expenditure
CREATE SEQUENCE seq_income_expenditure_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Trigger for income_expenditure
CREATE OR REPLACE TRIGGER trg_income_expenditure_id
    BEFORE INSERT ON income_expenditure
    FOR EACH ROW
BEGIN
    IF :NEW.record_id IS NULL THEN
        :NEW.record_id := seq_income_expenditure_id.NEXTVAL;
    END IF;
END;
/

-- =========================================================
-- 10. TAX DETAILS
-- =========================================================
CREATE TABLE income_tax (
    tax_id NUMBER PRIMARY KEY,
    comp_lead_id NUMBER,
    year_of_assessment NUMBER(4),
    assessable_income NUMBER(18,2),
    taxable_income NUMBER(18,2),
    tax_paid NUMBER(18,2),
    remarks VARCHAR2(2000),
    CREATED_DATE DATE,
    CREATED_BY VARCHAR2(100),
    MODIFIED_DATE DATE,
    MODIFIED_BY VARCHAR2(100),
    CONSTRAINT fk_income_tax_lead FOREIGN KEY (comp_lead_id) REFERENCES t_comp_lead(comp_lead_id)
);

-- Sequence for income_tax
CREATE SEQUENCE seq_income_tax_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Trigger for income_tax
CREATE OR REPLACE TRIGGER trg_income_tax_id
    BEFORE INSERT ON income_tax
    FOR EACH ROW
BEGIN
    IF :NEW.tax_id IS NULL THEN
        :NEW.tax_id := seq_income_tax_id.NEXTVAL;
    END IF;
END;
/

-- =========================================================
-- 11. INSURANCE CONSENT
-- =========================================================
CREATE TABLE insurance_consent (
    insurance_id NUMBER PRIMARY KEY,
    comp_lead_id NUMBER,
    consent VARCHAR2(1), -- 'Y' or 'N'
    loan_insurance_required VARCHAR2(1), -- 'Y' or 'N'
    insurance_amount NUMBER(18,2),
    insurance_company VARCHAR2(255),
    CREATED_DATE DATE,
    CREATED_BY VARCHAR2(100),
    MODIFIED_DATE DATE,
    MODIFIED_BY VARCHAR2(100),
    CONSTRAINT fk_insurance_consent_lead FOREIGN KEY (comp_lead_id) REFERENCES t_comp_lead(comp_lead_id)
);

-- Sequence for insurance_consent
CREATE SEQUENCE seq_insurance_consent_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Trigger for insurance_consent
CREATE OR REPLACE TRIGGER trg_insurance_consent_id
    BEFORE INSERT ON insurance_consent
    FOR EACH ROW
BEGIN
    IF :NEW.insurance_id IS NULL THEN
        :NEW.insurance_id := seq_insurance_consent_id.NEXTVAL;
    END IF;
END;
/

-- =========================================================
-- CREATE INDEXES FOR PERFORMANCE
-- =========================================================
CREATE INDEX idx_applicant_individual_lead_id ON applicant_individual(comp_lead_id);
CREATE INDEX idx_applicant_company_lead_id ON applicant_company(comp_lead_id);
CREATE INDEX idx_company_director_company_id ON company_director(company_id);
CREATE INDEX idx_employment_applicant_id ON employment_details(applicant_id);
CREATE INDEX idx_facility_lead_id ON facility_request(comp_lead_id);
CREATE INDEX idx_assets_lead_id ON assets(comp_lead_id);
CREATE INDEX idx_liabilities_lead_id ON liabilities(comp_lead_id);
CREATE INDEX idx_bank_accounts_lead_id ON bank_accounts(comp_lead_id);
CREATE INDEX idx_income_expenditure_applicant_id ON income_expenditure(applicant_id);
CREATE INDEX idx_income_tax_lead_id ON income_tax(comp_lead_id);
CREATE INDEX idx_insurance_consent_lead_id ON insurance_consent(comp_lead_id);
