package com.click4tab.flipanimation;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

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

		image1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (isFirstImage) {
					applyRotation(0, 90);
					isFirstImage = !isFirstImage;

				} else {
					applyRotation(0, -90);
					isFirstImage = !isFirstImage;
				}
			}
		});
	}

	private void applyRotation(float start, float end) {
		// Find the center of image
		 final float centerX = image1.getWidth() / 2.0f;
		 final float centerY = image1.getHeight() / 2.0f;

		// Create a new 3D rotation with the supplied parameter
		// The animation listener is used to trigger the next animation
		final Flip3dAnimation rotation = new Flip3dAnimation(start, end,
				centerX, centerY);
		rotation.setDuration(500);
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
}