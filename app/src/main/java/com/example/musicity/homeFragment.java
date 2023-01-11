package com.example.musicity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.musicity.R;
import com.google.firebase.database.DatabaseReference;


public class homeFragment extends Fragment implements View.OnClickListener {
    ImageButton sonu,hari,shankar,shreya,arijith,rrr,bajirao,bandish,kalho,lahari,zee,tseries,sony,classical;
    Bundle bundle=new Bundle();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_home, container, false);

        sonu=(ImageButton) view.findViewById(R.id.sonu);
        sonu.setOnClickListener(this);
        hari=(ImageButton) view.findViewById(R.id.hari);
        hari.setOnClickListener(this);
        shankar=(ImageButton) view.findViewById(R.id.shankar);
        shankar.setOnClickListener(this);
        shreya=view.findViewById(R.id.shreya);
        shreya.setOnClickListener(this);
        arijith=view.findViewById(R.id.arijith);
        arijith.setOnClickListener(this);
        rrr=view.findViewById(R.id.rrr);
        rrr.setOnClickListener(this);
        bajirao=view.findViewById(R.id.bajirao);
        bajirao.setOnClickListener(this);
        kalho=view.findViewById(R.id.kalhonaho);
        kalho.setOnClickListener(this);
        bandish=view.findViewById(R.id.bandish);
        bandish.setOnClickListener(this);
        lahari=view.findViewById(R.id.lahari);
        lahari.setOnClickListener(this);
        zee=view.findViewById(R.id.zee);
        zee.setOnClickListener(this);
        sony=view.findViewById(R.id.sony);
        sony.setOnClickListener(this);
        tseries=view.findViewById(R.id.tseries);
        tseries.setOnClickListener(this);
        classical= (ImageButton) view.findViewById(R.id.classical);
        classical.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.equals(sonu))
            bundle.putString("key","sonu");
        else if(view.equals(hari))
            bundle.putString("key","hari");
        else if(view.equals(shankar))
            bundle.putString("key","shankar");
        else if(view.equals(shreya))
            bundle.putString("key","shreya");
        else if(view.equals(arijith))
            bundle.putString("key","arijith");
        else if(view.equals(rrr))
            bundle.putString("key","rrr");
        else if(view.equals(bajirao))
            bundle.putString("key","bajirao");
        else if(view.equals(kalho))
            bundle.putString("key","kalho");
        else if(view.equals(bandish))
            bundle.putString("key","bandish");
        else if(view.equals(zee))
            bundle.putString("key","zee");
        else if(view.equals(tseries))
            bundle.putString("key","tseries");
        else if(view.equals(sony))
            bundle.putString("key","sony");
        else if(view.equals(lahari))
            bundle.putString("key","lahari");
        else if(view.equals(classical))
            bundle.putString("key","classy");
        buttonFragment frag = new buttonFragment();
        frag.setArguments(bundle);
        FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
        fm.replace(R.id.flayout, frag).commit();
    }
}