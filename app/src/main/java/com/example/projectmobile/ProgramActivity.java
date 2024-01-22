package com.example.projectmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProgramActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.projectmobile";
    DBHelper DB;
    EditText name, password, idr;
    Button insert, update, delete, view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);


        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        idr = findViewById(R.id.idr);

        insert = findViewById(R.id.add);
        update = findViewById(R.id.update1);
        delete = findViewById(R.id.delete1);
        view = findViewById(R.id.list1);

        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String passTXT = password.getText().toString();
                String idrTXT = idr.getText().toString();


                Boolean checkinsertdata = DB.AddData(idrTXT, passTXT, nameTXT);
                if (checkinsertdata == true)
                    Toast.makeText(ProgramActivity.this, (R.string.insert), Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ProgramActivity.this, (R.string.noinsert), Toast.LENGTH_LONG).show();

            }
        });




        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String passTXT = password.getText().toString();
                String idrTXT = idr.getText().toString();


                Boolean checkupdatedata = DB.updateuserdata(idrTXT, passTXT, nameTXT);
                if (checkupdatedata == true)
                    Toast.makeText(ProgramActivity.this, (R.string.update), Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ProgramActivity.this, (R.string.noupdate), Toast.LENGTH_LONG).show();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();


                Boolean checkdeletedata = DB.deletedata(nameTXT);
                if (checkdeletedata == true)
                    Toast.makeText(ProgramActivity.this, (R.string.delete), Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ProgramActivity.this, (R.string.nodelete), Toast.LENGTH_LONG).show();

            }

        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0) {
                    Toast.makeText(ProgramActivity.this, (R.string.exist), Toast.LENGTH_SHORT).show();
                   return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()) {

                    buffer.append("\n" + getText(R.string.name) + " : " + res.getString(1) + "\n");
                    buffer.append(getText(R.string.passwordlist) + " : " + res.getString(2) + "\n");
                    buffer.append(getText(R.string.id) + " : " + res.getString(3) + "\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(ProgramActivity.this);
                builder.setCancelable(true);
                builder.setTitle(R.string.listRME);
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_display_message, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear:
                clear();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    private void clear(){
        //clear shared preferences
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();

        //delete data all from db
        DB.DeleteALL();

        //update change clear
        finish();
        startActivity(getIntent());
    }

}
















