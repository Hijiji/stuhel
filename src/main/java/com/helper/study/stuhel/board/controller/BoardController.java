package com.helper.study.stuhel.board.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.helper.study.stuhel.book.service.BookServiceImpl;
import com.helper.study.stuhel.book.to.BookTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/stuhel/board")
@ResponseBody
public class BoardController {
    private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환

    /*private final BookServiceImpl bookService;

    @Autowired
    public StudyRoomController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }*/

    @PostMapping("/boardListRetrieve")
    HashMap<String, ArrayList> boardListRetrieve(HttpServletRequest request){
        HttpSession session = request.getSession();
        String sessionId = (String)session.getAttribute("memberId");
        return null;
    }

    @PostMapping("/boardKeywordSearch")
    HashMap<String,ArrayList> boardKeywordSearch(HttpServletRequest request,@RequestParam("keyword")String fullKeyword){

        //요거는 서비스단에서 하자..! 앞단에서 받은것을 문자열로 서비스단에 넘겨준 다음, 서비스단에서 가공하여서 디비조사.
        String[] keywordList = fullKeyword.split(" ");
        for(String keyword:keywordList){
            String k= "%"+keyword+"%";
        }

        return null;
    }

    @PostMapping("/writeSave")
    HashMap<String,String> writeSave(HttpServletRequest request,@RequestParam("title")String title, @RequestParam("wContents")String contents){

        return null;
    }
}
