package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Fragment5 extends Fragment {

    EditText PIN1, PIN2;
    ImageButton button;

    public Fragment5() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_5, container, false);
        PIN1 = (EditText) view.findViewById(R.id.iPinUbah1);
        PIN2 = (EditText) view.findViewById(R.id.iPinUbah2);
        button = (ImageButton) view.findViewById(R.id.btnPinEnter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sPin1 = PIN1.getText().toString();
                String sPin2 = PIN2.getText().toString();
                if (sPin1.length() < 6){
                    NOTIF("Pin terlalu pendek");
                } else if (sPin1.length() > 6){
                    NOTIF("Pin terlalu panjang");
                } else if (!sPin1.equals(sPin2)) {
                    NOTIF("Pin tidak sama");
                } else {
                    NOTIF("Pin berhasil dirubah");
                    PIN1.setText("");
                    PIN2.setText("");
                    SharedPreferences sharedPref = getActivity().getSharedPreferences("MySettings", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("pin", sPin1);
                    editor.putInt("fase", 1);
                    editor.apply();
                }
            }
        });
        return view;
    }
    void NOTIF (String message){
        Context context = getActivity();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}