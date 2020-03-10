package com.example.dresser_app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dresser_app.GeneratedCombination;
import com.example.dresser_app.Ideas;
import com.example.dresser_app.R;

public class DressMeFragment extends Fragment{

    private Button next, gallery;
    private ImageView photoFromGallery;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_ideas,
                container,
                false);

        next = (Button) rootView.findViewById(R.id.button_next_ideas);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GeneratedCombination.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
