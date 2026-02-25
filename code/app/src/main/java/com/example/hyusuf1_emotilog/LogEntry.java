package com.example.hyusuf1_emotilog;

import java.util.Date;

/**
 * Purpose: Records a specific instance of a user logging their feelings.
 * Design Rationale: Associates a specific Emotion object with a timestamp
 * and a unique ID.
 */
public class LogEntry {
    private long entryId;
    private Date timestamp;
    private Emotion emotion; // Associated with exactly one Emotion

    public LogEntry(long entryId, Date timestamp, Emotion emotion) {
        this.entryId = entryId;
        this.timestamp = timestamp;
        this.emotion = emotion;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public long getEntryId() {
        return entryId;
    }
}