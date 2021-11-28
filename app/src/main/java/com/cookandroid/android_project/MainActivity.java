package com.cookandroid.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //MediaPlayer mPlayer;
    Button btnStart, btnStop;
    Button music1play;
    ImageButton music1btn;
    LinearLayout music1list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MediaPlayer mPlayer;
        mPlayer = MediaPlayer.create(this, R.raw.yesterday);

        final Switch switch1 = (Switch) findViewById(R.id.switch1);

        music1btn = (ImageButton) findViewById(R.id.music1button);
        music1play = (Button) findViewById(R.id.music1play);
        music1list = (LinearLayout) findViewById(R.id.music1list);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);

        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switch1.isChecked() == true)
                    mPlayer.start();
                else
                    mPlayer.pause();
            }
        });

        music1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music1list.setVisibility(View.VISIBLE);
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.start();
                Toast.makeText(MainActivity.this,"Media Play Button",Toast.LENGTH_LONG).show();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.pause();
                Toast.makeText(MainActivity.this,"Media Pause Button",Toast.LENGTH_LONG).show();
            }
        });
    }
}