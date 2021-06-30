package com.example.virtualpetmonster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar health;
    private long hunger= 60000;
    private CountDownTimer healthTimer;
    private ProgressBar fatigue;
    private long showFatigue=60000;
    private CountDownTimer fatigueTimer;
    //int disable=`0;
    int laughing= 0;
    long timeAsleep = 0;
   //MediaPlayer mediaPlayer;
    boolean isSleep=false;

    TextView messages;
    ImageView petimg;
    TextView petNewName;
    ImageButton playBtn;
    ImageButton feedBtn;
    ImageButton sleepBtn;
    EditText petName;
    Button sendName;
    TextView fatiguetv;
    Button reset ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //linking Views

         messages= findViewById(R.id.messages);
         petimg= findViewById(R.id.petImg);
         petNewName= findViewById(R.id.petNewName);
         playBtn= findViewById(R.id.playBtn);
         feedBtn= findViewById(R.id.feedBtn);
         sleepBtn= findViewById(R.id.sleepBtn);
         petName= findViewById(R.id.etPetName);
         health=findViewById(R.id.pbarHealth);
         sendName=findViewById(R.id.sendName);
         fatigue=findViewById(R.id.fatiguepb);
         fatiguetv=findViewById(R.id.fatiguetv);
         reset = findViewById(R.id.resetbtn);
        //ImageButton danceBtn =findViewById(R.id.dancebtn);

        //settingImages
        petimg.setImageResource(R.drawable.unicornorg);
        feedBtn.setImageResource(R.drawable.burguercopy);
        sleepBtn.setImageResource(R.drawable.sleepimgcopy);
        playBtn.setImageResource(R.drawable.happyface2);


        //timerSettings
        health.setMax(60000);
        fatigue.setMax(60000);


        //calling the functions
        resetHunger(hunger);
        reseFatigue(showFatigue);

        //listeners
        sendName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = petName.getText().toString();
                petNewName.append(newName);
                petName.getText().clear();
                petNewName.setText(newName);
            }
        });

        feedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isSleep) {
                    messages.setText("      Num Num");}

                if(!isSleep) {
                    petimg.setImageResource(R.drawable.angelunicorn);
                }
                if(!isSleep) {
                    hunger+=5000;
                healthTimer.cancel();
                resetHunger(hunger);
            }}
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isSleep){
                petimg.setImageResource(R.drawable.musicunicorn1);
                messages.setText("        🎵🎵🎵");
            }}
        });

        sleepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petimg.setImageResource(R.drawable.unicornsleeping);
                messages.setText("       Snoring");
                showFatigue+=30000;
                fatigueTimer.cancel();
                reseFatigue(showFatigue);
                isSleep = !isSleep;
                //sleepBtn.setBackgroundColor(Color.GRAY);
//                disable++;
//                if(disable==1){
//                    petimg.setEnabled(false);
//                }
//                if(disable>=2){
//                    petimg.setEnabled(false);
//                    disable=0;
//                }
            }
        });

        petimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isSleep){
                    petimg.setImageResource(R.drawable.unicornhappy2);
                  laughing++;}
                if(!isSleep){
               if(laughing==1){
                   petimg.setImageResource(R.drawable.unicornhappy1);
               }}
                  if(!isSleep){
                      messages.setText("        tickles!!");
               if (laughing>=2){
                   messages.setText("laughs in italian*");
                   petimg.setImageResource(R.drawable.unicornhappy2);
                   laughing=0;
               } }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"pet has been revived",Toast.LENGTH_SHORT).show();
                showFatigue+=60000;
                fatigueTimer.onTick(showFatigue);
                hunger+=60000;
                healthTimer.onTick(hunger);
                messages.setText("   hello again!");
                petimg.setImageResource(R.drawable.unicornorg);
                petimg.setEnabled(true);
                playBtn.setEnabled(true);
                feedBtn.setEnabled(true);
                sleepBtn.setEnabled(true);
                reseFatigue(showFatigue);
                resetHunger(hunger);
                goRPS();
            }
        });


    }
//function for hunger
    public void resetHunger(long hungerTemp){

        hunger = hungerTemp;

        healthTimer=new CountDownTimer(hunger, 5000) {
            @Override
            public void onTick(long millisUntilFinished) {
                health.setProgress((int)millisUntilFinished);
                hunger=millisUntilFinished;
                if(hunger>=0&&showFatigue>=0){
                    reset.setEnabled(false);}
                if(millisUntilFinished <= 50000){
                    if(!isSleep) {
                        messages.setText("    I'm hungry!");
                    }

                    if(!isSleep) {
                        petimg.setImageResource(R.drawable.sadunicorn1);
                    }
                }
                if(millisUntilFinished<=40000){
                    if(!isSleep) {
                        messages.setText("    I'm Starving...");
                    }
                    if(!isSleep) {
                        petimg.setImageResource(R.drawable.sadunicorn2);
                    }
                }
                if (millisUntilFinished<=20000){
                    if(!isSleep) {
                        messages.setText("      FEED ME!");
                    }
                    if(!isSleep) {
                        petimg.setImageResource(R.drawable.angryunicorn);
                    }
                }
            }

            @Override
            public void onFinish() {
                hunger=0;
                health.setProgress(0);
                petimg.setImageResource(R.drawable.deadunicorn);
                petimg.setEnabled(false);
                if(hunger==0&&showFatigue==0){
                    reset.setEnabled(true);
                }
                else {}

            }
        }.start();
    }
    //function for fatigue
    public void reseFatigue(long fatigueTemp){

        showFatigue=fatigueTemp;

        fatigueTimer=new CountDownTimer(showFatigue, 10000) {
            @Override
            public void onTick(long millisUntilFinished) {

                fatigue.setProgress((int)millisUntilFinished);
                showFatigue=millisUntilFinished;
                if(hunger>=0&&showFatigue>=0){
                    reset.setEnabled(false);
                }

                if(isSleep){
                    timeAsleep += 10000;
                }
                if(timeAsleep >= 20000){
                    timeAsleep = 0;
                    petimg.setImageResource(R.drawable.unicornorg);
                    isSleep = !isSleep;
                }
            }

            @Override
            public void onFinish() {
                showFatigue=0;
                fatigue.setProgress(0);
                petimg.setEnabled(false);
                playBtn.setEnabled(false);
                feedBtn.setEnabled(false);
                sleepBtn.setEnabled(false);
                messages.setText("YOUR PET DIED!");
                if(hunger==0&&showFatigue==0){
                    reset.setEnabled(true);}
                else {}
            }
        }.start();
    }

    public void goRPS(){
        Intent i = new Intent(this, rpsreset.class);
        startActivity(i);

    }

    }

