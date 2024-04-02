package com.example.myapplication.chapter;

import com.google.gson.annotations.SerializedName;

public class Chapter {
    int ID;
    @SerializedName("IDnovel")
    int idNovel;
    @SerializedName("Name")
    private String chapterName;


    public Chapter(int ID, int idNovel, String chapterName) {
        this.ID = ID;
        this.idNovel = idNovel;
        this.chapterName = chapterName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public int getIdNovel() {
        return idNovel;
    }

    public void setIdNovel(int idNovel) {
        this.idNovel = idNovel;
    }
}
