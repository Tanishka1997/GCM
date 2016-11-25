package com.example.tanishka.gcpsample;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;


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
    EditText mobile;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
   Context context;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Intent i;
    String Mobile;

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
                    Toast.makeText(getActivity(),"Registeration success",Toast.LENGTH_LONG).show();
                    //new connect_it().execute();
                    RegisterPreferences.setMobile(getActivity(),Mobile);
                    Intent i=new Intent(getActivity(),TabViewActivity.class);
                    startActivity(i);
                    getActivity().finish();

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

            Button button = (Button) v.findViewById(R.id.register);
            mobile= (EditText) v.findViewById(R.id.mobile);
            context = getActivity();
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    i = new Intent(context, GCMRegisterationIntentService.class);
                    Mobile=mobile.getText().toString();
                    i.putExtra("mobile", Mobile);
                    getActivity().startService(i);
                }
            });



        return v;
    }
   /*
    private class connect_it extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                new SendMessage(Mobile);
            }catch (Exception e){

            }
            return null;
        }
    }
*/
   private class connect_it extends AsyncTask<Void,Void,Void> {
       private String error="";

       @Override
       protected Void doInBackground(Void... voids) {
           try {
               ConnectivityManager cm=(ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
               boolean	isNetworkAvailable	=	cm.getActiveNetworkInfo()	!=	null;
               boolean	isNetworkConnected	=	isNetworkAvailable	&&
                       cm.getActiveNetworkInfo().isConnected();
               NetworkInfo nf=cm.getActiveNetworkInfo();
               if(isNetworkAvailable&&isNetworkConnected) {
                   try {
                       SocketAddress addr=new InetSocketAddress("www.svnit.ac.in",80);
                       Socket sock=new Socket();
                       int timeout=10000;
                       sock.connect(addr,timeout);

                       new SendMessage(Mobile,"You have successfully registered");


                   } catch (IOException e) {
                       e.printStackTrace();
                       error="Network State Timed Out";
                   }
               }
               else
                   error="No Internet Connectivity";


           }catch (Exception e){

           }
           return null;
       }

       @Override
       protected void onPostExecute(Void aVoid) {
           super.onPostExecute(aVoid);
           if(!error.equals(""))
               Toast.makeText(getActivity(),error,Toast.LENGTH_LONG).show();
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
