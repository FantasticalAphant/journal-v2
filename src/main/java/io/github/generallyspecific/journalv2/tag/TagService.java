package io.github.generallyspecific.journalv2.tag;

import io.github.generallyspecific.journalv2.journalentry.JournalEntry;
import io.github.generallyspecific.journalv2.journalentry.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final JournalEntryRepository journalEntryRepository;

    @Autowired
    public TagService(TagRepository tagRepository, JournalEntryRepository journalEntryRepository) {
        this.tagRepository = tagRepository;
        this.journalEntryRepository = journalEntryRepository;
    }

    public List<Tag> getAllTags() {
        return tagRepository.getAllTags();
    }

    public List<Tag> getTwoMostRecentTags() {
        return tagRepository.findTwoMostRecentlyModifiedTags(PageRequest.of(0, 2));
    }

    public void saveTag(Tag tag) {
        tag.setTagId(UUID.randomUUID());
        tagRepository.save(tag);
    }

    public void renameTag(UUID tagId, String tagName) {
        Tag tag = tagRepository.findTagByTagId(tagId);
        tag.setTagName(tagName);
        tagRepository.save(tag);
    }

    public void deleteTag(UUID tagId) {
        tagRepository.deleteTag(tagId);
    }

    // Update the tags for a specific entry
    public void updateTagsForEntry(UUID entryId, List<UUID> tags) {
        JournalEntry entry = journalEntryRepository.findJournalEntryByEntryId(entryId);

        Set<Tag> currentTags = entry.getTags();

        List<Tag> tagList = tagRepository.findAllById(tags);
        Set<Tag> updatedTags = new HashSet<>(tagList);

        currentTags.retainAll(updatedTags);
        currentTags.addAll(updatedTags);

        journalEntryRepository.save(entry);
    }

    // Get all the corresponding journal entries for a specific tag
    public List<JournalEntry> getEntriesForTag(UUID tagId) {
        return tagRepository.getEntriesForTag(tagId);
    }

}
