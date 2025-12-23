package com.example.studentcompetition.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.studentcompetition.model.Competition;

@Service
public class CompetitionService {

    private final List<Competition> competitions;

    // 构造函数，初始化示例数据
    public CompetitionService() {
        competitions = new ArrayList<>();
        // 使用内存数据模拟数据库
        competitions.add(new Competition(1L, "中国大学生计算机设计大赛", "国家级"));
        competitions.add(new Competition(2L, "蓝桥杯程序设计大赛", "省级"));
        competitions.add(new Competition(3L, "互联网+创新创业大赛", "国家级"));
    }

    public List<Competition> listCompetitions() {
        return competitions;
    }

    public void addCompetition(Competition competition) {
        // 课程设计阶段，添加到内存列表
        competitions.add(competition);
        System.out.println("新增竞赛：" + competition.getName());
    }

    public Competition getById(int id) {
        for (Competition c : competitions) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null; // 简单课程设计可以先这样
    }
}
