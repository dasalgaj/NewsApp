package com.example.newsapp.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapp.MainActivity;
import com.example.newsapp.R;
import com.example.newsapp.listeners.SourceListener;

import java.util.ArrayList;
import java.util.Collections;

public class SettingsFragment extends Fragment {

    private TextView mIzvori;
    private View view;
    private boolean[] selectedSource;

    Integer obrisi;
    String[] sources;
    ArrayList<Integer> sourceList = new ArrayList<>();
    ArrayList<String> izvori = new ArrayList<>();

    public SourceListener sourceListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_settings, container, false);

        sources = sourceListener.getSource().toArray(new String[0]);

        selectedSource = new boolean[sourceListener.getSource().size()];

        mIzvori = view.findViewById(R.id.tvIzvorDropdown);

        mIzvori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                izvori.clear();

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Odaberi izvore");

                builder.setCancelable(false);

                builder.setMultiChoiceItems(sources, selectedSource, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInteface, int i, boolean b) {
                        if (b){

                            if (!sourceList.contains(i)) {
                                sourceList.add(i);

                                Collections.sort(sourceList);
                            }

                        }
                        else{

                            if (sourceList.contains(i)) {
                                sourceList.remove(sourceList.indexOf(i));
                            }

                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInteface, int which) {
                        StringBuilder stringBuilder = new StringBuilder();

                        for (int i = 0; i < sourceList.size(); i++){

                            stringBuilder.append(sources[sourceList.get(i)]);

                            izvori.add(sources[sourceList.get(i)]);

                            if (i != sourceList.size() - 1){
                                stringBuilder.append(", ");
                            }
                        }

                        mIzvori.setText(stringBuilder.toString());

                        sourceListener.setIzvor(izvori);
                    }
                });

                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInteface, int which) {

                        for (int i = 0; i < selectedSource.length; i++){

                            selectedSource[i] = false;

                            sourceList.clear();

                            mIzvori.setText("");
                        }
                    }
                });

                builder.show();
            }
        });

        return view;
    }

    public void onResume() {
        super.onResume();

        obrisi = sourceListener.getObrisi();

        if (obrisi != 0){
            if (selectedSource != null) {
                for (int i = 0; i < selectedSource.length; i++) {

                    selectedSource[i] = false;

                    sourceList.clear();
                    izvori.clear();

                    mIzvori.setText("");
                }
            }

            sourceListener.setObrisi(0);
        }
    }
}