package com.example.ibrahim.mapmessage.network.pojo;


import com.google.gson.annotations.SerializedName;


public class EntryItem {


    @SerializedName("content")
    private Content content;

    public void setContent(Content content) {
        this.content = content;
    }

    public Content getContent() {
        return content;
    }

    @Override
    public String toString() {
        return
                "EntryItem{" +
                        ",content = '" + content + '\'' +
                        "}";
    }
}