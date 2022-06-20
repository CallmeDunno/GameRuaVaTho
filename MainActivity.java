package com.example.cuocduakythu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textViewScore;
    CheckBox checkBoxTurtle, checkBoxRabbit;
    SeekBar seekBarTurtle, seekBarRabbit;
    Button buttonStart, buttonAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        DisableSeekbar();

        checkBoxRabbit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    checkBoxTurtle.setChecked(false);
                } else {
                    checkBoxTurtle.setChecked(true);
                }
            }
        });

        checkBoxTurtle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    checkBoxRabbit.setChecked(false);
                } else {
                    checkBoxRabbit.setChecked(true);
                }
            }
        });

        CountDownTimer countDownTimer = new CountDownTimer(20000, 300) {
            @Override
            public void onTick(long l) {
                DisableCheckBox();
                int currRabbit = seekBarRabbit.getProgress();
                int currTurtle = seekBarTurtle.getProgress();
                Random random = new Random();
                if((currRabbit < seekBarRabbit.getMax()) && (currTurtle < seekBarTurtle.getMax())){
                    currRabbit += random.nextInt(5);
                    currTurtle += random.nextInt(5);
                    seekBarRabbit.setProgress(currRabbit);
                    seekBarTurtle.setProgress(currTurtle);
                }

                String strScore = textViewScore.getText().toString();
                int score = Integer.parseInt(strScore);
                if(currRabbit >= seekBarRabbit.getMax()){
                    this.onFinish();
                    this.cancel();
                    EnableCheckBox();
                    if(checkBoxRabbit.isChecked()){
                        score += 10;
                        textViewScore.setText(String.valueOf(score));
                        Toast.makeText(MainActivity.this, "You win!!!", Toast.LENGTH_SHORT).show();
                    } else {
                        score -= 10;
                        textViewScore.setText(String.valueOf(score));
                        Toast.makeText(MainActivity.this, "You lose!!!", Toast.LENGTH_SHORT).show();
                    }
                }
                if(currTurtle >= seekBarTurtle.getMax()){
                    this.onFinish();
                    this.cancel();
                    EnableCheckBox();
                    if(checkBoxTurtle.isChecked()){
                        score += 10;
                        textViewScore.setText(String.valueOf(score));
                        Toast.makeText(MainActivity.this, "You win!!!", Toast.LENGTH_SHORT).show();
                    } else {
                        score -= 10;
                        textViewScore.setText(String.valueOf(score));
                        Toast.makeText(MainActivity.this, "You lose!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "Again!", Toast.LENGTH_SHORT).show();
            }
        };

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonStart.setVisibility(View.INVISIBLE);
                buttonAgain.setVisibility(View.VISIBLE);
                if(checkBoxRabbit.isChecked() || checkBoxTurtle.isChecked()){
                    countDownTimer.start();
                } else {
                    Toast.makeText(MainActivity.this, "Select Turtle or Rabbit!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonStart.setVisibility(View.VISIBLE);
                buttonAgain.setVisibility(View.INVISIBLE);
                seekBarRabbit.setProgress(0);
                seekBarTurtle.setProgress(0);
                Toast.makeText(MainActivity.this, "Start", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void EnableCheckBox(){
        checkBoxRabbit.setEnabled(true);
        checkBoxTurtle.setEnabled(true);
    }

    private void DisableCheckBox(){
        checkBoxTurtle.setEnabled(false);
        checkBoxRabbit.setEnabled(false);
    }

    private void DisableSeekbar(){
        seekBarRabbit.setEnabled(false);
        seekBarTurtle.setEnabled(false);
    }

    private void AnhXa(){
        textViewScore = (TextView) findViewById(R.id.textViewScore);
        checkBoxTurtle = (CheckBox) findViewById(R.id.checkBoxTurtle);
        checkBoxRabbit = (CheckBox) findViewById(R.id.checkBoxRabbit);
        seekBarTurtle = (SeekBar) findViewById(R.id.seekBarTurtle);
        seekBarRabbit = (SeekBar) findViewById(R.id.seekBarRabbit);
        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonAgain = (Button) findViewById(R.id.buttonAgain);
    }
}