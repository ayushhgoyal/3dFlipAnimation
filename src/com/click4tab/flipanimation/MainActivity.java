package com.click4tab.flipanimation;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView image1;
	private TextView image2;

	private boolean isFirstImage = true;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Drawable img = getResources().getDrawable(R.drawable.chrysanthemum);
		Drawable img2 = getResources().getDrawable(R.drawable.hydrangeas);

		image1 = (TextView) findViewById(R.id.ImageView01);
		image2 = (TextView) findViewById(R.id.ImageView02);
		image2.setVisibility(View.GONE);
		image1.setBackgroundDrawable(img);
		image2.setBackgroundDrawable(img2);

		final GestureDetector gestureDetector = new GestureDetector(this,
				new DoubleTapDetector());

		image1.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				gestureDetector.onTouchEvent(event);
				return true;
			}
		});

		// image1.setOnClickListener(new View.OnClickListener() {
		// public void onClick(View view) {
		// if (isFirstImage) {
		// applyRotation(0, -90);
		// isFirstImage = !isFirstImage;
		// } else {
		// applyRotation(0, 90);
		// isFirstImage = !isFirstImage;
		// }
		// }
		// });
	}

	private void applyRotation(float start, float end) {
		// Find the center of image
		final float centerX = image1.getWidth() / 2.0f;
		final float centerY = image1.getHeight() / 2.0f;
		// Log.e("width", image1.getWidth() + "");
		// final float centerX = 0;
		// final float centerY = image1.getHeight();

		// Create a new 3D rotation with the supplied parameter
		// The animation listener is used to trigger the next animation
		final Flip3dAnimation rotation = new Flip3dAnimation(start, end,
				centerX, centerY);
		rotation.setDuration(300);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setAnimationListener(new DisplayNextView(isFirstImage, image1,
				image2));

		if (isFirstImage) {
			image1.startAnimation(rotation);
		} else {
			image2.startAnimation(rotation);
		}

	}

	private class DoubleTapDetector extends
			GestureDetector.SimpleOnGestureListener {
		int i = 1;

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			// TODO Auto-generated method stub
			Log.e("yo", "scroll");
			return true;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// TODO Auto-generated method stub
			Log.e("yo", " fling");
			// flingUp.setText("fling " + (i++));

			if (e1.getX() - e2.getX() > 100) {
				// left
				if (isFirstImage) {
					applyRotation(0, -90);
					isFirstImage = !isFirstImage;
				}

			}
			if (e2.getX() - e1.getX() > 100) {
				if (!isFirstImage) {
					applyRotation(0, 90);
					isFirstImage = !isFirstImage;
				}
			}

			if (e1.getY() - e2.getY() > 100) {
				Log.e("yo", "up");
				Toast.makeText(getApplicationContext(), "Next word",
						Toast.LENGTH_SHORT).show();
				if (isFirstImage) {
					applyRotationVertical(0, 90);
					isFirstImage = !isFirstImage;
				}
			}

			if (e2.getY() - e1.getY() > 100) {
				Log.e("yo", "down");
				if (!isFirstImage) {
					applyRotationVertical(0, -90);
					isFirstImage = !isFirstImage;
				}

			}

			return true;
		}

		private void applyRotationVertical(int start, int end) {
			// TODO Auto-generated method stub
			// Find the center of image
			final float centerX = image1.getWidth() / 2.0f;
			final float centerY = image1.getHeight() / 2.0f;
			// Log.e("width", image1.getWidth() + "");
			// final float centerX = 0;
			// final float centerY = image1.getHeight();

			// Create a new 3D rotation with the supplied parameter
			// The animation listener is used to trigger the next animation
			final Flip3dAnimationVertical rotation = new Flip3dAnimationVertical(
					start, end, centerX, centerY);
			rotation.setDuration(600);
			rotation.setFillAfter(true);
			rotation.setInterpolator(new AccelerateInterpolator());
			rotation.setAnimationListener(new DisplayNextViewVertical(
					isFirstImage, image1, image2));

			if (isFirstImage) {
				image1.startAnimation(rotation);
			} else {
				image2.startAnimation(rotation);
			}

		}

		@Override
		public boolean onDoubleTap(MotionEvent e) {
			// TODO Auto-generated method stub
			// Log.e("yo", " double tap count " + (i++));
			return true;
		}

	}

}