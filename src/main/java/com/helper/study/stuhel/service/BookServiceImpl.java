package com.helper.study.stuhel.service;

import com.helper.study.stuhel.mapper.BookDAO;
import com.helper.study.stuhel.mapper.MemberDAO;
import com.helper.study.stuhel.to.BookTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class BookServiceImpl implements BookService{
    private final BookDAO bookDAO;

    @Autowired
    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }


    @Override
    public ArrayList<Integer> bookSearch(BookTO bookTO) {
        System.out.println("3");
        ArrayList<Integer> roomName=bookDAO.bookSearch(bookTO);
        System.out.println(roomName.get(1));
        System.out.println(roomName.get(2));
        System.out.println(roomName.get(3));
        System.out.println(roomName.get(4));
        System.out.println(roomName.get(5));
        System.out.println(roomName.get(0));
        System.out.println("4");
        return roomName;
    }
}
