package io.github.generallyspecific.journalv2.journalentry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "${cors.allowed.origins")
public class JournalEntryController {
    private final JournalEntryService journalEntryService;

    @Autowired
    public JournalEntryController(JournalEntryService journalEntryService) {
        this.journalEntryService = journalEntryService;
    }

    // Create a new entry for a journal
    @PostMapping("/journals/{journalId}")
    public ResponseEntity<JournalEntry> entrySubmit(@PathVariable UUID journalId, @RequestBody JournalEntry entry) {
        JournalEntry newEntry = journalEntryService.createJournalEntry(journalId, entry);

        return ResponseEntity.status(HttpStatus.CREATED).body(newEntry);
    }

    // Display individual entry
    @GetMapping("/entry/{entryId}")
    public JournalEntry getEntry(@PathVariable UUID entryId) {
        return journalEntryService.getJournalEntry(entryId);

        // TODO: return all tags for the journal entry

//        model.addAttribute("entry", entry);
//        model.addAttribute("tags", journalEntryService.getAllTagsForUser());
    }

    // Update individual entry
    @PostMapping("/entry/{entryId}")
    public ResponseEntity<Void> updateEntry(@PathVariable UUID entryId, @RequestBody JournalEntry entry) {
        journalEntryService.updateJournalEntry(entryId, entry);

        return ResponseEntity.ok().build();
    }

    // Delete individual entry
    @DeleteMapping("/entry/{entryId}")
    public ResponseEntity<Void> deleteEntry(@RequestParam UUID journalId, @PathVariable UUID entryId) {
        journalEntryService.deleteJournalEntry(journalId, entryId);
        return ResponseEntity.ok().build();
    }

    // Get all journal entries
    @GetMapping("/entries")
    public List<JournalEntry> getEntries() {
        return journalEntryService.getAllJournalEntries();
    }
}
