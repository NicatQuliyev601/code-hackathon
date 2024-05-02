package az.code.codehackathon.dto.response;

import lombok.Data;

@Data
public class StudentResponse {
    private String name;
    private double technicalStaffScore;
    private double juryScore;
    private double averageScore;
}
