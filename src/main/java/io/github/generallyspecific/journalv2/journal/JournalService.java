package io.github.generallyspecific.journalv2.journal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
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

    @Transactional(readOnly = true)
    public List<Journal> getAllJournalsByCreatedAt() {
        return journalRepository.getAllJournalsByCreatedAt();
    }

    @Transactional(readOnly = true)
    public List<Journal> getAllJournalsByModifiedAt() {
        return journalRepository.getAllJournalsByModifiedAt();
    }

    @Transactional(readOnly = true)
    public Journal getJournalByJournalId(UUID journalId) {
        return journalRepository.getJournalByJournalId(journalId);
    }

    public void deleteJournalByJournalId(UUID journalId) {
        journalRepository.deleteById(journalId);
    }
}
