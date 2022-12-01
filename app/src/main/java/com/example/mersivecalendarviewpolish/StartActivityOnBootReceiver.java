package com.example.mersivecalendarviewpolish;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivityOnBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction()))
        {
			/*SharedPreferences pref = context.getSharedPreferences("MyPref", 0); // 0 - for private mode
			SharedPreferences.Editor editor = pref.edit();

			String message = "refresh";

			editor.putString("refresh", message); // Storing string
			editor.commit();*/
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
                }
            }, 3000);
        }
    }
}
