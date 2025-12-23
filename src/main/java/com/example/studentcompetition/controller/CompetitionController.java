package com.example.studentcompetition.controller;

import com.example.studentcompetition.model.Competition;
import com.example.studentcompetition.service.CompetitionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
