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
    @Override
    public void onReceive(Context context, Intent intent) {
        IntentFilter filter2 = new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        filter2.setPriority(99999);
        context.getApplicationContext().registerReceiver(incomingCallReceiver, filter2);
    }
    BroadcastReceiver incomingCallReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final Bundle bundle = intent.getExtras();
            if (bundle == null) return;
            String state = bundle.getString(TelephonyManager.EXTRA_STATE);
            Log.e("log state",state);
            Log.e("state", String.valueOf(state.equalsIgnoreCase(String.valueOf(TelephonyManager.CALL_STATE_RINGING))));
            if (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_IDLE)) {
                Toast.makeText(context,"IDLE State",Toast.LENGTH_LONG).show();
            }
             else if ((state != null) &&  (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING))) {
                Toast.makeText(context,"CALL PICKED ",Toast.LENGTH_LONG).show();
            }
            else if (state.equalsIgnoreCase(String.valueOf(TelephonyManager.EXTRA_STATE_OFFHOOK))) {
                Toast.makeText(context,"OFFHOOK State",Toast.LENGTH_LONG).show();
            }
        }
    };
}

