package com.helper.study.stuhel.board.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.helper.study.stuhel.board.servie.BoardServiceImpl;
import com.helper.study.stuhel.board.to.BoardTO;
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

    @GetMapping("/retrieveBoardList")
    ArrayList<BoardTO> retrieveBoardList(HttpServletRequest request){
        HttpSession session = request.getSession();
        String sessionId = (String)session.getAttribute("memberId");
        ArrayList<BoardTO> board=boardService.retrieveBoardList();
        return board;
    }

    @GetMapping("/retrieveBoardKeyword")
    HashMap<String,ArrayList> retrieveBoardKeyword(HttpServletRequest request,@RequestParam("fullKeyword")String fullKeyword){
        HashMap<String, ArrayList> map = new HashMap<>();
        ArrayList<BoardTO> board = boardService.retrieveBoardKeyword(fullKeyword);
        map.put("writeList", board);
        return map; //리다이렉트 게시글 조회
    }

    @PatchMapping("/saveWrite")
    HashMap<String,Integer> saveWrite(HttpServletRequest request
                    ,@RequestParam("title")String title, @RequestParam("note")String note, @RequestParam("topicNm")String topicNm){

        HashMap<String, Integer> map = new HashMap<>();
        BoardTO boardTO=new BoardTO();
        boardTO.setTitle(title); boardTO.setNote(note); boardTO.setTopicNm(topicNm); boardTO.setWriter((String)request.getSession().getAttribute("memberId"));
        map.put("errorCode", 0);
        try {
            boardService.saveWrite(boardTO);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("errorCode", -1);
        }
        return map;
    }

    @GetMapping("/retrieveTopicList")
    String retrieveTopicList(){
        ArrayList<BoardTO> topicList=boardService.retrieveTopicList();
        return gson.toJson(topicList);
    }

    @GetMapping("/retrieveBoardRead")
    String retrieveBoardRead(@RequestParam("boardData")String boardData){
        BoardTO board = gson.fromJson(boardData, BoardTO.class);
        return gson.toJson(boardService.retrieveBoardRead(board));
    }
}
