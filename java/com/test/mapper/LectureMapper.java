package com.test.mapper;

import com.test.dto.LectureDto;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface LectureMapper {
    ArrayList<LectureDto> getItemList();
    void deleteItem(@Param("lecNo") int lecNo);
    void addItem(@Param("lecCategory") String lecCategory,
            @Param("lecName") String lecName,
            @Param("lecPrice") int lecPrice,
                 @Param("lecImg") String lecImg);
    LectureDto selectItem(@Param("lecNo") int lecNo);
    void editItem(@Param("lecNo") int lecNo,
            @Param("lecCategory") String lecCategory,
            @Param("lecName") String lecName,
            @Param("lecPrice") int lecPrice,
                  @Param("lecImg") String lecImg);
    void editItemWithoutImage(@Param("lecNo") int lecNo,
                  @Param("lecCategory") String lecCategory,
                  @Param("lecName") String lecName,
                  @Param("lecPrice") int lecPrice);
}
