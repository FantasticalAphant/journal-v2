package io.github.generallyspecific.journalv2.journal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class JournalController {
    private final JournalService journalService;

    @Autowired
    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    // Get all journals
    @GetMapping("/journals")
    public List<Journal> getJournals(OAuth2AuthenticationToken authentication) {
        System.out.println(authentication.getName());
        return journalService.getAllJournalsByCreatedAt();
    }

    // Create a new journal
    @PostMapping("/journals")
    public ResponseEntity<Void> createJournal(@RequestBody Journal journal) {
        journalService.createJournal(journal);
        return ResponseEntity.ok().build();
    }

    // Update a journal's name and/or description
    @PostMapping("/journals/{journalId}/update")
    public ResponseEntity<Void> updateJournal(@PathVariable UUID journalId, @RequestParam String name, @RequestParam(required = false) String description) {
        // Check if the operation actually succeeded
        journalService.updateJournal(journalId, name, description);
        return ResponseEntity.ok().build();
    }

    // Delete a journal
    @DeleteMapping("/journals/{journalId}/delete")
    public ResponseEntity<Void> deleteJournal(@PathVariable UUID journalId) {
        // Check if the operation actually succeeded
        journalService.deleteJournalByJournalId(journalId);
        return ResponseEntity.ok().build();
    }

}
