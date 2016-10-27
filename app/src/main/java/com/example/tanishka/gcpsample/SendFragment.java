package com.example.tanishka.gcpsample;


import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SendFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private EditText to_send;
    private EditText message;
    String Mobile;
    String Message;
    private Button send;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SendFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment SendFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SendFragment newInstance() {
        SendFragment fragment = new SendFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_send, container, false);
        to_send=(EditText) v.findViewById(R.id.to_send);
        message=(EditText) v.findViewById(R.id.message);
        send=(Button) v.findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message=message.getText().toString();
                Mobile=to_send.getText().toString();

                new connect_it().execute();
            }
        });
        return  v;
    }

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

                        new SendMessage(Mobile,Message);


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

    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
