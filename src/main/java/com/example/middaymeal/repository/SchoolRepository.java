package com.example.middaymeal.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.middaymeal.entity.School;
@Repository
public interface SchoolRepository extends JpaRepository<School, Long>, JpaSpecificationExecutor<School> {
	
    Optional<School> findBySchoolCode(String schoolCode);

    boolean existsBySchoolCode(String schoolCode);
    
//    @Query("SELECT s FROM School s WHERE (:district IS NULL OR s.district = :district) AND (:block IS NULL OR s.block = :block)")
//    Page<School> searchSchools(String district, String block, Pageable pageable);
    
    Page<School> findByDistrict(String district, Pageable pageable);

    Page<School> findByBlock(String block, Pageable pageable);

    Page<School> findByDistrictAndBlock(String district, String block, Pageable pageable);
    
}

