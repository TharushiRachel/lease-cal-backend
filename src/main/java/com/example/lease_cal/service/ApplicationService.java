package com.example.lease_cal.service;

import com.example.lease_cal.dto.*;
import com.example.lease_cal.entity.*;
import com.example.lease_cal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ApplicationService {
    
    @Autowired
    private ApplicationRepository applicationRepository;
    
    @Autowired
    private ComprehensiveLeadRepository comprehensiveLeadRepository;
    
    @Autowired
    private ApplicantIndividualRepository applicantIndividualRepository;
    
    @Autowired
    private ApplicantCompanyRepository applicantCompanyRepository;
    
    @Autowired
    private CompanyDirectorRepository companyDirectorRepository;
    
    @Autowired
    private EmploymentDetailsRepository employmentDetailsRepository;
    
    @Autowired
    private FacilityRequestRepository facilityRequestRepository;
    
    @Autowired
    private AssetRepository assetRepository;
    
    @Autowired
    private LiabilityRepository liabilityRepository;
    
    @Autowired
    private BankAccountRepository bankAccountRepository;
    
    @Autowired
    private IncomeExpenditureRepository incomeExpenditureRepository;
    
    @Autowired
    private IncomeTaxRepository incomeTaxRepository;
    
    @Autowired
    private InsuranceConsentRepository insuranceConsentRepository;
    
    /**
     * Save application with all child entities
     * 
     * @param applicationRequestDTO The application request DTO containing all application data
     * @return ApplicationDTO with saved data including IDs
     */
    public ApplicationDTO saveApplication(ApplicationRequestDTO applicationRequestDTO) {
        // Find the comprehensive lead
        ComprehensiveLead comprehensiveLead = comprehensiveLeadRepository.findById(applicationRequestDTO.getCompLeadId())
                .orElseThrow(() -> new RuntimeException("Comprehensive Lead not found with id: " + applicationRequestDTO.getCompLeadId()));
        
        // Create and save Application entity
        Application application = new Application();
        application.setComprehensiveLead(comprehensiveLead);
        application.setApplicationType(applicationRequestDTO.getApplicationType());
        application.setBranchCode(applicationRequestDTO.getBranchCode());
        application.setBranchName(applicationRequestDTO.getBranchName());
        application.setApplicationDate(applicationRequestDTO.getApplicationDate());
        application.setStatus(applicationRequestDTO.getStatus() != null ? applicationRequestDTO.getStatus() : "DRAFT");
        application.setCreatedBy(applicationRequestDTO.getCreatedBy());
        application.setCreatedDate(LocalDate.now());
        
        // Save application first to get the ID
        application = applicationRepository.save(application);
        
        // Save applicant individuals with employment details and income expenditures
        if (applicationRequestDTO.getApplicantIndividuals() != null && !applicationRequestDTO.getApplicantIndividuals().isEmpty()) {
            List<ApplicantIndividual> applicantIndividuals = new ArrayList<>();
            for (ApplicantIndividualRequestDTO individualRequestDTO : applicationRequestDTO.getApplicantIndividuals()) {
                ApplicantIndividual applicantIndividual = convertToApplicantIndividualEntity(individualRequestDTO, application);
                applicantIndividuals.add(applicantIndividual);
            }
            application.setApplicantIndividuals(applicantIndividuals);
        }
        
        // Save applicant companies with directors
        if (applicationRequestDTO.getApplicantCompanies() != null && !applicationRequestDTO.getApplicantCompanies().isEmpty()) {
            List<ApplicantCompany> applicantCompanies = new ArrayList<>();
            for (ApplicantCompanyRequestDTO companyRequestDTO : applicationRequestDTO.getApplicantCompanies()) {
                ApplicantCompany applicantCompany = convertToApplicantCompanyEntity(companyRequestDTO, application);
                applicantCompanies.add(applicantCompany);
            }
            application.setApplicantCompanies(applicantCompanies);
        }
        
        // Save facility requests
        if (applicationRequestDTO.getFacilityRequests() != null && !applicationRequestDTO.getFacilityRequests().isEmpty()) {
            List<FacilityRequest> facilityRequests = new ArrayList<>();
            for (FacilityRequestRequestDTO facilityRequestDTO : applicationRequestDTO.getFacilityRequests()) {
                FacilityRequest facilityRequest = convertToFacilityRequestEntity(facilityRequestDTO, application);
                facilityRequests.add(facilityRequest);
            }
            application.setFacilityRequests(facilityRequests);
        }
        
        // Save assets
        if (applicationRequestDTO.getAssets() != null && !applicationRequestDTO.getAssets().isEmpty()) {
            List<Asset> assets = new ArrayList<>();
            for (AssetRequestDTO assetRequestDTO : applicationRequestDTO.getAssets()) {
                Asset asset = convertToAssetEntity(assetRequestDTO, application);
                assets.add(asset);
            }
            application.setAssets(assets);
        }
        
        // Save liabilities
        if (applicationRequestDTO.getLiabilities() != null && !applicationRequestDTO.getLiabilities().isEmpty()) {
            List<Liability> liabilities = new ArrayList<>();
            for (LiabilityRequestDTO liabilityRequestDTO : applicationRequestDTO.getLiabilities()) {
                Liability liability = convertToLiabilityEntity(liabilityRequestDTO, application);
                liabilities.add(liability);
            }
            application.setLiabilities(liabilities);
        }
        
        // Save bank accounts
        if (applicationRequestDTO.getBankAccounts() != null && !applicationRequestDTO.getBankAccounts().isEmpty()) {
            List<BankAccount> bankAccounts = new ArrayList<>();
            for (BankAccountRequestDTO bankAccountRequestDTO : applicationRequestDTO.getBankAccounts()) {
                BankAccount bankAccount = convertToBankAccountEntity(bankAccountRequestDTO, application);
                bankAccounts.add(bankAccount);
            }
            application.setBankAccounts(bankAccounts);
        }
        
        // Save income taxes
        if (applicationRequestDTO.getIncomeTaxes() != null && !applicationRequestDTO.getIncomeTaxes().isEmpty()) {
            List<IncomeTax> incomeTaxes = new ArrayList<>();
            for (IncomeTaxRequestDTO incomeTaxRequestDTO : applicationRequestDTO.getIncomeTaxes()) {
                IncomeTax incomeTax = convertToIncomeTaxEntity(incomeTaxRequestDTO, application);
                incomeTaxes.add(incomeTax);
            }
            application.setIncomeTaxes(incomeTaxes);
        }
        
        // Save insurance consents
        if (applicationRequestDTO.getInsuranceConsents() != null && !applicationRequestDTO.getInsuranceConsents().isEmpty()) {
            List<InsuranceConsent> insuranceConsents = new ArrayList<>();
            for (InsuranceConsentRequestDTO insuranceConsentRequestDTO : applicationRequestDTO.getInsuranceConsents()) {
                InsuranceConsent insuranceConsent = convertToInsuranceConsentEntity(insuranceConsentRequestDTO, application);
                insuranceConsents.add(insuranceConsent);
            }
            application.setInsuranceConsents(insuranceConsents);
        }
        
        // Save application again with all child entities (cascade will save them)
        application = applicationRepository.save(application);
        
        // Convert to DTO and return
        return convertToApplicationDTO(application);
    }
    
    // Conversion methods from Request DTOs to Entities
    private ApplicantIndividual convertToApplicantIndividualEntity(ApplicantIndividualRequestDTO dto, Application application) {
        ApplicantIndividual entity = new ApplicantIndividual();
        entity.setApplication(application);
        entity.setFullName(dto.getFullName());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setNicPassportNo(dto.getNicPassportNo());
        entity.setPermanentAddress(dto.getPermanentAddress());
        entity.setCommunicationAddress(dto.getCommunicationAddress());
        entity.setResidenceType(dto.getResidenceType());
        entity.setYearsAtAddress(dto.getYearsAtAddress());
        entity.setNationality(dto.getNationality());
        // Convert Boolean to String
        if (dto.getIsPep() != null) {
            entity.setIsPep(dto.getIsPep() ? "Y" : "N");
        }
        entity.setPepDetails(dto.getPepDetails());
        entity.setCivilStatus(dto.getCivilStatus());
        entity.setDependentsCount(dto.getDependentsCount());
        entity.setHighestQualification(dto.getHighestQualification());
        entity.setProfessionalQualification(dto.getProfessionalQualification());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setCreatedDate(LocalDate.now());
        
        // Convert employment details
        if (dto.getEmploymentDetails() != null) {
            List<EmploymentDetails> employmentDetails = new ArrayList<>();
            for (EmploymentDetailsRequestDTO empRequestDTO : dto.getEmploymentDetails()) {
                EmploymentDetails empDetails = new EmploymentDetails();
                empDetails.setApplicantIndividual(entity);
                empDetails.setEmployerName(empRequestDTO.getEmployerName());
                empDetails.setEmploymentType(empRequestDTO.getEmploymentType());
                empDetails.setDesignation(empRequestDTO.getDesignation());
                empDetails.setYearsWithEmployer(empRequestDTO.getYearsWithEmployer());
                empDetails.setBusinessNature(empRequestDTO.getBusinessNature());
                empDetails.setEmployerAddress(empRequestDTO.getEmployerAddress());
                empDetails.setOfficePhone(empRequestDTO.getOfficePhone());
                empDetails.setAvgIncomeGrowth(empRequestDTO.getAvgIncomeGrowth());
                empDetails.setCreatedBy(empRequestDTO.getCreatedBy());
                empDetails.setCreatedDate(LocalDate.now());
                employmentDetails.add(empDetails);
            }
            entity.setEmploymentDetails(employmentDetails);
        }
        
        // Convert income expenditures
        if (dto.getIncomeExpenditures() != null) {
            List<IncomeExpenditure> incomeExpenditures = new ArrayList<>();
            for (IncomeExpenditureRequestDTO incomeExpRequestDTO : dto.getIncomeExpenditures()) {
                IncomeExpenditure incomeExp = new IncomeExpenditure();
                incomeExp.setApplicantIndividual(entity);
                incomeExp.setIncomeSource(incomeExpRequestDTO.getIncomeSource());
                incomeExp.setMonthlyIncome(incomeExpRequestDTO.getMonthlyIncome());
                incomeExp.setExpenseType(incomeExpRequestDTO.getExpenseType());
                incomeExp.setMonthlyExpense(incomeExpRequestDTO.getMonthlyExpense());
                incomeExp.setCreatedBy(incomeExpRequestDTO.getCreatedBy());
                incomeExp.setCreatedDate(LocalDate.now());
                incomeExpenditures.add(incomeExp);
            }
            entity.setIncomeExpenditures(incomeExpenditures);
        }
        
        return entity;
    }
    
    private ApplicantCompany convertToApplicantCompanyEntity(ApplicantCompanyRequestDTO dto, Application application) {
        ApplicantCompany entity = new ApplicantCompany();
        entity.setApplication(application);
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setRegistrationNo(dto.getRegistrationNo());
        entity.setIncorporationDate(dto.getIncorporationDate());
        entity.setBusinessType(dto.getBusinessType());
        entity.setConstitution(dto.getConstitution());
        entity.setNoOfEmployees(dto.getNoOfEmployees());
        entity.setPaidUpCapital(dto.getPaidUpCapital());
        entity.setIssuedCapital(dto.getIssuedCapital());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setCreatedDate(LocalDate.now());
        
        // Convert company directors
        if (dto.getCompanyDirectors() != null) {
            List<CompanyDirector> directors = new ArrayList<>();
            for (CompanyDirectorRequestDTO directorRequestDTO : dto.getCompanyDirectors()) {
                CompanyDirector director = new CompanyDirector();
                director.setApplicantCompany(entity);
                director.setName(directorRequestDTO.getName());
                director.setAddress(directorRequestDTO.getAddress());
                director.setNicNo(directorRequestDTO.getNicNo());
                director.setAge(directorRequestDTO.getAge());
                director.setShareholdingPercent(directorRequestDTO.getShareholdingPercent());
                director.setCreatedBy(directorRequestDTO.getCreatedBy());
                director.setCreatedDate(LocalDate.now());
                directors.add(director);
            }
            entity.setCompanyDirectors(directors);
        }
        
        return entity;
    }
    
    private FacilityRequest convertToFacilityRequestEntity(FacilityRequestRequestDTO dto, Application application) {
        FacilityRequest entity = new FacilityRequest();
        entity.setApplication(application);
        entity.setType(dto.getType());
        entity.setPurpose(dto.getPurpose());
        entity.setAmount(dto.getAmount());
        entity.setRepaymentYears(dto.getRepaymentYears());
        entity.setRepaymentType(dto.getRepaymentType());
        entity.setDeductionDate(dto.getDeductionDate());
        entity.setApplicantContribution(dto.getApplicantContribution());
        entity.setAdvancePayment(dto.getAdvancePayment());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setCreatedDate(LocalDate.now());
        return entity;
    }
    
    private Asset convertToAssetEntity(AssetRequestDTO dto, Application application) {
        Asset entity = new Asset();
        entity.setApplication(application);
        entity.setAssetType(dto.getAssetType());
        entity.setDescription(dto.getDescription());
        entity.setOwnerName(dto.getOwnerName());
        entity.setMarketValue(dto.getMarketValue());
        entity.setMortgageDetails(dto.getMortgageDetails());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setCreatedDate(LocalDate.now());
        return entity;
    }
    
    private Liability convertToLiabilityEntity(LiabilityRequestDTO dto, Application application) {
        Liability entity = new Liability();
        entity.setApplication(application);
        entity.setCreditorName(dto.getCreditorName());
        entity.setReferenceNo(dto.getReferenceNo());
        entity.setPurpose(dto.getPurpose());
        entity.setAmountBorrowed(dto.getAmountBorrowed());
        entity.setBalancePayable(dto.getBalancePayable());
        entity.setRepaymentPeriodMonths(dto.getRepaymentPeriodMonths());
        entity.setSecurity(dto.getSecurity());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setCreatedDate(LocalDate.now());
        return entity;
    }
    
    private BankAccount convertToBankAccountEntity(BankAccountRequestDTO dto, Application application) {
        BankAccount entity = new BankAccount();
        entity.setApplication(application);
        entity.setBankName(dto.getBankName());
        entity.setBranchName(dto.getBranchName());
        entity.setAccountNo(dto.getAccountNo());
        entity.setAccountType(dto.getAccountType());
        entity.setDateOpened(dto.getDateOpened());
        entity.setBalance(dto.getBalance());
        entity.setHolderName(dto.getHolderName());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setCreatedDate(LocalDate.now());
        return entity;
    }
    
    private IncomeTax convertToIncomeTaxEntity(IncomeTaxRequestDTO dto, Application application) {
        IncomeTax entity = new IncomeTax();
        entity.setApplication(application);
        entity.setYearOfAssessment(dto.getYearOfAssessment());
        entity.setAssessableIncome(dto.getAssessableIncome());
        entity.setTaxableIncome(dto.getTaxableIncome());
        entity.setTaxPaid(dto.getTaxPaid());
        entity.setRemarks(dto.getRemarks());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setCreatedDate(LocalDate.now());
        return entity;
    }
    
    private InsuranceConsent convertToInsuranceConsentEntity(InsuranceConsentRequestDTO dto, Application application) {
        InsuranceConsent entity = new InsuranceConsent();
        entity.setApplication(application);
        // Convert Boolean to String
        if (dto.getConsent() != null) {
            entity.setConsent(dto.getConsent() ? "Y" : "N");
        }
        if (dto.getLoanInsuranceRequired() != null) {
            entity.setLoanInsuranceRequired(dto.getLoanInsuranceRequired() ? "Y" : "N");
        }
        entity.setInsuranceAmount(dto.getInsuranceAmount());
        entity.setInsuranceCompany(dto.getInsuranceCompany());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setCreatedDate(LocalDate.now());
        return entity;
    }
    
    // Conversion method from Entity to DTO
    private ApplicationDTO convertToApplicationDTO(Application application) {
        ApplicationDTO dto = new ApplicationDTO();
        dto.setApplicationId(application.getApplicationId());
        dto.setCompLeadId(application.getComprehensiveLead() != null ? application.getComprehensiveLead().getCompLeadId() : null);
        dto.setApplicationType(application.getApplicationType());
        dto.setBranchCode(application.getBranchCode());
        dto.setBranchName(application.getBranchName());
        dto.setApplicationDate(application.getApplicationDate());
        dto.setStatus(application.getStatus());
        dto.setCreatedDate(application.getCreatedDate());
        dto.setCreatedBy(application.getCreatedBy());
        dto.setModifiedDate(application.getModifiedDate());
        dto.setModifiedBy(application.getModifiedBy());
        
        // Convert applicant individuals
        if (application.getApplicantIndividuals() != null) {
            List<ApplicantIndividualDTO> individualDTOs = new ArrayList<>();
            for (ApplicantIndividual individual : application.getApplicantIndividuals()) {
                individualDTOs.add(convertToApplicantIndividualDTO(individual));
            }
            dto.setApplicantIndividuals(individualDTOs);
        }
        
        // Convert applicant companies
        if (application.getApplicantCompanies() != null) {
            List<ApplicantCompanyDTO> companyDTOs = new ArrayList<>();
            for (ApplicantCompany company : application.getApplicantCompanies()) {
                companyDTOs.add(convertToApplicantCompanyDTO(company));
            }
            dto.setApplicantCompanies(companyDTOs);
        }
        
        // Convert facility requests
        if (application.getFacilityRequests() != null) {
            List<FacilityRequestDTO> facilityRequestDTOs = new ArrayList<>();
            for (FacilityRequest facilityRequest : application.getFacilityRequests()) {
                facilityRequestDTOs.add(convertToFacilityRequestDTO(facilityRequest));
            }
            dto.setFacilityRequests(facilityRequestDTOs);
        }
        
        // Convert assets
        if (application.getAssets() != null) {
            List<AssetDTO> assetDTOs = new ArrayList<>();
            for (Asset asset : application.getAssets()) {
                assetDTOs.add(convertToAssetDTO(asset));
            }
            dto.setAssets(assetDTOs);
        }
        
        // Convert liabilities
        if (application.getLiabilities() != null) {
            List<LiabilityDTO> liabilityDTOs = new ArrayList<>();
            for (Liability liability : application.getLiabilities()) {
                liabilityDTOs.add(convertToLiabilityDTO(liability));
            }
            dto.setLiabilities(liabilityDTOs);
        }
        
        // Convert bank accounts
        if (application.getBankAccounts() != null) {
            List<BankAccountDTO> bankAccountDTOs = new ArrayList<>();
            for (BankAccount bankAccount : application.getBankAccounts()) {
                bankAccountDTOs.add(convertToBankAccountDTO(bankAccount));
            }
            dto.setBankAccounts(bankAccountDTOs);
        }
        
        // Convert income taxes
        if (application.getIncomeTaxes() != null) {
            List<IncomeTaxDTO> incomeTaxDTOs = new ArrayList<>();
            for (IncomeTax incomeTax : application.getIncomeTaxes()) {
                incomeTaxDTOs.add(convertToIncomeTaxDTO(incomeTax));
            }
            dto.setIncomeTaxes(incomeTaxDTOs);
        }
        
        // Convert insurance consents
        if (application.getInsuranceConsents() != null) {
            List<InsuranceConsentDTO> insuranceConsentDTOs = new ArrayList<>();
            for (InsuranceConsent insuranceConsent : application.getInsuranceConsents()) {
                insuranceConsentDTOs.add(convertToInsuranceConsentDTO(insuranceConsent));
            }
            dto.setInsuranceConsents(insuranceConsentDTOs);
        }
        
        return dto;
    }
    
    private ApplicantIndividualDTO convertToApplicantIndividualDTO(ApplicantIndividual individual) {
        ApplicantIndividualDTO dto = new ApplicantIndividualDTO();
        dto.setApplicantId(individual.getApplicantId());
        dto.setApplicationId(individual.getApplication() != null ? individual.getApplication().getApplicationId() : null);
        dto.setFullName(individual.getFullName());
        dto.setDateOfBirth(individual.getDateOfBirth());
        dto.setNicPassportNo(individual.getNicPassportNo());
        dto.setPermanentAddress(individual.getPermanentAddress());
        dto.setCommunicationAddress(individual.getCommunicationAddress());
        dto.setResidenceType(individual.getResidenceType());
        dto.setYearsAtAddress(individual.getYearsAtAddress());
        dto.setNationality(individual.getNationality());
        dto.setIsPep(individual.getIsPep());
        dto.setPepDetails(individual.getPepDetails());
        dto.setCivilStatus(individual.getCivilStatus());
        dto.setDependentsCount(individual.getDependentsCount());
        dto.setHighestQualification(individual.getHighestQualification());
        dto.setProfessionalQualification(individual.getProfessionalQualification());
        dto.setCreatedDate(individual.getCreatedDate());
        dto.setCreatedBy(individual.getCreatedBy());
        dto.setModifiedDate(individual.getModifiedDate());
        dto.setModifiedBy(individual.getModifiedBy());
        
        // Convert employment details
        if (individual.getEmploymentDetails() != null) {
            List<EmploymentDetailsDTO> employmentDetailsDTOs = new ArrayList<>();
            for (EmploymentDetails empDetails : individual.getEmploymentDetails()) {
                employmentDetailsDTOs.add(convertToEmploymentDetailsDTO(empDetails));
            }
            dto.setEmploymentDetails(employmentDetailsDTOs);
        }
        
        // Convert income expenditures
        if (individual.getIncomeExpenditures() != null) {
            List<IncomeExpenditureDTO> incomeExpenditureDTOs = new ArrayList<>();
            for (IncomeExpenditure incomeExp : individual.getIncomeExpenditures()) {
                incomeExpenditureDTOs.add(convertToIncomeExpenditureDTO(incomeExp));
            }
            dto.setIncomeExpenditures(incomeExpenditureDTOs);
        }
        
        return dto;
    }
    
    private ApplicantCompanyDTO convertToApplicantCompanyDTO(ApplicantCompany company) {
        ApplicantCompanyDTO dto = new ApplicantCompanyDTO();
        dto.setCompanyId(company.getCompanyId());
        dto.setApplicationId(company.getApplication() != null ? company.getApplication().getApplicationId() : null);
        dto.setName(company.getName());
        dto.setAddress(company.getAddress());
        dto.setRegistrationNo(company.getRegistrationNo());
        dto.setIncorporationDate(company.getIncorporationDate());
        dto.setBusinessType(company.getBusinessType());
        dto.setConstitution(company.getConstitution());
        dto.setNoOfEmployees(company.getNoOfEmployees());
        dto.setPaidUpCapital(company.getPaidUpCapital());
        dto.setIssuedCapital(company.getIssuedCapital());
        dto.setCreatedDate(company.getCreatedDate());
        dto.setCreatedBy(company.getCreatedBy());
        dto.setModifiedDate(company.getModifiedDate());
        dto.setModifiedBy(company.getModifiedBy());
        
        // Convert company directors
        if (company.getCompanyDirectors() != null) {
            List<CompanyDirectorDTO> directorDTOs = new ArrayList<>();
            for (CompanyDirector director : company.getCompanyDirectors()) {
                directorDTOs.add(convertToCompanyDirectorDTO(director));
            }
            dto.setCompanyDirectors(directorDTOs);
        }
        
        return dto;
    }
    
    private CompanyDirectorDTO convertToCompanyDirectorDTO(CompanyDirector director) {
        CompanyDirectorDTO dto = new CompanyDirectorDTO();
        dto.setDirectorId(director.getDirectorId());
        dto.setCompanyId(director.getApplicantCompany() != null ? director.getApplicantCompany().getCompanyId() : null);
        dto.setName(director.getName());
        dto.setAddress(director.getAddress());
        dto.setNicNo(director.getNicNo());
        dto.setAge(director.getAge());
        dto.setShareholdingPercent(director.getShareholdingPercent());
        dto.setCreatedDate(director.getCreatedDate());
        dto.setCreatedBy(director.getCreatedBy());
        dto.setModifiedDate(director.getModifiedDate());
        dto.setModifiedBy(director.getModifiedBy());
        return dto;
    }
    
    private EmploymentDetailsDTO convertToEmploymentDetailsDTO(EmploymentDetails empDetails) {
        EmploymentDetailsDTO dto = new EmploymentDetailsDTO();
        dto.setEmploymentId(empDetails.getEmploymentId());
        dto.setApplicantId(empDetails.getApplicantIndividual() != null ? empDetails.getApplicantIndividual().getApplicantId() : null);
        dto.setEmployerName(empDetails.getEmployerName());
        dto.setEmploymentType(empDetails.getEmploymentType());
        dto.setDesignation(empDetails.getDesignation());
        dto.setYearsWithEmployer(empDetails.getYearsWithEmployer());
        dto.setBusinessNature(empDetails.getBusinessNature());
        dto.setEmployerAddress(empDetails.getEmployerAddress());
        dto.setOfficePhone(empDetails.getOfficePhone());
        dto.setAvgIncomeGrowth(empDetails.getAvgIncomeGrowth());
        dto.setCreatedDate(empDetails.getCreatedDate());
        dto.setCreatedBy(empDetails.getCreatedBy());
        dto.setModifiedDate(empDetails.getModifiedDate());
        dto.setModifiedBy(empDetails.getModifiedBy());
        return dto;
    }
    
    private IncomeExpenditureDTO convertToIncomeExpenditureDTO(IncomeExpenditure incomeExp) {
        IncomeExpenditureDTO dto = new IncomeExpenditureDTO();
        dto.setRecordId(incomeExp.getRecordId());
        dto.setApplicantId(incomeExp.getApplicantIndividual() != null ? incomeExp.getApplicantIndividual().getApplicantId() : null);
        dto.setIncomeSource(incomeExp.getIncomeSource());
        dto.setMonthlyIncome(incomeExp.getMonthlyIncome());
        dto.setExpenseType(incomeExp.getExpenseType());
        dto.setMonthlyExpense(incomeExp.getMonthlyExpense());
        dto.setCreatedDate(incomeExp.getCreatedDate());
        dto.setCreatedBy(incomeExp.getCreatedBy());
        dto.setModifiedDate(incomeExp.getModifiedDate());
        dto.setModifiedBy(incomeExp.getModifiedBy());
        return dto;
    }
    
    private FacilityRequestDTO convertToFacilityRequestDTO(FacilityRequest facilityRequest) {
        FacilityRequestDTO dto = new FacilityRequestDTO();
        dto.setFacilityId(facilityRequest.getFacilityId());
        dto.setApplicationId(facilityRequest.getApplication() != null ? facilityRequest.getApplication().getApplicationId() : null);
        dto.setType(facilityRequest.getType());
        dto.setPurpose(facilityRequest.getPurpose());
        dto.setAmount(facilityRequest.getAmount());
        dto.setRepaymentYears(facilityRequest.getRepaymentYears());
        dto.setRepaymentType(facilityRequest.getRepaymentType());
        dto.setDeductionDate(facilityRequest.getDeductionDate());
        dto.setApplicantContribution(facilityRequest.getApplicantContribution());
        dto.setAdvancePayment(facilityRequest.getAdvancePayment());
        dto.setCreatedDate(facilityRequest.getCreatedDate());
        dto.setCreatedBy(facilityRequest.getCreatedBy());
        dto.setModifiedDate(facilityRequest.getModifiedDate());
        dto.setModifiedBy(facilityRequest.getModifiedBy());
        return dto;
    }
    
    private AssetDTO convertToAssetDTO(Asset asset) {
        AssetDTO dto = new AssetDTO();
        dto.setAssetId(asset.getAssetId());
        dto.setApplicationId(asset.getApplication() != null ? asset.getApplication().getApplicationId() : null);
        dto.setAssetType(asset.getAssetType());
        dto.setDescription(asset.getDescription());
        dto.setOwnerName(asset.getOwnerName());
        dto.setMarketValue(asset.getMarketValue());
        dto.setMortgageDetails(asset.getMortgageDetails());
        dto.setCreatedDate(asset.getCreatedDate());
        dto.setCreatedBy(asset.getCreatedBy());
        dto.setModifiedDate(asset.getModifiedDate());
        dto.setModifiedBy(asset.getModifiedBy());
        return dto;
    }
    
    private LiabilityDTO convertToLiabilityDTO(Liability liability) {
        LiabilityDTO dto = new LiabilityDTO();
        dto.setLiabilityId(liability.getLiabilityId());
        dto.setApplicationId(liability.getApplication() != null ? liability.getApplication().getApplicationId() : null);
        dto.setCreditorName(liability.getCreditorName());
        dto.setReferenceNo(liability.getReferenceNo());
        dto.setPurpose(liability.getPurpose());
        dto.setAmountBorrowed(liability.getAmountBorrowed());
        dto.setBalancePayable(liability.getBalancePayable());
        dto.setRepaymentPeriodMonths(liability.getRepaymentPeriodMonths());
        dto.setSecurity(liability.getSecurity());
        dto.setCreatedDate(liability.getCreatedDate());
        dto.setCreatedBy(liability.getCreatedBy());
        dto.setModifiedDate(liability.getModifiedDate());
        dto.setModifiedBy(liability.getModifiedBy());
        return dto;
    }
    
    private BankAccountDTO convertToBankAccountDTO(BankAccount bankAccount) {
        BankAccountDTO dto = new BankAccountDTO();
        dto.setBankAccountId(bankAccount.getBankAccountId());
        dto.setApplicationId(bankAccount.getApplication() != null ? bankAccount.getApplication().getApplicationId() : null);
        dto.setBankName(bankAccount.getBankName());
        dto.setBranchName(bankAccount.getBranchName());
        dto.setAccountNo(bankAccount.getAccountNo());
        dto.setAccountType(bankAccount.getAccountType());
        dto.setDateOpened(bankAccount.getDateOpened());
        dto.setBalance(bankAccount.getBalance());
        dto.setHolderName(bankAccount.getHolderName());
        dto.setCreatedDate(bankAccount.getCreatedDate());
        dto.setCreatedBy(bankAccount.getCreatedBy());
        dto.setModifiedDate(bankAccount.getModifiedDate());
        dto.setModifiedBy(bankAccount.getModifiedBy());
        return dto;
    }
    
    private IncomeTaxDTO convertToIncomeTaxDTO(IncomeTax incomeTax) {
        IncomeTaxDTO dto = new IncomeTaxDTO();
        dto.setTaxId(incomeTax.getTaxId());
        dto.setApplicationId(incomeTax.getApplication() != null ? incomeTax.getApplication().getApplicationId() : null);
        dto.setYearOfAssessment(incomeTax.getYearOfAssessment());
        dto.setAssessableIncome(incomeTax.getAssessableIncome());
        dto.setTaxableIncome(incomeTax.getTaxableIncome());
        dto.setTaxPaid(incomeTax.getTaxPaid());
        dto.setRemarks(incomeTax.getRemarks());
        dto.setCreatedDate(incomeTax.getCreatedDate());
        dto.setCreatedBy(incomeTax.getCreatedBy());
        dto.setModifiedDate(incomeTax.getModifiedDate());
        dto.setModifiedBy(incomeTax.getModifiedBy());
        return dto;
    }
    
    private InsuranceConsentDTO convertToInsuranceConsentDTO(InsuranceConsent insuranceConsent) {
        InsuranceConsentDTO dto = new InsuranceConsentDTO();
        dto.setInsuranceId(insuranceConsent.getInsuranceId());
        dto.setApplicationId(insuranceConsent.getApplication() != null ? insuranceConsent.getApplication().getApplicationId() : null);
        dto.setConsent(insuranceConsent.getConsent());
        dto.setLoanInsuranceRequired(insuranceConsent.getLoanInsuranceRequired());
        dto.setInsuranceAmount(insuranceConsent.getInsuranceAmount());
        dto.setInsuranceCompany(insuranceConsent.getInsuranceCompany());
        dto.setCreatedDate(insuranceConsent.getCreatedDate());
        dto.setCreatedBy(insuranceConsent.getCreatedBy());
        dto.setModifiedDate(insuranceConsent.getModifiedDate());
        dto.setModifiedBy(insuranceConsent.getModifiedBy());
        return dto;
    }
}
