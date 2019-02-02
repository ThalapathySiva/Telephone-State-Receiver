package com.example.tekfloassignment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class BroadCast extends BroadcastReceiver {
    private static final String TAG = "PhoneStatReceiver";
    private static boolean incomingFlag = false;
    private static String incoming_number = null;
    @Override
    public void onReceive(Context context, Intent intent) {
        IntentFilter filter2 = new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        filter2.setPriority(99999);
        //context.registerReceiver(incomingCallReceiver,filter2);
        context.getApplicationContext().registerReceiver(incomingCallReceiver, filter2);

  /*      if(intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)){
            incomingFlag = false;
            String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            Log.i(TAG, "call OUT:"+phoneNumber);
            TelephonyManager tm =
                    (TelephonyManager)context.getSystemService(Service.TELEPHONY_SERVICE);
            Log.e("log state", String.valueOf(tm.getCallState()));
            switch (tm.getCallState()) {
                case TelephonyManager.CALL_STATE_RINGING:
                    incomingFlag = true;
                    incoming_number = intent.getStringExtra("incoming_number");
                    Log.i(TAG, "RINGING :"+ incoming_number);
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    if(incomingFlag){
                        Log.i(TAG, "incoming ACCEPT :"+ incoming_number);
                    }
                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    if(incomingFlag){
                        Log.i(TAG, "incoming IDLE");
                    }
                    break;
                    default:
                        Log.e("ds","Error");
            }
        }*/
    }
    BroadcastReceiver incomingCallReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final Bundle bundle = intent.getExtras();
            if (bundle == null) return;
            // Incoming call

            // Get the state
            String state = bundle.getString(TelephonyManager.EXTRA_STATE);
            // Process incoming call
            Log.e("log state",state);
            Log.e("state", String.valueOf(state.equalsIgnoreCase(String.valueOf(TelephonyManager.CALL_STATE_RINGING))));

            if (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_IDLE)) {
                Toast.makeText(context,"IDLE State",Toast.LENGTH_LONG).show();
            }
             else if ((state != null) &&  (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING))) {
                // Process the call
                Toast.makeText(context,"PICKED State",Toast.LENGTH_LONG).show();
            }

            else if (state.equalsIgnoreCase(String.valueOf(TelephonyManager.EXTRA_STATE_OFFHOOK))) {
                Toast.makeText(context,"OFFHOOK State",Toast.LENGTH_LONG).show();

            }

        }
    };

}

