package io.github.generallyspecific.journalredux.journal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="journals")
public class Journal {

    @Column(name="author_id")
    private UUID authorId;

    // TODO: make sure journal names are unique for a given user
    private String name;

    @Id
    @Column(name="journal_id")
    private UUID journalId;

    @Column(name="created_at")
    private Instant createdAt;

    @Column(name="modified_at")
    private Instant modifiedAt;

    private String description;

    // TODO: store the number of entries in the journal

    public Journal() {
    }

    public Journal(UUID authorId, String name, UUID journalId, Instant createdAt, Instant modifiedAt, String description) {
        this.authorId = authorId;
        this.name = name;
        this.journalId = journalId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.description = description;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
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

    @Override
    public String toString() {
        return "Journal{" +
                "authorId=" + authorId +
                ", name='" + name + '\'' +
                ", journalId=" + journalId +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", description='" + description + '\'' +
                '}';
    }
}
