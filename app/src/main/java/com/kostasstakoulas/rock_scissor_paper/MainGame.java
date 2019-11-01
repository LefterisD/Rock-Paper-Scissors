package com.kostasstakoulas.rock_scissor_paper;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainGame extends AppCompatActivity {

    private String tag,state;
    private ImageButton imgbtn;
    private String[] botId;
    private TextView botscore,playerscore,message;
    private int pp1=0,pp2=0;
    private ImageButton btnr,btnp,btns;
    ImageView botc, playerc;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreen();
        setContentView(R.layout.activity_main_game);
        botc = findViewById(R.id.botc);
        playerc = findViewById(R.id.playerc);
        botscore = findViewById(R.id.botPoints);
        playerscore = findViewById(R.id.playerPoints);
        message = findViewById(R.id.whowon);
        btnr = findViewById(R.id.rock);
        btnp = findViewById(R.id.paper);
        btns = findViewById(R.id.scissors);

    }

    public void fullScreen() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }


    public String pcPlay() {
        Random random = new Random();
        botId = new String[3];
        botId[0] = "scissors";
        botId[1] = "paper";
        botId[2] = "rock";
        int i = random.nextInt(3);
        return botId[i];
    }

    public void onClick(View view) {
        tag = (String) view.getTag();
        String botChoice = pcPlay();
        changeBackgroundColor(tag);
        showChoices(botChoice, tag);
        int whoWins = compare(tag, botChoice);
        updateScore(whoWins);
    }
    public void changeBackgroundColor(String tag){
        if(tag.equals("rock")){
            btnr.setBackgroundResource(R.drawable.imagebuttonroundedcorner);
            //other buttons
            btns.setBackgroundColor(Color.parseColor("#009688"));
            btnp.setBackgroundColor(Color.parseColor("#009688"));
        }else if(tag.equals("scissors")){
            btns.setBackgroundResource(R.drawable.imagebuttonroundedcorner);
            //other buttons
            btnr.setBackgroundColor(Color.parseColor("#009688"));
            btnp.setBackgroundColor(Color.parseColor("#009688"));
        }else if(tag.equals("paper")){
            btnp.setBackgroundResource(R.drawable.imagebuttonroundedcorner);
            //other buttons
            btns.setBackgroundColor(Color.parseColor("#009688"));
            btnr.setBackgroundColor(Color.parseColor("#009688"));
        }
    }

    public void showChoices(String botChoice, String playerChoice) {
        if (botChoice.equals("rock")) {
            botc.setImageResource(R.drawable.rock2);
        } else if (botChoice.equals("paper")) {
            botc.setImageResource(R.drawable.paper2);
        } else if (botChoice.equals("scissors")) {
            botc.setImageResource(R.drawable.scissors2);
        }
        if (playerChoice.equals("rock")) {
            playerc.setImageResource(R.drawable.rock2);
        } else if (playerChoice.equals("paper")) {
            playerc.setImageResource(R.drawable.paper2);
        } else if (playerChoice.equals("scissors")) {
            playerc.setImageResource(R.drawable.scissors2);
        }
    }

    public int compare(String p, String b) {
        if (p.equals("rock")) {
            if (b.equals("rock")) {
                return 0;
            } else if (b.equals("scissors")) {
                return 1;
            } else return -1;
        } else if (p.equals("paper")) {
            if (b.equals("rock")) {
                return 1;
            } else if (b.equals("paper")) {
                return 0;
            } else return -1;
        } else if (p.equals("scissors")) {
            if (b.equals("rock")) {
                return -1;
            } else if (b.equals("paper")) {
                return 1;
            } else return 0;
        }
        return 0;
    }

    public void updateScore(int a) {
        String pp = (String) ((TextView) findViewById(R.id.playerPoints)).getText();
        String bp = (String) ((TextView) findViewById(R.id.botPoints)).getText();
        pp1 = Integer.parseInt(pp);
        pp2 = Integer.parseInt(bp);
        if (a == 1) {
            pp1++;
            playerscore.setText(Integer.toString(pp1));
            state = "ΚΕΡΔΙΣΕΣ!";
            message.setText(state);
        } else if (a == -1) {
            pp2++;
            botscore.setText(Integer.toString(pp2));
            state = "ΕΧΑΣΕΣ";
            message.setText(state);
        } else {
            state = "ΙΣΟΠΑΛΙΑ";
            message.setText(state);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("player",pp1);
        outState.putInt("bot",pp2);
        outState.putString("state",state);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pp1 = savedInstanceState.getInt("player");
        pp2 = savedInstanceState.getInt("bot");
        state = savedInstanceState.getString("state");
        message.setText(state);
        playerscore.setText(Integer.toString(pp1));
        botscore.setText(Integer.toString(pp2));

    }

}
