package com.example.ashutoshdwivedi.ratatouilleworldcuisines;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Ashutosh Dwivedi on 17-03-2017.
 */

public class Language extends Fragment {
/*
    String lang;
    private DBHelper mydb;
    View rootView;

    *//* renamed from: com.cookware.worldcusinerecipes.Language.1 *//*
    class C03221 implements OnClickListener {
        C03221() {
        }

        public void onClick(View v) {
            switch (((RadioGroup) Language.this.rootView.findViewById(C0355R.id.radioGroup1)).getCheckedRadioButtonId()) {
                case R.id.radio0 *//*2131624123*//*:
                    Language.this.lang = "en";
                    break;
                case C0355R.id.radio1 *//*2131624124*//*:
                    Language.this.lang = "ar";
                    break;
                case C0355R.id.radio2 *//*2131624125*//*:
                    Language.this.lang = "zh-CN";
                    break;
                case C0355R.id.radio3 *//*2131624126*//*:
                    Language.this.lang = "it";
                    break;
                case R.id.radio4 *//*2131624230*//*:
                    Language.this.lang = "es";
                    break;

            }
            Locale locale = new Locale(Language.this.lang);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            Language.this.getActivity().getBaseContext().getResources().updateConfiguration(config, Language.this.getActivity().getBaseContext().getResources().getDisplayMetrics());
            Language.this.mydb.updatedb(1, Language.this.lang);
            Intent langintent = new Intent(Language.this.getActivity(), MainActivity.class);
            Language.this.getActivity().finish();
            Language.this.startActivity(langintent);
        }
    }

    public Language() {
        this.lang = "en";
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle((CharSequence) "Language");
        MainActivity mainActivity = (MainActivity) getActivity();
        MainActivity.result.setSelectionByIdentifier(7, false);
        mainActivity = (MainActivity) getActivity();
        MainActivity.search.setVisibility(8);
        ((TextView) this.rootView.findViewById(C0355R.id.rectext)).setText(Html.fromHtml("<font color='red'>Note</font> : For a better user experience and app quality we recommend you to <font color='#388e3c'>choose English Language</font>. Our research team is working hard to improve the translation quality of other languages."));
        Button button = (Button) this.rootView.findViewById(C0355R.id.button1);
        this.mydb = new DBHelper(getActivity());
        button.setOnClickListener(new C03221());
    }

    public void onDestroy() {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getActivity().getString(C0355R.string.toolbarname));
        super.onDestroy();
    }*/

}
