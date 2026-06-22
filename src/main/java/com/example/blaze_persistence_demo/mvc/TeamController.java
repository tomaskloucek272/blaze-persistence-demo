package com.example.blaze_persistence_demo.mvc;

import com.blazebit.persistence.spring.data.webmvc.EntityViewId;
import com.example.blaze_persistence_demo.service.TeamServiceImpl;
import com.example.blaze_persistence_demo.views.MemberUpdateView;
import com.example.blaze_persistence_demo.views.MemberView;
import com.example.blaze_persistence_demo.views.TeamView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamServiceImpl teamService;

    @GetMapping("/{teamName}/members")
    public ResponseEntity<TeamView> getTeamByTeamName(@PathVariable String teamName) {
        return ResponseEntity.ok(teamService.getTeamByTeamName(teamName));
    }

    @GetMapping("/members/top")
    public ResponseEntity<List<MemberView>> getMembersWithPointsAbove(
            @RequestParam(defaultValue = "50") int minPoints) {
        return ResponseEntity.ok(teamService.getMembersWithPointsAbove(minPoints));
    }

    @GetMapping("/members/by-email")
    public ResponseEntity<MemberView> getMemberByEmail(@RequestParam String email) {
        return teamService.getMemberByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-member-location")
    public ResponseEntity<List<TeamView>> getTeamsByMemberLocation(@RequestParam String location) {
        return ResponseEntity.ok(teamService.getTeamsByMemberLocation(location));
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<Void> updateMember(@EntityViewId @PathVariable Long id, @RequestBody MemberUpdateView member) {
        teamService.updateMember(member);
        return ResponseEntity.noContent().build();
    }
}
