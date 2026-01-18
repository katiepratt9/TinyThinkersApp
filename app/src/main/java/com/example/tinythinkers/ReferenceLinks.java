package com.example.tinythinkers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ReferenceLinks extends AppCompatActivity {

    RecyclerView recyclerView;
    Button backBtn;
    List<String> links;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reference_links);

        recyclerView = findViewById(R.id.recyclerView);
        backBtn = findViewById(R.id.backBtn);

        links = new ArrayList<>();

        addLinks();

        ReferenceAdapter adapter = new ReferenceAdapter(this, links);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        backBtn.setOnClickListener(v -> finish());
    }

    private void addLinks() {

        links.add("Alphabet Card – Freepik: https://www.freepik.com/free-vector/exotic-animal-alphabet_5417218.htm");
        links.add("Apple – catalyststuff (Freepik): https://www.freepik.com/free-vector/apple-fruit-cartoon-vector-icon-illustration-food-object-icon-isolated-flat-vector_246986277.htm");
        links.add("Balloons – rawpixel.com (Freepik): https://www.freepik.com/free-vector/colorful-festive-balloons-design-vectors_3387136.htm");
        links.add("Bear – catalyststuff (Freepik): https://www.freepik.com/free-vector/cute-bear-sitting-waving-hand-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat_47765984.htm");
        links.add("Car – catalyststuff (Freepik): https://www.freepik.com/free-vector/car-classic-transportation-cartoon-vector-icon-illustration-vehicle-transportation-isolated-flat_422328107.htm");
        links.add("Cat – Pixabay: https://pixabay.com/illustrations/cute-cartoon-cat-adorable-kitten-9815896/");
        links.add("Crocodile – catalyststuff (Freepik): https://www.freepik.com/free-vector/cute-crocodile-waving-hand-cartoon-vector-icon-illustration-animal-nature-icon-concept-isolated_31641105.htm");
        links.add("Dog – catalyststuff (Freepik): https://www.freepik.com/free-vector/cute-dachshund-dog-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat_415487494.htm");
        links.add("Elephant – catalyststuff (Freepik): https://www.freepik.com/free-vector/cute-elephant-scare-tree-cartoon-vector-icon-illustration-animal-nature-icon-concept-isolated_24211648.htm");
        links.add("Fish – catalyststuff (Freepik): https://www.freepik.com/free-vector/hand-drawn-clown-fish-cartoon-illustration_58564217.htm");
        links.add("Grapes – catalyststuff (Freepik): https://www.freepik.com/free-vector/grape-fruit-cartoon-vector-icon-illustration-food-nature-icon-isolated-flat-vector_414653892.htm");
        links.add("Hat – Freepik: https://www.freepik.com/free-psd/cartoon-hat-illustration_150891288.htm");
        links.add("Ice Cream – catalyststuff (Freepik): https://www.freepik.com/free-vector/cute-ice-cream-cone-holding-straw-cartoon-vector-icon-illustration-food-object-icon-concept-isolated_33456902.htm");
        links.add("Juice – Freepik: https://www.freepik.com/free-vector/hand-drawn-lemonade-cartoon-illustration_67999272.htm");
        links.add("Kite – Freepik: https://www.freepik.com/free-vector/hand-drawn-kite-cartoon-illustration_71861187.htm");
        links.add("Lion – catalyststuff (Freepik): https://www.freepik.com/free-vector/cute-lion-holding-steak-meat-flag-cartoon-vector-icon-illustration-animal-food-icon-isolated-flat_415892203.htm");
        links.add("Lion1 – catalyststuff (Freepik): https://www.freepik.com/free-vector/cute-lion-sitting-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_258426913.htm");
        links.add("Math Card – Freepik: https://www.freepik.com/free-vector/poster-design-with-numbers-signs_2439614.htm");
        links.add("Memory Game Card – Freepik: https://www.freepik.com/free-vector/hand-drawn-memory-game-cards_37451756.htm");
        links.add("Monkey – catalyststuff (Freepik): https://www.freepik.com/free-vector/cute-monkey-dabbing_15769584.htm");
        links.add("Music Notes – juicy_fish (Freepik): https://www.freepik.com/free-vector/music-beam-quaver-notes_37409065.htm");
        links.add("Number Nine – Freepik: https://www.freepik.com/free-psd/number-illustration-isolated_187448869.htm");
        links.add("Orange – catalyststuff (Freepik): https://www.freepik.com/free-vector/cute-orange-fruit-cartoon-vector-icon-illustration-food-object-icon-isolated-flat-vector_378102458.htm");
        links.add("Panda – catalyststuff (Freepik): https://www.freepik.com/free-vector/cute-panda-with-bamboo_13716079.htm");
        links.add("Pineapple – catalyststuff (Freepik): https://www.freepik.com/free-vector/cute-pineapple-fruit-cartoon-vector-icon-illustration-food-nature-icon-isolated-flat-vector_148774003.htm");
        links.add("Question Mark – Freepik: https://www.freepik.com/free-psd/cartoon-question-mark-isolated_208368523.htm");
        links.add("Robot – catalyststuff (Freepik): https://www.freepik.com/free-vector/cute-robot-waving-hand-cartoon-vector-icon-illustration-science-technology-isolated-flat-vector_361076432.htm");
        links.add("Sun – juicy_fish (Freepik): https://www.freepik.com/free-vector/sun-doodle-happy-face_207443233.htm");
        links.add("Tic Tac Toe – pch.vector (Freepik): https://www.freepik.com/free-vector/hands-holding-pencils-play-tic-tac-toe-people-drawing-crosses-noughts-simple-game-children-flat-vector-illustration-strategy-concept-banner-website-design-landing-web-page_27572532.htm");
        links.add("Tree – brgfx (Freepik): https://www.freepik.com/free-vector/green-cartoon-tree-with-hollow-trunk_416324314.htm");
        links.add("Umbrella – Freepik: https://www.freepik.com/free-vector/flat-illustration-monsoon-season_43172916.htm");
        links.add("Vegetables – pch.vector (Freepik): https://www.freepik.com/free-vector/happy-vegetable-cartoon-characters-vector-illustrations-set-cute-veggies-with-faces-hands-legs_21684035.htm");
        links.add("Water Drop – Freepik: https://www.freepik.com/free-vector/hand-drawn-water-drop-cartoon-illustration_69114243.htm");
        links.add("Xylophone – rawpixel.com (Freepik): https://www.freepik.com/free-vector/colorful-xylophone-sticker-musical-instrument-illustration_16367888.htm");
        links.add("Yoyo – catalyststuff (Freepik): https://www.freepik.com/free-vector/yo-yo-toy-cartoon-vector-icon-illustration-education-object-icon-concept-isolated-premium-vector_22271134.htm");
        links.add("Zebra – catalyststuff (Freepik): https://www.freepik.com/free-vector/cute-zebra-sitting-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_176962547.htm");

        links.add("Sound Effect – Game Music: https://pixabay.com/sound-effects/game-music-loop-7-145285/");
        links.add("Sound Effect – Correct: https://pixabay.com/sound-effects/correct-356013/");
        links.add("Sound Effect – Pop: https://pixabay.com/sound-effects/game-bonus-144751/");
        links.add("Sound Effect – Wrong Answer: https://pixabay.com/sound-effects/wrong-answer-126515/");
    }
}