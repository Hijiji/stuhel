package com.helper.study.stuhel.service;

import com.helper.study.stuhel.mapper.BookDAO;
import com.helper.study.stuhel.mapper.MemberDAO;
import com.helper.study.stuhel.to.BookTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class BookServiceImpl implements BookService {

    private final BookDAO bookDAO;

    @Autowired
    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }


    @Override
    public ArrayList<Integer> bookSearch(BookTO bookTO) {
        ArrayList<Integer> roomName = bookDAO.bookSearch(bookTO);
        return roomName;
    }

    @Override
    public HashMap<String, Integer> roomBook(BookTO bookTO) {
        HashMap<String,Integer> map =new HashMap<>();
                bookDAO.roomBook(bookTO);
        return map;
    }
}
