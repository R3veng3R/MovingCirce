package ajermatsenkov.movingcirce;

/**
 * Created by ajermatsenkov on 13.10.2015.
 * Created by andreyutkin on 23/04/15.
 */
/** * Created by andreyutkin on 23/04/15. */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.view.View;

public class DrawScene extends View {

    private Paint canvasPaint;
    private Paint circlePaint;

    public int x;
    public int y;
    private int circleRadius = 10;

    private int dx;
    private int dy;
    private final int FRAME_RATE = 60;

    private int sceneWidth;
    private int sceneHeight;

    private Handler h;

    public DrawScene(Context context, int startX, int startY, int dx, int dy) {
        super(context);
        x = startX;
        y = startY;

        this.dx = dx;
        this.dy = dy;

        canvasPaint = new Paint();
        canvasPaint.setStyle(Paint.Style.FILL);
        canvasPaint.setColor(Color.YELLOW);

        circlePaint = new Paint();
        circlePaint.setColor(Color.GREEN);

        h = new Handler();
    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    @Override
    protected void onDraw(Canvas sceneCanvas) {
        super.onDraw(sceneCanvas);

        sceneCanvas.drawPaint(canvasPaint);
        sceneWidth = this.getWidth();
        sceneHeight = this.getHeight();

        sceneCanvas.drawCircle(x, y, circleRadius, circlePaint);

        x += dx;
        y += dy;

        if ((x > sceneWidth - circleRadius) || (x < circleRadius)) {
            dx = dx * -1;
        }

        if ((y > sceneHeight - circleRadius) || (y < circleRadius)) {
            dy = dy * -1;
        }

        h.postDelayed(r, FRAME_RATE);
    }

    public Point getCircleXY() {
        Point p = new Point(this.x, this.y);
        return p;
    }

    public Point getCircleDxDy() {
        Point p = new Point(this.dx, this.dy);
        return p;
    }
}
