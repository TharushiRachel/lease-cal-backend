# API Payload Examples

This folder contains example JSON payloads for the Lead Management and Application Management API endpoints.

## Lead Management API

### 1. saveLeadWithChildren.json
**Endpoint:** `POST /api/comprehensive-leads`

Example payload for creating a comprehensive lead with parties, identifications, and addresses.

**Note:** Income sources and related parties should be saved separately using their respective endpoints.

---

### 2. saveFacility.json / updateFacility.json
**Endpoint:** `POST /api/comprehensive-leads/facilities`

Example payloads for saving or updating a facility using the unified endpoint.

**Request Body:**
- `compFacilityId` (Long, required) - Use `0` for create, existing ID for update
- `compLeadId` (Long, required for create) - Lead ID when creating
- `facilityDescription` (String, optional)
- `requestedTenure` (Integer, optional)
- `leaseRental` (BigDecimal, optional)
- `processingFee` (BigDecimal, optional)
- `validityOfOffer` (Date, optional) - "YYYY-MM-DD"
- `leaseAmount` (BigDecimal, optional)
- `repaymentMode` (String, optional)
- `upfront` (BigDecimal, optional)
- `insurance` (BigDecimal, optional)
- `createdBy` (String, optional)
- `modifiedBy` (String, optional)

**Example Usage:**
```http
POST /api/comprehensive-leads/facilities
Content-Type: application/json
```

---

### 3. saveIncomeSources.json
**Endpoint:** `POST /api/comprehensive-leads/{compLeadId}/income-sources`

Example payload for saving income sources for a comprehensive lead.

**Path Parameter:**
- `compLeadId` - The ID of the comprehensive lead (e.g., 1)

**Request Body:** Array of income source objects

**Fields:**
- `party` (String, required) - Party identifier
- `incomeType` (String, required) - Type of income (e.g., "SALARY", "RENTAL", "BUSINESS")
- `considerForRepayment` (String, optional) - "Y" or "N"

**Example Usage:**
```http
POST /api/comprehensive-leads/1/income-sources
Content-Type: application/json
```

---

### 4. saveRelatedParties.json
**Endpoint:** `POST /api/comprehensive-leads/{compLeadId}/related-parties`

Example payload for saving related parties for a comprehensive lead.

**Path Parameter:**
- `compLeadId` - The ID of the comprehensive lead (e.g., 1)

**Request Body:** Array of related party objects

**Fields:**
- `compLeadId` (Long, optional) - Lead ID (can be omitted if using path variable)
- `mainPartnerId` (Long, required) - ID of the main party
- `relatedPartnerId` (Long, required) - ID of the related party
- `isRelationShip` (Boolean, optional) - true or false
- `relationshipDescription` (String, optional) - Description of the relationship
- `sharePresentage` (BigDecimal, optional) - Share percentage

**Example Usage:**
```http
POST /api/comprehensive-leads/1/related-parties
Content-Type: application/json
```

---

## Application Management API

### 5. saveApplication.json
**Endpoint:** `POST /api/applications`

Complete example payload for creating an application with all child entities (INDIVIDUAL type).

**Fields:**
- `compLeadId` (Long, required) - ID of the comprehensive lead
- `applicationType` (String, required) - "INDIVIDUAL" or "COMPANY"
- `branchCode` (String, optional) - Branch code
- `branchName` (String, optional) - Branch name
- `applicationDate` (Date, optional) - Application date in "YYYY-MM-DD" format
- `status` (String, optional) - "DRAFT", "SUBMITTED", "APPROVED", "REJECTED" (default: "DRAFT")
- `createdBy` (String, required) - User who created the application
- `applicantIndividuals` (Array, optional) - List of individual applicants
- `applicantCompanies` (Array, optional) - List of company applicants
- `facilityRequests` (Array, optional) - List of facility requests
- `assets` (Array, optional) - List of assets
- `liabilities` (Array, optional) - List of liabilities
- `bankAccounts` (Array, optional) - List of bank accounts
- `incomeTaxes` (Array, optional) - List of income tax records
- `insuranceConsents` (Array, optional) - List of insurance consents

**Example Usage:**
```http
POST /api/applications
Content-Type: application/json
```

---

### 6. saveApplicationMinimal.json
**Endpoint:** `POST /api/applications`

Minimal example payload for creating an application with only required fields and basic information.

**Use Case:** Quick application creation with minimal data.

---

### 7. saveApplicationCompany.json
**Endpoint:** `POST /api/applications`

Complete example payload for creating a COMPANY type application with all child entities.

**Fields:**
- Similar structure to `saveApplication.json` but with `applicantCompanies` instead of `applicantIndividuals`
- Includes company directors, assets, liabilities, etc.

---

## Usage Notes

1. **Order of Operations:**
   - First: Save comprehensive lead with parties using `saveLeadWithChildren.json`
   - Second: Save income sources using `saveIncomeSources.json`
   - Third: Save related parties using `saveRelatedParties.json`
   - Fourth: Save application using `saveApplication.json` or `saveApplicationCompany.json`

2. **Field Values:**
   - Boolean fields: Use `true` or `false` (will be converted to "Y"/"N" in database)
   - Date fields: Use ISO format "YYYY-MM-DD"
   - IDs: Use Long integers
   - Decimal values: Use BigDecimal format (e.g., 150000.00)

3. **Application Types:**
   - **INDIVIDUAL**: Use `applicantIndividuals` array (can include employment details and income expenditures)
   - **COMPANY**: Use `applicantCompanies` array (can include company directors)

4. **Testing:**
   - Use these JSON files with Postman, curl, or any REST client
   - Replace placeholder IDs (especially `compLeadId`) with actual IDs from your database
   - Adjust field values according to your business requirements
   - All nested arrays are optional - you can include only the data you need

5. **Boolean to String Conversion:**
   - Fields like `isPep`, `consent`, `loanInsuranceRequired` accept Boolean values in the request
   - They are automatically converted to "Y"/"N" strings in the database
   - Response will include both Boolean and String versions for compatibility