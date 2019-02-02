package com.example.tekfloassignment;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1;
    Button bcall,bdial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText) findViewById(R.id.editText);
        bcall = (Button) findViewById(R.id.button);
        bdial = (Button) findViewById(R.id.button2);
        BroadCast br = new BroadCast();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        this.registerReceiver(br, filter);
        bcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String number = e1.getText().toString();
                    String uri = "tel:" + number;
                    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
                    startActivity(callIntent);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Calling...", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                    Log.e("failed",e.toString());
                }
            }
        });
        bdial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String uri = "tel:" + e1.getText().toString();
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                    dialIntent.setData(Uri.parse(uri));
                    startActivity(dialIntent);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"your call has failed",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }
}
