package io.github.generallyspecific.journalredux.journal;

import io.github.generallyspecific.journalredux.oauth2.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JournalController {
    private final JournalService journalService;
    private final UserService userService;

    @Autowired
    public JournalController(JournalService journalService, UserService userService) {
        this.journalService = journalService;
        this.userService = userService;
    }

    // Get all journals
    @GetMapping("/journals")
    public List<Journal> getJournals() {
        return journalService.getAllJournalsForUserByModifiedAt(userService.getUserIdFromServiceIdAndService());
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
