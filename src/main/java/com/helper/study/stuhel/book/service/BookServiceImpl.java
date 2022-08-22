package com.helper.study.stuhel.book.service;

import com.helper.study.stuhel.book.mapper.BookMapper;
import com.helper.study.stuhel.book.to.BookTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }


    @Override
    public ArrayList<Integer> retrieveBookableRoom(BookTO bookTO) {
        ArrayList<Integer> roomName = bookMapper.selectBookableRoom(bookTO);
        return roomName;
    }

    @Override
    public HashMap<String, Integer> roomBook(BookTO bookTO) {
        HashMap<String,Integer> map =new HashMap<>();
                bookMapper.insertRoomBook(bookTO);
        return map;
    }
}
