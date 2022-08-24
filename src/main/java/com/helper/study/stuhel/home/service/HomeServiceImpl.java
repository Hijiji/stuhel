package com.helper.study.stuhel.home.service;

import com.helper.study.stuhel.home.mapper.HomeMapper;
import com.helper.study.stuhel.home.to.BookTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class HomeServiceImpl implements HomeService {

    private final HomeMapper homeMapper;

    @Autowired
    public HomeServiceImpl(HomeMapper homeMapper) {
        this.homeMapper = homeMapper;
    }


    @Override
    public ArrayList<Integer> retrieveBookableRoom(BookTO bookTO) {
        ArrayList<Integer> roomName = homeMapper.selectBookableRoom(bookTO);
        return roomName;
    }

    @Override
    public HashMap<String, String> addReservationInfo(BookTO bookTO) {
        HashMap<String,String> map =new HashMap<>();
        try {
            homeMapper.insertReservationInfo(bookTO);
        }catch (Exception e) {
            map.put("errorCd","Y");
        }
        return map;
    }
}
