package com.example.ashutoshdwivedi.ratatouilleworldcuisines;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;


public class HomeFragment extends Fragment {
    View rootView;
    Button lunchbtn, breakfastbtn, dinnerbtn, snacksbtn;
    private SliderLayout sliderLayout;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lunchbtn = (Button) rootView.findViewById(R.id.lunch_btn);
        breakfastbtn = (Button) rootView.findViewById(R.id.breakfast_btn);
        snacksbtn = (Button) rootView.findViewById(R.id.snacks_btn);
        dinnerbtn = (Button) rootView.findViewById(R.id.dinner_btn);
        // slider element
        sliderLayout = (SliderLayout) rootView.findViewById(R.id.slider);

        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Banana Muffins", R.drawable.bkf_bnanamffins);
        file_maps.put("Pumpkin Seed Biscuits", R.drawable.bkf_pmpknbsct);
        file_maps.put("Kadhai Paneer", R.drawable.lunch_kadaipnr);
        file_maps.put("Mashed Potatos", R.drawable.bkf_mshdpotos);
        // iterate
        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity().getApplicationContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderLayout.addSlider(textSliderView);
            sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
            sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            sliderLayout.setCustomAnimation(new DescriptionAnimation());
            sliderLayout.setDuration(4000);


            lunchbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.lunch_btn) {
                        Intent intent = new Intent(getContext().getApplicationContext(), LunchActivity.class);
                        startActivity(intent);

                    }
                }
            });
            breakfastbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.breakfast_btn) {
                        Intent intent = new Intent(getContext().getApplicationContext(), BreakfastActivity.class);
                        startActivity(intent);
                    }
                }
            });
            snacksbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.snacks_btn) {
                        Intent intent = new Intent(getContext().getApplicationContext(), SnacksActivity.class);
                        startActivity(intent);
                    }

                }
            });
            dinnerbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.dinner_btn) {
                        Intent intent = new Intent(getContext().getApplicationContext(), DessertActivity.class);
                        startActivity(intent);
                    }
                }
            });


        }

    }

    @Override
    public void onStop() {
        super.onStop();
        sliderLayout.startAutoCycle();
    }
}
