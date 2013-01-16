package com.example.simplepagescroller;

public class CampaignModel {
	
	protected int tag1;
	protected int image1;
	protected String text1  = "";
	protected int tag2;
	protected int image2;
	protected String text2  = "";
	
	public CampaignModel(int tag1, int id, String text, int tag2, int id2, String text2) {
		
		this.tag1 = tag1;
		this.image1 = id;
		this.text1  = text;
		this.tag2 = tag2;
		this.image2 = id2;
		this.text2  = text2;
		
	}
}
