package io.github.generallyspecific.journalv2.journalentry;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class JournalInfoDTO {
    // This class is used to return a journal name and its entries
    private String journalName;
    private UUID journalId;
    private List<JournalEntry> entries;

    public JournalInfoDTO() {
    }

    public JournalInfoDTO(String journalName, UUID journalId, List<JournalEntry> entries) {
        this.journalName = journalName;
        this.journalId = journalId;
        this.entries = new ArrayList<>(entries);
    }

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    public UUID getJournalId() {
        return journalId;
    }

    public void setJournalId(UUID journalId) {
        this.journalId = journalId;
    }

    public List<JournalEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<JournalEntry> entries) {
        this.entries = new ArrayList<>(entries);
    }

    @Override
    public String toString() {
        return "JournalInfoDTO{" +
                "journalName='" + journalName + '\'' +
                ", journalId=" + journalId +
                ", entries=" + entries +
                '}';
    }
}
