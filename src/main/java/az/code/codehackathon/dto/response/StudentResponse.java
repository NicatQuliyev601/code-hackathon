package az.code.codehackathon.dto.response;

import lombok.Data;

@Data

public class StudentResponse {
    private String name;
    private double technicalStaffScore;
    private double juryScore;
    private double averageScore;

    public StudentResponse(String name, double technicalStaffScore, double juryScore, double averageScore) {

    }
}
