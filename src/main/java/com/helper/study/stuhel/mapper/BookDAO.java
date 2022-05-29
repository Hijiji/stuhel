package com.helper.study.stuhel.mapper;

import com.helper.study.stuhel.to.BookTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
@Mapper
public interface BookDAO {
   ArrayList<Integer> bookSearch(BookTO bookTO);
}
