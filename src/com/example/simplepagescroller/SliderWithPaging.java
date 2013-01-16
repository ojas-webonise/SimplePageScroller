package com.example.simplepagescroller;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SliderWithPaging extends Activity implements SliderViewActionListener{

	Integer[] pics = { R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4, R.drawable.ic_launcher,
			R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4 };
	public ImageAdapter mImageAdapter;
	public ArrayList<CampaignModel> mCampaignModelList = new ArrayList<CampaignModel>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slider_with_paging);

		initializeViews();
	}

	/**
	 * Initializes and creates initials views
	 */
	private void initializeViews() {
		LinearLayout linPagingIndicatorLayout = (LinearLayout) findViewById(R.id.pagingIndicatorLayout);

		int iPagingIndicatorId = 0;
		int iMyCampaignSize = pics.length;
		
		for(int iMyCampaign=0; iMyCampaign<iMyCampaignSize; iMyCampaign++)
		{
			ImageView paging = new ImageView(this);
			paging.setId(iPagingIndicatorId);
			paging.setBackgroundResource(R.drawable.slideicon_unfocus);
			linPagingIndicatorLayout.addView(paging);
			
			String strMyCampaignNameOne = "Text "+iMyCampaign;
			int iMyCampaignImageOne = pics[iMyCampaign];
			
			// Since have to dispaly tow campaigns on single view
			iMyCampaign++; 
			
			String strMyCampaignNameTwo = "";
			int iMyCampaignImageTwo = 0 ;
			
			if (iMyCampaign < iMyCampaignSize ) {
				strMyCampaignNameTwo = "Text "+iMyCampaign;
				iMyCampaignImageTwo = pics[iMyCampaign];
			}
			
			//Add new campaign model to my campaigns list
			mCampaignModelList.add(new CampaignModel(iMyCampaign-1, iMyCampaignImageOne, strMyCampaignNameOne, iMyCampaign, iMyCampaignImageTwo, strMyCampaignNameTwo));
			iPagingIndicatorId++;
		}

		// Creates views from list.
		mImageAdapter = new ImageAdapter( getApplicationContext(), mCampaignModelList);
		mImageAdapter.addListener(this);
		
		((Gallery)findViewById(R.id.galleryView)).setAdapter(mImageAdapter);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_slider_with_paging, menu);
		return true;
	}

	public void onViewSlide(int pre, int pos) {
		if(pre != -1) {
			ImageView img = (ImageView) findViewById(pre);
			img.setBackgroundResource(R.drawable.slideicon_unfocus);
		}
		ImageView img1 = (ImageView) findViewById(pos);
		img1.setBackgroundResource(R.drawable.slideicon_focus);
	}

	public void onClickCampaignView(int campaignPosition) {
		Toast.makeText(getApplicationContext(), "Selected campaign == "+campaignPosition, Toast.LENGTH_SHORT).show();
	}
	
//	public void onClickLeftView(String tag) {
//		Toast.makeText(getApplicationContext(), tag, Toast.LENGTH_SHORT).show();
//	}
//
//	public void onClickRightView(String tag) {
//		Toast.makeText(getApplicationContext(), tag, Toast.LENGTH_SHORT).show();		
//	}

}

