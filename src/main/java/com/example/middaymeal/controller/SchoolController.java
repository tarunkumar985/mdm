package com.example.middaymeal.controller;



import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.middaymeal.dto.SchoolRequestDTO;
import com.example.middaymeal.entity.School;
import com.example.middaymeal.response.ApiResponse;
import com.example.middaymeal.service.SchoolService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;
    
    //need to use ApiResponse all other api
    @PostMapping
    public ResponseEntity<ApiResponse<School>> createSchool(
            @Valid @RequestBody SchoolRequestDTO dto) {

        School school = new School();

        school.setSchoolCode(dto.getSchoolCode());
        school.setName(dto.getName());
        school.setDistrict(dto.getDistrict());
        school.setBlock(dto.getBlock());
        school.setState(dto.getState());
        school.setAddress(dto.getAddress());
        school.setTotalStudents(dto.getTotalStudents());
        school.setCreatedAt(LocalDateTime.now());

//        return ResponseEntity.ok(schoolService.create(school));
//        
        return ResponseEntity.ok(
                new ApiResponse<>(true, "School created successfully", schoolService.create(school))
        );
    }
    
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<School>>> searchSchools(

            @RequestParam(required = false) String district,
            @RequestParam(required = false) String block,
            @RequestParam(required = false) String state,
            Pageable pageable) {

        Page<School> result =
                schoolService.searchSchoolsByDBS(district, block, state, pageable);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Schools fetched", result)
        );
    }

    // Get All Schools
    @GetMapping
    public ResponseEntity<Page<School>> getAllSchools(Pageable pageable) {
    	return ResponseEntity.ok(
                schoolService.getAllSchools(pageable)
        );
    }
    
   
    
    @GetMapping("/getAllActiveSchool")
    public ResponseEntity<Page<School>> getAllActive(Pageable pageable) {
        return ResponseEntity.ok(schoolService.getAllActive(pageable));
    }
    
    
 
    
    @GetMapping("/{id}")
    public ResponseEntity<School> getSchool(@PathVariable Long id) {

        return ResponseEntity.ok(
                schoolService.getById(id)
        );
    }

    // Update School
    @PutMapping("/{id}")
    public ResponseEntity<School> updateSchool(
            @PathVariable Long id,
            @RequestBody School school) {

        School updated = schoolService.updateSchool(id, school);
        return ResponseEntity.ok(updated);
    }

    // Delete School
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchool(@PathVariable Long id) {
        schoolService.deleteSchool(id);
        return ResponseEntity.ok("School deleted successfully");
    }
}