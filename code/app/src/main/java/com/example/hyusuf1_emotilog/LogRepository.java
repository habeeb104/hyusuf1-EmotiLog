package com.example.hyusuf1_emotilog;

import java.util.Date;
import java.util.List;

/**
 * Purpose: Defines the contract for data storage and retrieval.
 * Design Rationale: Uses an interface to abstract the data access layer.
 * This ensures the application remains independent of the specific storage
 * implementation.
 */
interface LogRepository {
    void addLog(LogEntry entry);

    List<LogEntry> getLogsByDate(Date date);

    List<LogEntry> getLogsInPeriod(Date start, Date end);

}
