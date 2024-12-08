package io.github.generallyspecific.journalv2.journal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JournalRepository extends JpaRepository<Journal, UUID> {
    // Get all journals sorted by creation date
    @Query("SELECT j FROM Journal j ORDER BY j.createdAt DESC")
    List<Journal> getAllJournalsByCreatedAt();

    // Get all journals sorted by modification date
    @Query("SELECT j FROM Journal j ORDER BY j.modifiedAt DESC")
    List<Journal> getAllJournalsByModifiedAt();

    // Get the journal name from the journalId (used for the journal entry page)
    @Query("SELECT j.name FROM Journal j WHERE j.journalId = :journalId")
    String getJournalNameFromJournalId(@Param("journalId") UUID journalId);

    // Get journal by journalId
    @Query("SELECT j FROM Journal j WHERE j.journalId = :journalId")
    Journal getJournalByJournalId(@Param("journalId") UUID journalId);
}
