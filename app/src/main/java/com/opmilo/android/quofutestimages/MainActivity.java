package com.opmilo.android.quofutestimages;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Activity activity;

    private RelativeLayout rlayout;

    private ImageView image;

    private int _xDelta;

    private int _yDelta;

    private int[] imageDimensions;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        // Get window size
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        image = (ImageView) findViewById(R.id.image);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, width);
        image.setLayoutParams(layoutParams);

        final Button button = (Button) findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Add", Toast.LENGTH_SHORT).show();

                /*
                ImageView image = (ImageView) findViewById(R.id.image);

                Rect r = image.getDrawable().getBounds();

                int drawLeft = r.left;
                int drawTop = r.top;
                int drawRight = r.right;
                int drawBottom = r.bottom;

                Toast.makeText(getApplicationContext(),
                        Integer.toString(drawLeft) + " - " + Integer.toString(drawTop) + " - " + Integer.toString(drawRight) + " - " + Integer.toString(drawBottom),
                        Toast.LENGTH_SHORT).show();

                */

                LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                TextView tv = new TextView(activity);

                tv.setLayoutParams(lparams);

                tv.setText("test");

                tv.setX(image.getX() + 10);
                tv.setY(image.getY() + 10);

                tv.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent event) {
                        final int X = (int) event.getRawX();

                        final int Y = (int) event.getRawY();

                        switch (event.getAction() & MotionEvent.ACTION_MASK) {
                            case MotionEvent.ACTION_DOWN:

                                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();

                                _xDelta = X - lParams.leftMargin;

                                _yDelta = Y - lParams.topMargin;

                                break;

                            case MotionEvent.ACTION_UP:

                                break;

                            case MotionEvent.ACTION_POINTER_DOWN:

                                break;

                            case MotionEvent.ACTION_POINTER_UP:

                                break;

                            case MotionEvent.ACTION_MOVE:

                                if (X >= imageDimensions[0] && X <= imageDimensions[0] + imageDimensions[2] &&
                                        Y >= imageDimensions[1] && Y <= imageDimensions[1] + imageDimensions[3])
                                {
                                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();

                                    layoutParams.leftMargin = X - _xDelta;

                                    layoutParams.topMargin = Y - _yDelta;

                                    layoutParams.rightMargin = -250;

                                    layoutParams.bottomMargin = -250;

                                    view.setLayoutParams(layoutParams);

                                    //Toast.makeText(getApplicationContext(), String.valueOf(X) + " - " + String.valueOf(Y), Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), String.valueOf(X) + " - " + String.valueOf(Y), Toast.LENGTH_SHORT).show();


                                    Toast.makeText(getApplicationContext(),
                                            "left: " + String.valueOf(imageDimensions[0]) +
                                                    ", top: " + String.valueOf(imageDimensions[1]) +
                                                    ", width: " + String.valueOf(imageDimensions[2]) +
                                                    ", height: " + String.valueOf(imageDimensions[3]),
                                            Toast.LENGTH_SHORT).show();
                                }

                                break;
                        }

                        rlayout.invalidate();

                        return true;
                    }
                });

                rlayout.addView(tv);
            }
        });

        rlayout = (RelativeLayout) findViewById(R.id.mainlayout);

        rlayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    Toast.makeText(getApplicationContext(), String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()), Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });


        rlayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout()
            {
                imageDimensions = GetPositionImageView(image);

                /*
                Toast.makeText(getApplicationContext(),
                        "left: " + String.valueOf(imageDimensions[0]) +
                                ", top: " + String.valueOf(imageDimensions[1]) +
                                ", width: " + String.valueOf(imageDimensions[2]) +
                                ", height: " + String.valueOf(imageDimensions[3]),
                        Toast.LENGTH_SHORT).show();
                */
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    public static int[] GetBitmapPositionInsideImageView(ImageView imageView) {
        int[] ret = new int[4];

        if (imageView == null || imageView.getDrawable() == null)
            return ret;

        // Get image dimensions
        // Get image matrix values and place them in an array
        float[] f = new float[9];
        imageView.getImageMatrix().getValues(f);

        // Extract the scale values using the constants (if aspect ratio maintained, scaleX == scaleY)
        final float scaleX = f[Matrix.MSCALE_X];
        final float scaleY = f[Matrix.MSCALE_Y];

        // Get the drawable (could also get the bitmap behind the drawable and getWidth/getHeight)
        final Drawable d = imageView.getDrawable();
        final int origW = d.getIntrinsicWidth();
        final int origH = d.getIntrinsicHeight();

        // Calculate the actual dimensions
        final int actW = Math.round(origW * scaleX);
        final int actH = Math.round(origH * scaleY);

        ret[2] = actW;
        ret[3] = actH;

        // Get image position
        // We assume that the image is centered into ImageView
        int imgViewW = imageView.getWidth();
        int imgViewH = imageView.getHeight();

        int top = (int) (imgViewH - actH)/2;
        int left = (int) (imgViewW - actW)/2;

        ret[0] = left;
        ret[1] = top;

        return ret;
    }
    */

    public static int[] GetPositionImageView(ImageView imageView) {
        int[] ret = new int[4];

        if (imageView == null)
            return ret;

        ret[0] = imageView.getLeft();
        ret[1] = imageView.getTop();
        ret[2] = imageView.getWidth();
        ret[3] = imageView.getHeight();

        return ret;
    }
}
