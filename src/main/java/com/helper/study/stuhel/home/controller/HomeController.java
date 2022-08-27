package com.helper.study.stuhel.home.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.helper.study.stuhel.home.service.HomeServiceImpl;
import com.helper.study.stuhel.home.to.BookTO;
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
    HashMap<String,ArrayList> retrieveBookableRoom(@RequestParam("requestBookableDate") String requestBookableDate){
        HashMap<String, ArrayList> map = new HashMap<>();
        BookTO bookTO = gson.fromJson(requestBookableDate, BookTO.class);
        ArrayList<Integer> roomId = bookService.retrieveBookableRoom(bookTO);
        map.put("roomId", roomId);
        return map;
    }

    @PostMapping("/addReservationInfo")
    HashMap<String,String> addReservationInfo(@RequestParam("addBookData") String addBookData, HttpServletRequest request){
        HttpSession session=request.getSession();
        BookTO bookTO = gson.fromJson(addBookData, BookTO.class);
        bookTO.setUserId(session.getAttribute("memberId").toString());

        return bookService.addReservationInfo(bookTO);
    }
}
