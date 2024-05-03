package az.code.codehackathon.controllor;
import az.code.codehackathon.dto.request.JuryStaffRequest;
import az.code.codehackathon.dto.request.TechnicalStaffRequest;
import az.code.codehackathon.dto.response.JuryStaffResponse;
import az.code.codehackathon.dto.response.StudentResponse;
import az.code.codehackathon.dto.response.TechnicalStaffResponse;
import az.code.codehackathon.model.Team;
import az.code.codehackathon.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team createdTeam = teamService.createTeam(team);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    @PutMapping("/{teamId}/technical-score")
    public ResponseEntity<Team> updateTechnicalStaffScore(@PathVariable Long teamId, @RequestBody TechnicalStaffRequest request) {
        Team updatedTeam = teamService.updateTechnicalStaffScore(teamId, request);
        return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
    }

    @PutMapping("/{teamId}/jury-score")
    public ResponseEntity<Team> updateJuryScore(@PathVariable Long teamId, @RequestBody JuryStaffRequest request) {
        Team updatedTeam = teamService.updateJuryScore(teamId, request);
        return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
    }

    @GetMapping("/technical-scores")
    public ResponseEntity<List<TechnicalStaffResponse>> getAllTechnicalScores() {
        List<TechnicalStaffResponse> technicalScores = teamService.getAllTechnicalScores();
        return new ResponseEntity<>(technicalScores, HttpStatus.OK);
    }

    @GetMapping("/jury-scores")
    public ResponseEntity<List<JuryStaffResponse>> getAllJuryScores() {
        List<JuryStaffResponse> juryScores = teamService.getAllJuryScores();
        return new ResponseEntity<>(juryScores, HttpStatus.OK);
    }

    @GetMapping("/all-scores")
    public ResponseEntity<List<StudentResponse>> getAllScores() {
        List<StudentResponse> allScores = teamService.getAllScores();
        return new ResponseEntity<>(allScores, HttpStatus.OK);
    }
}
