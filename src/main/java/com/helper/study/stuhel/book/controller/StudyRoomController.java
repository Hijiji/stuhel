package com.helper.study.stuhel.book.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.helper.study.stuhel.book.service.BookServiceImpl;
import com.helper.study.stuhel.book.to.BookTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/stuhel/book")
@ResponseBody
public class StudyRoomController {

    private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환
    private final BookServiceImpl bookService;
    @Autowired
    public StudyRoomController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/bookableRoomRetrieve")
    HashMap<String,ArrayList> bookableRoomRetrieve(@RequestParam("bookTime") int bookTime, @RequestParam("bookDate") String bookDate){

        BookTO bookTO=new BookTO();
        bookTO.setBookDate(bookDate);
        bookTO.setBookTime(bookTime);

        ArrayList<Integer>  roomId = bookService.bookableRoomRetrieve(bookTO);
        HashMap<String, ArrayList> map = new HashMap<>();
        map.put("roomId",roomId);
        return map;
    }

    @GetMapping("/roomBook")
    HashMap<String,Integer> roomBook(@RequestParam("insertBookData") String insertBookData, HttpServletRequest request){
        HttpSession session=request.getSession();
        BookTO bookTO = gson.fromJson(insertBookData, BookTO.class);
        String memberId=session.getAttribute("memberId").toString();
        bookTO.setUserId(memberId);
        System.out.println(bookTO.getUserId());

        HashMap<String, Integer> map = new HashMap<>();
        map=bookService.roomBook(bookTO);

        return map;
    }
}
