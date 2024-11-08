package io.github.generallyspecific.journalv2.tag;

import io.github.generallyspecific.journalv2.journalentry.JournalEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    // Get a list of all tags
    @GetMapping("/tags")
    public List<Tag> getTags() {
        return tagService.getAllTags();
    }

    // Create a new tag
    @PostMapping("/tags")
    public ResponseEntity<Void> addTag(@RequestBody Tag tag) {
        // TODO: Just take tag name from the request body instead of the entire tag object
        // TODO: make sure no duplicate (same name) tags are created
        if (!tag.getTagName().isEmpty()) {
            tagService.saveTag(tag);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/tags/entry/{entryId}/update")
    public ResponseEntity<Void> updateTagsForEntry(@PathVariable UUID entryId, @RequestParam List<UUID> tags) {
        tagService.updateTagsForEntry(entryId, tags);

        return ResponseEntity.ok().build();
    }

    // Show all entries for a tag
    @GetMapping("/tag/{tagId}")
    public Tag getEntriesForTag(@PathVariable UUID tagId) {
        return tagService.getTag(tagId);
    }

    // Rename tag
    @PostMapping("/tag/{tagId}/rename")
    public ResponseEntity<Void> renameTag(@PathVariable UUID tagId, @RequestParam String tagName) {
        tagService.renameTag(tagId, tagName);
        return ResponseEntity.ok().build();
    }

    // Delete tag
    @DeleteMapping("/tag/{tagId}/delete")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID tagId) {
        tagService.deleteTag(tagId);

        return ResponseEntity.ok().build();
    }
}
