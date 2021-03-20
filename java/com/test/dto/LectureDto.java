package com.test.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LectureDto {
    private int lecNo;
    private String lecCategory;
    private String lecName;
    private int lecPrice;
    private Date lecRegDate;
    private String lecImg;

    public String getLecImg() {
        return lecImg;
    }
}
