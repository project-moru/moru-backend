package com.project.moru.data_field.repository;

import com.project.moru.data_field.domain.entity.AttributeBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeBlockRepository extends JpaRepository<AttributeBlock, Long> {
  List<AttributeBlock> findAttributesByDataFieldId(Long dataFieldId);
}
