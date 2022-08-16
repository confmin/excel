package com.example.excel.model.respon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponPage {
    private  String message ;
    private Integer act ;
    private  Integer total ;
    private Object data ;
}
