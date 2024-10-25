package io.github.generallyspecific.journalredux.journal;

import io.github.generallyspecific.journalredux.journalentry.JournalEntryRepository;
import io.github.generallyspecific.journalredux.oauth2.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class JournalService {
    private final JournalRepository journalRepository;
    private final JournalEntryRepository journalEntryRepository;
    private final UserService userService;

    @Autowired
    public JournalService(JournalRepository journalRepository, JournalEntryRepository journalEntryRepository, UserService userService) {
        this.journalRepository = journalRepository;
        this.journalEntryRepository = journalEntryRepository;
        this.userService = userService;
    }

    public void createJournal(Journal journal) {
        UUID id = userService.getUserIdFromServiceIdAndService();

        journal.setAuthorId(id);
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

    public List<Journal> getAllJournalsForUserByCreatedAt(UUID authorId) {
        return journalRepository.getAllJournalsForUserByCreatedAt(authorId);
    }

    public List<Journal> getAllJournalsForUserByModifiedAt(UUID authorId) {
        return journalRepository.getAllJournalsForUserByModifiedAt(authorId);
    }

    public Journal getMostRecentJournalForUser(UUID authorId) {
        Page<Journal> journalPage = journalRepository.getMostRecentlyModifiedJournalForUser(authorId, PageRequest.of(0, 1));
        Journal mostRecentJournal = null;

        if (journalPage.hasContent()) {
            mostRecentJournal = journalPage.getContent().get(0);
        }

        return mostRecentJournal;
    }

    public Journal getJournalByJournalId(UUID journalId) {
        return journalRepository.getJournalByJournalId(journalId);
    }

    public void deleteJournalByJournalId(UUID journalId) {
        journalRepository.deleteJournalByJournalId(journalId);
        // Also delete all the corresponding entries
        journalEntryRepository.deleteJournalEntriesByJournalId(journalId);
    }
}
