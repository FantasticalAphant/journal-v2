package io.github.generallyspecific.journalredux.journal;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JournalRepository extends JpaRepository<Journal, UUID> {
    // Get all journals for a specific user sorted by creation date
    @Query("SELECT j FROM Journal j WHERE j.authorId = :authorId ORDER BY j.createdAt DESC")
    List<Journal> getAllJournalsForUserByCreatedAt(@Param("authorId") UUID authorId);

    // Get all journals for a specific user sorted by modification date
    @Query("SELECT j FROM Journal j WHERE j.authorId = :authorId ORDER BY j.modifiedAt DESC")
    List<Journal> getAllJournalsForUserByModifiedAt(@Param("authorId") UUID authorId);

    // Get the most recently modified journal for a specific user (used for dashboard)
    @Query("SELECT j FROM Journal j WHERE j.authorId = :authorId ORDER BY j.modifiedAt")
    Page<Journal> getMostRecentlyModifiedJournalForUser(@Param("authorId") UUID authorId, Pageable pageable);

    // Get the journal name from the journalId (used for the journal entry page)
    @Query("SELECT j.name FROM Journal j WHERE j.journalId = :journalId")
    String getJournalNameFromJournalId(@Param("journalId") UUID journalId);

    // Get journal by journalId
    @Query("SELECT j FROM Journal j WHERE j.journalId = :journalId")
    Journal getJournalByJournalId(@Param("journalId") UUID journalId);

    // Delete a specific journal by journalId
    @Modifying
    @Transactional
    @Query("DELETE FROM Journal j WHERE j.journalId = :journalId")
    void deleteJournalByJournalId(@Param("journalId") UUID journalId);
}
