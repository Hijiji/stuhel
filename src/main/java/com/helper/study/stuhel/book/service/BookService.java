package com.helper.study.stuhel.book.service;

import com.helper.study.stuhel.book.to.BookTO;

import java.util.ArrayList;
import java.util.HashMap;

public interface BookService {

    ArrayList<Integer> retrieveBookableRoom(BookTO bookTO);
    HashMap<String, Integer> roomBook(BookTO bookTO);
}
