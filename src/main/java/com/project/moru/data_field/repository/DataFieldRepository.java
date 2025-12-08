package com.project.moru.data_field.repository;

import com.project.moru.data_field.domain.entity.DataField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataFieldRepository extends JpaRepository<DataField, Long> {
  List<DataField> findDataFieldsByUserId(Long userId);
}
