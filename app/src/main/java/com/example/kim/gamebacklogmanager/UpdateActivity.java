package com.example.kim.gamebacklogmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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

public class UpdateActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_update);
        setTitle("Update game");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initializes simple text editors
        editTitle = findViewById(R.id.editTitle_update);
        editPlatform = findViewById(R.id.editPlatform_update);
        editNotes = findViewById(R.id.editNotes_update);

        //sets currentDate to a string
        currentDate = LocalDate.now();

        //initializes spinner and sets adapter to spinner
        spinner = (Spinner) findViewById(R.id.editStatus_update);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //obtain variables provided by OverviewGames
        final Game gameUpdate = getIntent().getParcelableExtra(OverviewGames.EXTRA_GAME);
        editTitle.setText(gameUpdate.getGameTitle());
        editPlatform.setText(gameUpdate.getGamePlatform());
        editNotes.setText(gameUpdate.getGameNotes());

        //passes changes on to OverviewGames
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameUpdate.setGameTitle(editTitle.getText().toString());
                gameUpdate.setGamePlatform(editPlatform.getText().toString());
                gameUpdate.setGameNotes(editNotes.getText().toString());
                gameUpdate.setGameStatus(spinner.getSelectedItem().toString());
                gameUpdate.setCurrentDate(currentDate);

                Intent resultIntent = new Intent();
                resultIntent.putExtra(OverviewGames.EXTRA_GAME, gameUpdate);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

}
