package com.test.service.test;

import com.test.dao.LectureDao;
import com.test.dto.LectureDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LectureServiceImp implements LectureService {
    @Autowired
    LectureDao lectureDao;

    @Override
    public ArrayList<LectureDto> getItemList() {
        System.out.println("lecture.do Service do");
        ArrayList<LectureDto> lectureInfoList = lectureDao.getItemList();
        System.out.println("lecture.do Service end");
        return lectureInfoList;
    }

    @Override
    public void deleteItem(int lecNo) {
        lectureDao.deleteItem(lecNo);
    }

    @Override
    public void addItem(String lecCategory, String lecName, int lecPrice, String lecImg) {
        System.out.println("service: " + lecCategory +" "+ lecName +" "+ lecPrice);
        lectureDao.addItem(lecCategory, lecName, lecPrice, lecImg);
    }

    @Override
    public LectureDto selectItem(int lecNo) {
        LectureDto lecture = lectureDao.selectItem(lecNo);
        return lecture;
    }

    @Override
    public void editItem(int lecNo, String lecCategory, String lecName, int lecPrice, String lecImg) {
        System.out.println("service: " + lecCategory + " " + lecName + " " + lecPrice);
        lectureDao.editItem(lecNo, lecCategory, lecName, lecPrice, lecImg);
    }

    @Override
    public void editItemWithoutImage(int lecNo, String lecCategory, String lecName, int lecPrice) {
        System.out.println("service: " + lecCategory + " " + lecName + " " + lecPrice);
        lectureDao.editItemWithoutImage(lecNo, lecCategory, lecName, lecPrice);
    }
}
