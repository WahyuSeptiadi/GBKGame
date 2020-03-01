package mobile.dev.gbkgame;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import org.w3c.dom.Text;

import static mobile.dev.gbkgame.Notif.CHANNEL_1_ID;

public class FragmentGame extends Fragment {

    int randomNumber;
    int pointHuman, pointCpu;
    static String title, message;
    TextView statusHuman;
    public static boolean stopThread = false;

    private FragmentGameListener Listener;
    private TextView scoreHuman, scoreCpu, thread1, thread2;
    private Button btnStart, btnStop, btnReset;

    public interface FragmentGameListener {
        void onInputFragmentGameSent(CharSequence input1, CharSequence input2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game, container, false);

        scoreHuman = v.findViewById(R.id.scoreHuman);
        scoreCpu = v.findViewById(R.id.scoreCpu);
        thread1 = v.findViewById(R.id.tread1);
        thread2 = v.findViewById(R.id.tread2);
        btnStart = v.findViewById(R.id.btnStart);
        btnStop = v.findViewById(R.id.btnStop);
        btnReset = v.findViewById(R.id.btnReset);

        try{
            Bundle bundle = new Bundle();
            bundle.putInt("ScoreAkhirCpu", pointCpu);
            bundle.putInt("ScoreAkhirHuman", pointHuman);
            FragmentHistory fragment2 = new FragmentHistory();
            fragment2.setArguments(bundle);

            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contianer2, fragment2)
                    .commit();

        }catch(Exception e){
            e.printStackTrace();
        }

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startThread(v);
//                updatePoint();
//                cekMenangMatch(thread1, thread2);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopThread(v);
                updatePoint();
                cekMenangMatch();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset(v);
            }
        });

        //DEFINISI nilai thread ===> 1 = kertas, 2 = gunting, 3 = batu

        return v;
    }

    public void sendOnChannel1() {
        sendChannel1Notification(this.getContext());
    }

    public static void sendChannel1Notification(Context context) {

        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_games)
                .setColor(Color.BLUE)
                .setContentTitle(title)
                .setContentText(message)
                .setSound(defaultSound)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setOnlyAlertOnce(true)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, notification);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentGameListener) {
            Listener = (FragmentGameListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Listener = null;
    }

    public void cekMenangMatch(){

        if(pointHuman == 3){
            pointHuman = 0;
            CharSequence CPU = scoreCpu.getText();
            CharSequence HUMAN = scoreHuman.getText();
            Listener.onInputFragmentGameSent(CPU, HUMAN);

            btnStart.setEnabled(false);
            btnStop.setEnabled(false);

            title = "YEEEY.. HUMAN THE WINNER";
            message = "Congratulation :)";
            sendOnChannel1();
            showSnackBar("CLICK BUTTON RESET TO ENABLE BUTTON START & STOP");
        }else if(pointCpu == 3){
            pointCpu = 0;
            CharSequence CPU = scoreCpu.getText();
            CharSequence HUMAN = scoreHuman.getText();
            Listener.onInputFragmentGameSent(CPU, HUMAN);

            btnStart.setEnabled(false);
            btnStop.setEnabled(false);

            title = "Hmmm.. HUMAN THE LOSER";
            message = "So Very Sad :(";
            sendOnChannel1();
            showSnackBar("CLICK BUTTON RESET TO ENABLE BUTTON START & STOP");
        }
    }

    public void updatePoint(){
        // 1 = kertas , 2 = gunting, 3 = batu
        if (pointCpu != 3 || pointHuman != 3){
            if (thread1.getText().toString() == "1"){
                if (thread2.getText().toString() == "1"){
                    scoreCpu.setText(String.valueOf(pointCpu));
                    scoreHuman.setText(String.valueOf(pointHuman));
                    showSnackBar("Draw bosq..");
                }
                if (thread2.getText().toString() == "2"){
                    pointCpu += 1;
                    scoreCpu.setText(String.valueOf(pointCpu));
                    showSnackBar("Yeay CPU WIN");
                }
                if (thread2.getText().toString() == "3"){
                    pointHuman += 1;
                    scoreHuman.setText(String.valueOf(pointHuman));
                    showSnackBar("Yeay Human WIN");
                }
            }else if (thread1.getText().toString() == "2"){
                if (thread2.getText().toString() == "1"){
                    pointHuman += 1;
                    scoreHuman.setText(String.valueOf(pointHuman));
                    showSnackBar("Yeay Human WIN");
                }
                if (thread2.getText().toString() == "2"){
                    scoreCpu.setText(String.valueOf(pointCpu));
                    scoreHuman.setText(String.valueOf(pointHuman));
                    showSnackBar("Draw bosq..");
                }
                if (thread2.getText().toString() == "3"){
                    pointCpu += 1;
                    scoreCpu.setText(String.valueOf(pointCpu));
                    showSnackBar("Yeay CPU WIN");
                }
            }else if (thread1.getText().toString() == "3"){
                if (thread2.getText().toString() == "1"){
                    pointCpu += 1;
                    scoreCpu.setText(String.valueOf(pointCpu));
                    showSnackBar("Yeay CPU WIN");
                }
                if (thread2.getText().toString() == "2"){
                    pointHuman += 1;
                    scoreHuman.setText(String.valueOf(pointHuman));
                    showSnackBar("Yeay Human WIN");
                }
                if (thread2.getText().toString() == "3"){
                    scoreCpu.setText(String.valueOf(pointCpu));
                    scoreHuman.setText(String.valueOf(pointHuman));
                    showSnackBar("Draw bosq..");
                }
            }
        }
    }
    public void showSnackBar(String massage){
        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), massage, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }

    // THREAD
    public void startThread(View view){
        stopThread = false;
        ThreadSatu thread1 = new ThreadSatu();
        thread1.start();
        ThreadDua thread2 = new ThreadDua();
        thread2.start();
    }

    public void stopThread(View view) {
        stopThread = true;
    }

    class ThreadSatu extends Thread {
        @Override
        public void run() {
            do {
                try{
                    Thread.sleep(1000);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            randomNumber = ((int) (3*Math.random()+1));
                            thread1.setText(String.valueOf(randomNumber));
                            if(randomNumber == 1){
                                thread1.setBackgroundResource(R.drawable.paper);
                            }else if(randomNumber == 2){
                                thread1.setBackgroundResource(R.drawable.gunting);
                            }else{
                                thread1.setBackgroundResource(R.drawable.stone);
                            }
                        }
                    });
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                };
            }while (stopThread == false);
        }
    }

    class ThreadDua extends Thread {
        @Override
        public void run() {
            do {
                try{
                    Thread.sleep(1000);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            randomNumber = ((int) (3*Math.random()+1));
                            thread2.setText(String.valueOf(randomNumber));
                            if(randomNumber == 1){
                                thread2.setBackgroundResource(R.drawable.paper);
                            }else if(randomNumber == 2){
                                thread2.setBackgroundResource(R.drawable.gunting);
                            }else{
                                thread2.setBackgroundResource(R.drawable.stone);
                            }
                        }
                    });
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                };
            }while (stopThread == false);
        }
    }
    public void reset(View view) {
        // Reset button
        btnStop.setEnabled(true);
        btnStart.setEnabled(true);

        // Reset textview
        pointCpu = 0;
        pointHuman = 0;
        scoreCpu.setText("0");
        scoreHuman.setText("0");
        thread1.setText("0");
        thread2.setText("0");
        thread1.setBackgroundResource(R.drawable.paper);
        thread2.setBackgroundResource(R.drawable.paper);
    }
}

























//    private void sendData()
//    {
//        //INTENT OBJ
//        Intent i = new Intent(getActivity().getBaseContext(), MainActivity.class);
//
//        //PACK DATA
//        i.putExtra("SENDER_KEY", "MyFragment");
//        i.putExtra("STATUS_KEY", statusHuman.getText().toString());
//
//        //RESET WIDGETS
//        statusHuman.setText("NULL");
//        //START ACTIVITY
//        getActivity().startActivity(i);
//    }