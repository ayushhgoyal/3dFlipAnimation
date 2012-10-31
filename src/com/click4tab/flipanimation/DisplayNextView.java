package com.click4tab.flipanimation;

import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DisplayNextView implements Animation.AnimationListener {
	private boolean mCurrentView;
	TextView image1;
	TextView image2;

	public DisplayNextView(boolean currentView, TextView image12,
			TextView image22) {
		mCurrentView = currentView;
		this.image1 = image12;
		this.image2 = image22;
	}

	public void onAnimationStart(Animation animation) {
	}

	public void onAnimationEnd(Animation animation) {
		image1.post(new SwapViews(mCurrentView, image1, image2));
	}

	public void onAnimationRepeat(Animation animation) {
	}
}