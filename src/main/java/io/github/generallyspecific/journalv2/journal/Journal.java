package io.github.generallyspecific.journalv2.journal;

import io.github.generallyspecific.journalv2.journalentry.JournalEntry;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="journals")
public class Journal {
    private String name;

    @Id
    @Column(name="journal_id")
    private UUID journalId;

    @Column(name="created_at")
    private Instant createdAt;

    @Column(name="modified_at")
    private Instant modifiedAt;

    private String description;

    @OneToMany(mappedBy = "journal", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JournalEntry> entries = new HashSet<>();

    public Journal() {
    }

    public Journal(String name, UUID journalId, Instant createdAt, Instant modifiedAt, String description, Set<JournalEntry> entries) {
        this.name = name;
        this.journalId = journalId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.description = description;
        this.entries = entries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getJournalId() {
        return journalId;
    }

    public void setJournalId(UUID journalId) {
        this.journalId = journalId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<JournalEntry> getEntries() {
        return entries;
    }

    public void addEntry(JournalEntry entry) {
        entries.add(entry);
        entry.setJournal(this);
    }

    public void removeEntry(JournalEntry entry) {
        entries.remove(entry);
        entry.setJournal(null);
    }

    @Override
    public String toString() {
        return "Journal{" +
                "name='" + name + '\'' +
                ", journalId=" + journalId +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", description='" + description + '\'' +
                ", entries=" + entries +
                '}';
    }
}
