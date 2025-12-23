package com.example.studentcompetition.controller;

import com.example.studentcompetition.model.Competition;
import com.example.studentcompetition.service.CompetitionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
public class CompetitionController {

    private final CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping("/competitions")
    public List<Competition> listCompetitions() {
        return competitionService.listCompetitions();
    }

    @PostMapping("/competitions")
    public String addCompetition(@RequestBody Competition competition) {
        competitionService.addCompetition(competition);
        return "新增成功";
    }
}
