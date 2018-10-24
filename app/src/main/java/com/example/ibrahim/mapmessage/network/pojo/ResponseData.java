package com.example.ibrahim.mapmessage.network.pojo;

//ss
import com.google.gson.annotations.SerializedName;


public class ResponseData {

	@SerializedName("feed")
	private Feed feed;

	public void setFeed(Feed feed){
		this.feed = feed;
	}

	public Feed getFeed(){
		return feed;
	}


	@Override
 	public String toString(){
		return 
			"ResponseData{" +
			"feed = '" + feed + '\'' +
			"}";
		}
}