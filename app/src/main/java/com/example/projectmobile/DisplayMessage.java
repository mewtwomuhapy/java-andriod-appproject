package com.example.projectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DisplayMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Intent intent = getIntent();
        String message =intent.getStringExtra(ProgramActivity.EXTRA_MESSAGE);

        TextView textView = findViewById((R.id.text_message));
        textView.setText(message);
        textView.setTextSize(50);

        Toast.makeText(getApplicationContext(), (R.string.success),Toast.LENGTH_LONG).show();
    }
}