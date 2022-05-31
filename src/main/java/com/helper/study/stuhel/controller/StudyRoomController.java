package com.helper.study.stuhel.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.helper.study.stuhel.service.BookServiceImpl;
import com.helper.study.stuhel.to.BookTO;
import com.helper.study.stuhel.to.MemberTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.lang.model.type.ArrayType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/book")
@ResponseBody
public class StudyRoomController {

    private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환

    private final BookServiceImpl bookService;

    @Autowired
    public StudyRoomController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/roomSearch")
    HashMap<String,ArrayList> roomSearch(@RequestParam("bookingTime") int bookTime, @RequestParam("bookingDate") String bookDate){
        System.out.println(bookTime);
        System.out.println(bookDate);

        BookTO bookTO=new BookTO();

        bookTO.setBookingDate(bookDate);
        bookTO.setBookingTime(bookTime);

        ArrayList<Integer>  roomId = bookService.bookSearch(bookTO);

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
