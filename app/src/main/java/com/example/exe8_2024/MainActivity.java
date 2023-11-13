package com.example.exe8_2024;
/**
 * @author		Yiftah David yd2058@bs.amalnet.k12.il
 * @version	    1.1
 * @since		13/11/2023
 * this program uses internal files in order to save a string and display it later
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnCreateContextMenuListener {
    int count = 0;
    TextView tvc;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvc = findViewById(R.id.tvc);
        et = findViewById(R.id.et);
        strt();
    }
    /**
     * creates the context menu.
     * <p>
     *
     * @param	menu Description	refers to the current context menu.
     * @return	Description			returns a super action of this function.
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    /**
     * reacts to item selection.
     * <p>
     *
     * @param	item Description	refers to the selected menu item.
     * @return	Description			returns true.
     */

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.btnc){
            save();
            Intent si = new Intent(this,credits.class);
            startActivity(si);
        }
        return true;
    }
    /**
     * saves the counter and the string to the shared preferences file.
     * <p>
     *
     */

    public void save(){
        SharedPreferences settings = getSharedPreferences("PREFS_NAME",MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("Counter",count);
        editor.putString("String",et.getText().toString());
        editor.commit();
    }
    /**
     * reacts to a counter add button press and increments the counter.
     * <p>
     *
     * @param	view Description	refers to the current activity.
     */

    public void add(View view) {
        count++;
        tvc.setText(count+"");
    }
    /**
     * reacts to a counter reset button press and resets the counter.
     * <p>
     *
     * @param	view Description	refers to the current activity.
     */

    public void res(View view) {
        count = 0;
        tvc.setText(count+"");
    }
    /**
     * activates the save function and closes the app.
     * <p>
     *
     * @param	view Description	refers to the current activity.
     */

    public void out(View view) {
        save();
        finish();
    }
    /**
     * when the app starts the function will read the shared preferences file and fill the relevant field.
     * <p>
     *
     */
    public void strt(){
        SharedPreferences settings = getSharedPreferences("PREFS_NAME",MODE_PRIVATE);
        tvc.setText(settings.getInt("Counter",0)+"");
        et.setHint(settings.getString("String","Enter Text"));
    }

}