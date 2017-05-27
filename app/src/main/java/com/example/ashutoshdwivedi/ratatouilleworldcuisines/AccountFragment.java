package com.example.ashutoshdwivedi.ratatouilleworldcuisines;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
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
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment  implements GoogleApiClient.OnConnectionFailedListener{
 private View rootView;
    private TextView mProfileTitle;
    private SignInButton mGoogleSignIn;
    private  Button mSignOut;
    Button myrecipe;
    Button chefStuff;
    private ImageView mGoogleProfile;
    private FirebaseUser mFirebaseUser;
    private FirebaseAuth mFirebaseAuth;
    private GoogleSignInOptions googleSignInOptions;
    GoogleSignInResult googleSignInResult;
    GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 9001;
    private  ProgressDialog mProgressDialog;
    private final String TAG="MyAccount";
    private  FirebaseAuth.AuthStateListener mAuthListener;



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
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);


        this.mProfileTitle=(TextView)this.rootView.findViewById(R.id.profile_name);
        this.mGoogleProfile= (ImageView) this.rootView.findViewById(R.id.googleProfile);
        this.myrecipe=(Button) this.rootView.findViewById(R.id.myrecipe);
        this.chefStuff=(Button)this.rootView.findViewById(R.id.community);
        this.mSignOut=(Button)this.rootView.findViewById(R.id.sign_out_button);
        this.mGoogleSignIn=(SignInButton)this.rootView.findViewById(R.id.google_signIn);
        this.mGoogleSignIn.setSize(SignInButton.SIZE_STANDARD);

       googleSignInOptions= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
               .requestEmail()
               .requestProfile()
               .build();
        this.googleApiClient =new GoogleApiClient.Builder(getActivity()).addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions)
                .build();

       /* this.googleApiClient =new GoogleApiClient.Builder(getActivity()).addApi(Auth.GOOGLE_SIGN_IN_API,
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .requestProfile()
                        .build())
                       .build();

*/


        this.myrecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AccountFragment.this.getActivity().getSharedPreferences("pref",0).getString("displayname",
                        "").equals(""))
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

        this.mGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent signInIntent=Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(signInIntent,RC_SIGN_IN);*/

                  signIn();
            }

        });
        //add sign out  button here
        this.mSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
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
            /*   GoogleSignInAccount account= googleSignInResult.getSignInAccount();*/
            GoogleSignInResult result= Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                handleSignIn(result);


            }
        }
    }


    //[START]sign in method
    private void signIn()
    {

        startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(this.googleApiClient),RC_SIGN_IN);

    }
    //[END] of sign in

    public void handleSignIn(GoogleSignInResult result)
    {
        Log.d("AccountFragment","handleSignInResult:"+ result.isSuccess());
        if(result.isSuccess())
        {
            GoogleSignInAccount account=result.getSignInAccount();
            String name=account.getDisplayName();
            this.mProfileTitle.setText(name);
            getActivity();
            Uri photo=account.getPhotoUrl();
           this.mGoogleProfile.setImageURI(photo);


            updateUI(true);
        }
        else
        {
            updateUI(false);
        }
    }


    //[START] sign out method
    public void signOut()
    {
       /* this.STATUS=Integer.valueOf(1);*/
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
    }  // end of sign out

    // Update UI

    public void updateUI(boolean signedIn)
    {
        if(signedIn==true)
        {
            rootView.findViewById(R.id.googleProfile).setVisibility(rootView.VISIBLE);
            rootView.findViewById(R.id.google_signIn).setVisibility(rootView.GONE);
            rootView.findViewById(R.id.sign_out_button).setVisibility(rootView.VISIBLE);

        }
        else
        {
            rootView.findViewById(R.id.googleProfile).setVisibility(rootView.GONE);
            rootView.findViewById(R.id.google_signIn).setVisibility(rootView.VISIBLE);
            rootView.findViewById(R.id.sign_out_button).setVisibility(rootView.GONE);
        }
    }//END of updateUi


    // START of revoke access
    public void revokeAccess()
    {
        Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
    }  // END of revokeAcess


    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage("Loading");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
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
