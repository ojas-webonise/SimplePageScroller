package com.example.simplepagescroller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.Gallery;

public class GalleryCustom extends Gallery {

	Context context;
	GestureDetector gestureDetector = new GestureDetector(new innerGestureDetector());

	public GalleryCustom(Context ctx, AttributeSet attrSet) {
		super(ctx,attrSet);
		this.context = ctx;
	}

	@SuppressWarnings("unused")
	private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2) {
		return e2.getX() > e1.getX(); 
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
		super.onFling( e1, e2, (float)(velocityX / 5) , velocityY );  // This enables fling on inner views of gallery
		return true;
	}
	
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int viewsOnScreen = getLastVisiblePosition() - getFirstVisiblePosition();
        if(viewsOnScreen <= 0)
            super.onLayout(changed, l, t, r, b);
    }

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (gestureDetector.onTouchEvent(ev)) {
//			super.onTouchEvent(ev);
			return true;
		}
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public int pointToPosition(int x, int y) {
		return INVALID_POSITION; 
	}
	
	class innerGestureDetector extends SimpleOnGestureListener {

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,	float distanceX, float distanceY) { 
			// For handling slow paging movement
			GalleryCustom.this.onScroll(e1, e2, (float)(distanceX/1.5) , distanceY);
//			System.out.println(" distance x === "+distanceX);
//			MotionEvent me1 = MotionEvent.obtain(
//				    SystemClock.uptimeMillis(), 
//				    SystemClock.uptimeMillis(),  
//				    MotionEvent.ACTION_DOWN, 89.333336f, 265.33334f, 0);
//				MotionEvent me2 = MotionEvent.obtain(
//				    SystemClock.uptimeMillis(), 
//				    SystemClock.uptimeMillis(), 
//				    MotionEvent.ACTION_UP, 300.0f, 238.00003f, 0);
//			GalleryCustom.this.onScroll(me1, me2, (float)(distanceX/1.5) , distanceY);
//			GalleryCustom.this.scrollBy(0 * (int)distanceX, 0);
			return true;
		}
		
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			// For handling fast drag movement
			return GalleryCustom.this.onFling( e1, e2, velocityX * 2, velocityY );
		}
	}
}
