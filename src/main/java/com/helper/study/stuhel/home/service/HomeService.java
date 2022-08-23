package com.helper.study.stuhel.home.service;

import com.helper.study.stuhel.home.to.BookTO;

import java.util.ArrayList;
import java.util.HashMap;

public interface HomeService {

    ArrayList<Integer> retrieveBookableRoom(BookTO bookTO);
    HashMap<String, Integer> roomBook(BookTO bookTO);
}
