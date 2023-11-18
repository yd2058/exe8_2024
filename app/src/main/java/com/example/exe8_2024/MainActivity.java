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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnCreateContextMenuListener {
    int count = 0;
    TextView tvt;
    EditText et;
    private final String FILENAME = "bnk.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvt = findViewById(R.id.tvc);
        et = findViewById(R.id.et);
        read();
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
            write(false);
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

    public void write(boolean res){
        try{
            FileOutputStream fOS = openFileOutput(FILENAME,MODE_PRIVATE);
            OutputStreamWriter oSW = new OutputStreamWriter(fOS);
            BufferedWriter bW = new BufferedWriter(oSW);
            if (!res){bW.write(read()+et.getText().toString());}
            else{bW.write("");}
            bW.close();
            oSW.close();
            fOS.close();
        }
        catch(Exception e){
            Toast.makeText(this, "Something went wrong \n Please try again", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * reacts to a counter add button press and increments the counter.
     * <p>
     *
     * @param	view Description	refers to the current activity.
     */

    public void saver(View view) {
        write(false);
        tvt.setText(read());
    }
    /**
     * reacts to a counter reset button press and resets the counter.
     * <p>
     *
     * @param	view Description	refers to the current activity.
     */

    public void res(View view) {
        write(true);
        tvt.setText("");
    }
    /**
     * activates the save function and closes the app.
     * <p>
     *
     * @param	view Description	refers to the current activity.
     */

    public void out(View view) {
        finish();
    }
    /**
     * when the app starts the function will read the shared preferences file and fill the relevant field.
     * <p>
     *
     */
    public String read(){
        try{
            FileInputStream fIS= openFileInput(FILENAME);
            InputStreamReader iSR = new InputStreamReader(fIS);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            String line = bR.readLine();
            while (line != null) {
                sB.append(line+'\n');
                line = bR.readLine();
            }
            bR.close();
            iSR.close();
            fIS.close();
            return sB.toString();}
        catch(Exception e){
            Toast.makeText(this, "Something went wrong \n Please try again", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

}