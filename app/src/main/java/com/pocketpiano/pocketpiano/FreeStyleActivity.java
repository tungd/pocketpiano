package com.pocketpiano.pocketpiano;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.pocketpiano.pocketpiano.instruments.PianoNodes;
import com.pocketpiano.pocketpiano.instruments.PianoNodes.Note;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.Arrays;
import java.util.List;


public class FreeStyleActivity extends Activity implements CvCameraViewListener2 {

    static {
        System.loadLibrary("opencv_java3");
    }

    private static final String TAG = "OCVSample::Activity";

    private Mat previousFrame;
    private Mat currentFrame;

    private CameraBridgeViewBase mOpenCvCameraView;

    PianoNodes piano;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i(TAG, "OpenCV loaded successfully");

                    mOpenCvCameraView.enableView();
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
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_freestyle);

        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.main_surface_view);
        mOpenCvCameraView.setCvCameraViewListener(this);

        piano = new PianoNodes(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    boolean first = true;

    @Override
    public void onCameraViewStarted(int width, int height) {
        String caption = String.format("Resolution %sx%s",
                mOpenCvCameraView.getWidth(),
                mOpenCvCameraView.getHeight());
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
    final Scalar RED = new Scalar(255, 0, 0);
    Mat diff;

    double[] pixels;
    final int RANGE = 14;
    final int[] scores = new int[RANGE];
    final Note[] notes = new Note[] {
            Note.C3, Note.D3, Note.E3, Note.F3, Note.G3, Note.A3, Note.B3,
            Note.C4, Note.D4, Note.E4, Note.F4, Note.G4, Note.A4, Note.B4
    };

    protected Mat drawListDroptitleNewFrame(List<Rect> dropRect, Mat img) {
        long height = Math.round(diff.height() * RATIO);
        for (Rect rect: dropRect) {
            if(rect.height < 60) {
                rect.height +=1;
            } else {
                rect.y+= 1;
            }
            if(rect.y < height)
            Imgproc.rectangle(img,rect.br(),rect.tl(),WHITE,3,8,0);
        }
        return img;
    }

    protected void NoteToPosition(Note note, Mat img) {
        Note[] noteList = notes;
        int range = Math.round(diff.width() / RANGE);
        List<Note> notes =  Arrays.asList(noteList);
        int pos = notes.indexOf(note);
        Point startPoint = new Point(pos * range,0);
        Rect dropRect = new Rect(pos * range,0,1,range);

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        if (first) {
            inputFrame.gray().copyTo(previousFrame);
            previousFrame.copyTo(diff);
            first = false;
        } else {
            inputFrame.gray().copyTo(currentFrame);

            Core.absdiff(previousFrame, currentFrame, diff);

            inputFrame.gray().copyTo(previousFrame);
        }

        Core.flip(diff, diff, 0);

//        Imgproc.line(diff,
//                new Point(0, diff.height() * RATIO),
//                new Point(diff.width(), diff.height() * RATIO),
//                WHITE, 3);

        int points;
        long height = Math.round(diff.height() * RATIO);
        long row = Math.round(height);
        int range = Math.round(diff.width() / RANGE);

        Mat out = inputFrame.rgba();
        Core.flip(out, out, 0);

        Imgproc.line(out,
                new Point(0, height),
                new Point(diff.cols(), height),
                new Scalar(190, 190, 190), 3);

        // Iterate along the line
        for (int i = 0; i < scores.length; i += 1) {
            // Gather all the pixels
            scores[i] = 0;

            for (int j = range * i; j < range * i + range; j += 1) {
                pixels = diff.get((int) height, j);

//                Log.i("PIXELS", Arrays.toString(pixels));
//                if (pixels[0] > 200 && pixels[1] > 200 && pixels[2] > 200) {
//                    points += 1;
//                }
                if (pixels[0] > 8) {
                    scores[i] += 1;
                }
            }

            if (scores[i] >= range * 0.3) {
                Imgproc.line(out,
                        new Point(i * range, height),
                        new Point(i * range + range, height),
                        RED, 3);

                new ScheduledNote().execute(notes[i]);
            }

            Imgproc.line(out,
                    new Point(i * range + range, 0),
                    new Point(i * range + range, diff.width()),
                    WHITE, 3);
        }

        Log.i("SCORES", Arrays.toString(scores));

        return out;
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

    class ScheduledNote extends AsyncTask<Note, Void, Void> {

        @Override
        protected Void doInBackground(Note... notes) {
            piano.play(notes[0]);
            piano.startMedia(notes[0]);
            return null;
        }
    }
}
