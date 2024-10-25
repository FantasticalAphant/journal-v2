package io.github.generallyspecific.journalv2.tag;

import io.github.generallyspecific.journalv2.journalentry.JournalEntry;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {
    // Get all the tags in alphabetical order
    @Query("SELECT t FROM Tag t ORDER BY t.tagName ASC")
    List<Tag> getAllTags();

    // Find a tag by tagId
    @Query("SELECT t FROM Tag t WHERE t.tagId = :tagId")
    Tag findTagByTagId(UUID tagId);

    // Get all the corresponding journal entries for a specific tag
    @Query("SELECT j FROM JournalEntry j JOIN j.tags t WHERE t.tagId = :tagId ORDER BY j.modifiedAt DESC")
    List<JournalEntry> getEntriesForTag(UUID tagId);

    // Get the three most recently modified tags sorted by modifiedAt
    // TODO: change to most popular tags
    @Query("SELECT t FROM Tag t")
    List<Tag> findTwoMostRecentlyModifiedTags(Pageable pageable);

    // Delete tag given tagId
    @Modifying
    @Transactional
    @Query("DELETE FROM Tag t WHERE t.tagId = :tagId")
    void deleteTag(UUID tagId);
}
