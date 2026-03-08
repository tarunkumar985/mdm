package com.example.middaymeal.repository;

import org.springframework.data.jpa.domain.Specification;

import com.example.middaymeal.entity.School;

public class SchoolSpecification {

    public static Specification<School> hasDistrict(String district) {
        return (root, query, cb) ->
                district == null ? null : cb.equal(root.get("district"), district);
    }

    public static Specification<School> hasBlock(String block) {
        return (root, query, cb) ->
                block == null ? null : cb.equal(root.get("block"), block);
    }

    public static Specification<School> hasState(String state) {
        return (root, query, cb) ->
                state == null ? null : cb.equal(root.get("state"), state);
    }
}