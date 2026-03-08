package com.example.middaymeal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolRequestDTO {

    @NotBlank(message = "School code is required")
    private String schoolCode;

    @NotBlank(message = "School name is required")
    private String name;

    @NotBlank(message = "District is required")
    private String district;

    private String block;

    @NotBlank(message = "State is required")
    private String state;

    private String address;

    @NotNull(message = "Total students required")
    private Integer totalStudents;
}