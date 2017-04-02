package com.example.ashutoshdwivedi.ratatouilleworldcuisines;


import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.*;
import android.support.v4.BuildConfig;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuthException;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment  implements GoogleApiClient.OnConnectionFailedListener{
 private TextView textView;
    private GoogleApiClient googleApiClient;
    private SignInButton signInButton;
    private ImageView imageView;
    private Button chefStuff;
    private Button myrecipe;
    private View rootView;
    private Integer STATUS=Integer.valueOf(0);
    private static final int RC_SIGN_IN=9001;
    private  static final String TAG="GoogleActivity";
    GoogleSignInOptions googleSignInOptions;




    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.rootView=inflater.inflate(R.layout.fragment_account, container, false);
        return this.rootView;
        //google api call

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainActivity mainActivity = (MainActivity) getActivity();
        this.imageView= (ImageView) this.rootView.findViewById(R.id.text_view_googleProfile);
        this.myrecipe=(Button) this.rootView.findViewById(R.id.myrecipe);
        this.chefStuff=(Button)this.rootView.findViewById(R.id.community);
        this.signInButton=(SignInButton)this.rootView.findViewById(R.id.google_signIn);
        this.signInButton.setSize(SignInButton.SIZE_WIDE);

       // googleSignInOptions= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        this.googleApiClient =new GoogleApiClient.Builder(getActivity()).addApi(Auth.GOOGLE_SIGN_IN_API,
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()).build();

        this.myrecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AccountFragment.this.getActivity().getSharedPreferences("pref",0).getString("displayname",
                        BuildConfig.FLAVOR).equals(BuildConfig.FLAVOR))
                {
                    Toast.makeText(AccountFragment.this.getActivity(),"Plesae Sign In to use services",
                            Toast.LENGTH_SHORT).show();
                }
                AccountFragment.this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.comtent_main ,
                        new  MyRecipeFragment(),"privacy").addToBackStack("privacy").commit();
            }
        });
        this.chefStuff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AccountFragment.this.getActivity().getSharedPreferences("pref",0).getString("displayname",
                        BuildConfig.FLAVOR).equals(BuildConfig.FLAVOR))
                {
                    Toast.makeText(AccountFragment.this.getActivity(),"Plesae Sign In to use services",
                            Toast.LENGTH_SHORT).show();
                }
                AccountFragment.this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.comtent_main ,
                        new  CommunityFragment(),"privacy").addToBackStack("privacy").commit();
            }
        });

        this.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent=Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(signInIntent,RC_SIGN_IN);
            }
        });
        //add sign out  button here



        this.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountFragment.this.signIn();
            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RC_SIGN_IN)
        {
            GoogleSignInResult googleSignInResult=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(googleSignInResult.isSuccess())
            {
                // add firebase authorization
               GoogleSignInAccount account= googleSignInResult.getSignInAccount();
                textView.setText(account.getDisplayName());

            }
        }
    }

    //sign in method
    private void signIn()
    {
        this.STATUS=Integer.valueOf(1);
        startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(this.googleApiClient),RC_SIGN_IN);
    }
    //sign out method
    public void signOut()
    {
        this.STATUS=Integer.valueOf(1);
        // add firebase authentication here to invalidate cache and session
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG,"onConnectionFailed:" +connectionResult);
        Toast.makeText(getActivity(),"Google  Play Services Error.",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(this.googleApiClient!=null)
        {
            this.googleApiClient.connect();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.googleApiClient.disconnect();
        //((MainActivity) getActivity()).getSupportActionBar().setTitle(getActivity().getString(R.string.toolbarname));
    }

}
