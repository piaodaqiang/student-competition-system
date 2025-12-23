package com.example.studentcompetition.service;

import com.example.studentcompetition.model.Competition;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompetitionService {

    public List<Competition> listCompetitions() {
        // 使用内存数据模拟数据库
        List<Competition> list = new ArrayList<>();

        list.add(new Competition(1L, "中国大学生计算机设计大赛", "国家级"));
        list.add(new Competition(2L, "蓝桥杯程序设计大赛", "省级"));
        list.add(new Competition(3L, "互联网+创新创业大赛", "国家级"));

        return list;
    }
}
