package com.helper.study.stuhel.controller;

import com.helper.study.stuhel.service.BookServiceImpl;
import com.helper.study.stuhel.to.BookTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/book")
@ResponseBody
public class StudyRoomController {

    private final BookServiceImpl bookService;

    @Autowired
    public StudyRoomController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/roomSearch")
    HashMap<String,ArrayList> roomSearch(@RequestParam("bookingTime") int bookTime, @RequestParam("bookingDate") String bookDate){
        System.out.println(bookTime);
        System.out.println(bookDate);
        System.out.println("1");
        BookTO bookTO=new BookTO();

         bookTO.setBookingDate(bookDate);
        bookTO.setBookingTime(bookTime);
        System.out.println("2");
        ArrayList<Integer>  roomId = bookService.bookSearch(bookTO);
        System.out.println(roomId.get(0));
        System.out.println(roomId.get(1));
        System.out.println(roomId.get(2));
        System.out.println(roomId.get(3));
        System.out.println(roomId.get(4));
        System.out.println(roomId.get(5));
        System.out.println("5");
        HashMap<String, ArrayList> map = new HashMap<>();
        map.put("roomId",roomId);



        return map;
    }
}
