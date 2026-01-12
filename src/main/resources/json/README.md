# API Payload Examples

This folder contains example JSON payloads for the Lead Management API endpoints.

## Files

### 1. saveLeadWithChildren.json
**Endpoint:** `POST /api/leads`

Example payload for creating a lead with parties, identifications, and addresses.

**Note:** Income sources and related parties should be saved separately using their respective endpoints.

---

### 2. saveIncomeSources.json
**Endpoint:** `POST /api/leads/parties/{partyId}/income-sources`

Example payload for saving income sources for a party.

**Path Parameter:**
- `partyId` - The ID of the party (e.g., 1)

**Request Body:** Array of income source objects

**Fields:**
- `incomeType` (String, required) - Type of income (e.g., "SALARY", "RENTAL", "BUSINESS")
- `considerForRepayment` (String, optional) - "Y" or "N"

**Example Usage:**
```http
POST /api/leads/parties/1/income-sources
Content-Type: application/json
```

---

### 3. saveRelatedParties.json
**Endpoint:** `POST /api/leads/{leadId}/related-parties`

Example payload for saving related parties for a lead.

**Path Parameter:**
- `leadId` - The ID of the lead (e.g., 1)

**Request Body:** Array of related party objects

**Fields:**
- `compLeadId` (Long, optional) - Lead ID (can be omitted if using path variable)
- `mainPartnerId` (Long, required) - ID of the main party
- `relatedPartnerId` (Long, required) - ID of the related party
- `relationshipDescription` (String, optional) - Description of the relationship
- `considerCrib` (String, optional) - "Y" or "N"
- `considerAdvanceAnalysis` (String, optional) - "Y" or "N"

**Example Usage:**
```http
POST /api/leads/1/related-parties
Content-Type: application/json
```

---

## Usage Notes

1. **Order of Operations:**
   - First: Save lead with parties using `saveLeadWithChildren.json`
   - Second: Save income sources for each party using `saveIncomeSources.json`
   - Third: Save related parties using `saveRelatedParties.json`

2. **Field Values:**
   - Boolean-like fields use "Y" or "N" (VARCHAR(5) in database)
   - Date fields use ISO format: "YYYY-MM-DD"
   - IDs are Long integers

3. **Testing:**
   - Use these JSON files with Postman, curl, or any REST client
   - Replace placeholder IDs with actual IDs from your database
   - Adjust field values according to your business requirements
