package com.cookandroid.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    Button[] musicplay, musicinfo;
    ImageButton[] listbtn;
    LinearLayout[] musiclist;
    ImageView prev, play, next, equalizer, playingmusicimage, shufflebutton;
    ImageButton drawer;
    Integer presentmusic, maxmusiccount, musiccount;
    boolean playflag, shuffleflag;
    ScrollView scrollView;
    SlidingDrawer slidingDrawer;
    TextView lyric1, lyric2;
    TextView[] musictitles;
    InputStream inputS;
    ProgressBar sb;
    MediaPlayer mPlayernow, mPlayer;
    MediaPlayer.OnCompletionListener completionListener;

    class MyThread extends Thread {
        @Override
        public void run() { // 쓰레드가 시작되면 콜백되는 메서드
            // 씨크바 막대기 조금씩 움직이기 (노래 끝날 때까지 반복)
            while(playflag) {
                sb.setProgress(mPlayer.getCurrentPosition());
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        playflag = false;
        shuffleflag = false;
        presentmusic = 0;
        maxmusiccount = 13; // 총 음악 개수
        musicplay = new Button[maxmusiccount];
        listbtn = new ImageButton[maxmusiccount];
        musiclist = new LinearLayout[maxmusiccount];

        int[] menuflag = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] playlist = {R.raw.yesterday, R.raw.hotelcalifonia, R.raw.letitbe, R.raw.aftermidnight, R.raw.loveofmylife, R.raw.alone
                            , R.raw.oldrussianwaltz, R.raw.oldmoney, R.raw.timebomb, R.raw.molotovheart, R.raw.lofichillhop, R.raw.worknplay, R.raw.badlikethat};
        int[] lyriclist = {R.raw.yesterdaylyric, R.raw.hotelcalifornialyric, R.raw.letitbelyric, R.raw.aftermidnightlyric, R.raw.loveofmylifelyric, 0
                , 0, R.raw.oldmoneylyric, R.raw.timebomblyric, R.raw.molotovheartlyric, 0, R.raw.worknplaylyric, R.raw.badlikethatlyric};
        int[] albumimagelist = {R.drawable.yesterday, R.drawable.hotelcalifornia, R.drawable.letitbe, R.drawable.aftermidnight, R.drawable.loveofmylife, R.drawable.alone
                , R.drawable.oldrussianwaltz, R.drawable.oldmoney, R.drawable.timebomb, R.drawable.molotovheart, R.drawable.lofichillhop, R.drawable.worknplay, R.drawable.badlikethat};

        String[] title = {"Yesterday - The Beatles",
                "Hotel California - Eagles",
                "Let It Be - The Beatles",
                "After Midnight - Paxtom Pennington",
                "Love of My Life - BEN LVCAS",
                "Alone - Color Out",
                "Old Russian Waltz #2 - The Piano Lady",
                "Old Money - TAB",
                "Time Bomb - The Spin Wires",
                "Molotov Heart - Radio Nowhere",
                "Lo Fi Chill Hop - LART",
                "Work N' Play - Samie Bower",
                "Bad Like That - Carbon Casca"};
        MainActivity temp = this;


        mPlayer = MediaPlayer.create(this, playlist[0]);
        completionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                presentmusic++;
                presentmusic %= maxmusiccount;
                musicPlay(playlist, lyriclist, albumimagelist, title, temp, presentmusic);
            }
        };

        musictitles = new TextView[]{
                (TextView) findViewById(R.id.music1title),
                (TextView) findViewById(R.id.music2title),
                (TextView) findViewById(R.id.music3title),
                (TextView) findViewById(R.id.music4title),
                (TextView) findViewById(R.id.music5title),
                (TextView) findViewById(R.id.music6title),
                (TextView) findViewById(R.id.music7title),
                (TextView) findViewById(R.id.music8title),
                (TextView) findViewById(R.id.music9title),
                (TextView) findViewById(R.id.music10title),
                (TextView) findViewById(R.id.music11title),
                (TextView) findViewById(R.id.music12title),
                (TextView) findViewById(R.id.music13title)
        };

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
        shufflebutton = (ImageView) findViewById(R.id.shufflebutton);
        playingmusicimage = (ImageView) findViewById(R.id.playingmusicimage);


        equalizer = (ImageView) findViewById(R.id.equal);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(equalizer);
        Glide.with(this).load(R.drawable.equalizer).into(gifImage);

        drawer = (ImageButton) findViewById(R.id.handle);
        scrollView = (ScrollView) findViewById(R.id.scrollview1);
        slidingDrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer1);

        lyric1 = (TextView) findViewById(R.id.lyric1);
        lyric2 = (TextView) findViewById(R.id.lyric2);

        sb = (ProgressBar)findViewById(R.id.progressbar);

        musicinfo[0] = (Button) findViewById(R.id.music1info);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playflag == false) {
                    mPlayer.start();
                    int range = mPlayer.getDuration();
                    sb.setMax(range);
                    new MyThread().start();
                    playflag = true;
                    play.setImageResource(R.drawable.pause);
                    equalizer.setVisibility(View.VISIBLE);

                }
                else if(playflag == true) {
                    mPlayer.pause();
                    playflag = false;
                    play.setImageResource(R.drawable.play);
                    equalizer.setVisibility(View.INVISIBLE);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.stop();
                musictitles[presentmusic].setTextColor(Color.BLACK);
                if(shuffleflag) presentmusic = (int) (Math.random() * maxmusiccount);
                else presentmusic++;
                presentmusic %= maxmusiccount;
                musicPlay(playlist, lyriclist, albumimagelist, title, temp, presentmusic);
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.stop();
                musictitles[presentmusic].setTextColor(Color.BLACK);
                if(shuffleflag) presentmusic = (int) (Math.random() * maxmusiccount);
                else presentmusic--;
                if(presentmusic == -1) presentmusic = 12;
                musicPlay(playlist, lyriclist, albumimagelist, title, temp, presentmusic);
            }
        });

        shufflebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(shuffleflag) {
                    shufflebutton.setImageResource(R.drawable.noshuffle);
                    shuffleflag = false;
                }
                else {
                    shufflebutton.setImageResource(R.drawable.shuffle);
                    shuffleflag = true;
                }
            }
        });

        musicplay[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlay(playlist, lyriclist, albumimagelist, title, temp, 0);
            }
        });
        listbtn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listButtonClick(menuflag, 0);
            }
        });
        /*
        musicinfo[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickInfo(R.drawable.yesterday, R.raw.yesterdayinfo, "Yesterday", "The Beatles");
            }
        });

         */



        musicplay[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlay(playlist, lyriclist, albumimagelist, title, temp, 1);
            }
        });
        listbtn[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listButtonClick(menuflag, 1);
            }
        });

        musicplay[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlay(playlist, lyriclist, albumimagelist, title, temp, 2);
            }
        });
        listbtn[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listButtonClick(menuflag, 2);
            }
        });

        musicplay[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlay(playlist, lyriclist, albumimagelist, title, temp, 3);
            }
        });
        listbtn[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listButtonClick(menuflag, 3);
            }
        });

        musicplay[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlay(playlist, lyriclist, albumimagelist, title, temp, 4);
            }
        });
        listbtn[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listButtonClick(menuflag, 4);
            }
        });

        musicplay[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlay(playlist, lyriclist, albumimagelist, title, temp, 5);
            }
        });
        listbtn[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listButtonClick(menuflag, 5);
            }
        });

        musicplay[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlay(playlist, lyriclist, albumimagelist, title, temp, 6);
            }
        });
        listbtn[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listButtonClick(menuflag, 6);
            }
        });

        musicplay[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlay(playlist, lyriclist, albumimagelist, title, temp, 7);
            }
        });
        listbtn[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listButtonClick(menuflag, 7);
            }
        });

        musicplay[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlay(playlist, lyriclist, albumimagelist, title, temp, 8);
            }
        });
        listbtn[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listButtonClick(menuflag, 8);
            }
        });

        musicplay[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlay(playlist, lyriclist, albumimagelist, title, temp, 9);
            }
        });
        listbtn[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listButtonClick(menuflag, 9);
            }
        });

        musicplay[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlay(playlist, lyriclist, albumimagelist, title, temp, 10);
            }
        });
        listbtn[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listButtonClick(menuflag, 10);
            }
        });

        musicplay[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlay(playlist, lyriclist, albumimagelist, title, temp, 11);
            }
        });
        listbtn[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listButtonClick(menuflag, 11);
            }
        });

        musicplay[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlay(playlist, lyriclist, albumimagelist, title, temp, 12);
            }
        });
        listbtn[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listButtonClick(menuflag, 12);
            }
        });
    }

    public void musicPlay(int[] playlist, int[] lyriclist, int[] albumimagelist, String[] title, MainActivity temp, int musicindex) {
        if(playflag == true && presentmusic != musicindex) {
            musictitles[presentmusic].setTextColor(Color.BLACK);
            mPlayer.stop();
        }
        if(lyriclist[musicindex] == 0) lyric2.setText("가사 없음");
        else {
            try {
                inputS = getResources().openRawResource(lyriclist[musicindex]);
                byte[] txt = new byte[inputS.available()];
                inputS.read(txt);
                lyric2.setText(new String(txt));
                inputS.close();
            } catch (IOException e) {
            }
        }
        musictitles[musicindex].setTextColor(Color.parseColor("#CC00CC"));
        lyric1.setText(title[musicindex]);
        mPlayer = MediaPlayer.create(temp, playlist[musicindex]);
        mPlayer.start();
        mPlayer.setOnCompletionListener(completionListener);
        mPlayernow = mPlayer;
        playflag = true;
        presentmusic = musicindex;
        play.setImageResource(R.drawable.pause);
        playingmusicimage.setImageResource(albumimagelist[musicindex]);
        equalizer.setVisibility(View.VISIBLE);
        int range = mPlayer.getDuration();
        sb.setMax(range);
        new MyThread().start();
    }

    public void listButtonClick(int[] menuflag, int musicindex) {
        if(menuflag[musicindex] == 0) {
            musiclist[musicindex].setVisibility(View.VISIBLE);
            menuflag[musicindex] = 1;
        }
        else if (menuflag[musicindex] == 1) {
            musiclist[musicindex].setVisibility(View.GONE);
            menuflag[musicindex] = 0;
        }
    }

    /*
    public void clickInfo(int image, int info, String title, String singer) {
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        intent.putExtra("AlbumImage", image);
        intent.putExtra("MusicTitle", title);
        intent.putExtra("Singer", singer);
        intent.putExtra("MusicInfo", info);
        //intent.putExtra("VoteCount", voteCount);  // 투표 결과를 ResultActivity로 보냄
        //intent.putExtra("ImageName", imgName);  // 그림 제목을 ResultActivity로 보냄
        startActivity(intent);
    }

     */
}