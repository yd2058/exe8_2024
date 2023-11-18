package com.example.exe8_2024;
/**
 * @author		Yiftah David yd2058@bs.amalnet.k12.il
 * @version	    1.2
 * @since		13/11/2023
 * this program uses internal files in order to save a string and display it later
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
    TextView tvt;
    EditText et;
    private final String FILENAME = "bnk.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvt = findViewById(R.id.tvc);
        et = findViewById(R.id.et);
        tvt.setText(read());
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
     * writes to file.
     * <p>
     * @param res   indicates if the write is in order to reset the file or not.
     */

    public void write(boolean res){
        try{
            String prev = read();
            FileOutputStream fOS = openFileOutput(FILENAME,MODE_PRIVATE);
            OutputStreamWriter oSW = new OutputStreamWriter(fOS);
            BufferedWriter bW = new BufferedWriter(oSW);
            if (res){bW.write("");}
            else{bW.write(prev+" "+et.getText().toString());}
            bW.close();
            oSW.close();
            fOS.close();
        }
        catch(Exception e){
            Toast.makeText(this, "Something went wrong \n Please try again", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * reacts to a text save button and executes save, and update the textview
     * <p>
     *
     * @param	view Description	refers to the current activity.
     */

    public void saver(View view) {
        write(false);
        tvt.setText(read());
    }
    /**
     * resets the file
     * <p>
     *
     * @param	view Description	refers to the current activity.
     */

    public void res(View view) {
        write(true);
        tvt.setText("");
    }
    /**
     * closes the app.
     * <p>
     *
     * @param	view Description	refers to the current activity.
     */

    public void out(View view) {
        finish();
    }
    /**
     * read from the file.
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
                sB.append(line);
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