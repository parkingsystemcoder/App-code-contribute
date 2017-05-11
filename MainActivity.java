package com.example.yenchang.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //public Button button;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private TextView textView;
    private Handler handler = new Handler();

      /*  public void init () {
          button=(Button)findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent login = new Intent(MainActivity.this, LoginActivity.class);

                    startActivity(login);
                }
            });
        }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textView);

        progressStatus = 0;
        new Thread(new Runnable(){
            public void run(){
                while (progressStatus < 100){
                    progressStatus += 5;
                    handler.post (new Runnable(){
                        public void run(){
                            progressBar.setProgress(progressStatus);
                            textView.setText("Loading: " + progressStatus + "/" + progressBar.getMax());
                        }
                    });
                    try {
                        Thread.sleep(200);
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                Intent loading = new Intent(MainActivity.this, LoginActivity.class);

                startActivity(loading);
            }
        }).start();
    }
}
