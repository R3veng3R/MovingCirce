package ajermatsenkov.movingcirce;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private int start_x;
    private int start_y;

    private DrawScene scene;
    public static final String PREFS_NAME = "SharedPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences storedData = getSharedPreferences(PREFS_NAME, 0);
        start_x = storedData.getInt("x", 50);
        start_y = storedData.getInt("y", 50);
        int dx = storedData.getInt("dx", 5);
        int dy = storedData.getInt("dy", 5);

        scene = new DrawScene(this, start_x, start_y, dx, dy);
        setContentView(scene);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.saveData();
    }

    protected void saveData() {
        SharedPreferences storedData = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor storedDataEditor = storedData.edit();
        storedDataEditor.putInt("x", scene.getCircleXY().x);
        storedDataEditor.putInt("y", scene.getCircleXY().y);
        storedDataEditor.putInt("dx", scene.getCircleDxDy().x);
        storedDataEditor.putInt("dy", scene.getCircleDxDy().y);
        storedDataEditor.commit();
    }
}
