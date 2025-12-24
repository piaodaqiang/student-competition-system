package com.example.studentcompetition.service;

import com.example.studentcompetition.model.Competition;
import com.example.studentcompetition.model.Participant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CompetitionService {

    private List<Competition> competitions;
    private List<Participant> participants;

    public CompetitionService() {
        competitions = new ArrayList<>();
        participants = new ArrayList<>();
        
        // 添加示例竞赛数据
        competitions.add(new Competition(1L, "全国大学生程序设计竞赛", "国家级", new Date(), 200, "北京", 30));
        competitions.add(new Competition(2L, "省级数学建模竞赛", "省级", new Date(), 150, "上海", 25));
        competitions.add(new Competition(3L, "校级英语演讲比赛", "校级", new Date(), 50, "广州", 10));
        
        // 添加示例参赛人员数据
        participants.add(new Participant(1L, "张三", "计算机学院", "计科1班", "20200001", 1L));
        participants.add(new Participant(2L, "李四", "数学学院", "数学1班", "20200002", 2L));
        participants.add(new Participant(3L, "王五", "外国语学院", "英语1班", "20200003", 3L));
    }

    public List<Competition> listCompetitions() {
        return competitions;
    }

    public Competition addCompetition(Competition competition) {
        competition.setId((long) (competitions.size() + 1));
        competitions.add(competition);
        return competition;
    }

    public Competition updateCompetition(Long id, Competition competitionDetails) {
        Optional<Competition> optionalCompetition = competitions.stream()
                .filter(comp -> comp.getId().equals(id))
                .findFirst();
        
        if (optionalCompetition.isPresent()) {
            Competition competition = optionalCompetition.get();
            competition.setName(competitionDetails.getName());
            competition.setLevel(competitionDetails.getLevel());
            competition.setTime(competitionDetails.getTime());
            competition.setParticipantCount(competitionDetails.getParticipantCount());
            competition.setLocation(competitionDetails.getLocation());
            competition.setWinnerCount(competitionDetails.getWinnerCount());
            return competition;
        }
        return null;
    }

    public boolean deleteCompetition(Long id) {
        return competitions.removeIf(competition -> competition.getId().equals(id));
    }

    public Competition getById(Long id) {
        Optional<Competition> optionalCompetition = competitions.stream()
                .filter(competition -> competition.getId().equals(id))
                .findFirst();
        return optionalCompetition.orElse(null);
    }
    
    // 参赛人员相关方法
    public List<Participant> getAllParticipants() {
        return participants;
    }
    
    public List<Participant> getParticipantsByCompetitionId(Long competitionId) {
        List<Participant> competitionParticipants = new ArrayList<>();
        for (Participant participant : participants) {
            if (participant.getCompetitionId().equals(competitionId)) {
                competitionParticipants.add(participant);
            }
        }
        return competitionParticipants;
    }
    
    public Participant addParticipant(Participant participant) {
        participant.setId((long) (participants.size() + 1));
        participants.add(participant);
        
        // 更新竞赛的参与人数
        Competition competition = getById(participant.getCompetitionId());
        if (competition != null) {
            competition.setParticipantCount(competition.getParticipantCount() + 1);
        }
        
        return participant;
    }
    
    public boolean deleteParticipant(Long id) {
        Optional<Participant> optionalParticipant = participants.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        
        if (optionalParticipant.isPresent()) {
            Participant participant = optionalParticipant.get();
            
            // 更新竞赛的参与人数
            Competition competition = getById(participant.getCompetitionId());
            if (competition != null && competition.getParticipantCount() > 0) {
                competition.setParticipantCount(competition.getParticipantCount() - 1);
            }
            
            return participants.remove(participant);
        }
        return false;
    }
}
