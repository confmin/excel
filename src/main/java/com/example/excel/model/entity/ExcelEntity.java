package com.example.excel.model.entity;

import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@Entity
@ToString
@Table(name = "excels")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@CrossOrigin("*")
public class ExcelEntity {
@Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id ;
private String name ;
private String description ;
}
