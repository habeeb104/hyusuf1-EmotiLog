package com.example.hyusuf1_emotilog;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Date;
import java.util.Map;

public class SummaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView tvSummary = findViewById(R.id.tv_summary_display);
        DailySummary summary = new DailySummary(new Date());

        // Use the shared data from MainActivity
        summary.calculateSummary(MainActivity.emotionLogger.getLogsByDate(new Date()));

        StringBuilder sb = new StringBuilder();
        sb.append("Daily Stats\n-----------\n");
        for (Map.Entry<Emotion, Integer> entry : summary.getEmotionCounts().entrySet()) {
            sb.append(entry.getKey().getName())
                    .append(": ")
                    .append(entry.getValue()) // Shows the counts (e.g., 10 times)
                    .append("\n");
        }

        tvSummary.setText(sb.toString());
    }
}