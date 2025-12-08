package com.project.moru.data_field.repository;

import com.project.moru.data_field.domain.entity.DataField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataFieldRepository extends JpaRepository<DataField, Long> {
}
