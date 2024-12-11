package io.github.generallyspecific.journalv2.journalentry;

import io.github.generallyspecific.journalv2.journal.Journal;
import io.github.generallyspecific.journalv2.tag.Tag;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "journal_entries")
public class JournalEntry {
    @Id
    @Column(name = "entry_id")
    private UUID entryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "journal_id")
    private Journal journal;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "modified_at")
    private Instant modifiedAt;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    private Double longitude;
    private Double latitude;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "journal_entry_tags",
            joinColumns = @JoinColumn(name = "entry_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    public JournalEntry() {
    }

    public JournalEntry(UUID entryId, Journal journal, Instant createdAt, Instant modifiedAt, String title, String body, Double longitude, Double latitude, Set<Tag> tags) {
        this.entryId = entryId;
        this.journal = journal;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.title = title;
        this.body = body;
        this.longitude = longitude;
        this.latitude = latitude;
        this.tags = tags;
    }

    public UUID getEntryId() {
        return entryId;
    }

    public void setEntryId(UUID entryId) {
        this.entryId = entryId;
    }

    public UUID getJournalId() {
        return journal != null ? journal.getJournalId() : null;
    }

    public String getJournalName() {
        return journal != null ? journal.getName() : null;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "JournalEntry{" +
                "entryId=" + entryId +
                ", journal=" + journal +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", tags=" + tags +
                '}';
    }
}
