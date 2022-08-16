package com.example.excel.service;

import com.example.excel.helper.ExcelHelper;
import com.example.excel.model.entity.ExcelEntity;
import com.example.excel.model.respon.Respon;
import com.example.excel.model.respon.ResponPage;
import com.example.excel.reponsitory.ExcelReponsitory;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExcelServiceImp implements ExcelInterface {
    @Autowired
    private ExcelReponsitory excelReponsitory;

    @Override
    public ResponPage getAll(Integer act, Integer total) {
        Pageable pageable = PageRequest.of(act,total);
        Page<ExcelEntity> excelEntities = excelReponsitory.findAll(pageable);

    return new ResponPage("ss",excelEntities.getNumber(),excelEntities.getTotalPages(),excelEntities.toList());
    }

    @Override
    public Respon import_excel(MultipartFile file) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows() - 1; i++) {
            ExcelEntity excelEntity = new ExcelEntity();
            XSSFRow row = worksheet.getRow(i);
            DataFormatter formatter = new DataFormatter();
            excelEntity.setName(formatter.formatCellValue(row.getCell(1)));
            excelEntity.setDescription(formatter.formatCellValue(row.getCell(2)));
            excelReponsitory.save(excelEntity);
        }
        return new Respon("Suss", null);

    }

    @Override
    public ByteArrayInputStream load(Integer to , Integer from) {
        List<ExcelEntity> excelEntities = excelReponsitory.findTheoY(to,from);
        ByteArrayInputStream in = ExcelHelper.demoExcel(excelEntities);
        return in;
    }

    @Override
    public ByteArrayInputStream load() {
        List<ExcelEntity> excelEntities = excelReponsitory.findAll();
        ByteArrayInputStream in = ExcelHelper.demoExcel(excelEntities);
        return in;
    }

    @Override
    public Respon add(ExcelEntity excelEntity) {
        ExcelEntity excelEntity1 = new ExcelEntity();
        excelEntity1.setName(excelEntity.getName());
        excelEntity1.setDescription(excelEntity.getDescription());
        excelReponsitory.save(excelEntity1);
        return  new Respon("ccc",null);
    }

    @Override
    public Respon update(Integer id, ExcelEntity excelEntity) {
        Optional<ExcelEntity> excelEntity1 = excelReponsitory.findById(id);
        ExcelEntity excelEntity2 = excelEntity1.get();
        excelEntity2.setName(excelEntity.getName());
        excelEntity2.setDescription(excelEntity.getDescription());
        excelReponsitory.save(excelEntity2);
        return new Respon("aa",null);
    }

    @Override
    public Respon del(Integer id) {
        excelReponsitory.deleteById(id);
        return new Respon("done",null);
    }
}
