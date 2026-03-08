package com.example.middaymeal.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.middaymeal.entity.School;
import com.example.middaymeal.repository.SchoolRepository;
import com.example.middaymeal.repository.SchoolSpecification;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class SchoolService {

	private final SchoolRepository schoolRepository;

	public School create(School school) {
		if (schoolRepository.existsBySchoolCode(school.getSchoolCode())) {
			throw new IllegalArgumentException("School code already exists");
		}
		school.setCreatedAt(LocalDateTime.now());
		school.setActive(true);
		return schoolRepository.save(school);
	}

	public School getById(Long id) {
		return schoolRepository.findById(id).orElseThrow(() -> new RuntimeException("School not found"));
	}
	
	public Page<School> getAllActive(Pageable pageable) {
	    Page<School> allSchools = schoolRepository.findAll(pageable);
	    List<School> activeSchools = allSchools.stream()
	                                           .filter(School::getActive)
	                                           .toList();
	    return new PageImpl<>(activeSchools, pageable, allSchools.getTotalElements());
	}

	public Page<School> getAllSchools(Pageable pageable) {
		return schoolRepository.findAll( pageable);
	}

	public School updateSchool(Long id, School school) {
		School existing = getById(id);
		existing.setName(school.getName());
		existing.setDistrict(school.getDistrict());
		existing.setBlock(school.getBlock());
		existing.setState(school.getState());
		existing.setAddress(school.getAddress());
		existing.setTotalStudents(school.getTotalStudents());
		existing.setActive(school.getActive());
		return schoolRepository.save(existing);
	}

	public void deleteSchool(Long id) {
		School school = getById(id);
		schoolRepository.delete(school);
	}

//	public Page<School> searchSchools(String district, String block, Pageable pageable) {
//		if (district != null && block != null) {
//			return schoolRepository.findByDistrictAndBlock(district, block, pageable);
//		}
//		if (district != null) {
//			return schoolRepository.findByDistrict(district, pageable);
//		}
//		if (block != null) {
//			return schoolRepository.findByBlock(block, pageable);
//		}
//		return schoolRepository.findAll(pageable);
//	}
	
	
	public Page<School> searchSchoolsByDBS(String district, String block, String state, Pageable pageable) {

        Specification<School> spec = Specification
                .where(SchoolSpecification.hasDistrict(district))
                .and(SchoolSpecification.hasBlock(block))
                .and(SchoolSpecification.hasState(state));

        return schoolRepository.findAll(spec, pageable);
    }
}