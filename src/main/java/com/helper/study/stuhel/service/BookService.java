package com.helper.study.stuhel.service;

import com.helper.study.stuhel.to.BookTO;

import java.util.ArrayList;
import java.util.HashMap;

public interface BookService {

    ArrayList<Integer> bookSearch(BookTO bookTO);
}
