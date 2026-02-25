package com.example.hyusuf1_emotilog;

/**
 * Purpose: Represents a predefined feeling
 * Design Rationale: Acts as a reusable value object that stores metadata
 * (name and icon ID) for each emotion. This allows the UI to remain
 * consistent while providing a data structure for logs.
 */

public class Emotion {
    private int id;
    private String name; // e.g., Happy, Sad, Angry
    private int iconId;  // Reference to Android drawable resource

    public Emotion(int id, String name, int iconId) {
        this.id = id;
        this.name = name;
        this.iconId = iconId;
    }

    public String getName() { return name; }
    public int getIcon() { return iconId; }
}

