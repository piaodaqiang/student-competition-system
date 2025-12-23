package com.example.studentcompetition.controller;

import com.example.studentcompetition.model.Competition;
import com.example.studentcompetition.service.CompetitionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    // 1️⃣ 查询全部竞赛
    @GetMapping
    public List<Competition> listCompetitions() {
        return competitionService.listCompetitions();
    }

    // 2️⃣ 新增竞赛
    @PostMapping
    public String addCompetition(@RequestBody Competition competition) {
        competitionService.addCompetition(competition);
        return "新增成功";
    }

    // 3️⃣ 根据 ID 查询竞赛详情
    @GetMapping("/{id}")
    public Competition getById(@PathVariable int id) {
        return competitionService.getById(id);
    }
}
