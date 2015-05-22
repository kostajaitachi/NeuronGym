package com.edlogiq.neurongym.neurongym;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.edlogiq.neurongym.constant.DataBase;
import com.edlogiq.neurongym.game.ReversalGame;
import com.edlogiq.neurongym.howtoplay.HowToPlayReversal;
import com.edlogiq.neurongym.R;


public class ReversalInstruction extends ActionBarActivity implements View.OnClickListener {

    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reversal_instruction);

        ((RelativeLayout)findViewById(R.id.reversalinstruction)).setBackgroundResource(R.color.white);
        if(DataBase.getTheam(this).equals("dark")){
            ((RelativeLayout)findViewById(R.id.reversalinstruction)).setBackgroundResource(R.color.black);
        }

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_title);

        final TextView nameoflayoute = (TextView) findViewById(R.id.name_of_activity);
        final ImageView iconeimage = (ImageView) findViewById(R.id.icone);
        final ImageView slideimage = (ImageView) findViewById(R.id.slide_image);

        if (nameoflayoute != null) {
            nameoflayoute.setText(R.string.reversal);
        }

        if (iconeimage != null) {
            iconeimage.setBackgroundResource(R.drawable.next);
        }

        if (slideimage != null) {
            slideimage.setBackgroundResource(R.drawable.sub_reversal);
        }

        ((TextView)findViewById(R.id.highesttextscore)).setText(""+ DataBase.getReversal(this));
        ((TextView)findViewById(R.id.textmuiltiplier)).setText(""+ DataBase.getReversalM(this));

        ((ImageView) findViewById(R.id.startarrow)).setOnClickListener(this);
        ((ImageView) findViewById(R.id.howtoplayarrow)).setOnClickListener(this);
        ((TextView) findViewById(R.id.starttextView)).setOnClickListener(this);
        ((TextView) findViewById(R.id.howtoplaytextView)).setOnClickListener(this);
        iconeimage.setOnClickListener(this);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#f57f17"), PorterDuff.Mode.SRC_ATOP);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar arg0, float arg1, boolean arg2) {
                ((TextView)findViewById(R.id.textViewratingvalue)).setText(""+arg1);
                Log.e("Value", arg1 + "  " + arg0.getRating());
            }
        });
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.starttextView ) {
            Intent intent = new Intent(this, ReversalGame.class);
            startActivity(intent);

        } else if (v.getId() == R.id.howtoplaytextView ) {
            Intent intent = new Intent(this, HowToPlayReversal.class);
            startActivity(intent);
        }else if( v.getId() == R.id.startarrow){
            this.finish();
            Intent intent = new Intent(this, ReversalProInstruction.class);
            startActivity(intent);
            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }else if( v.getId() == R.id.howtoplayarrow){
            this.finish();
            Intent intent = new Intent(this, MatchItProInstruction.class);
            startActivity(intent);
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }else if(v.getId()==R.id.icone){
            ReversalInstruction.this.finish();
        }

    }
}
