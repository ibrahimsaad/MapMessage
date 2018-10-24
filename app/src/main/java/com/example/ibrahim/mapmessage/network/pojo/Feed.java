package com.example.ibrahim.mapmessage.network.pojo;

import java.util.List;
//ss
import com.google.gson.annotations.SerializedName;


public class Feed {


    @SerializedName("entry")
    private List<EntryItem> entry;


    public void setEntry(List<EntryItem> entry) {
        this.entry = entry;
    }

    public List<EntryItem> getEntry() {
        return entry;
    }


    @Override
    public String toString() {
        return
                "Feed{" +
                        ",entry = '" + entry + '\'' +
                        "}";
    }
}