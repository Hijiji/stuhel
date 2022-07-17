package com.helper.study.stuhel.book.mapper;

import com.helper.study.stuhel.book.to.BookTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface BookDAO {
   ArrayList<Integer> bookSearch(BookTO bookTO);
   void roomBook(BookTO bookTO);

   }
