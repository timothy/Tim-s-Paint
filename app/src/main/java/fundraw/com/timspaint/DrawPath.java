package fundraw.com.timspaint;

import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by tbrad_000 on 1/28/2015.
 */
public class DrawPath {

    public Path path;
    public Paint paint;
    public float size;
    public int color;


    DrawPath(Path p, Paint pnt, int color, float size){
        this.color = color;
        this.size = size;
        this.paint = pnt;
        this.path = p;
    }
}
