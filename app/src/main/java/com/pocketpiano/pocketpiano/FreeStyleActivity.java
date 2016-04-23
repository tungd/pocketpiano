package com.pocketpiano.pocketpiano;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;


public class FreeStyleActivity extends Activity implements View.OnTouchListener, CvCameraViewListener2 {

    static {
        System.loadLibrary("opencv_java3");

        if (!OpenCVLoader.initDebug()) {
            Log.i("ERROR", "");
        }
    }

    private static final String TAG = "OCVSample::Activity";

    private Mat previousFrame;
    private Mat currentFrame;

    private CameraBridgeViewBase mOpenCvCameraView;
    private SeekBar minThresholdSeekbar = null;
    private TextView minThresholdSeekbarText = null;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i(TAG, "OpenCV loaded successfully");

                    mOpenCvCameraView.enableView();
                    mOpenCvCameraView.setOnTouchListener(FreeStyleActivity.this);
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "called onCreate");
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_freestyle);

        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.main_surface_view);
        mOpenCvCameraView.setCvCameraViewListener(this);

        minThresholdSeekbarText = (TextView) findViewById(R.id.textView3);

        minThresholdSeekbar = (SeekBar) findViewById(R.id.seekBar1);
        minThresholdSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progessChanged = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progessChanged = progress;
                minThresholdSeekbarText.setText(String.valueOf(progessChanged));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                minThresholdSeekbarText.setText(String.valueOf(progessChanged));
            }
        });

        minThresholdSeekbar.setProgress(8700);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    boolean first = true;

    @Override
    public void onCameraViewStarted(int width, int height) {
        String caption = "Resolution " + Integer.valueOf(mOpenCvCameraView.getWidth()).toString() + "x" + Integer.valueOf(mOpenCvCameraView.getHeight()).toString();
        Toast.makeText(this, caption, Toast.LENGTH_SHORT).show();

        currentFrame = new Mat(width, height, CvType.CV_8UC4);
        previousFrame = new Mat(width, height, CvType.CV_8UC4);
        diff = new Mat(width, height, CvType.CV_8UC4);
    }

    @Override
    public void onCameraViewStopped() {
        currentFrame.release();
        previousFrame.release();
        diff.release();
    }

    final double RATIO = 2.2 / 3;
    final Scalar WHITE = new Scalar(255, 255, 255);
    Mat processedPreviousFrame;
    Mat processedCurrentFrame;
    Mat diff;

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        if (first) {
            inputFrame.gray().copyTo(previousFrame);
            previousFrame.copyTo(diff);
            first = false;
        } else {
            inputFrame.gray().copyTo(currentFrame);

            Core.absdiff(previousFrame, currentFrame, diff);

            currentFrame.copyTo(previousFrame);
        }



        Imgproc.line(diff,
                new Point(0, diff.height() * RATIO),
                new Point(diff.width(), diff.height() * RATIO),
                WHITE, 3);

        return diff;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mOpenCvCameraView != null) {
            mOpenCvCameraView.disableView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null) {
            mOpenCvCameraView.disableView();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
