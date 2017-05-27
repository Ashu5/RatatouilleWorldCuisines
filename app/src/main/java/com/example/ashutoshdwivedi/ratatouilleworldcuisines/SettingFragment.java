package com.example.ashutoshdwivedi.ratatouilleworldcuisines;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    TextView clear;
    TextView language;
    TextView moreApp;
    TextView shareApp;
    TextView notification;
    View rootView;
    Snackbar snackbar;


    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         rootView=inflater.inflate(R.layout.fragment_setting, container, false);
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle((CharSequence) "Settings");
        MainActivity mainActivity = (MainActivity) getActivity();
        this.clear = (TextView) this.rootView.findViewById(R.id.clear_cache);
        this.notification = (TextView) this.rootView.findViewById(R.id.notification);
        this.language = (TextView) this.rootView.findViewById(R.id.language);
        this.moreApp = (TextView) this.rootView.findViewById(R.id.more_app);
        this.shareApp = (TextView) this.rootView.findViewById(R.id.share_app);

        if (Integer.valueOf(getActivity().getSharedPreferences("pref", 0).getInt("notification", 0)).intValue() == 1) {
            this.notification.setText(Html.fromHtml("Notifications &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#388e3c'>ON</font>"));
        } else {
            this.notification.setText(Html.fromHtml("Notifications &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='red'>OFF</font>"));
        }

        this.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText((getContext()),"Cache Cleared",Toast.LENGTH_SHORT).show();
            }
        });
        this.notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText((getContext()),"Notification OFf",Toast.LENGTH_SHORT).show();
            }
        });
        this.language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingFragment.this.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.comtent_main, new Language(), "Language").addToBackStack("Language").commit();
            }
        });
        this.moreApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iintent = new Intent("android.intent.action.VIEW");
                iintent.setData(Uri.parse("market://search?q=pub:" + SettingFragment.this.getString(R.string.app_name)));

            }
        });
        this.shareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingFragment.this.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.comtent_main,new InviteFragment(),"removeads").addToBackStack("removeads").commit();
            }
            });

    }
}
