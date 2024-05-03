package az.code.codehackathon.service;


import az.code.codehackathon.dto.request.JuryStaffRequest;
import az.code.codehackathon.dto.request.TechnicalStaffRequest;
import az.code.codehackathon.dto.response.JuryStaffResponse;
import az.code.codehackathon.dto.response.StudentResponse;
import az.code.codehackathon.dto.response.TechnicalStaffResponse;
import az.code.codehackathon.model.Team;
import az.code.codehackathon.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    private final ModelMapper modelMapper;

    public TeamService(TeamRepository teamRepository, ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

    public Team createTeam(Team team) {
        team.setAverageScore(calculateAverageScore(team.getTechnicalStaffScore(), team.getJuryScore()));
        return teamRepository.save(team);
    }

    public Team updateTechnicalStaffScore(Long teamId, TechnicalStaffRequest request) {
        Team team = teamRepository.findById(teamId).orElseThrow();
        team.setTechnicalStaffVoteCount(team.getTechnicalStaffVoteCount());
        team.setTechnicalStaffScore(request.getTechnicalStaffScore());
        team.setAverageScore(calculateAverageScore(team.getTechnicalStaffScore(), team.getJuryScore()));
        return teamRepository.save(team);
    }

    public Team updateJuryScore(Long teamId, JuryStaffRequest request) {
        Team team = teamRepository.findById(teamId).orElseThrow();
        team.setJuryScore(request.getJuryScore());
        team.setAverageScore(calculateAverageScore(team.getTechnicalStaffScore(), team.getJuryScore()));
        return teamRepository.save(team);
    }

    public List<TechnicalStaffResponse> getAllTechnicalScores() {
        return teamRepository.findAll().stream()
                .map(team -> modelMapper.map(team, TechnicalStaffResponse.class))
                .collect(Collectors.toList());
    }

    public List<JuryStaffResponse> getAllJuryScores() {
        return teamRepository.findAll().stream()
                .map(team -> modelMapper.map(team, JuryStaffResponse.class))
                .collect(Collectors.toList());
    }

    public List<StudentResponse> getAllScores() {
        return teamRepository.findAll().stream()
                .map(team -> new StudentResponse(team.getName(), team.getTechnicalStaffScore(), team.getJuryScore(), team.getAverageScore()))
                .collect(Collectors.toList());
    }

    private double calculateAverageScore(double technicalScore, double juryScore) {
        return (technicalScore + juryScore) / 2;
    }
}
