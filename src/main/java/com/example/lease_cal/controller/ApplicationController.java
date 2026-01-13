package com.example.lease_cal.controller;

import com.example.lease_cal.dto.application.ApplicationDTO;
import com.example.lease_cal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    
    @Autowired
    private ApplicationService applicationService;
    
    /**
     * Save application with all child entities
     * 
     * @param applicationDTO The application DTO containing all application data
     * @return ResponseEntity with ApplicationDTO containing saved data including IDs
     */
    @PostMapping
    public ResponseEntity<ApplicationDTO> saveApplication(@RequestBody ApplicationDTO applicationDTO) {
        try {
            ApplicationDTO savedApplication = applicationService.saveApplication(applicationDTO);
            return new ResponseEntity<>(savedApplication, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
