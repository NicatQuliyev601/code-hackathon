package az.code.codehackathon.model;qw

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double technicalStaffScore;
    private double juryScore;
    private double averageScore;
    private int technicalStaffVoteCount;
    private int juryVoteCount;


    public Team(String name, double technicalStaffScore, double juryScore, int technicalStaffVoteCount, int juryVoteCount) {
        this.name = name;
        this.technicalStaffScore = technicalStaffScore;
        this.juryScore = juryScore;
        this.technicalStaffVoteCount = technicalStaffVoteCount;
        this.juryVoteCount = juryVoteCount;
//        this.averageScore = calculateAverageScore(technicalStaffScore, juryScore);
    }

    public Team() {

    }
}
