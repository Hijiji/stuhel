package com.helper.study.stuhel.home.mapper;

import com.helper.study.stuhel.home.to.BookTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface HomeMapper {
   ArrayList<Integer> selectBookableRoom(BookTO bookTO);
   void insertReservationInfo(BookTO bookTO);

   }
