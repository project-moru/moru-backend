package com.project.moru.data_field.repository;

import com.project.moru.data_field.domain.entity.LinkBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkBlockRepository extends JpaRepository<LinkBlock, Long> {
  List<LinkBlock> findLinkBlocksByDataFieldId(Long dataFieldId);
}
