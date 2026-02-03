package com.example.hyusuf1_emotilog;

/**
 * Represents predefined emotion that can be logged by user.
 * This class is a simple data holder, does not contain behavior.
 *
 * Design rationale:
 * Emotions differ only by name and icon, not behavior, so inheritance
 * was avoided for a single reusable class.
 */

public class Emotion {
    private int id;
    private String name;
    private int iconResId;

    public Emotion(int id, String name, int iconResId) {
        this.id = id;
        this.name = name;
        this.iconResId = iconResId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getIconResId() {
        return iconResId;
    }
}
}
