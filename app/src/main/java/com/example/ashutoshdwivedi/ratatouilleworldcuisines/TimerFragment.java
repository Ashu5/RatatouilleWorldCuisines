package com.example.ashutoshdwivedi.ratatouilleworldcuisines;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimerFragment extends Fragment {
    AlarmManager alarmManager;
    View rootView;
    TextView textView1;
    TextView textView2;
    Button timerbtn;
    public static final int REQUEST_CODE = 0;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      rootView= inflater.inflate(R.layout.fragment_timer, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.textView1=(TextView)rootView.findViewById(R.id.timer_textView);
        this.textView2=(TextView)rootView.findViewById(R.id.timer_textView2);
        this.timerbtn=(Button)rootView.findViewById(R.id.button_timer);




        timerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.button_timer)
                {
                    setAlarm();
                }
            }
        });

    }

    public void setAlarm()
    {

        int alarmType = AlarmManager.ELAPSED_REALTIME;
        final int FIFTEEN_SEC_MILLIS = 15000;
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);


        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), REQUEST_CODE,
                intent, 0);

        AlarmManager alarmManager = (AlarmManager)
                getActivity().getSystemService(getActivity().ALARM_SERVICE);
        alarmManager.setRepeating(alarmType, SystemClock.elapsedRealtime() + FIFTEEN_SEC_MILLIS,
                FIFTEEN_SEC_MILLIS, pendingIntent);
        // END_INCLUDE (configure_alarm_manager);
        Log.i("RepeatingAlarmFragment", "Alarm set.");



    }

}
