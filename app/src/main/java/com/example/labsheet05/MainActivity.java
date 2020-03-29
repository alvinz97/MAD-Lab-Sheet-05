package com.example.labsheet05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String BROADCAST_ACTION = "";
    private Button broadcastBtn;
    Receiver localListener;

    @Override
    protected void onStart() {
        super.onStart();
        Receiver localListener = new Receiver();
        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        this.registerReceiver(localListener, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(localListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        broadcastBtn = (Button) findViewById(R.id.broadcastBtn);
        TextView textView = (TextView) findViewById(R.id.broadcastReceiverText);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.broadcastBtn) {
            BackgroundServices.startAction(this, getApplicationContext());
        }
    }
}

class Receiver extends  BroadcastReceiver {
    private TextView textView;
    @Override
    public void onReceive(Context context, Intent intent) {
        String currentText = textView.getText().toString();
        String message = intent.getStringExtra("value");
        currentText += "\nReceived " + message;
        textView.setText(currentText);
    }
}