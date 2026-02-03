package com.example.hyusuf1_emotilog;

import java.time.LocalDateTime;

/**
 * Represents a log entry created when user selects an emotion.
 *
 * Each LogEntry associates one Emotion with a timestamp.
 */

public class LogEntry {
    private Emotion emotion;
    private long timestamp;

    public LogEntry(Emotion emotion) {
        this.emotion = emotion;
        this.timestamp = System.currentTimeMillis();
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
