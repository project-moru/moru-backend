package com.project.moru.data_field.repository;

import com.project.moru.data_field.domain.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
  List<Attribute> findAttributesByDataFieldId(Long dataFieldId);
}
