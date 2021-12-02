package com.cookandroid.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SlidingDrawer;
import android.widget.Switch;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class MainActivity extends AppCompatActivity {
    Button[] musicplay;
    ImageButton[] listbtn;
    LinearLayout[] musiclist;
    ImageView prev, play, next, equalizer, playingmusicimage;
    ImageButton drawer;
    Integer playflag, presentmusic;
    ScrollView scrollView;
    SlidingDrawer slidingDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playflag = 0;

        musicplay = new Button[13];
        listbtn = new ImageButton[13];
        musiclist = new LinearLayout[13];
        Integer[] menuflag = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        final MediaPlayer[] mPlayer = new MediaPlayer[13];

        mPlayer[0] = MediaPlayer.create(this, R.raw.yesterday);
        mPlayer[1] = MediaPlayer.create(this, R.raw.hotelcalifonia);
        mPlayer[2] = MediaPlayer.create(this, R.raw.letitbe);
        mPlayer[3] = MediaPlayer.create(this, R.raw.aftermidnight);
        mPlayer[4] = MediaPlayer.create(this, R.raw.loveofmylife);
        mPlayer[5] = MediaPlayer.create(this, R.raw.alone);
        mPlayer[6] = MediaPlayer.create(this, R.raw.oldrussianwaltz);
        mPlayer[7] = MediaPlayer.create(this, R.raw.oldmoney);
        mPlayer[8] = MediaPlayer.create(this, R.raw.timebomb);
        mPlayer[9] = MediaPlayer.create(this, R.raw.molotovheart);
        mPlayer[10] = MediaPlayer.create(this, R.raw.lofichillhop);
        mPlayer[11] = MediaPlayer.create(this, R.raw.worknplay);
        mPlayer[12] = MediaPlayer.create(this, R.raw.badlikethat);

        listbtn[0] = (ImageButton) findViewById(R.id.music1button);
        listbtn[1] = (ImageButton) findViewById(R.id.music2button);
        listbtn[2] = (ImageButton) findViewById(R.id.music3button);
        listbtn[3] = (ImageButton) findViewById(R.id.music4button);
        listbtn[4] = (ImageButton) findViewById(R.id.music5button);
        listbtn[5] = (ImageButton) findViewById(R.id.music6button);
        listbtn[6] = (ImageButton) findViewById(R.id.music7button);
        listbtn[7] = (ImageButton) findViewById(R.id.music8button);
        listbtn[8] = (ImageButton) findViewById(R.id.music9button);
        listbtn[9] = (ImageButton) findViewById(R.id.music10button);
        listbtn[10] = (ImageButton) findViewById(R.id.music11button);
        listbtn[11] = (ImageButton) findViewById(R.id.music12button);
        listbtn[12] = (ImageButton) findViewById(R.id.music13button);

        musicplay[0] = (Button) findViewById(R.id.music1play);
        musicplay[1] = (Button) findViewById(R.id.music2play);
        musicplay[2] = (Button) findViewById(R.id.music3play);
        musicplay[3] = (Button) findViewById(R.id.music4play);
        musicplay[4] = (Button) findViewById(R.id.music5play);
        musicplay[5] = (Button) findViewById(R.id.music6play);
        musicplay[6] = (Button) findViewById(R.id.music7play);
        musicplay[7] = (Button) findViewById(R.id.music8play);
        musicplay[8] = (Button) findViewById(R.id.music9play);
        musicplay[9] = (Button) findViewById(R.id.music10play);
        musicplay[10] = (Button) findViewById(R.id.music11play);
        musicplay[11] = (Button) findViewById(R.id.music12play);
        musicplay[12] = (Button) findViewById(R.id.music13play);

        musiclist[0] = (LinearLayout) findViewById(R.id.music1list);
        musiclist[1] = (LinearLayout) findViewById(R.id.music2list);
        musiclist[2] = (LinearLayout) findViewById(R.id.music3list);
        musiclist[3] = (LinearLayout) findViewById(R.id.music4list);
        musiclist[4] = (LinearLayout) findViewById(R.id.music5list);
        musiclist[5] = (LinearLayout) findViewById(R.id.music6list);
        musiclist[6] = (LinearLayout) findViewById(R.id.music7list);
        musiclist[7] = (LinearLayout) findViewById(R.id.music8list);
        musiclist[8] = (LinearLayout) findViewById(R.id.music9list);
        musiclist[9] = (LinearLayout) findViewById(R.id.music10list);
        musiclist[10] = (LinearLayout) findViewById(R.id.music11list);
        musiclist[11] = (LinearLayout) findViewById(R.id.music12list);
        musiclist[12] = (LinearLayout) findViewById(R.id.music13list);



        prev = (ImageView) findViewById(R.id.prev);
        play = (ImageView) findViewById(R.id.play);
        next = (ImageView) findViewById(R.id.next);
        playingmusicimage = (ImageView) findViewById(R.id.playingmusicimage);

        equalizer = (ImageView) findViewById(R.id.equal);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(equalizer);
        Glide.with(this).load(R.drawable.equalizer).into(gifImage);

        drawer = (ImageButton) findViewById(R.id.handle);
        scrollView = (ScrollView) findViewById(R.id.scrollview1);
        slidingDrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer1);

        playingmusicimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                scrollView.setLayoutParams(new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.FILL_PARENT, 300));
                play.setImageResource(R.drawable.pause);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playflag == 0 && presentmusic != null) {
                    mPlayer[presentmusic].start();
                    playflag = 1;
                    play.setImageResource(R.drawable.pause);
                    equalizer.setVisibility(View.VISIBLE);

                }
                else if(playflag == 1 && presentmusic != null) {
                    mPlayer[presentmusic].pause();
                    playflag = 0;
                    play.setImageResource(R.drawable.play);
                    equalizer.setVisibility(View.INVISIBLE);
                }
            }
        });

        musicplay[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playflag == 1 && presentmusic != null) mPlayer[presentmusic].pause();
                mPlayer[0].start();
                playflag = 1;
                presentmusic = 0;
                play.setImageResource(R.drawable.pause);
                playingmusicimage.setImageResource(R.drawable.yesterday);
                equalizer.setVisibility(View.VISIBLE);
            }
        });

        listbtn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menuflag[0] == 0) {
                    musiclist[0].setVisibility(View.VISIBLE);
                    menuflag[0] = 1;
                }
                else if (menuflag[0] == 1) {
                    musiclist[0].setVisibility(View.GONE);
                    menuflag[0] = 0;
                }
            }
        });

        musicplay[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playflag == 1) mPlayer[presentmusic].pause();
                mPlayer[1].start();
                playflag = 1;
                presentmusic = 1;
                play.setImageResource(R.drawable.pause);
                playingmusicimage.setImageResource(R.drawable.hotelcalifornia);
                equalizer.setVisibility(View.VISIBLE);
            }
        });

        listbtn[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menuflag[1] == 0) {
                    musiclist[1].setVisibility(View.VISIBLE);
                    menuflag[1] = 1;
                }
                else if (menuflag[1] == 1) {
                    musiclist[1].setVisibility(View.GONE);
                    menuflag[1] = 0;
                }
            }
        });

        musicplay[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playflag == 1) mPlayer[presentmusic].pause();
                mPlayer[2].start();
                playflag = 1;
                presentmusic = 2;
                play.setImageResource(R.drawable.pause);
                playingmusicimage.setImageResource(R.drawable.letitbe);
                equalizer.setVisibility(View.VISIBLE);
            }
        });

        listbtn[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menuflag[2] == 0) {
                    musiclist[2].setVisibility(View.VISIBLE);
                    menuflag[2] = 1;
                }
                else if (menuflag[2] == 1) {
                    musiclist[2].setVisibility(View.GONE);
                    menuflag[2] = 0;
                }
            }
        });

        musicplay[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playflag == 1) mPlayer[presentmusic].pause();
                mPlayer[3].start();
                playflag = 1;
                presentmusic = 3;
                play.setImageResource(R.drawable.pause);
                playingmusicimage.setImageResource(R.drawable.aftermidnight);
                equalizer.setVisibility(View.VISIBLE);
            }
        });

        listbtn[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menuflag[3] == 0) {
                    musiclist[3].setVisibility(View.VISIBLE);
                    menuflag[3] = 1;
                }
                else if (menuflag[3] == 1) {
                    musiclist[3].setVisibility(View.GONE);
                    menuflag[3] = 0;
                }
            }
        });

        musicplay[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playflag == 1) mPlayer[presentmusic].pause();
                mPlayer[4].start();
                playflag = 1;
                presentmusic = 4;
                play.setImageResource(R.drawable.pause);
                playingmusicimage.setImageResource(R.drawable.loveofmylife);
                equalizer.setVisibility(View.VISIBLE);
            }
        });

        listbtn[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menuflag[4] == 0) {
                    musiclist[4].setVisibility(View.VISIBLE);
                    menuflag[4] = 1;
                }
                else if (menuflag[4] == 1) {
                    musiclist[4].setVisibility(View.GONE);
                    menuflag[4] = 0;
                }
            }
        });

        musicplay[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playflag == 1) mPlayer[presentmusic].pause();
                mPlayer[5].start();
                playflag = 1;
                presentmusic = 5;
                play.setImageResource(R.drawable.pause);
                playingmusicimage.setImageResource(R.drawable.alone);
                equalizer.setVisibility(View.VISIBLE);
            }
        });

        listbtn[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menuflag[5] == 0) {
                    musiclist[5].setVisibility(View.VISIBLE);
                    menuflag[5] = 1;
                }
                else if (menuflag[5] == 1) {
                    musiclist[5].setVisibility(View.GONE);
                    menuflag[5] = 0;
                }
            }
        });

        musicplay[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playflag == 1) mPlayer[presentmusic].pause();
                mPlayer[6].start();
                playflag = 1;
                presentmusic = 6;
                play.setImageResource(R.drawable.pause);
                playingmusicimage.setImageResource(R.drawable.oldrussianwaltz);
                equalizer.setVisibility(View.VISIBLE);
            }
        });

        listbtn[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menuflag[6] == 0) {
                    musiclist[6].setVisibility(View.VISIBLE);
                    menuflag[6] = 1;
                }
                else if (menuflag[6] == 1) {
                    musiclist[6].setVisibility(View.GONE);
                    menuflag[6] = 0;
                }
            }
        });

        musicplay[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playflag == 1) mPlayer[presentmusic].pause();
                mPlayer[7].start();
                playflag = 1;
                presentmusic = 7;
                play.setImageResource(R.drawable.pause);
                playingmusicimage.setImageResource(R.drawable.oldmoney);
                equalizer.setVisibility(View.VISIBLE);
            }
        });

        listbtn[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menuflag[7] == 0) {
                    musiclist[7].setVisibility(View.VISIBLE);
                    menuflag[7] = 1;
                }
                else if (menuflag[7] == 1) {
                    musiclist[7].setVisibility(View.GONE);
                    menuflag[7] = 0;
                }
            }
        });

        musicplay[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playflag == 1) mPlayer[presentmusic].pause();
                mPlayer[8].start();
                playflag = 1;
                presentmusic = 8;
                play.setImageResource(R.drawable.pause);
                playingmusicimage.setImageResource(R.drawable.timebomb);
                equalizer.setVisibility(View.VISIBLE);
            }
        });

        listbtn[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menuflag[8] == 0) {
                    musiclist[8].setVisibility(View.VISIBLE);
                    menuflag[8] = 1;
                }
                else if (menuflag[8] == 1) {
                    musiclist[8].setVisibility(View.GONE);
                    menuflag[8] = 0;
                }
            }
        });

        musicplay[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playflag == 1) mPlayer[presentmusic].pause();
                mPlayer[9].start();
                playflag = 1;
                presentmusic = 9;
                play.setImageResource(R.drawable.pause);
                playingmusicimage.setImageResource(R.drawable.molotovheart);
                equalizer.setVisibility(View.VISIBLE);
            }
        });

        listbtn[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menuflag[9] == 0) {
                    musiclist[9].setVisibility(View.VISIBLE);
                    menuflag[9] = 1;
                }
                else if (menuflag[9] == 1) {
                    musiclist[9].setVisibility(View.GONE);
                    menuflag[9] = 0;
                }
            }
        });

        musicplay[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playflag == 1) mPlayer[presentmusic].pause();
                mPlayer[10].start();
                playflag = 1;
                presentmusic = 10;
                play.setImageResource(R.drawable.pause);
                playingmusicimage.setImageResource(R.drawable.lofichillhop);
                equalizer.setVisibility(View.VISIBLE);
            }
        });

        listbtn[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menuflag[10] == 0) {
                    musiclist[10].setVisibility(View.VISIBLE);
                    menuflag[10] = 1;
                }
                else if (menuflag[10] == 1) {
                    musiclist[10].setVisibility(View.GONE);
                    menuflag[10] = 0;
                }
            }
        });

        musicplay[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playflag == 1) mPlayer[presentmusic].pause();
                mPlayer[11].start();
                playflag = 1;
                presentmusic = 11;
                play.setImageResource(R.drawable.pause);
                playingmusicimage.setImageResource(R.drawable.worknplay);
                equalizer.setVisibility(View.VISIBLE);
            }
        });

        listbtn[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menuflag[11] == 0) {
                    musiclist[11].setVisibility(View.VISIBLE);
                    menuflag[11] = 1;
                }
                else if (menuflag[11] == 1) {
                    musiclist[11].setVisibility(View.GONE);
                    menuflag[11] = 0;
                }
            }
        });

        musicplay[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playflag == 1) mPlayer[presentmusic].pause();
                mPlayer[12].start();
                playflag = 1;
                presentmusic = 12;
                play.setImageResource(R.drawable.pause);
                playingmusicimage.setImageResource(R.drawable.badlikethat);
                equalizer.setVisibility(View.VISIBLE);
            }
        });

        listbtn[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menuflag[12] == 0) {
                    musiclist[12].setVisibility(View.VISIBLE);
                    menuflag[12] = 1;
                }
                else if (menuflag[12] == 1) {
                    musiclist[12].setVisibility(View.GONE);
                    menuflag[12] = 0;
                }
            }
        });
    }
}