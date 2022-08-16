package com.example.excel.service;

import com.example.excel.model.entity.ExcelEntity;
import com.example.excel.model.respon.Respon;
import com.example.excel.model.respon.ResponPage;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Component
public interface ExcelInterface {
ResponPage getAll(Integer act , Integer total);
    Respon import_excel(MultipartFile file) throws IOException;
    ByteArrayInputStream load(Integer to, Integer from) ;
    ByteArrayInputStream load() ;
    Respon add(ExcelEntity excelEntity);
    Respon update(Integer id,ExcelEntity excelEntity);
    Respon del(Integer id);

}

