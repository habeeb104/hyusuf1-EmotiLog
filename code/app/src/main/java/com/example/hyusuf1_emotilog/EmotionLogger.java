package com.example.hyusuf1_emotilog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Purpose: Implementation of the LogRepository for session-based logging.
 * Design Rationale: Manages a collection of LogEntry objects in an ArrayList.
 * It handles logic for filtering logs by specific dates.
 * Outstanding Issues: Data is not persistent across app restarts
 */

public class EmotionLogger implements LogRepository {
    private List<LogEntry> logs = new ArrayList<>();

    @Override
    public void addLog(LogEntry entry) {
    // Handles the creation and storage of log data
        logs.add(entry);
    }

    @Override
    public List<LogEntry> getLogsByDate(Date targetDate) {
    // Queries logs based on a specific calendar day
        return logs.stream()
                .filter(log -> isSameDay(log.getTimestamp(), targetDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<LogEntry> getLogsInPeriod(Date start, Date end) {
    // Retrieves logs within a specified time range
        return logs.stream()
                .filter(log -> {
                    Date logDate = log.getTimestamp();
                    // !before(start) = same as or after start
                    // !after(end) = same as or before end
                    return !logDate.before(start) && !logDate.after(end);
                })
                .collect(Collectors.toList());
    }

    /**
     * Helper method to compare only Year, Month, and Day.
     * Required because Date.equals() checks down to the millisecond.
     */
    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    public List<LogEntry> getAllLogs() {
        return new ArrayList<>(logs); // Returns everything currently in memory
    }
}