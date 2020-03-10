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

import com.example.dresser_app.R;
import com.example.dresser_app.TakePicture;

public class WardrobeFragment extends Fragment {

    private Button mOpen, mAdd, mRemove;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_wardrobe,
                container,
                false);

        mAdd = (Button) rootView.findViewById(R.id.add_button);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TakePicture.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
