package com.example.kim.gamebacklogmanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    private List<Game> mGames;
    private Context context;

    //generates the constructor
    public GamesAdapter(Context context, List<Game> mGames) {
        this.context = context;
        this.mGames = mGames;
    }

    //sets layout from grid_cell as what is being shown by the ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.grid_cell, null);

        GamesAdapter.ViewHolder viewHolder = new GamesAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // gets a single item in the list from its position
        final Game game = mGames.get(i);

        // populates the views with the data from the list
        viewHolder.gameTitle.setText(game.getmGameTitle());
        viewHolder.gamePlatform.setText(game.getmGamePlatform());
        viewHolder.gameStatus.setText(game.getmGameStatus());
        viewHolder.currentDate.setText(game.getmCurrentDate());
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }

    //sets TextViews from grid_cell as the items in the ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView gameTitle;
        public TextView gamePlatform;
        public TextView gameStatus;
        public TextView currentDate;
        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gameTitle = itemView.findViewById(R.id.gameTitle);
            gamePlatform = itemView.findViewById(R.id.gamePlatform);
            gameStatus = itemView.findViewById(R.id.gameStatus);
            currentDate = itemView.findViewById(R.id.currentDate);
            view = itemView;
        }
    }
}
