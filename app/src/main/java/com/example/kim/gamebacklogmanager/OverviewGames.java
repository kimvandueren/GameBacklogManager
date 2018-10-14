package com.example.kim.gamebacklogmanager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class OverviewGames extends AppCompatActivity {
    private Toolbar toolbar;

    private RecyclerView mRecyclerView;
    private GamesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Game> mGames;
    private GamesAdapter.GameClickListener gameClickListener;

    private FloatingActionButton addGame;

    public static final String EXTRA_GAME = "Game";
    public static final int REQUESTCODE1 = 1234;
    public static final int REQUESTCODE2 = 4321;
    private int mModifyPosition;

    public final static int TASK_GET_ALL_GAMES = 0;
    public final static int TASK_DELETE_GAME = 1;
    public final static int TASK_UPDATE_GAME = 2;
    public final static int TASK_INSERT_GAME = 3;

    static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_games);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = AppDatabase.getInstance(this);
        new GameAsyncTask(TASK_GET_ALL_GAMES).execute();

        mGames = new ArrayList<>();
        mRecyclerView = findViewById(R.id.recyclerView);

        // sets linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        gameClickListener = new GamesAdapter.GameClickListener(){
            @Override
            public void gameOnClick(int i) {
                Intent intent = new Intent(OverviewGames.this, UpdateActivity.class);
                mModifyPosition = i;
                intent.putExtra(EXTRA_GAME, mGames.get(i));
                startActivityForResult(intent, REQUESTCODE2);
            }
        };

        //floating action button to open addGame activity
        addGame = (FloatingActionButton) findViewById(R.id.addGame);
        addGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OverviewGames.this, AddActivity.class);
                startActivityForResult(intent, REQUESTCODE1);
            }
        });

        //adds the ability to swipe to delete an object
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        int position = (viewHolder.getAdapterPosition());
                        new GameAsyncTask(TASK_DELETE_GAME).execute(mGames.get(position));
                        mGames.remove(position);
                    }

                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        updateUI();
    }

    public void onGameDbUpdated(List list) {
        mGames = list;
        updateUI();
    }

    private void updateUI(){
        if (mAdapter == null){
            mAdapter = new GamesAdapter(this, mGames, gameClickListener);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.swapList(mGames);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_overview_games, menu);
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUESTCODE1){
            if (resultCode == RESULT_OK){
                Game addedGame = data.getParcelableExtra(OverviewGames.EXTRA_GAME);
                mGames.add(addedGame);
                new GameAsyncTask(TASK_INSERT_GAME).execute(addedGame);
            }
        } else if (requestCode == REQUESTCODE2){
            if(resultCode == RESULT_OK){
                Game updatedGame = data.getParcelableExtra(OverviewGames.EXTRA_GAME);
                mGames.set(mModifyPosition, updatedGame);
                new GameAsyncTask(TASK_UPDATE_GAME).execute(updatedGame);
            }
        }
    }

    public class GameAsyncTask extends AsyncTask<Game, Void, List> {
        private int taskCode;

        public GameAsyncTask(int taskCode) {
            this.taskCode = taskCode;
        }

        @Override
        protected List doInBackground(Game... games) {
            switch (taskCode){
                case TASK_DELETE_GAME:
                    db.gameDao().deleteGames(games[0]);
                    break;
                case TASK_UPDATE_GAME:
                    db.gameDao().updateGames(games[0]);
                    break;
                case TASK_INSERT_GAME:
                    db.gameDao().insertGames(games[0]);
                    break;
            }
            //To return a new list with the updated data, we get all the data from the database again.
            return db.gameDao().getAllGames();
        }

        @Override
        protected void onPostExecute(List list) {
            super.onPostExecute(list);
            onGameDbUpdated(list);
        }
    }
}
