package com.example.ashutoshdwivedi.ratatouilleworldcuisines;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackFragment extends Fragment {

   View rootView;
    Button button;
    Button ratrBtn;
    RatingBar ratingBar;
    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_feedback, container, false);

       button =(Button)rootView.findViewById(R.id.button);
        return  rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.button)
                {
                    composeEmail("justashu5@gmail.com","Ratatouille |User Feedback",null);
                }
            }
        });
        setRatingListener();
    }
    public void setRatingListener()
    {
        ratingBar=(RatingBar)rootView.findViewById(R.id.ratingBar);
        ratrBtn=(Button)rootView.findViewById(R.id.rateBtn);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                String ratedValue= String.valueOf(rating);
            }
        });
        ratrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.rateBtn)
                {
                   // Toast.makeText(getActivity().getApplicationContext(),"Thanks for rating:" + String.valueOf(ratingBar.getRating()),Toast.LENGTH_SHORT).show();
             Snackbar snackbar=Snackbar.make(rootView.findViewById(R.id.relativeLayoutfeedback),"Thanks for rating us:"+ String.valueOf(ratingBar.getRating()),Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });

    }

    public void composeEmail(String addresses, String subject, Uri attachment) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_STREAM, attachment);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}
