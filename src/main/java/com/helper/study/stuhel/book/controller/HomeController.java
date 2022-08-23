package com.helper.study.stuhel.book.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.helper.study.stuhel.book.service.HomeServiceImpl;
import com.helper.study.stuhel.book.to.BookTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/stuhel/home")
@ResponseBody
public class HomeController {

    private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환
    private final HomeServiceImpl bookService;
    @Autowired
    public HomeController(HomeServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/retrieveBookableRoom")
    HashMap<String,ArrayList> retrieveBookableRoom(@RequestParam("bookTime") int bookTime, @RequestParam("bookDate") String bookDate){

        BookTO bookTO=new BookTO();
        bookTO.setBookDate(bookDate);
        bookTO.setBookTime(bookTime);

        ArrayList<Integer>  roomId = bookService.retrieveBookableRoom(bookTO);
        HashMap<String, ArrayList> map = new HashMap<>();
        map.put("roomId",roomId);
        return map;
    }

    @PostMapping("/roomBook")
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
