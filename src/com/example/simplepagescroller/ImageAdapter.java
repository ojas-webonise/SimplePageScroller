package com.example.simplepagescroller;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("UseValueOf")
public class ImageAdapter extends BaseAdapter {

	protected Context context;
	protected int iPreviousPosition = -1;
	protected int iListCount;
	protected LayoutInflater inflator;
	protected ArrayList<CampaignModel> list_model;
	private ArrayList<SliderViewActionListener> listeners = new ArrayList<SliderViewActionListener>();

	public ImageAdapter(Context context,  ArrayList<CampaignModel> list_model) {
		this.context = context;
		this.iListCount = list_model.size();
		this.inflator = LayoutInflater.from(context);
		this.list_model = list_model;
	}

	public int getCount() {
		return iListCount;
	}

	public View getView(int pos, View convertView, ViewGroup arg2) {

		View baseView;
		CampaignModel model = getModel(pos);
		if (convertView == null) {

			baseView = inflator.inflate(R.layout.campaign_filp_scroll_image, null);
			ImageView img1 = (ImageView)baseView.findViewById(R.id.flipperImgOne);
			TextView txt1 = (TextView)baseView.findViewById(R.id.myCmpgnNameOne);
			ImageView img2 = (ImageView)baseView.findViewById(R.id.flipperImgTwo);
			TextView txt2 = (TextView)baseView.findViewById(R.id.myCmpgnNameTwo);

			img1.setImageResource(model.image1);
			img1.setTag(new Integer(model.tag1));
			img1.setOnClickListener(imageClickListener);

			txt1.setText(model.text1);

			if (model.text2.equals("")) {
				img2.setVisibility(View.INVISIBLE);
				txt2.setVisibility(View.GONE);
			} else {
				img2.setImageResource(model.image2);
				img2.setTag(new Integer(model.tag2));
				img2.setOnClickListener(imageClickListener);
				txt2.setText(model.text2);
			}

		} else {
			baseView = (View) convertView;
		}

		baseView.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.WRAP_CONTENT, Gallery.LayoutParams.WRAP_CONTENT));	
		return baseView;
	}

	OnClickListener imageClickListener = new OnClickListener() {
		public void onClick(View view) {
			Integer tag = (Integer)view.getTag();
			notifyListeners( tag);
		}
	};

	public CampaignModel getModel(int position)	{
		return (CampaignModel) list_model.get(position);
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		notifySwipeListener( iPreviousPosition, position);
		this.iPreviousPosition = position;
		return position;
	}

	public void addListener(SliderViewActionListener listener){
		listeners.add(listener);
	}

	private void notifyListeners( int tag){
		for (SliderViewActionListener listener : listeners) {
			listener.onClickCampaignView(tag);
		}
	}

	private void notifySwipeListener(int iPreviousView, int iCurrentView) {
		for (SliderViewActionListener listener : listeners) {
			listener.onViewSlide(iPreviousView, iCurrentView);
		}
	}
}
