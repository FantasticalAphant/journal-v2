package io.github.generallyspecific.journalv2.journalentry;

import io.github.generallyspecific.journalv2.journal.Journal;
import io.github.generallyspecific.journalv2.journal.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class JournalEntryService {
    private final JournalRepository journalRepository;
    private final JournalEntryRepository journalEntryRepository;

    @Autowired
    public JournalEntryService(JournalRepository journalRepository, JournalEntryRepository journalEntryRepository) {
        this.journalRepository = journalRepository;
        this.journalEntryRepository = journalEntryRepository;
    }

    @Transactional(readOnly = true)
    public List<JournalEntry> getAllJournalEntries() {
        return journalEntryRepository.getAllJournalEntries();
    }

    @Transactional(readOnly = true)
    public List<JournalEntry> getThreeMostRecentJournalEntries() {
        return journalEntryRepository.findThreeMostRecentlyModifiedEntries(PageRequest.of(0, 3));
    }

    @Transactional(readOnly = true)
    public JournalEntry getJournalEntry(UUID entryId) {
        return journalEntryRepository.findJournalEntryByEntryId(entryId);
    }

    public void deleteJournalEntry(UUID journalId, UUID entryId) {
        // TODO: update the modification time of the corresponding journal
        journalEntryRepository.deleteJournalEntryByJournalIdAndEntryId(journalId, entryId);
    }

    public JournalEntry createJournalEntry(UUID journalId, JournalEntry entry) {
        // TODO: Create title from body if the title is not provided
        Journal journal = journalRepository.getJournalByJournalId(journalId);
        entry.setJournal(journal);
        entry.setEntryId(UUID.randomUUID());
        entry.setCreatedAt(Instant.now());
        entry.setModifiedAt(entry.getCreatedAt());
        entry.setTitle(entry.getTitle().trim());
        entry.setBody(entry.getBody().trim());

        journalEntryRepository.save(entry);
        return entry;
    }

    public void updateJournalEntry(UUID entryId, JournalEntry entry) {
        // TODO: update the modification time of the corresponding journal

        JournalEntry existingEntry = journalEntryRepository.findJournalEntryByEntryId(entryId);
        existingEntry.setTitle(entry.getTitle());
        existingEntry.setBody(entry.getBody());
        existingEntry.setModifiedAt(Instant.now());

        journalEntryRepository.save(existingEntry);
    }

    public void saveJournalEntry(JournalEntry entry) {
        journalEntryRepository.save(entry);
    }

}
