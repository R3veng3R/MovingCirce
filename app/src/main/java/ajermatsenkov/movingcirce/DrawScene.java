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
import android.os.Handler;
import android.view.View;

public class DrawScene extends View {

    private Paint canvasPaint;
    private Paint circlePaint;

    public int x;
    public int y;
    private int circleRadius = 10;

    private int dx = 10;
    private int dy = 10;
    private final int FRAME_RATE = 10;

    private int scene_width;
    private int scene_height;

    private Handler h;

    public DrawScene(Context context, int start_x, int start_y) {
        super(context);
        x = start_x;
        y = start_y;

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
        scene_width = this.getWidth();
        scene_height = this.getHeight();

        sceneCanvas.drawCircle(x, y, circleRadius, circlePaint);

        x += dx;
        y += dy;

        if ((x > scene_width - circleRadius) || (x < circleRadius)) {
            dx = dx * -1;
        }

        if ((y > scene_height - circleRadius) || (y < circleRadius)) {
            dy = dy * -1;
        }

        h.postDelayed(r, FRAME_RATE);
    }

}
