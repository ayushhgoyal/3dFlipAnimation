package com.click4tab.flipanimation;

import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

public final class SwapViewsVertical implements Runnable {
	private boolean mIsFirstView;
	TextView image1;
	TextView image2;

	public SwapViewsVertical(boolean isFirstView, TextView image12,
			TextView image22) {
		mIsFirstView = isFirstView;
		this.image1 = image12;
		this.image2 = image22;
	}

	public void run() {
		final float centerX = image1.getWidth() / 2.0f;
		final float centerY = image1.getHeight() / 2.0f;
		// final float centerX = 0;
		// final float centerY = 0;
		Flip3dAnimationVertical rotation;

		if (mIsFirstView) {
			image1.setVisibility(View.GONE);
			image2.setVisibility(View.VISIBLE);
			image2.requestFocus();
			Log.e("DOWN2", "CALEED");

			rotation = new Flip3dAnimationVertical(-90, 0, centerX, centerY);
		} else {
			image2.setVisibility(View.GONE);
			image1.setVisibility(View.VISIBLE);
			image1.requestFocus();
			Log.e("DOWN", "CALEED");
			rotation = new Flip3dAnimationVertical(90, 0, centerX, centerY);
		}

		rotation.setDuration(400);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new DecelerateInterpolator());

		if (mIsFirstView) {
			image2.startAnimation(rotation);
		} else {
			image1.startAnimation(rotation);
		}
	}
}
