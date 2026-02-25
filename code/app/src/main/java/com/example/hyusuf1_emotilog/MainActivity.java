package com.example.hyusuf1_emotilog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Purpose: The primary UI controller for the EmotiLog application.
 * Design Rationale: Handles the initialization of 6 preset emotion buttons
 * and connects UI events to the EmotionLogger. It coordinates between the
 * user's input and the generation of the Daily Summary display.
 */
public class MainActivity extends AppCompatActivity {
    public static EmotionLogger emotionLogger;
    private List<Emotion> availableEmotions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Initialize the logger for session persistence
        emotionLogger = new EmotionLogger();

        // 2. Define the 6 preset emotions
        initEmotions();

        // 3. Setup the interactive buttons
        setupEmotionButtons();

        // Navigation to Log List
        findViewById(R.id.btn_view_list).setOnClickListener(v -> {
            startActivity(new Intent(this, LogListActivity.class));
        });

        // Navigation to Summary
        findViewById(R.id.btn_view_summary).setOnClickListener(v -> {
            startActivity(new Intent(this, SummaryActivity.class));
        });

    }

    /**
     * Creates the Emotion data objects as reusable value objects
     * iconId refers to drawable resources
     */
    private void initEmotions() {
        availableEmotions = new ArrayList<>();
        availableEmotions.add(new Emotion(1, "Happy", R.drawable.ic_happy));
        availableEmotions.add(new Emotion(2, "Sad", R.drawable.ic_sad));
        availableEmotions.add(new Emotion(3, "Angry", R.drawable.ic_angry));
        availableEmotions.add(new Emotion(4, "Scared", R.drawable.ic_scared));
        availableEmotions.add(new Emotion(5, "Excited", R.drawable.ic_excited));
        availableEmotions.add(new Emotion(6, "Surprised", R.drawable.ic_surprised));
    }

    /**
     * Connects UI buttons to the EmotionLogger
     */
    private void setupEmotionButtons() {
        int[] buttonIds = {R.id.btn_happy, R.id.btn_sad, R.id.btn_angry,
                R.id.btn_scared, R.id.btn_excited, R.id.btn_surprised};

        for (int i = 0; i < buttonIds.length; i++) {
            final Emotion emotion = availableEmotions.get(i);
            findViewById(buttonIds[i]).setOnClickListener(v -> {
                LogEntry entry = new LogEntry(System.currentTimeMillis(), new Date(), emotion);
                emotionLogger.addLog(entry);
                Toast.makeText(this, emotion.getName() + " Logged!", Toast.LENGTH_SHORT).show();
            });
        }
    }

    /**
     * Creates a new LogEntry with the current timestamp and saves it
     */
    private void logFeeling(Emotion emotion) {
        // Records the emotion and current Date (API 24 compatible)
        LogEntry newEntry = new LogEntry(System.currentTimeMillis(), new Date(), emotion);

        // Add to the logger's managed collection [cite: 92]
        emotionLogger.addLog(newEntry);

        Toast.makeText(this, "Logged: " + emotion.getName(), Toast.LENGTH_SHORT).show();
    }

    /**
     * Example method to trigger the DailySummary logic
     */
    public void onViewSummaryClicked(View v) {
        Date today = new Date();
        List<LogEntry> todayLogs = emotionLogger.getLogsByDate(today);

        DailySummary summary = new DailySummary(today);
        summary.calculateSummary(todayLogs);

        // Logic to display summary.getTotalCount() or frequencies
        String result = "Total logs today: " + summary.getTotalCount();
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}