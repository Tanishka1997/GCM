package com.example.tanishka.gcpsample;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private BroadcastReceiver broadcastReceiver;
    private String token;
    EditText message;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
   Context context;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Intent i;


    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance() {
        Home fragment = new Home();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        broadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(GCMRegisterationIntentService.REGISTERATION_SUCCESS)){
                    token=intent.getStringExtra("token");
                    Toast.makeText(getActivity(),"Registeration success"+token,Toast.LENGTH_LONG).show();
                    new connect_it().execute();
                }
                else if(intent.getAction().equals(GCMRegisterationIntentService.REGISTERATION_ERROR)){
                    Toast.makeText(getActivity(),"Registeration Error",Toast.LENGTH_LONG).show();
                }
            }
        };

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        int resultcode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
        if (resultcode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultcode)) {
                Toast.makeText(getActivity(), "device does not support or not installed play services", Toast.LENGTH_LONG).show();
                GooglePlayServicesUtil.showErrorNotification(resultcode, getActivity());
            } else {
                Toast.makeText(getActivity(), "not installed play services", Toast.LENGTH_LONG).show();
            }
        } else {
            Button button = (Button) v.findViewById(R.id.send);
            message = (EditText) v.findViewById(R.id.message);
            context = getActivity();
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    i = new Intent(context, GCMRegisterationIntentService.class);
                    i.putExtra("message", message.toString());
                    getActivity().startService(i);
                }
            });


        }
        return v;
    }
    private class connect_it extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                new SendMessage(token);
            }catch (Exception e){

            }
            return null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(broadcastReceiver,new IntentFilter(GCMRegisterationIntentService.REGISTERATION_SUCCESS));
        getActivity().registerReceiver(broadcastReceiver,new IntentFilter(GCMRegisterationIntentService.REGISTERATION_ERROR));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(broadcastReceiver);
    }
}
