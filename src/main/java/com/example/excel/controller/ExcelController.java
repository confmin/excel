package com.example.excel.controller;

import com.example.excel.model.entity.ExcelEntity;
import com.example.excel.reponsitory.ExcelReponsitory;
import com.example.excel.service.ExcelInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;


@RestController
@CrossOrigin("*")
@RequestMapping("/api")
@Api(value = "Import va Export Excel ")
public class ExcelController {
    @Autowired
    private ExcelInterface excelInterface;
    @ApiOperation("Import 1 file excel ")
    @PostMapping("/import" )
    public ResponseEntity<?> import_excel(@ApiParam("Nhap file") @RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(excelInterface.import_excel(file), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAll(@RequestParam Integer act, @RequestParam Integer total) {
        return new ResponseEntity<>(excelInterface.getAll(act, total), HttpStatus.OK);
    }

    @GetMapping("/downloadExcel")
    public ResponseEntity<Resource> getFile(@RequestParam(required = false) Integer to, @RequestParam(required = false) Integer from) {
        String filename = "demo.xlsx";

        if (to != null && from != null) {
            InputStreamResource file = new InputStreamResource(excelInterface.load(to, from));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                    .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                    .body(file);
        }
        InputStreamResource file = new InputStreamResource(excelInterface.load());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);

    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ExcelEntity excelEntity) {
        return new ResponseEntity<>(excelInterface.add(excelEntity), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> up(@PathVariable Integer id, @RequestBody ExcelEntity excelEntity) {
        return new ResponseEntity<>(excelInterface.update(id, excelEntity), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> del(@PathVariable Integer id) {
        return new ResponseEntity<>(excelInterface.del(id), HttpStatus.OK);
    }


}
