CREATE TABLE journal_entries
(
    entry_id    UUID NOT NULL,
    journal_id  UUID,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    title       VARCHAR(255),
    body        VARCHAR(255),
    CONSTRAINT pk_journal_entries PRIMARY KEY (entry_id)
);

CREATE TABLE journal_entry_tags
(
    entry_id UUID NOT NULL,
    tag_id   UUID NOT NULL,
    CONSTRAINT pk_journal_entry_tags PRIMARY KEY (entry_id, tag_id)
);

CREATE TABLE journals
(
    journal_id  UUID NOT NULL,
    name        VARCHAR(255),
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    description VARCHAR(255),
    CONSTRAINT pk_journals PRIMARY KEY (journal_id)
);

CREATE TABLE tags
(
    tag_id   UUID NOT NULL,
    tag_name VARCHAR(255),
    CONSTRAINT pk_tags PRIMARY KEY (tag_id)
);

ALTER TABLE journal_entries
    ADD CONSTRAINT FK_JOURNAL_ENTRIES_ON_JOURNAL FOREIGN KEY (journal_id) REFERENCES journals (journal_id);

ALTER TABLE journal_entry_tags
    ADD CONSTRAINT fk_jouenttag_on_journal_entry FOREIGN KEY (entry_id) REFERENCES journal_entries (entry_id);

ALTER TABLE journal_entry_tags
    ADD CONSTRAINT fk_jouenttag_on_tag FOREIGN KEY (tag_id) REFERENCES tags (tag_id);