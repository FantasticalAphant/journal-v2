package io.github.generallyspecific.journalv2.journalentry;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JournalEntryRepository extends CrudRepository<JournalEntry, UUID> {
    // Get all entries
    @Query("SELECT j FROM JournalEntry j ORDER BY j.createdAt DESC")
    List<JournalEntry> getAllJournalEntries();

    // Get all entries for a journal
    @Query("SELECT j FROM JournalEntry j WHERE j.journal.journalId = :journalId ORDER BY j.createdAt DESC")
    List<JournalEntry> findJournalEntriesByJournalId(@Param("journalId") UUID journalId);

    // Get a specific entry by entryId
    @Query("SELECT j FROM JournalEntry j WHERE j.entryId = :entryId")
    JournalEntry findJournalEntryByEntryId(@Param("entryId") UUID entryId);

    // Delete a specific entry by journalId and entryId
    @Transactional
    @Modifying
    @Query("DELETE FROM JournalEntry j WHERE j.journal.journalId = :journalId AND j.entryId = :entryId")
    void deleteJournalEntryByJournalIdAndEntryId(@Param("journalId") UUID journalId, @Param("entryId") UUID entryId);

    // Delete all entries for a specific journal given journalId
    @Transactional
    @Modifying
    @Query("DELETE FROM JournalEntry j WHERE j.journal.journalId = :journalId")
    void deleteJournalEntriesByJournalId(@Param("journalId") UUID journalId);

    // Get the three most recently modified entries sorted by modifiedAt
    @Query("SELECT j FROM JournalEntry j JOIN Journal jo ON j.journal.journalId = jo.journalId ORDER BY j.modifiedAt DESC")
    List<JournalEntry> findThreeMostRecentlyModifiedEntries(Pageable pageable);
}
