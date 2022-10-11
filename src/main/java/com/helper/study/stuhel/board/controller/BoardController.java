package com.helper.study.stuhel.board.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.helper.study.stuhel.board.servie.BoardServiceImpl;
import com.helper.study.stuhel.board.to.BoardCommentTO;
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
    ArrayList<BoardTO> retrieveBoardList(HttpServletRequest request,@RequestParam("retrieveBoardData")String retrieveBoardData){
        HttpSession session = request.getSession();
        BoardTO boardTO = gson.fromJson(retrieveBoardData, BoardTO.class);
        ArrayList<BoardTO> board=boardService.retrieveBoardList(boardTO);
        return board;
    }

    @GetMapping("/retrieveBoardKeyword")
    ArrayList<BoardTO> retrieveBoardKeyword(HttpServletRequest request,@RequestParam("retrieveBoardData")String retrieveBoardData){
        BoardTO board = gson.fromJson(retrieveBoardData, BoardTO.class);
        return boardService.retrieveBoardKeyword(board); //리다이렉트 게시글 조회
    }

    @PostMapping("/saveWrite")
    HashMap<String,String> saveWrite(HttpServletRequest request,@RequestParam("addNoteData")String addNoteData){
/*        JsonReader reader = new JsonReader(new StringReader(addNoteData)); *//*특수문자 변환*//*
        reader.setLenient(true);*/
        BoardTO boardTO = gson.fromJson(addNoteData, BoardTO.class);
        boardTO.setWriter((String)request.getSession().getAttribute("memberId"));

        return boardService.saveWrite(boardTO);
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
    @PatchMapping("/addViewCount")
    int addViewCount(@RequestParam("boardData")String boardData){
        BoardTO board = gson.fromJson(boardData, BoardTO.class);
        return boardService.addViewCount(board);
    }
    @GetMapping("/retrieveBoardComment")
    ArrayList<BoardCommentTO> retrieveBoardComment (@RequestParam("retrieveCmtData") String retrieveCmtData){
        BoardCommentTO boardCommentTO=gson.fromJson(retrieveCmtData, BoardCommentTO.class);
        ArrayList<BoardCommentTO> boardCommentList = boardService.retrieveBoardComment(boardCommentTO);
        return boardCommentList;
    }
    @PostMapping("/saveComment")
    HashMap<String,String> saveComment (@RequestParam("commentData") String commentData, HttpServletRequest request){
        HttpSession session = request.getSession();
        BoardCommentTO boardComment= gson.fromJson(commentData, BoardCommentTO.class);
        boardComment.setWriter((String)session.getAttribute("memberId"));

        return boardService.saveComment(boardComment);
    }
    @DeleteMapping("/deleteBoardComment")
    HashMap<String,String> deleteBoardComment(@RequestParam("deleteCommentData") String deleteCommentData){
        ArrayList<BoardCommentTO> boardComment = gson.fromJson(deleteCommentData,  new TypeToken<ArrayList<BoardCommentTO>>() {}.getType());
        return boardService.deleteCheckedBoardComment(boardComment);

    }
    @DeleteMapping("/deleteBoard")
    HashMap<String,String> deleteBoard(@RequestParam("deleteBoardData") String deleteBoardData, HttpServletRequest request){
        BoardTO board = gson.fromJson(deleteBoardData, BoardTO.class);
        return boardService.deleteBoard(board);
    }
}
