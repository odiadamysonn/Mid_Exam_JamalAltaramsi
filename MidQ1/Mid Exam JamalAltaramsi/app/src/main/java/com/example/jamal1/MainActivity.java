package com.example.jamal1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "current_index";

    private TextView tvCounter;
    private ImageView ivArtwork;
    private TextView tvTitle;
    private TextView tvArtistYear;
    private Button btnPrevious;
    private Button btnNext;

    private List<Artwork> artworks;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCounter = findViewById(R.id.tvCounter);
        ivArtwork = findViewById(R.id.ivArtwork);
        tvTitle = findViewById(R.id.tvTitle);
        tvArtistYear = findViewById(R.id.tvArtistYear);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        initArtworks();

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        updateUI();

        btnPrevious.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateUI();
            }
        });

        btnNext.setOnClickListener(v -> {
            if (currentIndex < artworks.size() - 1) {
                currentIndex++;
                updateUI();
            }
        });

        ivArtwork.setOnClickListener(v -> {
            Artwork current = artworks.get(currentIndex);
            Intent intent = new Intent(MainActivity.this, ArtworkDetailsActivity.class);
            intent.putExtra(ArtworkDetailsActivity.EXTRA_TITLE, current.getTitle());
            intent.putExtra(ArtworkDetailsActivity.EXTRA_ARTIST, current.getArtist());
            intent.putExtra(ArtworkDetailsActivity.EXTRA_YEAR, current.getYear());
            intent.putExtra(ArtworkDetailsActivity.EXTRA_URL, current.getWebsiteUrl());
            startActivity(intent);
        });
    }

    private void initArtworks() {
        artworks = new ArrayList<>();
        artworks.add(new Artwork(R.drawable.art1, "Starry Night", "Vincent van Gogh", 1889, "https://www.moma.org"));
        artworks.add(new Artwork(R.drawable.art2, "The Persistence of Memory", "Salvador Dalí", 1931, "https://www.moma.org"));
        artworks.add(new Artwork(R.drawable.art3, "Girl with a Pearl Earring", "Johannes Vermeer", 1665, "https://www.mauritshuis.nl"));
        artworks.add(new Artwork(R.drawable.art4, "The Scream", "Edvard Munch", 1893, "https://www.nasjonalmuseet.no"));
        artworks.add(new Artwork(R.drawable.art5, "Water Lilies", "Claude Monet", 1919, "https://www.musee-orangerie.fr"));
        artworks.add(new Artwork(R.drawable.art6, "The Great Wave off Kanagawa", "Hokusai", 1831, "https://www.metmuseum.org"));
    }

    private void updateUI() {
        Artwork current = artworks.get(currentIndex);
        tvCounter.setText((currentIndex + 1) + " / " + artworks.size());
        ivArtwork.setImageResource(current.getImageResId());
        tvTitle.setText(current.getTitle());
        tvArtistYear.setText(current.getArtist() + " (" + current.getYear() + ")");

        btnPrevious.setEnabled(currentIndex > 0);
        btnNext.setEnabled(currentIndex < artworks.size() - 1);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, currentIndex);
    }
}