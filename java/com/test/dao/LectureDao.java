package com.test.dao;

import com.test.dto.LectureDto;
import com.test.mapper.LectureMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class LectureDao {
    @Autowired
    SqlSession sqlSession;

    public ArrayList<LectureDto> getItemList(){
        try{
            System.out.println("lec.do Dao do");
            LectureMapper lectureMapper = sqlSession.getMapper(LectureMapper.class);
            ArrayList<LectureDto> lectureInfoList = lectureMapper.getItemList();
            System.out.println("lec.do Dao end");
            return lectureInfoList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public void deleteItem(int lecNo){
        try{
            LectureMapper lectureMapper = sqlSession.getMapper(LectureMapper.class);
            lectureMapper.deleteItem(lecNo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void addItem(String lecCategory, String lecName, int lecPrice, String lecImg){
        try{
            LectureMapper lectureMapper = sqlSession.getMapper(LectureMapper.class);
            System.out.println("dao: " + lecCategory +" "+ lecName +" "+ lecPrice);
            lectureMapper.addItem(lecCategory, lecName, lecPrice, lecImg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public LectureDto selectItem(int lecNo){
        try{
            LectureMapper lectureMapper = sqlSession.getMapper(LectureMapper.class);
            System.out.println("select: " + lecNo);
            LectureDto lecture = lectureMapper.selectItem(lecNo);
            return lecture;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void editItem(int lecNo, String lecCategory, String lecName, int lecPrice, String lecImg){
        try{
            LectureMapper lectureMapper = sqlSession.getMapper(LectureMapper.class);
            System.out.println("edit: " + lecCategory +" "+ lecName +" "+ lecPrice);
            lectureMapper.editItem(lecNo, lecCategory, lecName, lecPrice, lecImg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void editItemWithoutImage(int lecNo, String lecCategory, String lecName, int lecPrice){
        try{
            LectureMapper lectureMapper = sqlSession.getMapper(LectureMapper.class);
            System.out.println("edit: " + lecCategory +" "+ lecName +" "+ lecPrice);
            lectureMapper.editItemWithoutImage(lecNo, lecCategory, lecName, lecPrice);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
