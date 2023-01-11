package com.example.musicity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class libraryFragment extends Fragment {
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    songname song;
    ImageView bg,bgplayer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_button, container, false);

        listView=view.findViewById(R.id.mylist);
        database=FirebaseDatabase.getInstance();
        JcPlayerView jcplayer=(JcPlayerView)view.findViewById(R.id.jcplayer);
        ArrayList<JcAudio> jcAudios = new ArrayList<>();
        bg=view.findViewById(R.id.bg);
        bgplayer=view.findViewById(R.id.bgplayer);
        ref=database.getReference("Musicity");
        song=new songname();
        list=new ArrayList<String>();
        adapter=new ArrayAdapter<String>(getContext(), R.layout.list_edit,R.id.textView2,list);
        song=new songname();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
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
                jcplayer.createNotification();
                jcplayer.setVisibility(View.VISIBLE);
                bg.setVisibility(View.VISIBLE);
                bgplayer.setVisibility(View.VISIBLE);
            }
        });


        return view;
    }
}