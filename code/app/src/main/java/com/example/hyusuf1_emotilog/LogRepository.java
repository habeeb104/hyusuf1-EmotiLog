package com.example.hyusuf1_emotilog;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface that defines how log entries are stored and retrieved.
 *
 * Design rationale:
 * Separates app logic from the storage mechanism.
 * Allows future replacement with a database without changing logic.
 */

public interface LogRepository {
    void addLog(LogEntry entry);

    List<LogEntry> getLogsByDate(LocalDate date);

    List<LogEntry> getLogsInPeriod(LocalDate start, LocalDate end);
}
