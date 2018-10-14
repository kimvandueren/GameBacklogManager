package com.example.kim.gamebacklogmanager;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    private List<Game> mGames;
    private Context context;
    final private GameClickListener mGameClickListener;

    public interface GameClickListener{
        void gameOnClick (int i);
    }

    public void swapList(List<Game> newList) {
        mGames = newList;
        if (newList != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    //sets TextViews from grid_cell as the items in the ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mGameClickListener.gameOnClick(clickedPosition);
        }
    }

    //generates the constructor
    public GamesAdapter(Context context, List<Game> mGames, GameClickListener mGameClickListener) {
        this.context = context;
        this.mGames = mGames;
        this.mGameClickListener = mGameClickListener;
    }

    //sets layout from grid_cell as what is being shown by the ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.grid_cell, viewGroup, false);

        GamesAdapter.ViewHolder viewHolder = new GamesAdapter.ViewHolder(view);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // gets a single item in the list from its position
        final Game game = mGames.get(i);

        // populates the views with the data from the list
        viewHolder.gameTitle.setText(game.getGameTitle());
        viewHolder.gamePlatform.setText(game.getGamePlatform());
        viewHolder.gameStatus.setText(game.getGameStatus());
        viewHolder.currentDate.setText(game.getCurrentDate());
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }
}
