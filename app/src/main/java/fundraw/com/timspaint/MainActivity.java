package fundraw.com.timspaint;

import android.os.Bundle;
// import android.support.v7.app.ActionBarActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Images for Buttons made by Shuang
 * Created by tbrad_000 on 1/28/2015.
 */

public class MainActivity extends AppCompatActivity {

    public static MyView myview;
    MyButtons myButtons = new MyButtons();
    private LinearLayout viewlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        myview = new MyView(this, null, width, height);
        viewlayout = (LinearLayout) findViewById(R.id.viewlayout);
        viewlayout.addView(myview);
        myview.setMainActivity(MainActivity.this);
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
            Toast.makeText(getApplicationContext(),
                    "Settings",
                    Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.menu_size) {
            this.myButtons.sizeChanger(this.viewlayout, myview);
            return true;
        } else if (id == R.id.menu_color) {
            this.myButtons.color_button(this.viewlayout, myview);
            return true;
        } else if (id == R.id.menu_undo) {
            this.myButtons.undo_btn(this.viewlayout, myview);
            return true;
        } else if (id == R.id.menu_redo) {
            myview.redo();
            return true;
        } else if (id == R.id.menu_clear) {
            this.myButtons.clear_btn(this.viewlayout, myview);
            return true;
        } else if (id == R.id.menu_save) {
            this.myButtons.save_btn(this.viewlayout, myview);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
