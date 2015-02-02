package fundraw.com.timspaint;

/**
 * I worked with Shuang to create this class
 * Created by tbrad_000 on 1/28/2015.
 */
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class MyView extends View{

    public int pen_size = 1;
    public int pen_Color = 0xFF000000;

    private static Canvas mCanvas ;
    private static int width;
    private static int height;
    private Paint mBitmapPaint;
    private Paint mPaint;
    private static Bitmap mBitmap;
    private Path mPath;
    float mX,mY;
    private static final float TOUCH_TOLERANCE = 4;
    private List<DrawPath> savePath;
    private List<DrawPath> savePath_other;
    private DrawPath dp;
    private static MainActivity mainActivity;

    public MyView(Context context, AttributeSet attrs, int CanvasWidth, int CanvasHeight) {
        super(context,attrs);
        width = CanvasWidth;
        height = CanvasHeight;
        mBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mPaint.setColor(pen_Color);// sets the pen color

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);

        mPaint.setStrokeWidth(pen_size);// set the pen thickness

        savePath = new ArrayList<DrawPath>();
        savePath_other = new ArrayList<DrawPath>();
       }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public Canvas getmCanvas(){
        return mCanvas;
    }

    public void B_ground(Canvas canvas,Bitmap bitmap){
        canvas.drawBitmap(bitmap,getLeft(),getTop(),null);
    }

    public static Uri addImageToGallery(Context context, String filepath, String title, String description) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, title);
        values.put(MediaStore.Images.Media.DESCRIPTION, description);
        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.MediaColumns.DATA, filepath);

        return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }

    public String file_save(Context context){//this will take the bitmap saved in cache and will put it in the gallery and return a copy of the bitmap
             // MediaStore.Images.Media.insertImage(getContentResolver(), yourBitmap, yourTitle , yourDescription);
        setDrawingCacheEnabled(true);
       return MediaStore.Images.Media.insertImage(context.getContentResolver(), getDrawingCache(), "Test.png" ,  "Paint Fun");
   //     return  MediaStore.Images.Media.insertImage(getContext().getContentResolver(),
     //           getDrawingCache(),UUID.randomUUID().toString()+".png", "Paint Fun");
    }//http://developer.android.com/reference/android/provider/MediaStore.Images.Media.html

    public void color_change(int color){//this will change the color of the pen

        invalidate();//invalidating the View
        mPaint.setColor(color);// set the color to the new color

        pen_Color = color;
    }

    /**
     *
     * @param size takes size of pen and sets new size
     */
    public void size_change(int size){
        mPaint.setStrokeWidth(size);

        this.pen_size = size;
    }

    /**
     *
     * @param canvas draws a blank white canvas
     */
    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawColor(Color.WHITE);

        canvas.drawBitmap(mBitmap,0,0,mBitmapPaint);

        if(mPath != null)
            canvas.drawPath(mPath , mPaint);
    }

    /**
     *
     * the first press of the screen
     * @param x x coordinate
     * @param y y coordinate
     */
    private void touch_start(float x,float y){
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    /**
     *
     * when finger is swiping across screen
     * @param x x coordinate
     * @param y y coordinate
     */
    private void touch_move(float x,float y){
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if(dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE){
            mPath.quadTo(mX, mY, (x+mX)/2, (y+mY)/2);
            mX = x;
            mY = y;
        }
    }

    /**
     * finger released from screen
     */
    private void touch_up(){
        mPath.lineTo(mX, mY);
        mCanvas.drawPath(mPath, mPaint);
        savePath.add(dp);
        mPath = null;
      //  undoList.add(mCanvas);
    }

    /**
     * This will undo any drawings
     * @throws IOException
     */
    public void undo() throws IOException{//undo
        mBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        mCanvas.setBitmap(mBitmap);
        if(savePath != null && savePath.size()>0){


            savePath_other.add(savePath.get(savePath.size() - 1));

            savePath.remove(savePath.size() - 1);
            Iterator<DrawPath> iter = savePath.iterator();
            DrawPath drawPath;
            while (iter.hasNext()) {

                drawPath = iter.next();
                mPaint.setColor(drawPath.color);
                mPaint.setStrokeWidth(drawPath.size);
                mCanvas.drawPath(drawPath.path, drawPath.paint);
            }
            mainActivity.myview.invalidate();
            mPaint.setColor(this.pen_Color);
            mPaint.setStrokeWidth(this.pen_size);
        }
    }

    public void redo(){//redo
        mBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        mCanvas.setBitmap(mBitmap);
        if(savePath_other != null && savePath_other.size()>0){

            savePath.add(savePath_other.get(savePath_other.size() - 1));

            savePath_other.remove(savePath_other.size() - 1);
            Iterator<DrawPath> iter = savePath.iterator();
            DrawPath drawPath;
            while (iter.hasNext()) {
                drawPath = iter.next();
                mPaint.setColor(drawPath.color);
                mPaint.setStrokeWidth(drawPath.size);
                mCanvas.drawPath(drawPath.path, drawPath.paint);
            }
            mainActivity.myview.invalidate();
            mPaint.setColor(this.pen_Color);
            mPaint.setStrokeWidth(this.pen_size);
        }
    }

    public void clear(){//clear
        mBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        mCanvas.setBitmap(mBitmap);
        savePath.clear();
        savePath_other.clear();

        mainActivity.myview.invalidate();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                mPath = new Path();
                dp = new DrawPath(mPath,mPaint, mPaint.getColor(), mPaint.getStrokeWidth());

                touch_start(x, y);

                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                break;
        }
        return true;
    }


}
