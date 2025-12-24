package com.example.studentcompetition.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentcompetition.model.Competition;
import com.example.studentcompetition.model.Participant;
import com.example.studentcompetition.service.CompetitionService;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping
    public List<Competition> listCompetitions() {
        return competitionService.listCompetitions();
    }

    @PostMapping
    public Competition addCompetition(@RequestBody Competition competition) {
        return competitionService.addCompetition(competition);
    }

    @PutMapping("/{id}")
    public Competition updateCompetition(@PathVariable Long id, @RequestBody Competition competition) {
        Competition updatedCompetition = competitionService.updateCompetition(id, competition);
        if (updatedCompetition == null) {
            throw new RuntimeException("Competition not found with id: " + id);
        }
        return updatedCompetition;
    }

    @DeleteMapping("/{id}")
    public boolean deleteCompetition(@PathVariable Long id) {
        return competitionService.deleteCompetition(id);
    }

    @GetMapping("/{id}")
    public Competition getById(@PathVariable Long id) {
        return competitionService.getById(id);
    }
    
    // 参赛人员相关接口
    @GetMapping("/{competitionId}/participants")
    public List<Participant> getParticipantsByCompetitionId(@PathVariable Long competitionId) {
        return competitionService.getParticipantsByCompetitionId(competitionId);
    }
    
    @PostMapping("/{competitionId}/participants")
    public Participant addParticipant(@PathVariable Long competitionId, @RequestBody Participant participant) {
        participant.setCompetitionId(competitionId);
        return competitionService.addParticipant(participant);
    }
    
    @DeleteMapping("/participants/{id}")
    public boolean deleteParticipant(@PathVariable Long id) {
        return competitionService.deleteParticipant(id);
    }
}
