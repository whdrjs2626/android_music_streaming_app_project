package com.cookandroid.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class InfoActivity extends AppCompatActivity {
    ImageView albumimage;
    TextView infotitle, infomation;
    InputStream inputS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        setTitle("음악 정보");

        albumimage = (ImageView) findViewById(R.id.albumimage);
        infotitle = (TextView) findViewById(R.id.infotitle);
        infomation = (TextView) findViewById(R.id.infomation);

        Intent intent = getIntent();
        int AlbumImage = intent.getIntExtra("AlbumImage", 0);
        int musicInfo = intent.getIntExtra("MusicInfo", 0);
        String title = intent.getStringExtra("MusicTitle");
        String name = intent.getStringExtra("Singer");

        albumimage.setImageResource(AlbumImage);
        infotitle.setText(title);
        try {
            inputS = getResources().openRawResource(musicInfo);
            byte[] txt = new byte[inputS.available()];
            inputS.read(txt);
            infomation.setText(new String(txt));
            inputS.close();
        } catch (IOException e) {
        }
    }
}
