package com.test.service.test;

import com.test.dto.LectureDto;

import java.util.ArrayList;

public interface LectureService {
    ArrayList<LectureDto> getItemList();
    void deleteItem(int lecNo);
    void addItem(String lecCategory, String lecName, int lecPrice, String lecImg);
    LectureDto selectItem(int lecNo);
    void editItem(int lecNo, String lecCategory, String lecName, int lecPrice, String lecImg);
    void editItemWithoutImage(int lecNo, String lecCategory, String lecName, int lecPrice);
}
