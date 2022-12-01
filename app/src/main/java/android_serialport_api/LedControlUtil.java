package android_serialport_api;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class LedControlUtil {
	static final String TAG = "LedControlUtil";
	private int MAX_DUTY = 0x70;
	private int MAX_BRIGHT = 0x0B;
    private int num1 = 0x55;
    private int num2 = 0xaa;
    private int numL;
    private int numR;
    private int numG;
    private int numB;
    private int num7 = 0x88;

    private SerialPort sp;
    private FileOutputStream out = null;
    private final Object lock= new Object();
    private Boolean LedFlag = false;
    private Boolean LedType = false;  //true is means 2 Side LED bar,false is means 52 LED bar


    public void setLedOn(int color) {
        handleColorValue(color);
        AllOn();
    }

    public void CustomLightOn(final int R, final int G, final int B) {                   //All the lights
        try {
            sp = new SerialPort(new File("/dev/ttyS1"), 115200, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (lock == null) {
                    System.out.println("lock = null");
                } else {
                    try {
                        synchronized (lock) {
                            out = sp.getFileOutputStream();
                            AllCustomOnLamps(R, G, B);
                            out.close();
                            sp.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).run();
    }

    public void SelectedLampOn(final int R, final int G, final int B, final int lampNumber) {                   //All the lights
        try {
            sp = new SerialPort(new File("/dev/ttyS1"), 115200, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (lock == null) {
                    System.out.println("lock = null");
                } else {
                    try {
                        synchronized (lock) {
                            out = sp.getFileOutputStream();
                            SelectedLamp(R, G, B, lampNumber);
                            out.close();
                            sp.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).run();
    }

    public void SelectedThreeLampsOn(final int R, final int G, final int B, final int lampNumber) {                   //All the lights
        try {
            sp = new SerialPort(new File("/dev/ttyS1"), 115200, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (lock == null) {
                    System.out.println("lock = null");
                } else {
                    try {
                        synchronized (lock) {
                            out = sp.getFileOutputStream();
                            SelectedThreeLamps(R, G, B, lampNumber);
                            out.close();
                            sp.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).run();
    }

    public void SelectedFiveLampsOn(final int R, final int G, final int B, final int lampNumber) {                   //All the lights
        try {
            sp = new SerialPort(new File("/dev/ttyS1"), 115200, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (lock == null) {
                    System.out.println("lock = null");
                } else {
                    try {
                        synchronized (lock) {
                            out = sp.getFileOutputStream();
                            SelectedFiveLamps(R, G, B, lampNumber);
                            out.close();
                            sp.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).run();
    }

    public void SelectedTenLampsOn(final int R, final int G, final int B, final int lampNumber) {                   //All the lights
        try {
            sp = new SerialPort(new File("/dev/ttyS1"), 115200, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (lock == null) {
                    System.out.println("lock = null");
                } else {
                    try {
                        synchronized (lock) {
                            out = sp.getFileOutputStream();
                            SelectedTenLamps(R, G, B, lampNumber);
                            out.close();
                            sp.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).run();
    }

    public void CustomFade(final int R, final int G, final int B, final int numbLl) {                   //All the lights
        try {
            sp = new SerialPort(new File("/dev/ttyS1"), 115200, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (lock == null) {
                    System.out.println("lock = null");
                } else {
                    try {
                        synchronized (lock) {
                            out = sp.getFileOutputStream();
                            FadeColor(R, G, B, numbLl);
                            out.close();
                            sp.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).run();
    }

    public void AllStars3On(final int R, final int G, final int B) {                   //All the lights
        try {
            sp = new SerialPort(new File("/dev/ttyS1"), 115200, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (lock == null) {
                    System.out.println("lock = null");
                } else {
                    try {
                        synchronized (lock) {
                            out = sp.getFileOutputStream();
                            StarsLamps3(R, G, B);
                            out.close();
                            sp.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).run();
    }

    public void AllStars2On(final int R, final int G, final int B) {                   //All the lights
        try {
            sp = new SerialPort(new File("/dev/ttyS1"), 115200, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (lock == null) {
                    System.out.println("lock = null");
                } else {
                    try {
                        synchronized (lock) {
                            out = sp.getFileOutputStream();
                            StarsLamps2(R, G, B);
                            out.close();
                            sp.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).run();
    }

    public void AllStars1On(final int R, final int G, final int B) {                   //All the lights
        try {
            sp = new SerialPort(new File("/dev/ttyS1"), 115200, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (lock == null) {
                    System.out.println("lock = null");
                } else {
                    try {
                        synchronized (lock) {
                            out = sp.getFileOutputStream();
                            StarsLamps1(R, G, B);
                            out.close();
                            sp.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).run();
    }

    public void AllYellowOn() {                   //All the lights
        try {
            sp = new SerialPort(new File("/dev/ttyS1"), 115200, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (lock == null) {
                    System.out.println("lock = null");
                } else {
                    try {
                        synchronized (lock) {
                            out = sp.getFileOutputStream();
                            AllYOnLamps();
                            out.close();
                            sp.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).run();
    }

    public void AllGreenOn() {                   //All Green the lights
        try {
            sp = new SerialPort(new File("/dev/ttyS1"), 115200, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (lock == null) {
                    System.out.println("lock = null");
                } else {
                    try {
                        synchronized (lock) {
                            out = sp.getFileOutputStream();
                            AllGOnLamps();
                            out.close();
                            sp.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).run();
    }

    public void AllRedOn() {                   //All Red the lights
        try {
            sp = new SerialPort(new File("/dev/ttyS1"), 115200, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (lock == null) {
                    System.out.println("lock = null");
                } else {
                    try {
                        synchronized (lock) {
                            out = sp.getFileOutputStream();
                            AllROnLamps();
                            out.close();
                            sp.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).run();
    }

    public void AllBlueOn() {                   //All Blue the lights
        try {
            sp = new SerialPort(new File("/dev/ttyS1"), 115200, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (lock == null) {
                    System.out.println("lock = null");
                } else {
                    try {
                        synchronized (lock) {
                            out = sp.getFileOutputStream();
                            AllBOnLamps();
                            out.close();
                            sp.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).run();
    }

    public void AllCyanOn() {                   //All Cyan the lights
        try {
            sp = new SerialPort(new File("/dev/ttyS1"), 115200, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (lock == null) {
                    System.out.println("lock = null");
                } else {
                    try {
                        synchronized (lock) {
                            out = sp.getFileOutputStream();
                            AllCOnLamps();
                            out.close();
                            sp.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).run();
    }

    public void AllPurpleOn() {                   //All Purple the lights
        try {
            sp = new SerialPort(new File("/dev/ttyS1"), 115200, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (lock == null) {
                    System.out.println("lock = null");
                } else {
                    try {
                        synchronized (lock) {
                            out = sp.getFileOutputStream();
                            AllPOnLamps();
                            out.close();
                            sp.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).run();
    }

    public void AllWhiteOn() {                   //All White the lights
        try {
            sp = new SerialPort(new File("/dev/ttyS1"), 115200, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (lock == null) {
                    System.out.println("lock = null");
                } else {
                    try {
                        synchronized (lock) {
                            out = sp.getFileOutputStream();
                            AllWOnLamps();
                            out.close();
                            sp.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).run();
    }

    public void AllOff() {                   //Close all the lights
        try {
            sp = new SerialPort(new File("/dev/ttyS1"), 115200, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (lock == null) {
                    System.out.println("lock = null");
                } else {
                    try {
                        synchronized (lock) {
                            out = sp.getFileOutputStream();
                            AllOffLamps();
                            out.close();
                            sp.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).run();
    }

    public void AllOn() {                   //All  the lights
        try {
            sp = new SerialPort(new File("/dev/ttyS1"), 115200, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (lock == null) {
                    System.out.println("lock = null");
                } else {
                    try {
                        synchronized (lock) {
                            out = sp.getFileOutputStream();
                            AllOnLamps();
                            out.close();
                            sp.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).run();
    }

    // return value :  true is means 2 Side LED bar
    //                 false is means 52 LED bar
    public boolean checkWhichLed(){
        numL = stringsimpleToByte("ff");
        numB = stringsimpleToByte("00");
        numR = stringsimpleToByte("00");
        numG = stringsimpleToByte("00");
        try {
            final SerialPort ttyS1= new SerialPort(new File("dev/ttyS1"), 115200, 0);		//Open the baud rate
            new Thread(new Runnable() {
                @Override
                public void run() {
                    FileInputStream in = null;
                    FileOutputStream out = null;
                    try {
                        synchronized (lock) {
                            in = ttyS1.getFileInputStream();
                            out = ttyS1.getFileOutputStream();
                            AllOnLamps();
                            Thread.sleep(100);
                            BufferedInputStream in0 = new BufferedInputStream(in);
                            int i = 0;
                            while (in0.available() == 0 && i < 100)
                                i++;
                            byte[] buffer = new byte[in0.available()];
                            in0.read(buffer);
                            int a[] = new int[buffer.length];
                            StringBuffer sb = new StringBuffer();
                            for (int j = 0;j<buffer.length;j++){
                                a[j] = buffer[j];
                                String s = Integer.toHexString(a[j]);
                                if (s.length() < 2){
                                    sb.append("0" + s + " ");
                                }else {
                                    sb.append(s.substring(s.length() - 2) + " ");
                                }
                            }
                            System.out.println("result：" + sb);
                            String checkResult = new String(sb);
                            if (checkResult.contains("55 aa 06 50 88")){
                                Log.d(TAG, "run: is 52 LED frame Connected");
                                LedFlag = false;
                            } else if (checkResult.contains("55 aa 06 51 88")){
                                Log.d(TAG, "run: is 52 2 Side LED bar Connected");
                                LedFlag = true;
                            } else {
                                Log.d(TAG, "run: is undefind LED frame Connected");
                                LedFlag = false;
                            }

                        }
                    } catch (IOException e){
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Exception e){
                        e.printStackTrace();
                    } finally {
                        try {
                            if (out != null){
                                out.close();
                                Log.d(TAG, "run: out.close");
                            }
                            if (in != null){
                                in.close();
                                Log.d(TAG, "run: in.close");
                            }
                            if(ttyS1 != null){
                                ttyS1.close();
                                Log.d(TAG, "run: ttyS1.close");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).run();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return LedFlag;
    }


    private void AllOnLamps() throws IOException {                   //All the lights

        Log.d(TAG, "AllOnLamps: numL=="+numL);
        Log.d(TAG, "AllOnLamps: numB=="+numB);
        Log.d(TAG, "AllOnLamps: numR=="+numR);
        Log.d(TAG, "AllOnLamps: numG=="+numG);
        Log.d(TAG, "AllOnLamps: num1=="+num1);
        Log.d(TAG, "AllOnLamps: num2=="+num2);
        out.write(intToByteArray(num1));
        out.write(intToByteArray(num2));
        for (int i = 0; i < 52; i++) {
            out.write(intToByteArray(numL));
            out.write(intToByteArray(numB));
            out.write(intToByteArray(numR));
            out.write(intToByteArray(numG));
        }
        out.write(intToByteArray(num7));
    }

    private void AllCustomOnLamps(int R, int G, int B) throws IOException, InterruptedException {                   //All the Red lights
        numL = 0xf4;
        double constantValue = (double)112/(double)255;
        R = (int) ((int) R*constantValue);
        G = (int) ((int) G*constantValue);
        B = (int) ((int) B*constantValue);
        numR = (byte) R;
        numG = (byte) G;
        numB = (byte) B;
        Log.d(TAG, "CUSTOM R:" + numR);
        Log.d(TAG, "CUSTOM G:" + numG);
        Log.d(TAG, "CUSTOM B:" + numB);

        out.write(intToByteArray(num1));
        out.write(intToByteArray(num2));
        for (int i = 0; i < 52; i++) {
            out.write(intToByteArray(numL));
            out.write(intToByteArray(numB));
            out.write(intToByteArray(numR));
            out.write(intToByteArray(numG));
        }
        out.write(intToByteArray(num7));
    }

    private void FadeColor(int R, int G, int B, int numbLl) throws IOException, InterruptedException {                   //All the Red lights
        double constantValue = (double)112/(double)255;
        R = (int) ((int) R*constantValue);
        G = (int) ((int) G*constantValue);
        B = (int) ((int) B*constantValue);
        numR = (byte) R;
        numG = (byte) G;
        numB = (byte) B;
        Log.d(TAG, "CUSTOM R:" + numR);
        Log.d(TAG, "CUSTOM G:" + numG);
        Log.d(TAG, "CUSTOM B:" + numB);

        out.write(intToByteArray(num1));
        out.write(intToByteArray(num2));
        for (int i = 0; i < 52; i++) {
            out.write(intToByteArray(numbLl));
            out.write(intToByteArray(numB));
            out.write(intToByteArray(numR));
            out.write(intToByteArray(numG));
        }
        out.write(intToByteArray(num7));
    }

    private void StarsLamps1(int R, int G, int B) throws IOException, InterruptedException {                   //All the Red lights
        numL = 0xf4;
        double constantValue = (double)112/(double)255;
        int numZero;
        R = (int) ((int) R*constantValue);
        G = (int) ((int) G*constantValue);
        B = (int) ((int) B*constantValue);
        numR = (byte) R;
        numG = (byte) G;
        numB = (byte) B;
        numZero = (byte) 0;
        Log.d(TAG, "CUSTOM R:" + numR);
        Log.d(TAG, "CUSTOM G:" + numG);
        Log.d(TAG, "CUSTOM B:" + numB);

        out.write(intToByteArray(num1));
        out.write(intToByteArray(num2));
        for (int i = 0; i < 52; i++) {
            if ((i % 5) == 0){
                out.write(intToByteArray(numL));
                out.write(intToByteArray(numB));
                out.write(intToByteArray(numR));
                out.write(intToByteArray(numG));
            }
            else{
                out.write(intToByteArray(numL));
                out.write(intToByteArray(numZero));
                out.write(intToByteArray(numZero));
                out.write(intToByteArray(numZero));
            }
        }
        out.write(intToByteArray(num7));
    }

    private void StarsLamps2(int R, int G, int B) throws IOException, InterruptedException {                   //All the Red lights
        numL = 0xf4;
        double constantValue = (double)112/(double)255;
        int numZero;
        R = (int) ((int) R*constantValue);
        G = (int) ((int) G*constantValue);
        B = (int) ((int) B*constantValue);
        numR = (byte) R;
        numG = (byte) G;
        numB = (byte) B;
        numZero = (byte) 0;
        Log.d(TAG, "CUSTOM R:" + numR);
        Log.d(TAG, "CUSTOM G:" + numG);
        Log.d(TAG, "CUSTOM B:" + numB);

        out.write(intToByteArray(num1));
        out.write(intToByteArray(num2));
        for (int i = 0; i < 52; i++) {
            if ((i % 4) == 0){
                out.write(intToByteArray(numL));
                out.write(intToByteArray(numB));
                out.write(intToByteArray(numR));
                out.write(intToByteArray(numG));
            }
            else{
                out.write(intToByteArray(numL));
                out.write(intToByteArray(numZero));
                out.write(intToByteArray(numZero));
                out.write(intToByteArray(numZero));
            }
        }
        out.write(intToByteArray(num7));
    }

    private void StarsLamps3(int R, int G, int B) throws IOException, InterruptedException {                   //All the Red lights
        numL = 0xf4;
        double constantValue = (double)112/(double)255;
        int numZero;
        R = (int) ((int) R*constantValue);
        G = (int) ((int) G*constantValue);
        B = (int) ((int) B*constantValue);
        numR = (byte) R;
        numG = (byte) G;
        numB = (byte) B;
        numZero = (byte) 0;
        Log.d(TAG, "CUSTOM R:" + numR);
        Log.d(TAG, "CUSTOM G:" + numG);
        Log.d(TAG, "CUSTOM B:" + numB);

        out.write(intToByteArray(num1));
        out.write(intToByteArray(num2));
        for (int i = 0; i < 52; i++) {
            if ((i % 3) == 0){
                out.write(intToByteArray(numL));
                out.write(intToByteArray(numB));
                out.write(intToByteArray(numR));
                out.write(intToByteArray(numG));
            }
            else{
                out.write(intToByteArray(numL));
                out.write(intToByteArray(numZero));
                out.write(intToByteArray(numZero));
                out.write(intToByteArray(numZero));
            }
        }
        out.write(intToByteArray(num7));
    }

    private void AllROnLamps() throws IOException {                   //All the Red lights
        numL = 0xfb;
        numR = 0x70;
        numB = 0;
        numG = 0;
        Log.d(TAG, "CUSTOM R:" + numR);
        Log.d(TAG, "CUSTOM G:" + numG);
        Log.d(TAG, "CUSTOM B:" + numB);

        out.write(intToByteArray(num1));
        out.write(intToByteArray(num2));
        for (int i = 0; i < 52; i++) {
            out.write(intToByteArray(numL));
            out.write(intToByteArray(numB));
            out.write(intToByteArray(numR));
            out.write(intToByteArray(numG));
        }
        out.write(intToByteArray(num7));
    }

    private void AllGOnLamps() throws IOException {                   //All the Green lights
        numL = 0xfb;
        numR = 0;
        numB = 0;
        numG = 0x70;
        Log.d(TAG, "CUSTOM R:" + numR);
        Log.d(TAG, "CUSTOM G:" + numG);
        Log.d(TAG, "CUSTOM B:" + numB);

        out.write(intToByteArray(num1));
        out.write(intToByteArray(num2));
        for (int i = 0; i < 52; i++) {
            out.write(intToByteArray(numL));
            out.write(intToByteArray(numB));
            out.write(intToByteArray(numR));
            out.write(intToByteArray(numG));
        }
        out.write(intToByteArray(num7));
    }

    private void AllBOnLamps() throws IOException {                   //All the Blue lights
        numL = 0xfb;
        numR = 0;
        numB = 0x70;
        numG = 0;
        Log.d(TAG, "CUSTOM R:" + numR);
        Log.d(TAG, "CUSTOM G:" + numG);
        Log.d(TAG, "CUSTOM B:" + numB);

        out.write(intToByteArray(num1));
        out.write(intToByteArray(num2));
        for (int i = 0; i < 52; i++) {
            out.write(intToByteArray(numL));
            out.write(intToByteArray(numB));
            out.write(intToByteArray(numR));
            out.write(intToByteArray(numG));
        }
        out.write(intToByteArray(num7));
    }

    private void AllYOnLamps() throws IOException {                   //All the Yellow lights
        numL = 0xf4;
        numR = 0x70;
        numB = 0;
        numG = 0x70;

        out.write(intToByteArray(num1));
        out.write(intToByteArray(num2));
        for (int i = 0; i < 52; i++) {
            out.write(intToByteArray(numL));
            out.write(intToByteArray(numB));
            out.write(intToByteArray(numR));
            out.write(intToByteArray(numG));
        }
        out.write(intToByteArray(num7));
    }

    private void AllCOnLamps() throws IOException {                   //All the Cyan lights

        numL = 0xf4;
        numR = 0;
        numB = 0x70;
        numG = 0x70;
        out.write(intToByteArray(num1));
        out.write(intToByteArray(num2));
        for (int i = 0; i < 52; i++) {
            out.write(intToByteArray(numL));
            out.write(intToByteArray(numB));
            out.write(intToByteArray(numR));
            out.write(intToByteArray(numG));
        }
        out.write(intToByteArray(num7));
    }

    private void AllPOnLamps() throws IOException {                   //All the Purpule lights

        numL = 0xf4;
        numR = 0x70;
        numB = 0x70;
        numG = 0;

        out.write(intToByteArray(num1));
        out.write(intToByteArray(num2));
        for (int i = 0; i < 52; i++) {
            out.write(intToByteArray(numL));
            out.write(intToByteArray(numB));
            out.write(intToByteArray(numR));
            out.write(intToByteArray(numG));
        }
        out.write(intToByteArray(num7));
    }

    private void AllWOnLamps() throws IOException {                   //All the White lights

        numL = 0xf4;
        numR = 0x70;
        numB = 0x70;
        numG = 0x70;
        out.write(intToByteArray(num1));
        out.write(intToByteArray(num2));
        for (int i = 0; i < 52; i++) {
            out.write(intToByteArray(numL));
            out.write(intToByteArray(numB));
            out.write(intToByteArray(numR));
            out.write(intToByteArray(numG));
        }
        out.write(intToByteArray(num7));
    }

    private void AllOffLamps() throws IOException {                   //Close all the lights
        numL = 0xfb;
        numR = 0;
        numB = 0;
        numG = 0;

        out.write(intToByteArray(num1));
        out.write(intToByteArray(num2));
        for (int i = 0; i < 52; i++) {
            out.write(intToByteArray(numL));
            out.write(intToByteArray(numB));
            out.write(intToByteArray(numR));
            out.write(intToByteArray(numG));
        }
        out.write(intToByteArray(num7));
    }



    private void SelectedLamp(int R, int G, int B, int lampNumber) throws IOException, InterruptedException {                   //All the Red lights
        numL = 0xf4;
        double constantValue = (double)112/(double)255;
        R = (int) ((int) R*constantValue);
        G = (int) ((int) G*constantValue);
        B = (int) ((int) B*constantValue);
        numR = (byte) R;
        numG = (byte) G;
        numB = (byte) B;
        int numZero = (byte) 0;
        Log.d(TAG, "CUSTOM R:" + numR);
        Log.d(TAG, "CUSTOM G:" + numG);
        Log.d(TAG, "CUSTOM B:" + numB);

        out.write(intToByteArray(num1));
        out.write(intToByteArray(num2));
        for (int i = 0; i < 52; i++) {
            if (i == lampNumber){
                out.write(intToByteArray(numL));
                out.write(intToByteArray(numB));
                out.write(intToByteArray(numR));
                out.write(intToByteArray(numG));
            }
            else{
                out.write(intToByteArray(numL));
                out.write(intToByteArray(numZero));
                out.write(intToByteArray(numZero));
                out.write(intToByteArray(numZero));
            }
        }
        out.write(intToByteArray(num7));
    }

    private void SelectedThreeLamps(int R, int G, int B, int lampNumber) throws IOException, InterruptedException {                   //All the Red lights
        numL = 0xf4;
        double constantValue = (double)112/(double)255;
        R = (int) ((int) R*constantValue);
        G = (int) ((int) G*constantValue);
        B = (int) ((int) B*constantValue);
        numR = (byte) R;
        numG = (byte) G;
        numB = (byte) B;
        int numZero = (byte) 0;
        Log.d(TAG, "CUSTOM R:" + numR);
        Log.d(TAG, "CUSTOM G:" + numG);
        Log.d(TAG, "CUSTOM B:" + numB);

        out.write(intToByteArray(num1));
        out.write(intToByteArray(num2));
        for (int i = 0; i < 50; i++) {
            if (i == lampNumber){
                out.write(intToByteArray(numL));
                out.write(intToByteArray(numB));
                out.write(intToByteArray(numR));
                out.write(intToByteArray(numG));
                out.write(intToByteArray(numL));
                out.write(intToByteArray(numB));
                out.write(intToByteArray(numR));
                out.write(intToByteArray(numG));
                out.write(intToByteArray(numL));
                out.write(intToByteArray(numB));
                out.write(intToByteArray(numR));
                out.write(intToByteArray(numG));
            }
            else{
                out.write(intToByteArray(numL));
                out.write(intToByteArray(numZero));
                out.write(intToByteArray(numZero));
                out.write(intToByteArray(numZero));
            }
        }
        out.write(intToByteArray(num7));
    }

    private void SelectedFiveLamps(int R, int G, int B, int lampNumber) throws IOException, InterruptedException {                   //All the Red lights
        numL = 0xf4;
        double constantValue = (double)112/(double)255;
        R = (int) ((int) R*constantValue);
        G = (int) ((int) G*constantValue);
        B = (int) ((int) B*constantValue);
        numR = (byte) R;
        numG = (byte) G;
        numB = (byte) B;
        int numZero = (byte) 0;
        Log.d(TAG, "CUSTOM R:" + numR);
        Log.d(TAG, "CUSTOM G:" + numG);
        Log.d(TAG, "CUSTOM B:" + numB);

        out.write(intToByteArray(num1));
        out.write(intToByteArray(num2));
        for (int i = 0; i < 48; i++) {
            if (i == lampNumber){
                out.write(intToByteArray(numL));
                out.write(intToByteArray(numB));
                out.write(intToByteArray(numR));
                out.write(intToByteArray(numG));
                out.write(intToByteArray(numL));
                out.write(intToByteArray(numB));
                out.write(intToByteArray(numR));
                out.write(intToByteArray(numG));
                out.write(intToByteArray(numL));
                out.write(intToByteArray(numB));
                out.write(intToByteArray(numR));
                out.write(intToByteArray(numG));
                out.write(intToByteArray(numL));
                out.write(intToByteArray(numB));
                out.write(intToByteArray(numR));
                out.write(intToByteArray(numG));
                out.write(intToByteArray(numL));
                out.write(intToByteArray(numB));
                out.write(intToByteArray(numR));
                out.write(intToByteArray(numG));
            }
            else{
                out.write(intToByteArray(numL));
                out.write(intToByteArray(numZero));
                out.write(intToByteArray(numZero));
                out.write(intToByteArray(numZero));
            }
        }
        out.write(intToByteArray(num7));
    }

    // DO POPRAWY
    private void SelectedTenLamps(int R, int G, int B, int lampNumber) throws IOException, InterruptedException {                   //All the Red lights
        numL = 0xf4;
        double constantValue = (double)112/(double)255;
        R = (int) ((int) R*constantValue);
        G = (int) ((int) G*constantValue);
        B = (int) ((int) B*constantValue);
        numR = (byte) R;
        numG = (byte) G;
        numB = (byte) B;
        int numZero = (byte) 0;
        Log.d(TAG, "CUSTOM R:" + numR);
        Log.d(TAG, "CUSTOM G:" + numG);
        Log.d(TAG, "CUSTOM B:" + numB);

        out.write(intToByteArray(num1));
        out.write(intToByteArray(num2));
        for (int i = 0; i < 43; i++) {
            if (i == lampNumber){
                out.write(intToByteArray(numL));
                out.write(intToByteArray(numB));
                out.write(intToByteArray(numR));
                out.write(intToByteArray(numG));

                out.write(intToByteArray(numL));
                out.write(intToByteArray(numB));
                out.write(intToByteArray(numR));
                out.write(intToByteArray(numG));

                out.write(intToByteArray(numL));
                out.write(intToByteArray(numB));
                out.write(intToByteArray(numR));
                out.write(intToByteArray(numG));

                out.write(intToByteArray(numL));
                out.write(intToByteArray(numB));
                out.write(intToByteArray(numR));
                out.write(intToByteArray(numG));

                out.write(intToByteArray(numL));
                out.write(intToByteArray(numB));
                out.write(intToByteArray(numR));
                out.write(intToByteArray(numG));

                out.write(intToByteArray(numL));
                out.write(intToByteArray(numB));
                out.write(intToByteArray(numR));
                out.write(intToByteArray(numG));

                out.write(intToByteArray(numL));
                out.write(intToByteArray(numB));
                out.write(intToByteArray(numR));
                out.write(intToByteArray(numG));

                out.write(intToByteArray(numL));
                out.write(intToByteArray(numB));
                out.write(intToByteArray(numR));
                out.write(intToByteArray(numG));

                out.write(intToByteArray(numL));
                out.write(intToByteArray(numB));
                out.write(intToByteArray(numR));
                out.write(intToByteArray(numG));

                out.write(intToByteArray(numL));
                out.write(intToByteArray(numB));
                out.write(intToByteArray(numR));
                out.write(intToByteArray(numG));
            }
            else{
                out.write(intToByteArray(numL));
                out.write(intToByteArray(numZero));
                out.write(intToByteArray(numZero));
                out.write(intToByteArray(numZero));
            }
        }
        out.write(intToByteArray(num7));
    }


    private void handleColorValue(int color) {
        // Obtain brightness value
        Integer brightness = (color >> 24) & 0xFF;
        Log.d(TAG, "brightness:" + brightness);
        if (brightness < 0) {
            brightness = 0xF0;
        }
        if (brightness > 0x7F) {
            brightness = 0xFB;
        }

        brightness = brightness * MAX_BRIGHT / 0x7F;
        brightness |= 0xF0;

        // Obtain red value
        Integer red = (color >> 16) & 0xFF;
        Log.d(TAG, "red:" + red);
        if (red < 0) {
            red = 0;
        }
        if (red > 255) {
            red = 255;
        }
		red = red * MAX_DUTY / 255;

        // Obtain green value
        Integer green = (color >> 8) & 0xFF;
        Log.d(TAG, "green:" + green);
        if (green < 0) {
            green = 0;
        }
        if (green > 255) {
            green = 255;
        }
		green = green * MAX_DUTY / 255 ;

        // Obtain blue value
        Integer blue = color & 0xFF;
        Log.d(TAG, "blue:" + blue);
        if (blue < 0) {
            blue = 0;
        }
        if (blue > 255) {
            blue = 255;
        }
		blue = blue * MAX_DUTY / 255;

        //limiting 52 LED bar's brightness while LED RGB Mixer
        if(!LedType && ((red+green) > MAX_DUTY || (red+blue) > MAX_DUTY || (blue+green) > MAX_DUTY || (red+blue+green) > MAX_DUTY)){
            if (brightness > 0xFc) {
                brightness = 0xF4;
            }
        }

        numL = brightness;
        numR = red;
        numG = green;
        numB = blue;
        Log.d(TAG, "numL:" + numL);
        Log.d(TAG, "numR:" + numR);
        Log.d(TAG, "numG:" + numG);
        Log.d(TAG, "numB:" + numB);
    }

    public int stringsimpleToByte(String in) {
        int b = Integer.parseInt(in, 16);
        return b;
    }

    public static byte[] intToByteArray(int a) {
        return new byte[]{(byte) (a & 0xFF)
        };
    }

}
