package com.example.myapplication.chapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder>{

    private Context mContext;
    private ArrayList<Chapter> chapters;

    public ChapterAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setChapters(ArrayList<Chapter> chapters) {
        this.chapters = chapters;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter_list,parent,false);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        Chapter chapter = chapters.get(position);
        if(chapter == null)
            return;
        holder.chapterName.setText(chapter.getChapterName());
    }

    @Override
    public int getItemCount() {
        if(chapters != null)
            return chapters.size();
        return 0;
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder {
        TextView chapterName;
        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            chapterName = itemView.findViewById(R.id.textView3);
        }
    }
}
