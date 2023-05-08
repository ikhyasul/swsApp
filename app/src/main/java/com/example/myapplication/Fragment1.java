package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Fragment1 extends Fragment {

    TextView tvNotif1, tvNotif2, tvNotif3;
    LinearLayout lNotif1, lNotif2, lNotif3;
    ImageView imgNotif1, imgNotif2, imgNotif3;
    String[] sNotif = {"","",""};
    int[] iNotif = {0,0,0};
    int thisNotif = 0;

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        lNotif1 = (LinearLayout)view.findViewById(R.id.nOver1);
        lNotif2 = (LinearLayout)view.findViewById(R.id.nOver2);
        lNotif3 = (LinearLayout)view.findViewById(R.id.nOver3);
        imgNotif1 = (ImageView)view.findViewById(R.id.imgOverNotif1);
        imgNotif2 = (ImageView)view.findViewById(R.id.imgOverNotif2);
        imgNotif3 = (ImageView)view.findViewById(R.id.imgOverNotif3);
        lNotif1.setVisibility(View.GONE);
        lNotif2.setVisibility(View.GONE);
        lNotif3.setVisibility(View.GONE);
        tvNotif1 = (TextView)view.findViewById(R.id.tvOverNotif1);
        tvNotif2 = (TextView)view.findViewById(R.id.tvOverNotif2);
        tvNotif3 = (TextView)view.findViewById(R.id.tvOverNotif3);
        Notif("Terdapat kebocoran gas", 1);
        Notif("Sonar tidak terdeteksi", 2);
        Notif("Pintu Tertutup", 3);
        Notif("", 2);
        return view;
    }
    void Notif(String sVall, int iType){
        iType--;
        if (sVall.equals("") && iNotif[iType] != 0) {
            iNotif[iType] = 0;
            for (int i = 0; i < iNotif.length; i++){
                if (iNotif[i] > iNotif[iType]){iNotif[i] = iNotif[i] - 1;}
            }
            thisNotif--;
        } else if (iNotif[iType] == 0){
            thisNotif++;
            sNotif[iType] = sVall;
            iNotif[iType] = thisNotif;
        } else {
            sNotif[iType] = sVall;
        }
        if (thisNotif == 0) {
            lNotif1.setVisibility(View.GONE);
            lNotif2.setVisibility(View.GONE);
            lNotif3.setVisibility(View.GONE);
        } else {
            lNotif1.setVisibility(View.VISIBLE);
            lNotif2.setVisibility(View.VISIBLE);
            lNotif3.setVisibility(View.VISIBLE);
            if (thisNotif == 1){
                lNotif1.setBackgroundResource(R.drawable.costume_notif_background);
                lNotif2.setBackground(null);
                lNotif3.setBackground(null);
                imgNotif1.setVisibility(View.VISIBLE);
                imgNotif2.setVisibility(View.GONE);
                imgNotif3.setVisibility(View.GONE);
                tvNotif2.setText("");
                tvNotif3.setText("");
                for (int i = 0; i < iNotif.length; i++){
                    if (iNotif[i] == 1){
                        tvNotif1.setText(sNotif[i]);
                    }
                }
            } else if (thisNotif == 2) {
                lNotif1.setBackgroundResource(R.drawable.costume_notif_background);
                lNotif2.setBackgroundResource(R.drawable.costume_notif_background);
                lNotif3.setBackground(null);
                imgNotif1.setVisibility(View.VISIBLE);
                imgNotif2.setVisibility(View.VISIBLE);
                imgNotif3.setVisibility(View.GONE);
                tvNotif3.setText("");
                for (int i = 0; i < iNotif.length; i++){
                    if (iNotif[i] == 1){tvNotif1.setText(sNotif[i]);}
                    else if (iNotif[i] == 2){tvNotif2.setText(sNotif[i]);}
                }
            } else if (thisNotif == 3) {
                lNotif1.setBackgroundResource(R.drawable.costume_notif_background);
                lNotif2.setBackgroundResource(R.drawable.costume_notif_background);
                lNotif3.setBackgroundResource(R.drawable.costume_notif_background);
                imgNotif1.setVisibility(View.VISIBLE);
                imgNotif2.setVisibility(View.VISIBLE);
                imgNotif3.setVisibility(View.VISIBLE);
                for (int i = 0; i < iNotif.length; i++){
                    if (iNotif[i] == 1){tvNotif1.setText(sNotif[i]);}
                    else if (iNotif[i] == 2){tvNotif2.setText(sNotif[i]);}
                    else if (iNotif[i] == 3){tvNotif3.setText(sNotif[i]);}
                }
            }
        }
    }
}