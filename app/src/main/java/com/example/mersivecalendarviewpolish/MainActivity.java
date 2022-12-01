package com.example.mersivecalendarviewpolish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText solsticePodIp;
    EditText keyMAC;
    EditText editTextPodPassword;
    Button saveBtn;

    public String MacID;
    public String DecryptedMacID;
    public String PodPassword;
    public static final String simpleKey = "Simple@#!123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        solsticePodIp = findViewById(R.id.editTextIP);
        keyMAC = findViewById(R.id.editTextKey);
        editTextPodPassword = findViewById(R.id.editTextPodPassword);
        saveBtn = findViewById(R.id.saveBtn);


        saveBtn.setOnClickListener(view -> {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
            SharedPreferences.Editor editor = pref.edit();

            Intent intent = new Intent(MainActivity.this, CalendarView.class);
            String podIp = solsticePodIp.getText().toString();
            MacID = keyMAC.getText().toString();
            PodPassword = editTextPodPassword.getText().toString();

            editor.putString("IP", podIp); // Storing string
            if(MacID != null) {
                editor.putString("macID", MacID);
                editor.putString("Key", MacID);
                editor.putString("PodPassword", PodPassword);

            }else
            {
                editor.putString("macID", "02:00:00:00:00:00");
            }

            editor.commit();

            String macIDPulled;
            macIDPulled = getMacAddr();

            if(pref.getString("Key", null).equals(simpleKey)){
                if (pref.getString("IP", null) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Brak IP lub niepoprawny klucz",
                            Toast.LENGTH_LONG).show();
                }
            }

            else if(pref.getString("macID", null) != null) {
                byte[] decrypt= Base64.decode(pref.getString("macID", null), Base64.DEFAULT);
                try {
                    DecryptedMacID = new String(decrypt, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if ((pref.getString("IP", null) != null) && DecryptedMacID.equals(macIDPulled)) {
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Brak IP lub niepoprawny klucz",
                            Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(MainActivity.this, "Brak klucza",
                        Toast.LENGTH_LONG).show();
            }
        });

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        if(pref.getString("Key", null) != null){
            if(pref.getString("Key", null).equals(simpleKey)){
                if (pref.getString("IP", null) != null) {
                    Intent intent = new Intent(MainActivity.this, CalendarView.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Brak IP lub niepoprawny klucz",
                            Toast.LENGTH_LONG).show();
                }
            }
        }

        else if(pref.getString("macID", null) != null) {
            String macIDPulled;
            macIDPulled = getMacAddr();
            String macIDPref;

            //macIDPref = pref.getString("macID", null);
            byte[] decrypt= Base64.decode(pref.getString("macID", null), Base64.DEFAULT);
            try {
                DecryptedMacID = new String(decrypt, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if ((pref.getString("IP", null) != null) && DecryptedMacID.equals(macIDPulled)) {
                Intent intent = new Intent(MainActivity.this, CalendarView.class);
                startActivity(intent);
            }
        }

    }

    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }
}