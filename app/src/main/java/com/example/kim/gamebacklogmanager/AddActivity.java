package com.example.kim.gamebacklogmanager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddActivity extends AppCompatActivity{

    public static Game game;

    private EditText editTitle;
    private EditText editPlatform;
    private EditText editNotes;
    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;
    private LocalDate currentDate;

    Intent intent;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setTitle("Add game");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initializes simple text editors
        editTitle = findViewById(R.id.editTitle_add);
        editPlatform = findViewById(R.id.editPlatform_add);
        editNotes = findViewById(R.id.editNotes_add);

        //sets currentDate to a string
        currentDate = LocalDate.now();

        //initializes spinner and sets adapter to spinner
        spinner = (Spinner) findViewById(R.id.editStatus_add);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //floating action button to add game to Game Overview
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                game = new Game(editTitle.getText().toString(),
                        editPlatform.getText().toString(),
                        spinner.getSelectedItem().toString(),
                        editNotes.getText().toString(),
                        currentDate
                );
                intent.putExtra(OverviewGames.EXTRA_GAME, game);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}
