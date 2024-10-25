package io.github.generallyspecific.journalv2.journal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class JournalService {
    private final JournalRepository journalRepository;

    @Autowired
    public JournalService(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    public void createJournal(Journal journal) {
        journal.setJournalId(UUID.randomUUID());
        journal.setCreatedAt(Instant.now());
        journal.setModifiedAt(Instant.now());

        journalRepository.save(journal);
    }

    public void updateJournal(UUID journalId, String name, String description) {
        Journal j = this.getJournalByJournalId(journalId);
        j.setName(name);
        if (description != null) {
            j.setDescription(description);
        }
        j.setModifiedAt(Instant.now());

        journalRepository.save(j);
    }

    public List<Journal> getAllJournalsByCreatedAt() {
        return journalRepository.getAllJournalsByCreatedAt();
    }

    public List<Journal> getAllJournalsByModifiedAt() {
        return journalRepository.getAllJournalsByModifiedAt();
    }

    public Journal getJournalByJournalId(UUID journalId) {
        return journalRepository.getJournalByJournalId(journalId);
    }

    public void deleteJournalByJournalId(UUID journalId) {
        journalRepository.deleteJournalByJournalId(journalId);
        // Also delete all the corresponding entries
//        journalEntryRepository.deleteJournalEntriesByJournalId(journalId);
    }
}
