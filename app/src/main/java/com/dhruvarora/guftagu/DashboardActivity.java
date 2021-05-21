package com.dhruvarora.guftagu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class DashboardActivity extends AppCompatActivity {

    EditText codeBox;
    Button joinBtn, shareBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        codeBox = findViewById(R.id.codeBox);
        joinBtn = findViewById(R.id.joinBtn);
        shareBtn = findViewById(R.id.shareBtn);

        Random rnd = new Random();
        char [] digits = new char[10];
        digits[0] = (char) (rnd.nextInt(9) + '1');
        for(int i=1; i<digits.length; i++) {
            digits[i] = (char) (rnd.nextInt(10) + '0');
        }
        long code = Long.parseLong(new String(digits));
        codeBox.setText(code+"");

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "You are invited for Gufatgu! Just paste this code to Join: "+codeBox.getText().toString());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });

        URL serverURL;
        try {
            serverURL = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions = new JitsiMeetConferenceOptions.Builder().setServerURL(serverURL).setWelcomePageEnabled(false).build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JitsiMeetConferenceOptions option = new JitsiMeetConferenceOptions.Builder().setRoom(codeBox.getText().toString()).setWelcomePageEnabled(false).build();

                JitsiMeetActivity.launch(DashboardActivity.this,option);
            }
        });
    }
}