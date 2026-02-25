package com.example.hyusuf1_emotilog;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class LogListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_list);

        TextView tvLogs = findViewById(R.id.tv_log_display);
        List<LogEntry> logs = MainActivity.emotionLogger.getAllLogs();

        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault());

        for (LogEntry log : logs) {
            sb.append(sdf.format(log.getTimestamp())) // Confirms rapid succession timestamps
                    .append(" - ")
                    .append(log.getEmotion().getName())
                    .append("\n");
        }

        tvLogs.setText(sb.length() > 0 ? sb.toString() : "No logs recorded yet.");
    }
}