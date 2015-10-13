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
        start_x = storedData.getInt("currentRate", 50);
        start_y = storedData.getInt("highestRate", 50);

        scene = new DrawScene(this, start_x, start_y);
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
        storedDataEditor.putInt("currentRate", scene.x);
        storedDataEditor.putInt("highestRate", scene.y);
        storedDataEditor.commit();

    }
}
