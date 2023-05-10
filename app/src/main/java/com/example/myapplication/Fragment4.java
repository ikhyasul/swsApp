package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment4 extends Fragment {

    EditText Nama;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    public Fragment4() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_4, container, false);
        editor = sharedPref.edit();
        TextView Simpan = (TextView) view.findViewById(R.id.btnSetingSimpan);
        TextView EDITpin = (TextView) view.findViewById(R.id.btnSetingPin);
        Nama = (EditText)view.findViewById(R.id.edtSetingName);
        sharedPref = getActivity().getSharedPreferences("MySettings", Context.MODE_PRIVATE);
        String sName = sharedPref.getString("nama", "Belum Dinamai");
        Nama.setText(sName);
        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sNama = Nama.getText().toString();
                editor.putString("nama", sNama);
                editor.apply();
                NOTIF("Sudah disimpan sebagai " + sNama);
            }
        });
        EDITpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("fase", 2);
                editor.apply();
            }
        });
        return view;
    }
    void NOTIF (String message){
        Context context = getActivity().getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}