package com.example.musicity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class buttonFragment extends Fragment{

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    songname song;
    Query refer;
    ImageView bg,bgplayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_button, container, false);
        listView=view.findViewById(R.id.mylist);
        database=FirebaseDatabase.getInstance();
        bg=view.findViewById(R.id.bg);
        bgplayer=view.findViewById(R.id.bgplayer);
        JcPlayerView jcplayer=(JcPlayerView)view.findViewById(R.id.jcplayer);
        ArrayList<JcAudio> jcAudios = new ArrayList<>();
        ref=database.getReference("Musicity");
        assert getArguments() != null;
        switch (getArguments().getString("key")) {
            case "sonu":
                refer = ref.orderByChild("artist").equalTo("Sonu Nigam");
                break;
            case "hari":
                refer = ref.orderByChild("artist").equalTo("Hariharan");
                break;
            case "shankar":
                refer = ref.orderByChild("artist").equalTo("Shankar Mahadevan");
                break;
            case "shreya":
                refer = ref.orderByChild("artist").equalTo("Shreya Ghoshal");
                break;
            case "arijith":
                refer = ref.orderByChild("artist").equalTo("Arijith Singh");
                break;
            case "rrr":
                refer=ref.orderByChild("album").equalTo("RRR");
                break;
            case "bandish":
                refer=ref.orderByChild("album").equalTo("Bandish Bandits");
                break;
            case "kalho":
                refer=ref.orderByChild("album").equalTo("Kal Ho Na Ho");
                break;
            case "bajirao":
                refer=ref.orderByChild("album").equalTo("Bajirao Mastani");
                break;
            case "zee":
                refer=ref.orderByChild("label").equalTo("Zee Music");
                break;
            case "tseries":
                refer=ref.orderByChild("label").equalTo("T Series");
                break;
            case "lahari":
                refer=ref.orderByChild("label").equalTo("Lahari Music");
                break;
            case "sony":
                refer=ref.orderByChild("label").equalTo("Sony Music");
                break;
            case "classy":
                refer=ref.orderByChild("genre").equalTo("Classical");
                break;
        }
        list=new ArrayList<String>();
        adapter=new ArrayAdapter<String>(getContext(), R.layout.list_edit,R.id.textView2,list);
        song=new songname();
        refer.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
                        song=ds.getValue(songname.class);
                        list.add(song.getSong());
                        jcAudios.add(JcAudio.createFromURL(song.getSong(),song.getSongurl()));

                }
                jcplayer.initPlaylist(jcAudios,null);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                jcplayer.playAudio(jcAudios.get(i));
                jcplayer.setVisibility(View.VISIBLE);
                jcplayer.createNotification();
                bg.setVisibility(View.VISIBLE);
                bgplayer.setVisibility(View.VISIBLE);

            }
        });

        return view;

    }



}