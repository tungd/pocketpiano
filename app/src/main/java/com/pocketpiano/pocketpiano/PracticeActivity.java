package com.pocketpiano.pocketpiano;

import android.os.Bundle;
import android.util.Log;

import com.pocketpiano.pocketpiano.exercises.HappyBirthDay;
import com.pocketpiano.pocketpiano.exercises.JingleBell;
import com.pocketpiano.pocketpiano.exercises.Tile;
import com.pocketpiano.pocketpiano.instruments.PianoNodes;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PracticeActivity extends FreeStyleActivity {

    List<Tile> tiles;
    long start;
    long frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //tiles = new JingleBell().tiles();
        tiles = new HappyBirthDay().tiles();
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        super.onCameraViewStarted(width, height);

        start = System.currentTimeMillis();
    }

    List<Rect> dropRectList = new ArrayList<Rect>();
    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        Mat out = super.onCameraFrame(inputFrame);
        for(Tile tile: tiles) {
            long tileFrame = tile.time * 64;
            if (frame == tileFrame) {
                Rect dropRect = noteToPosition(tile.note,out);
                dropRectList.add(dropRect);
                break;
            }
        }
        out = drawListDroptitleNewFrame(dropRectList,out);
        frame ++;

        return out;
    }

    Scalar[] colors = new Scalar[] {
            new Scalar(253, 13, 27),
            new Scalar(254, 153, 39),
            new Scalar(255, 254, 56),
            new Scalar(66, 254, 47),
            new Scalar(27, 156, 253),
            new Scalar(110, 64, 252),
            new Scalar(253, 58, 155)
    };

    protected Mat drawListDroptitleNewFrame(List<Rect> dropRect, Mat img) {
        long height = Math.round(diff.height() * RATIO);
        int range = Math.round(diff.width() / RANGE);

        for (Rect rect: dropRect) {
            if(rect.height < 120) {
                rect.height +=20;
            } else {
                rect.y+= 20;
            }

            Scalar color = colors[(int)Math.floor(rect.x / range) - 1];
            if(rect.y < height+10) {
                Imgproc.rectangle(img,rect.br(),rect.tl(), color, -1,8,0);
            }

            Log.i("TAG","Rect point " + rect.br().x+" "+rect.br().y);
            Log.i("TAG","Rect point " + rect.tl().x+" "+rect.tl().y);
        }
        return img;
    }

    protected Rect noteToPosition(PianoNodes.Note note, Mat img) {
        PianoNodes.Note[] noteList = notes;
        int range = Math.round(diff.width() / RANGE);
        List<PianoNodes.Note> notes =  Arrays.asList(noteList);
        int pos = notes.indexOf(note);
        Log.i("Position","Position :" + pos);
        Point startPoint = new Point(pos * range,0);
        Rect dropRect = new Rect(pos * range, 0, range, 1);
        return dropRect;
    }
}
