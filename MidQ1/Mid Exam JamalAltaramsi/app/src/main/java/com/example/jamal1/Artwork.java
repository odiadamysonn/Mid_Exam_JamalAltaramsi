package com.example.jamal1;

public class Artwork {
    private final int imageResId;
    private final String title;
    private final String artist;
    private final int year;
    private final String websiteUrl;

    public Artwork(int imageResId, String title, String artist, int year, String websiteUrl) {
        this.imageResId = imageResId;
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.websiteUrl = websiteUrl;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getYear() {
        return year;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }
}