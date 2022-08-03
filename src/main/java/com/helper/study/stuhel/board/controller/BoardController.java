package com.helper.study.stuhel.board.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.helper.study.stuhel.board.servie.BoardService;
import com.helper.study.stuhel.board.servie.BoardServiceImpl;
import com.helper.study.stuhel.board.to.BoardTO;
import com.helper.study.stuhel.book.service.BookServiceImpl;
import com.helper.study.stuhel.book.to.BookTO;
import com.helper.study.stuhel.exception.IdNotFoundException;
import com.helper.study.stuhel.exception.PwMissMatchException;
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

    private final BoardServiceImpl boardService;

    @Autowired
    public BoardController(BoardServiceImpl boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/boardListRetrieve")
    HashMap<String, ArrayList> boardListRetrieve(HttpServletRequest request){
        HttpSession session = request.getSession();
        String sessionId = (String)session.getAttribute("memberId");
        return null;
    }

    @PostMapping("/boardKeywordSearch")
    HashMap<String,ArrayList> boardKeywordSearch(HttpServletRequest request,@RequestParam("fullKeyword")String fullKeyword){
        HashMap<String, ArrayList> map = new HashMap<>();
        ArrayList<BoardTO> board = boardService.boardKeywordSearch(fullKeyword);
        map.put("writeList", board);
        return map; //리다이렉트 게시글 조회
    }

    @GetMapping("/writeSave")
    HashMap<String,Integer> writeSave(HttpServletRequest request
                    ,@RequestParam("title")String title, @RequestParam("note")String note, @RequestParam("topicNm")String topicNm){

        HashMap<String, Integer> map = new HashMap<>();
        BoardTO boardTO=new BoardTO();
        boardTO.setTitle(title); boardTO.setNote(note); boardTO.setTopicNm(topicNm); boardTO.setWriter((String)request.getSession().getAttribute("memberId"));
        map.put("errorCode", 0);
        try {
            boardService.writeSave(boardTO);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("errorCode", -1);
        }
        return map;
    }

    @PostMapping("/topicRetrieve")
    String topicRetrieve(){
        ArrayList<BoardTO> topicList=boardService.topicRetrieve();
        return gson.toJson(topicList);
    }
}
