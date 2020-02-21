package com.example.mapp_ashton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

public class P08RecyclerView extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private final LinkedList<String> mWordList = new LinkedList<>();
    private int mCount = 0;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p08_recycler_view);

        for (int i = 0; i < 20; i++) {
            mWordList.addLast("Word " + mCount++);
            Log.d("WordList", mWordList.getLast());
        }
        // Get a handle to the RecyclerView.
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new WordListAdapter(this, mWordList);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int wordListSize = mWordList.size();
// Add a new word to the end of the wordList.
                mWordList.addLast("+ Word " + wordListSize);
// Notify the adapter, that the data has changed so it can
// update the RecyclerView to display the data.
                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
// Scroll to the bottom.
                mRecyclerView.smoothScrollToPosition(wordListSize);
            }
        });

    }

    public static class WordListAdapter extends


            RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
        private final LinkedList<String> mWordList;
        private LayoutInflater mInflater;
        @NonNull
        @Override
        public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View mItemView = mInflater.inflate(R.layout.wordlist_item, parent, false);
            return new WordViewHolder(mItemView, this);

        }

        @Override
        public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
            String mCurrent = mWordList.get(position);
            holder.wordItemView.setText(mCurrent);

        }

        @Override
        public int getItemCount() {

            return mWordList.size();
        }
        class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public final TextView wordItemView;
            final WordListAdapter mAdapter;
            public WordViewHolder(View itemView, WordListAdapter adapter) {
                super(itemView);
                wordItemView = (TextView) itemView.findViewById(R.id.word);
                this.mAdapter = adapter;
                itemView.setOnClickListener(this);



            }

            @Override
            public void onClick(View v) {
                // Get the position of the item that was clicked.
                int mPosition = getLayoutPosition();
    // Use that to access the affected item in mWordList.
                String element = mWordList.get(mPosition);
    // Change the word in the mWordList.
                mWordList.set(mPosition, "Clicked! " + element);
    // Notify the adapter, that the data has changed so it can
    // update the RecyclerView to display the data.
                mAdapter.notifyDataSetChanged();

            }
        }
        public WordListAdapter(Context context, LinkedList<String> wordList) {
            mInflater = LayoutInflater.from(context);
            this.mWordList = wordList;
        }

    }
}
