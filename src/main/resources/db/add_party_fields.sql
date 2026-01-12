-- Add consider_crib and consider_advance_analysis columns to t_comp_parties table
-- Run this script if the columns don't exist in your database

ALTER TABLE t_comp_parties
ADD (
    consider_crib VARCHAR2(5),
    consider_advance_analysis VARCHAR2(5)
);

-- Optional: Add comments to document the columns
COMMENT ON COLUMN t_comp_parties.consider_crib IS 'Flag to consider CRIB check (Y/N)';
COMMENT ON COLUMN t_comp_parties.consider_advance_analysis IS 'Flag to consider advance analysis (Y/N)';

