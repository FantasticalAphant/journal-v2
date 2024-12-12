export interface Entry {
    entryId: number;
    title: string;
    body: string;
    createdAt: Date;
    modifiedAt: Date;
    journalName: string;
    journalId: number;
    longitude: number;
    latitude: number;
    tags: string[];
}

export interface Journal {
    name: string;
    description: string;
    createdAt: Date;
    modifiedAt: Date;
    journalId: number;
    entries: Entry[];
}

export interface Tag {
    tagId: number;
    tagName: string;
    entries: Entry[];
}