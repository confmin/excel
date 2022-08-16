package com.example.excel.reponsitory;

import com.example.excel.model.entity.ExcelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExcelReponsitory extends JpaRepository<ExcelEntity , Integer> {
    @Query(value = "select * from excels  limit ?1,?2",nativeQuery = true)
    List<ExcelEntity> findTheoY(Integer to  , Integer from);
    Page<ExcelEntity> findAll(Pageable pageable);

}
