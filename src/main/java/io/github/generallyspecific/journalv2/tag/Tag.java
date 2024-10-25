package io.github.generallyspecific.journalv2.tag;

import io.github.generallyspecific.journalv2.journalentry.JournalEntry;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @Column(name = "tag_id")
    private UUID tagId;

    @Column(name = "tag_name")
    private String tagName;

    @ManyToMany(mappedBy = "tags")
    private Set<JournalEntry> entries = new HashSet<>();

    public Tag() {
    }

    public Tag(UUID tagId, String tagName, Set<JournalEntry> entries) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.entries = entries;
    }

    public UUID getTagId() {
        return tagId;
    }

    public void setTagId(UUID tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Set<JournalEntry> getEntries() {
        return entries;
    }

    public void setEntries(Set<JournalEntry> entries) {
        this.entries = new HashSet<>(entries);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", entries=" + entries +
                '}';
    }
}
