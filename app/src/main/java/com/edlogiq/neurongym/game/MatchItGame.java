package com.edlogiq.neurongym.game;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.edlogiq.neurongym.constant.DataBase;
import com.edlogiq.neurongym.constant.RefrenceWrapper;
import com.edlogiq.neurongym.neurongym.GameOver;
import com.edlogiq.neurongym.R;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MatchItGame extends Activity implements View.OnClickListener {

    public int StartTime = 45;
    int ab = 0;
    Timer timer;
    TimerTask mTimerTask;
    Handler handler = null;
    private TextView textview,timertext,scoretext;
    private ArrayList<String> meaning = new ArrayList<String>();
    private ArrayList<String> color = new ArrayList<String>();
    //    private String[] meaning={"BLUE","GREEN","WHITE"};
//    private String[] color={"BLUE","GREEN","WHITE"};
    private int indexMeaning, indexColor;
    private RelativeLayout pausepopup;
    private LinearLayout rightanswer, wronganswer;
    private ImageView rightans, wrongans;
    private RefrenceWrapper refrence;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_it_game);

        ((RelativeLayout)findViewById(R.id.matchitgame)).setBackgroundResource(R.color.white);
        if(DataBase.getTheam(this).equals("dark")){
            ((RelativeLayout)findViewById(R.id.matchitgame)).setBackgroundResource(R.color.black);
        }
        progress=(ProgressBar)findViewById(R.id.progressBar);
        progress.setProgress(0);
        refrence = RefrenceWrapper.getRefrenceWrapper(this);
        rightans = (ImageView) findViewById(R.id.imageright);
        wrongans = (ImageView) findViewById(R.id.imagewrong);
        rightanswer = (LinearLayout) findViewById(R.id.righttick);
        wronganswer = (LinearLayout) findViewById(R.id.wrongcross);
        handler = new Handler();
        timertext=(TextView)findViewById(R.id.timertextview);
        ((ImageView) findViewById(R.id.rightimageview)).setOnClickListener(this);
        ((ImageView) findViewById(R.id.wrongimageview)).setOnClickListener(this);
        textview = (TextView) findViewById(R.id.gametextview);
        scoretext = (TextView) findViewById(R.id.scoretextview);
        refrence.setMatchit(0);
        timer = new Timer();
        startTimer();
        init();
        game();
        popuplayout();
    }

    private void popuplayout() {
        ((LinearLayout) findViewById(R.id.layoutepause)).setOnClickListener(listner1);

        pausepopup = (RelativeLayout) findViewById(R.id.pausepopup);
        ((LinearLayout) findViewById(R.id.resume)).setOnClickListener(listner1);
        ((LinearLayout) findViewById(R.id.replay)).setOnClickListener(listner1);
        ((LinearLayout) findViewById(R.id.instructions)).setOnClickListener(listner1);
        ((LinearLayout) findViewById(R.id.sound)).setOnClickListener(listner1);
        ((LinearLayout) findViewById(R.id.exitgame)).setOnClickListener(listner1);
    }

    private void pausepopup() {
        if (pausepopup.getVisibility() == View.GONE) {
            pausepopup.setVisibility(View.VISIBLE);
        } else {
            pausepopup.setVisibility(View.GONE);
        }
    }

    View.OnClickListener listner1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.layoutepause) {
                pausepopup();
            } else if (v.getId() == R.id.resume) {

            } else if (v.getId() == R.id.replay) {


            } else if (v.getId() == R.id.instructions) {

                pausepopup();
            } else if (v.getId() == R.id.sound) {

            } else if (v.getId() == R.id.exitgame) {

            }

        }
    };

    int val=0;
    private void game() {
        scoretext.setText(""+refrence.getMatchit());
        if(meaning.size()==0 || color.size()==0){
            init();
        }
        indexMeaning = (new Random().nextInt(meaning.size()));


        if (meaning.get(indexMeaning).equals("BLUE")) {
            textview.setText(getResources().getString(R.string.blue));
        } else if (meaning.get(indexMeaning).equals("GREEN")) {
            textview.setText(getResources().getString(R.string.green));
        } else if (meaning.get(indexMeaning).equals("WHITE")) {
            textview.setText(getResources().getString(R.string.white));
        }else if (meaning.get(indexMeaning).equals("RED")) {
            textview.setText(getResources().getString(R.string.red));
        } else if (meaning.get(indexMeaning).equals("ORANGE")) {
            textview.setText(getResources().getString(R.string.orange));
        }else if (meaning.get(indexMeaning).equals("VIOLET")) {
            textview.setText(getResources().getString(R.string.violet));
        } else if (meaning.get(indexMeaning).equals("BROWN")) {
            textview.setText(getResources().getString(R.string.brown));
        }else if (meaning.get(indexMeaning).equals("PINK")) {
            textview.setText(getResources().getString(R.string.pink));
        }


        if(val==0){
            indexColor=indexMeaning;
            val=1;
        }else if(val==1){
            indexColor = (new Random().nextInt(color.size()));
            val=0;
        }

        if (color.get(indexColor).equals("BLUE")) {
            textview.setTextColor(getResources().getColor(R.color.blue));
        } else if (color.get(indexColor).equals("GREEN")) {
            textview.setTextColor(getResources().getColor(R.color.green));
        } else if (color.get(indexColor).equals("WHITE")) {
            textview.setTextColor(getResources().getColor(R.color.white));
        }else if (color.get(indexColor).equals("RED")) {
            textview.setTextColor(getResources().getColor(R.color.red));
        } else if (color.get(indexColor).equals("ORANGE")) {
            textview.setTextColor(getResources().getColor(R.color.orange));
        }else if (color.get(indexColor).equals("VIOLET")) {
            textview.setTextColor(getResources().getColor(R.color.violet));
        } else if (color.get(indexColor).equals("BROWN")) {
            textview.setTextColor(getResources().getColor(R.color.brown));
        }else if (color.get(indexColor).equals("PINK")) {
            textview.setTextColor(getResources().getColor(R.color.pink));
        }
        animationval=true;

    }


    private void init() {
        meaning.clear();
        color.clear();

        meaning.add("BLUE");
        meaning.add("GREEN");
        meaning.add("WHITE");
        meaning.add("RED");
        meaning.add("ORANGE");
        meaning.add("VIOLET");
        meaning.add("BROWN");
        meaning.add("PINK");

        color.add("BLUE");
        color.add("GREEN");
        color.add("WHITE");
        color.add("RED");
        color.add("ORANGE");
        color.add("VIOLET");
        color.add("BROWN");
        color.add("PINK");
    }

    @Override
    public void onClick(View v) {
        if(animationval) {
            if (v.getId() == R.id.rightimageview) {
                if (meaning.get(indexMeaning).equals(color.get(indexColor))) {
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.wrongimageview) {
                if (meaning.get(indexMeaning).equals(color.get(indexColor))) {
                    wronganswer();
                } else {
                    rightanswer();
                }
            }

            color.remove(indexColor);
            meaning.remove(indexMeaning);
        }
    }

 boolean animationval=true;

    private void rightanswer() {
        {
            if (rightanswer.getVisibility() == View.GONE) {
                rightanswer.setVisibility(View.VISIBLE);
                rightans.setVisibility(View.VISIBLE);
                ScaleAnimation scal = new ScaleAnimation(1f, 1.5f, 1f, 1.5f, Animation.RELATIVE_TO_SELF,
                        (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
                scal.setDuration(500);

                AlphaAnimation animation1 = new AlphaAnimation(1f, 0f);
                animation1.setDuration(500);
                rightans.startAnimation(animation1);
                AnimationSet anim = new AnimationSet(false);
                anim.addAnimation(scal);
                anim.addAnimation(animation1);
                rightanswer.startAnimation(anim);
                scal.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        animationval=false;
                        refrence.setMatchit(refrence.getMatchit() + 100);
                        process();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        rightanswer.setVisibility(View.GONE);
                        rightans.setVisibility(View.GONE);
                        rightval=rightval+1;
                        game();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }

    }

    private void wronganswer() {
        if (wronganswer.getVisibility() == View.GONE) {
            wronganswer.setVisibility(View.VISIBLE);
            wrongans.setVisibility(View.VISIBLE);
            ScaleAnimation scal = new ScaleAnimation(1f, 1.5f, 1f, 1.5f, Animation.RELATIVE_TO_SELF,
                    (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
            scal.setDuration(500);

            AlphaAnimation animation1 = new AlphaAnimation(1f, 0f);
            animation1.setDuration(500);
            wrongans.startAnimation(animation1);
            AnimationSet anim = new AnimationSet(false);
            anim.addAnimation(scal);
            anim.addAnimation(animation1);
            wronganswer.startAnimation(anim);
            scal.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    wronganswer.setVisibility(View.GONE);
                    wrongans.setVisibility(View.GONE);
                    if(progress.getProgress()<50){
                        for(int i=progress.getProgress();i>=0;i--){
                            progress.setProgress(i);
                        }
                    }else{
                        for(int i=progress.getProgress();i>=50;i--){
                            progress.setProgress(i);
                        }
                    }
                    wrongval=wrongval+1;
                    game();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }

    int multyplyer=0,prog=0,multiplierval=0;
    private void process(){
        for(int i=prog;i<=(prog+10);i++) {
            progress.setProgress(i);
        }

        prog=prog+10;
        if(prog==50){
            ((LinearLayout)findViewById(R.id.processlayoute2)).
                    setBackgroundResource(R.drawable.circle_text_cyan);
            ((TextView) findViewById(R.id.processtext2)).setTextColor(Color.parseColor("#ffffff"));
            multiplierval=multiplierval+1;
        }
        if(prog==100){
            multiplierval=multiplierval+1;
            for(int i=prog;i>0;i--) {
                progress.setProgress(i);

            }
            prog=0;
            if(multyplyer==0) {
                ((TextView) findViewById(R.id.processtext1)).setText("x3");
                ((TextView) findViewById(R.id.processtext2)).setText("x4");
                ((TextView) findViewById(R.id.processtext3)).setText("x5");
                ((TextView) findViewById(R.id.processtext2)).setTextColor(Color.parseColor("#888888"));
                ((LinearLayout)findViewById(R.id.processlayoute2)).
                        setBackgroundResource(R.drawable.circle_text);
                multyplyer=1;
            }else if(multyplyer==1) {
                ((TextView) findViewById(R.id.processtext1)).setText("x5");
                ((TextView) findViewById(R.id.processtext2)).setText("x6");
                ((TextView) findViewById(R.id.processtext3)).setText("x7");
                ((TextView) findViewById(R.id.processtext2)).setTextColor(Color.parseColor("#888888"));
                ((LinearLayout)findViewById(R.id.processlayoute2)).
                        setBackgroundResource(R.drawable.circle_text);
                multyplyer=2;
            }else if(multyplyer==2) {
                ((TextView) findViewById(R.id.processtext1)).setText("x7");
                ((TextView) findViewById(R.id.processtext2)).setText("x8");
                ((TextView) findViewById(R.id.processtext3)).setText("x9");
                ((TextView) findViewById(R.id.processtext2)).setTextColor(Color.parseColor("#888888"));
                ((LinearLayout)findViewById(R.id.processlayoute2)).
                        setBackgroundResource(R.drawable.circle_text);
                multyplyer=3;
            }else if(multyplyer==3) {
                ((TextView) findViewById(R.id.processtext1)).setText("x9");
                ((TextView) findViewById(R.id.processtext2)).setText("x10");
                ((TextView) findViewById(R.id.processtext3)).setText("x11");
                ((TextView) findViewById(R.id.processtext2)).setTextColor(Color.parseColor("#888888"));
                ((LinearLayout)findViewById(R.id.processlayoute2)).
                        setBackgroundResource(R.drawable.circle_text);
            }

        }
    }

    @Override
    public void onBackPressed() {
        stopTimer();
        super.onBackPressed();
    }

    public void startTimer() {

        Log.e("StartTime", "" + StartTime);
        mTimerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        if (StartTime > 0) {
                            StartTime--;
                        }
                        if (StartTime == 0) {
                            gameover();
                            stopTimer();
                        }
                        timertext.setText("" + StartTime);
                    }
                });
            }
        };
        timer.schedule(mTimerTask, 0, 1000);
    }

    int wrongval=0,rightval=0;
    private void gameover() {
        refrence.setGame("MatchIt");
        refrence.setScore(refrence.getMatchit());
        refrence.setMultiplier(multiplierval);
        int total=wrongval+rightval;
        refrence.setAccuracy((int)((rightval*100)/total));
        Intent intent=new Intent(this, GameOver.class);
        startActivity(intent);
        this.finish();
    }

    public void stopTimer() {
        if (mTimerTask != null) {
            mTimerTask.cancel();
            StartTime = 0;
        }

    }
}
