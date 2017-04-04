package com.example.ashutoshdwivedi.ratatouilleworldcuisines;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends Fragment  {
View rootView;
    Button button;

    public ShareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView=inflater.inflate(R.layout.fragment_share, container, false);
         button=(Button)rootView.findViewById(R.id.share_btn);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.share_btn)
                {
                   shareApp();
                }
            }
        });


    }

    // method to share over various applications
    public void shareApp()
    {
        Intent shareIntent= new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Ratapuille-World Cuisines");
        if (shareIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(Intent.createChooser(shareIntent," Choose Any one"));
        }
    }


}
