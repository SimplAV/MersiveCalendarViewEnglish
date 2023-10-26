package com.example.mersivecalendarviewpolish;

import static com.example.mersivecalendarviewpolish.Constants.FIFTH_COLUMN;
import static com.example.mersivecalendarviewpolish.Constants.FIRST_COLUMN;
import static com.example.mersivecalendarviewpolish.Constants.FOURTH_COLUMN;
import static com.example.mersivecalendarviewpolish.Constants.SECOND_COLUMN;
import static com.example.mersivecalendarviewpolish.Constants.THIRD_COLUMN;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import android_serialport_api.LedControlUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CalendarView extends AppCompatActivity {

    androidx.constraintlayout.widget.ConstraintLayout bookingMenu;
    TextClock textClock;
    TextView deviceNameTextView;
    TextView dayTextView;
    TextView monthTextView;
    TextView timeOccupiedTextView;
    ImageButton escapeBtn;
    Button bookRoomBtn;
    Button min15Btn;
    Button min30Btn;
    Button min45Btn;
    Button hideMenuBtn;
    ImageButton deleteFastMeetingBtn;
    ImageView loadingScreenImageView;
    ImageView loadingBar;
    ProgressBar progressBar;
    View layoutBackground;

    Animation loadingBarAnimation;
    Animation menuOn;
    Animation menuOff;

    public static String hostName;
    public static String PodPassword;
    public static String urlGetData;
    public static String urlPostData;
    public static String urlDeleteData;
    public static String urlGetHostConfig;
    public static String MonthString;
    public static String fastMeetingID;
    public static String globalCurrentMeetingID;
    public static int escCount = 0;
    public static long currentTimeInMilliSec;
    public static boolean animationOn = false;
    public static boolean fastMeetingBool = false;
    public static boolean fastMeetingBoolAfterTime = TRUE;
    Context context;

    List<String> listaId = new ArrayList<>();
    List<String> listaTitle = new ArrayList<>();
    List<String> listaOrganizer = new ArrayList<>();
    List<Integer> listaStartTime = new ArrayList<>();
    List<Integer> listaEndTime = new ArrayList<>();
    private ArrayList<HashMap<String, String>> list;

    public Timer RequestTimer;


    Handler handler;

    DatagramSocket datagramSocket;
    public static final int MYPORT = 12345;
    static private byte[] buf = new byte[256];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        Objects.requireNonNull(getSupportActionBar()).hide();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        context = getApplicationContext();

        textClock = findViewById(R.id.textClock);
        deviceNameTextView = findViewById(R.id.deviceNameTextView);
        escapeBtn = findViewById(R.id.escapeBtn);
        dayTextView = findViewById(R.id.dayTextView);
        monthTextView = findViewById(R.id.monthTextView);
        timeOccupiedTextView = findViewById(R.id.timeOccupiedTextView);
        progressBar = findViewById(R.id.progressBar);
        bookRoomBtn = findViewById(R.id.bookRoomBtn);
        min15Btn = findViewById(R.id.min15Btn);
        min30Btn = findViewById(R.id.min30Btn);
        min45Btn = findViewById(R.id.min45Btn);
        hideMenuBtn = findViewById(R.id.hideMenuBtn);
        bookingMenu = findViewById(R.id.leftMenu);
        deleteFastMeetingBtn = findViewById(R.id.gifImageButton);
        loadingScreenImageView = findViewById(R.id.loadingImageView);
        layoutBackground = findViewById(R.id.layoutBackground);
        loadingBar = findViewById(R.id.loadingBar);

        timeOccupiedTextView.setText("");
        progressBar.setVisibility(View.INVISIBLE);
        textClock.setFormat12Hour("kk:mm");
        bookingMenu.setVisibility(View.INVISIBLE);
        deleteFastMeetingBtn.setVisibility(View.INVISIBLE);
        loadingScreenImageView.setVisibility(View.INVISIBLE);
        loadingBar.setVisibility(View.INVISIBLE);

        PodPassword = pref.getString("PodPassword", null);
        if(PodPassword.length() > 0){

            urlGetData="http://"+ pref.getString("IP", null) +"/api/calendar?password=" + PodPassword; // getting String+"/api/calendar";
            urlPostData="http://"+ pref.getString("IP", null) +"/api/calendar/add?password=" + PodPassword; // getting String+"/api/calendar";
            urlDeleteData="http://"+ pref.getString("IP", null) +"/api/calendar/delete?password=" + PodPassword;
            urlGetHostConfig="http://"+ pref.getString("IP", null) +"/api/config?password=" + PodPassword;
        }
        else{
            urlGetData="http://"+ pref.getString("IP", null) +"/api/calendar"; // getting String+"/api/calendar";
            urlPostData="http://"+ pref.getString("IP", null) +"/api/calendar/add"; // getting String+"/api/calendar";
            urlDeleteData="http://"+ pref.getString("IP", null) +"/api/calendar/delete";
            urlGetHostConfig="http://"+ pref.getString("IP", null) +"/api/config";
        }


        menuOn = AnimationUtils.loadAnimation(context, R.anim.slide_right);
        menuOff = AnimationUtils.loadAnimation(context, R.anim.slide_left);
        loadingBarAnimation = AnimationUtils.loadAnimation(context, R.anim.menu_on);

        escapeBtn.setOnClickListener(view -> {
            if(escCount == 0){
                handler = new Handler();

                Runnable resetIteratorRef = () -> {
                    Looper.prepare();
                    handler = new Handler();

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    escCount = 0;
                };

                Thread thread = new Thread(resetIteratorRef);
                thread.start();
            }

            escCount += 1;
            if(escCount == 8){
                CalendarView.this.finish();
            }
        });

        Runnable clearAnims = new Thread(() -> {
            Looper.prepare();
            try {
                Thread.sleep(500);
                CalendarView.this.runOnUiThread(() -> {
                    bookingMenu.clearAnimation();
                    menuOn.reset();
                    menuOff.reset();
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Runnable loadingScreen = new Thread(() -> {
            Looper.prepare();
            try {
                CalendarView.this.runOnUiThread(() -> {
                    progressBar.setVisibility(View.VISIBLE);
                    loadingScreenImageView.setVisibility(View.VISIBLE);
                });
                Thread.sleep(5000);
                CalendarView.this.runOnUiThread(() -> {
                    progressBar.setVisibility(View.INVISIBLE);
                    loadingScreenImageView.setVisibility(View.INVISIBLE);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        Runnable makeMenuInvisible = new Thread(() -> {
            Looper.prepare();
            try {
                Thread.sleep(480);
                CalendarView.this.runOnUiThread(() -> {
                    bookingMenu.clearAnimation();
                    menuOn.reset();
                    menuOff.reset();
                    bookingMenu.setVisibility(View.INVISIBLE);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Runnable disableDeleteBtnAfterTime = new Thread(() -> {
            Looper.prepare();
            try {
                Thread.sleep(5000);
                CalendarView.this.runOnUiThread(() -> {
                    glide();
                    deleteFastMeetingBtn.setVisibility(View.VISIBLE);
                });

                Thread.sleep(13000);
                CalendarView.this.runOnUiThread(() -> {

                    Animation fadeOut = new AlphaAnimation(1, 0);
                    fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
                    fadeOut.setDuration(1000);

                    AnimationSet animation = new AnimationSet(false); //change to false
                    animation.addAnimation(fadeOut);
                    deleteFastMeetingBtn.setAnimation(animation);
                    animation.start();
                });

                Thread.sleep(1000);
                CalendarView.this.runOnUiThread(() -> {
                    fastMeetingBoolAfterTime = FALSE;
                    deleteFastMeetingBtn.setVisibility(View.INVISIBLE);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        loadingScreenImageView.setOnClickListener(view -> {
        });

        deleteFastMeetingBtn.setOnClickListener(view -> {
            deleteFastMeeting();
            deleteFastMeetingBtn.setVisibility(View.INVISIBLE);
            Thread loading = new Thread(loadingScreen);
            loading.start();
        });

        bookRoomBtn.setOnClickListener(view -> {
            bookingMenu.startAnimation(menuOn);
            bookingMenu.setVisibility(View.VISIBLE);
            Thread clearAnimsTh = new Thread(clearAnims);
            clearAnimsTh.start();
        });

        hideMenuBtn.setOnClickListener(view -> {
            bookingMenu.clearAnimation();
            bookingMenu.startAnimation(menuOff);

            Thread makeMenuInvisibleTh = new Thread(makeMenuInvisible);
            makeMenuInvisibleTh.start();
        });

        min15Btn.setOnClickListener(view -> {
            Calendar c = Calendar.getInstance();
            long rightNow = c.getTimeInMillis()/1000;
            long endTime = rightNow+900;
            sendPost(rightNow, endTime, "15 minutes");

            bookingMenu.clearAnimation();
            bookingMenu.startAnimation(menuOff);

            Thread makeMenuInvisibleTh = new Thread(makeMenuInvisible);
            makeMenuInvisibleTh.start();
            fastMeetingBoolAfterTime = TRUE;
            bookRoomBtn.setVisibility(View.INVISIBLE);

            Thread loading = new Thread(loadingScreen);
            loading.start();

            Thread disableDeleteBtn = new Thread(disableDeleteBtnAfterTime);
            disableDeleteBtn.start();

            Toast.makeText(this, "MEETING ADDED",
                    Toast.LENGTH_LONG).show();
        });

        min30Btn.setOnClickListener(view -> {
            Calendar c = Calendar.getInstance();
            long rightNow = c.getTimeInMillis()/1000;
            long endTime = rightNow+1800;
            sendPost(rightNow, endTime, "30 minutes");

            bookingMenu.clearAnimation();
            bookingMenu.startAnimation(menuOff);

            Thread makeMenuInvisibleTh = new Thread(makeMenuInvisible);
            makeMenuInvisibleTh.start();
            fastMeetingBoolAfterTime = TRUE;
            bookRoomBtn.setVisibility(View.INVISIBLE);

            Thread loading = new Thread(loadingScreen);
            loading.start();

            Thread disableDeleteBtn = new Thread(disableDeleteBtnAfterTime);
            disableDeleteBtn.start();

            Toast.makeText(this, "MEETING ADDED",
                    Toast.LENGTH_LONG).show();
        });

        min45Btn.setOnClickListener(view -> {
            Calendar c = Calendar.getInstance();
            long rightNow = c.getTimeInMillis()/1000;
            long endTime = rightNow+3600;
            sendPost(rightNow, endTime, "60 minutes");

            bookingMenu.clearAnimation();
            bookingMenu.startAnimation(menuOff);

            Thread makeMenuInvisibleTh = new Thread(makeMenuInvisible);
            makeMenuInvisibleTh.start();
            fastMeetingBoolAfterTime = TRUE;
            bookRoomBtn.setVisibility(View.INVISIBLE);

            Thread loading = new Thread(loadingScreen);
            loading.start();

            Thread disableDeleteBtn = new Thread(disableDeleteBtnAfterTime);
            disableDeleteBtn.start();

            Toast.makeText(this, "MEETING ADDED",
                    Toast.LENGTH_LONG).show();
        });

        CountDownTimer newtimer = new CountDownTimer(Long.MAX_VALUE, 1000) {

            public void onTick(long millisUntilFinished) {
                Date dt = new Date();

                Calendar calendar = new GregorianCalendar();
                calendar.setTime(dt);
                //int year = calendar.get(Calendar.YEAR);

                // int year = dt.getYear();
                int month = calendar.get(Calendar.MONTH) + 1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                //int hours = calendar.get(Calendar.HOUR_OF_DAY);
                //int minutes = calendar.get(Calendar.MINUTE);
                //String curTime = day + "/" + month + "/" + year + " - " + hours + ":" + minutes;
                if(month == 1)
                {
                    MonthString="JANUARY";
                }
                if(month == 2)
                {
                    MonthString="FEBRUARY";
                }
                if(month == 3)
                {
                    MonthString="MARCH";
                }
                if(month == 4)
                {
                    MonthString="APRIL";
                }
                if(month == 5)
                {
                    MonthString="MAY";
                }
                if(month == 6)
                {
                    MonthString="JUNE";
                }
                if(month == 7)
                {
                    MonthString="JULY";
                }
                if(month == 8)
                {
                    MonthString="AUGUST";
                }
                if(month == 9)
                {
                    MonthString="SEPTEMBER";
                }
                if(month == 10)
                {
                    MonthString="OCTOBER";
                }
                if(month == 11)
                {
                    MonthString="NOVEMBER";
                }
                if(month == 12)
                {
                    MonthString="DECEMBER";
                }
                dayTextView.setText(String.valueOf(day));
                monthTextView.setText(MonthString);

                /*
                for (int i = 0; i < listaEndTime.size(); i++) {
                    Date date = new Date(listaStartTime.get(i) *1000L);
                    Date date2 = new Date(listaEndTime.get(i) *1000L);
                    Date date3 = new Date();
                }
                */
            }
            public void onFinish() {

            }
        };
        newtimer.start();

        try {

            runRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            runRequestHostName();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RequestTimer = new Timer();
        RequestTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    runRequest();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 5000);//put here time 1000 milliseconds=1 second

        ////////////////////// UDP SOCKET ///////////////////////////
        Runnable updListener = new Runnable() {
            @Override
            public void run() {

                try {
                    datagramSocket = new DatagramSocket(MYPORT);
                    System.out.println(datagramSocket.getLocalSocketAddress());
                    System.out.println(datagramSocket.getLocalAddress());
                    System.out.println(datagramSocket.getLocalPort());
                } catch (SocketException e) {
                    e.printStackTrace();
                }

                System.out.println("SOCKET CREATED");

                while (true) {
                    try {
                        DatagramPacket packet = new DatagramPacket(buf, buf.length);

                        System.out.println("WAITING FOR PACKAGE");
                        datagramSocket.receive(packet);
                        System.out.println("PACKED RECEIVED");

                        InetAddress address = packet.getAddress();
                        int port = packet.getPort();
                        packet = new DatagramPacket(buf, buf.length, address, port);
                        String received = new String(packet.getData(), 0, packet.getLength());

                        if(received.contains("meetingoff")){
                            deleteMeeting(globalCurrentMeetingID);
                        }

                    } catch (IOException e) {
                        if(!datagramSocket.isClosed()){
                            datagramSocket.close();
                        }
                        e.printStackTrace();
                        break;
                    }
                }

            }
        };

        Thread btnThead = new Thread(updListener);
        btnThead.start();
    }

     void glide(){
        Glide.with(CalendarView.this)
                .asGif()
                .load(R.drawable.cancel_btn) //Your gif resource
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .skipMemoryCache(true)
                .listener(new RequestListener<GifDrawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                        resource.setLoopCount(1);
                        return false;
                    }
                })
                .into(deleteFastMeetingBtn);
    }


    void runRequestHostName() throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(urlGetHostConfig)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                assert response.body() != null;
                final String myResponse = response.body().string();

                CalendarView.this.runOnUiThread(() -> {
                    try {
                        hostName = myResponse;
                        //System.out.println(hostName);
                        String[] temp = hostName.split("m_displayName");
                        hostName = temp[1];
                        String[] temp2 = hostName.split("m_port");
                        hostName = temp2[0];
                        hostName = hostName.replaceAll(",","");
                        hostName = hostName.replaceAll("\"","");
                        hostName = hostName.replaceAll(":","");
                        //System.out.println("SOLSTICE DEVICE NAME: " + hostName);
                        deviceNameTextView.setText(hostName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

            }
        });
    }

    void runRequestHostTime() throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(urlGetHostConfig)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                assert response.body() != null;
                final String myResponse = response.body().string();

                CalendarView.this.runOnUiThread(() -> {
                    try {
                        String hostTime;
                        hostTime = myResponse;
                        String[] temp = hostTime.split("\"dateTime\":");
                        hostTime = temp[1];
                        String[] temp2 = hostTime.split(",\"timeZone\":");
                        hostTime = temp2[0];
                        currentTimeInMilliSec = Long.parseLong(hostTime);
                        //System.out.println("SOLSTICE DEVICE TIME: " + String.valueOf(currentTimeInMilliSec));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }

    public static void deleteMeeting(String CurrentMeetingID) {

        Thread thread = new Thread(() -> {
            try {
                //while(!Thread.currentThread().isInterrupted()) {
                URL url3 = new URL(urlDeleteData);
                HttpURLConnection conn = (HttpURLConnection) url3.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);


                JSONObject jsonParam = new JSONObject();
                jsonParam.put("id", CurrentMeetingID);

                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                os.writeBytes(jsonParam.toString());

                os.flush();
                os.close();

                Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                Log.i("MSG", conn.getResponseMessage());

                conn.disconnect();
                //}
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread.start();
        //thread.interrupt();
    }

    public static void deleteFastMeeting() {

        Thread thread = new Thread(() -> {
            try {
                //while(!Thread.currentThread().isInterrupted()) {
                URL url3 = new URL(urlDeleteData);
                HttpURLConnection conn = (HttpURLConnection) url3.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);


                JSONObject jsonParam = new JSONObject();
                jsonParam.put("id", fastMeetingID);

                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                os.writeBytes(jsonParam.toString());

                os.flush();
                os.close();

                Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                Log.i("MSG", conn.getResponseMessage());

                conn.disconnect();
                //}
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread.start();
        //thread.interrupt();
    }

    public static void sendPost(long startTime, long endTime, String spotkanie) {

        final long startTime1 =startTime;
        final long endTime1 = endTime;
        final String spotkanie1 = spotkanie;
        Thread thread = new Thread(() -> {
            try {
                //while(!Thread.currentThread().isInterrupted()) {
                URL url3 = new URL(urlPostData);
                HttpURLConnection conn = (HttpURLConnection) url3.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);


                JSONObject jsonParam = new JSONObject();
                jsonParam.put("id", "001");
                jsonParam.put("startTime", startTime1);
                jsonParam.put("endTime", endTime1);
                jsonParam.put("title", "Quick meeting - "+spotkanie1);
                jsonParam.put("organizer", "Organization");

                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                os.writeBytes(jsonParam.toString());

                os.flush();
                os.close();

                Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                Log.i("MSG", conn.getResponseMessage());

                conn.disconnect();
                //}
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread.start();
        //thread.interrupt();
    }

    void runRequest() throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(urlGetData)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                assert response.body() != null;
                final String myResponse = response.body().string();


                SimpleDateFormat format = new SimpleDateFormat("HH:mm");

                Date date3 = new Date();



                CalendarView.this.runOnUiThread(() -> {

                    // txtString.setText(myResponse);
                    boolean doesFastMeetingHappened = FALSE;
                    JSONArray arr = null;
                    try {
                        arr = new JSONArray(myResponse);
                        listaId.clear();
                        listaTitle.clear();
                        listaOrganizer.clear();
                        listaStartTime.clear();
                        listaEndTime.clear();
                        for(int i = 0; i < arr.length(); i++){
                            listaTitle.add(arr.getJSONObject(i).getString("title"));
                            listaId.add(arr.getJSONObject(i).getString("id"));
                            listaOrganizer.add(arr.getJSONObject(i).getString("organizer"));
                            listaStartTime.add(arr.getJSONObject(i).getInt("startTime"));
                            listaEndTime.add(arr.getJSONObject(i).getInt("endTime"));
                            Date date = new Date(listaStartTime.get(i) *1000L);
                            Date date2 = new Date(listaEndTime.get(i) *1000L);
                            if(listaTitle.get(i).contains("Quick meeting - ")){
                                fastMeetingID = listaId.get(i);
                                fastMeetingBool = TRUE;
                                doesFastMeetingHappened = TRUE;
                            }
                            if(date3.before(date2) && date3.after(date)){
                                globalCurrentMeetingID = listaId.get(i);
                                System.out.println("CURRENT MEETING ID: " + listaId.get(i));
                            }
                        }

                        if (doesFastMeetingHappened == FALSE){
                            fastMeetingBool = FALSE;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //Log.d(listaId.toString(),"id");
                    //Log.d(listaTitle.toString(),"title");
                    //Log.d(listaOrganizer.toString(),"organizer");
                    //Log.d(listaEndTime.toString(),"startTime");
                    //Log.d(listaStartTime.toString(),"EndTime");

                    ListView listView = findViewById(R.id.ListView1);

                    listView.setClickable(true);
                    listView.setOnItemClickListener((arg0, arg1, position, arg3) -> {

                        //Object o = arg0.getItemAtPosition(position);
                        //timerInactive.cancel(); //timer

                        //timerInactive.start();

                    });
                    listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                        @Override
                        public void onScrollStateChanged(AbsListView absListView, int i) {
                        }

                        @Override
                        public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                        }
                    });

                    listView.setAdapter(null);

                    try{
                        assert arr != null;

                        populateList(arr.length());
                        try {
                            runRequestHostTime();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        ListViewAdapter adapter = new ListViewAdapter(CalendarView.this,list);
                        listView.setAdapter(adapter);
                    }
                    catch (AssertionError ex){
                        ex.printStackTrace();
                        RequestTimer.cancel();
                        CalendarView.this.runOnUiThread(() -> {
                            Toast.makeText(CalendarView.this, "WRONG PASSWORD",
                                    Toast.LENGTH_LONG).show();
                            datagramSocket.close();
                            CalendarView.this.finish();

                        });
                    }
                });
            }
        });
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private void populateList(int j) {
        list = new ArrayList<>();

        boolean pomocnicza = FALSE;
        for (int i = 0; i < j; i++) {
            HashMap<String, String> temp = new HashMap<>();

            Date date = new Date(listaStartTime.get(i) *1000L);

            SimpleDateFormat format = new SimpleDateFormat("HH:mm");

            Date date2 = new Date(listaEndTime.get(i) *1000L);

            Date date3 = new Date();


            if (date3.after(date2))
            {
                continue;
            }
            if (date3.before(date2) && date3.after(date))
            {
                //ustawienie ze zajete
                pomocnicza=TRUE;
                temp.put(FIRST_COLUMN, Integer.toString(2));

                /*
                CalendarView.this.runOnUiThread(() -> {
                    if(fastMeetingBool == TRUE && fastMeetingBoolAfterTime == TRUE){
                        deleteFastMeetingBtn.setVisibility(View.VISIBLE);
                    }
                    else{
                        deleteFastMeetingBtn.setVisibility(View.INVISIBLE);
                    }
                });

                 */

                CalendarView.this.runOnUiThread(() -> {
                    layoutBackground.setBackground(getDrawable(R.drawable.background_zajete_pure));
                    String startEndTime = format.format(date) + " - " + format.format(date2);
                    timeOccupiedTextView.setText(startEndTime);
                });


                if (animationOn == FALSE && currentTimeInMilliSec > 0){
                    long deltaMeetingTime = (listaEndTime.get(i) - listaStartTime.get(i))* 1000L;
                    //float deltaCurrentAndEndofMeetingTime = (float)listaEndTime.get(i) * 1000 - (float)currentTimeInMilliSec;
                    float deltaCurrentAndStartofMeetingTime = ((float)currentTimeInMilliSec - (float)listaStartTime.get(i) * 1000);

                    CalendarView.this.runOnUiThread(() -> {

                        loadingBar.setVisibility(View.VISIBLE);
                        //System.out.println("currentTimeInMilliSec: " + String.valueOf(currentTimeInMilliSec));
                        //System.out.println("deltaCurrentAndEndofMeetingTime: " + String.valueOf(deltaCurrentAndEndofMeetingTime));
                        //System.out.println("deltaMeetingTime: " + String.valueOf(deltaMeetingTime));
                        //System.out.println("deltaCurrentAndEndofMeetingTime/(float)deltaMeetingTime: " + String.valueOf(deltaCurrentAndEndofMeetingTime/(float)deltaMeetingTime));

                        long animationTime = deltaMeetingTime - (long)deltaCurrentAndStartofMeetingTime;
                        float animationStartOffset = deltaCurrentAndStartofMeetingTime/(float)deltaMeetingTime;
                        //System.out.println("animationTime: " + String.valueOf(animationTime));

                        if (animationTime < 1000){
                            animationTime = 1000;
                        }

                        loadingBarAnimation = new ScaleAnimation(animationStartOffset, 1,1,1);
                        loadingBarAnimation.setDuration(animationTime);
                        loadingBarAnimation.setInterpolator(new LinearInterpolator());
                        loadingBar.startAnimation(loadingBarAnimation);
                    });

                    animationOn = TRUE;
                }
            }else
            {
                //ustawienie ze wolne
                temp.put(FIRST_COLUMN, Integer.toString(1));

            }
            //temp.put(FIRST_COLUMN, Integer.toString(i));
            temp.put(SECOND_COLUMN, listaOrganizer.get(i));
            temp.put(THIRD_COLUMN, listaTitle.get(i));
            temp.put(FOURTH_COLUMN, format.format(date));
            temp.put(FIFTH_COLUMN, format.format(date2));

            list.add(temp);
        }

        if(pomocnicza)
        {
            LedControlUtil ledControlUtil = new LedControlUtil();
            ledControlUtil.CustomLightOn(250, 0, 0);
            bookRoomBtn.setVisibility(View.INVISIBLE);

        }else
        {
            LedControlUtil ledControlUtil = new LedControlUtil();
            ledControlUtil.CustomLightOn(0, 250, 0);
            bookRoomBtn.setVisibility(View.VISIBLE);
            layoutBackground.setBackground(getDrawable(R.drawable.background_wolne_pure));
            CalendarView.this.runOnUiThread(() -> {
                loadingBar.clearAnimation();
                try {
                    loadingBarAnimation.reset();
                }
                catch (Exception e){
                    System.out.println("No animation online");
                }
                loadingBar.setAnimation(null);

                loadingBar.setVisibility(View.INVISIBLE);
                //deleteFastMeetingBtn.setVisibility(View.INVISIBLE);

                timeOccupiedTextView.setText("");
            });
            animationOn = FALSE;
            fastMeetingBool = FALSE;
        }

        try {
            runRequestHostName();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}