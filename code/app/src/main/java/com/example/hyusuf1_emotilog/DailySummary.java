package com.example.hyusuf1_emotilog;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Purpose: Logic class responsible for generating statistics from raw logs.
 * Design Rationale: Separates the "analysis" logic from the "storage" logic.
 * It processes a list of logs to calculate total counts and individual
 * frequencies, providing the formatted data needed for the UI display.
 */
public class DailySummary {
    private Date date;
    private Map<Emotion, Integer> emotionCounts = new HashMap<>();

    public DailySummary(Date date) {
        this.date = date;
    }

    /**
     * Processes collections of LogEntry objects to calculate totals
     * and frequency distributions.
     */
    public void calculateSummary(List<LogEntry> dayLogs) {
        emotionCounts.clear();
        for (LogEntry log : dayLogs) {
            Emotion e = log.getEmotion();
            // Increment the count for this specific emotion
            int currentCount = emotionCounts.getOrDefault(e, 0);
            emotionCounts.put(e, currentCount + 1);
        }
    }

    public int getTotalCount() {
        // Calculate total logs for the day
        int total = 0;
        for (int count : emotionCounts.values()) {
            total += count;
        }
        return total;
    }

    public double getFrequency(Emotion emotion) {
        // Calculate emotion frequencies
        int total = getTotalCount();
        if (total == 0) return 0.0;
        return (double) emotionCounts.getOrDefault(emotion, 0) / total;
    }

    public Map<Emotion, Integer> getEmotionCounts() {
        return emotionCounts; // Present summarized data
    }
}