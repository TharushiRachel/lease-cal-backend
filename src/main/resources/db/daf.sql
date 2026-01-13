-- =========================================================
-- 1. CORE APPLICATION TABLE
-- =========================================================
CREATE TABLE application (
    application_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    application_type ENUM('INDIVIDUAL', 'COMPANY') NOT NULL,
    branch_code VARCHAR(50),
    branch_name VARCHAR(255),
    application_date DATE,
    status ENUM('DRAFT','SUBMITTED','APPROVED','REJECTED') DEFAULT 'DRAFT',
    created_by VARCHAR(100),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================================
-- 2. INDIVIDUAL APPLICANT DETAILS
-- =========================================================
CREATE TABLE applicant_individual (
    applicant_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    application_id BIGINT,
    full_name VARCHAR(255),
    date_of_birth DATE,
    nic_passport_no VARCHAR(50),
    permanent_address TEXT,
    communication_address TEXT,
    residence_type ENUM('OWN','RENTED','OTHER'),
    years_at_address INT,
    nationality VARCHAR(50),
    is_pep BOOLEAN,
    pep_details TEXT,
    civil_status VARCHAR(50),
    dependents_count INT,
    highest_qualification VARCHAR(255),
    professional_qualification VARCHAR(255),
    FOREIGN KEY (application_id) REFERENCES application(application_id)
);

-- =========================================================
-- 3. COMPANY APPLICANT DETAILS
-- =========================================================
CREATE TABLE applicant_company (
    company_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    application_id BIGINT,
    name VARCHAR(255),
    address TEXT,
    registration_no VARCHAR(100),
    incorporation_date DATE,
    business_type VARCHAR(255),
    constitution ENUM('PRIVATE LTD','PUBLIC LTD','CORPORATION','OFF-SHORE'),
    no_of_employees INT,
    paid_up_capital DECIMAL(18,2),
    issued_capital DECIMAL(18,2),
    FOREIGN KEY (application_id) REFERENCES application(application_id)
);

-- =========================================================
-- 4. DIRECTORS / PARTNERS
-- =========================================================
CREATE TABLE company_director (
    director_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    company_id BIGINT,
    name VARCHAR(255),
    address TEXT,
    nic_no VARCHAR(20),
    age INT,
    shareholding_percent DECIMAL(5,2),
    FOREIGN KEY (company_id) REFERENCES applicant_company(company_id)
);

-- =========================================================
-- 5. EMPLOYMENT DETAILS (FOR INDIVIDUALS)
-- =========================================================
CREATE TABLE employment_details (
    employment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    applicant_id BIGINT,
    employer_name VARCHAR(255),
    employment_type VARCHAR(50),
    designation VARCHAR(255),
    years_with_employer INT,
    business_nature VARCHAR(255),
    employer_address TEXT,
    office_phone VARCHAR(50),
    avg_income_growth ENUM('>10%','>5%','<=5%','POSITIVE_ONE_YEAR','NEGATIVE_BOTH'),
    FOREIGN KEY (applicant_id) REFERENCES applicant_individual(applicant_id)
);

-- =========================================================
-- 6. FACILITY REQUEST DETAILS
-- =========================================================
CREATE TABLE facility_request (
    facility_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    application_id BIGINT,
    type VARCHAR(100),
    purpose TEXT,
    amount DECIMAL(18,2),
    repayment_years INT,
    repayment_type VARCHAR(100),
    deduction_date ENUM('04','26'),
    applicant_contribution DECIMAL(18,2),
    advance_payment DECIMAL(18,2),
    FOREIGN KEY (application_id) REFERENCES application(application_id)
);

-- =========================================================
-- 7. ASSETS
-- =========================================================
CREATE TABLE assets (
    asset_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    application_id BIGINT,
    asset_type ENUM('LAND_BUILDING','VEHICLE','SHARE_TBILL','INSURANCE','OTHER'),
    description TEXT,
    owner_name VARCHAR(255),
    market_value DECIMAL(18,2),
    mortgage_details TEXT,
    FOREIGN KEY (application_id) REFERENCES application(application_id)
);

-- =========================================================
-- 8. LIABILITIES
-- =========================================================
CREATE TABLE liabilities (
    liability_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    application_id BIGINT,
    creditor_name VARCHAR(255),
    reference_no VARCHAR(100),
    purpose VARCHAR(255),
    amount_borrowed DECIMAL(18,2),
    balance_payable DECIMAL(18,2),
    repayment_period_months INT,
    security TEXT,
    FOREIGN KEY (application_id) REFERENCES application(application_id)
);

-- =========================================================
-- 9. BANK ACCOUNTS
-- =========================================================
CREATE TABLE bank_accounts (
    bank_account_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    application_id BIGINT,
    bank_name VARCHAR(255),
    branch_name VARCHAR(255),
    account_no VARCHAR(50),
    account_type VARCHAR(50),
    date_opened DATE,
    balance DECIMAL(18,2),
    holder_name VARCHAR(255),
    FOREIGN KEY (application_id) REFERENCES application(application_id)
);

-- =========================================================
-- 10. INCOME & EXPENDITURE
-- =========================================================
CREATE TABLE income_expenditure (
    record_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    applicant_id BIGINT,
    income_source VARCHAR(100),
    monthly_income DECIMAL(18,2),
    expense_type VARCHAR(100),
    monthly_expense DECIMAL(18,2),
    FOREIGN KEY (applicant_id) REFERENCES applicant_individual(applicant_id)
);

-- =========================================================
-- 11. TAX DETAILS
-- =========================================================
CREATE TABLE income_tax (
    tax_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    application_id BIGINT,
    year_of_assessment YEAR,
    assessable_income DECIMAL(18,2),
    taxable_income DECIMAL(18,2),
    tax_paid DECIMAL(18,2),
    remarks TEXT,
    FOREIGN KEY (application_id) REFERENCES application(application_id)
);

-- =========================================================
-- 12. INSURANCE CONSENT
-- =========================================================
CREATE TABLE insurance_consent (
    insurance_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    application_id BIGINT,
    consent BOOLEAN,
    loan_insurance_required BOOLEAN,
    insurance_amount DECIMAL(18,2),
    insurance_company VARCHAR(255),
    FOREIGN KEY (application_id) REFERENCES application(application_id)
);
