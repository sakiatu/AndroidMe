package com.beecoder.androidme.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.beecoder.androidme.R;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment {
    private static final String IMAGE_ID_LIST = "image_ids";
    private static final String LIST_INDEX = "list_index";
    private List<Integer> imageIds;
    private int listIndex;

    public BodyPartFragment(List<Integer> imageIds, int listIndex) {
        this.imageIds = imageIds;
        this.listIndex = listIndex;
    }

    public void setListIndex(int listIndex) {
        this.listIndex = listIndex;
    }

    public BodyPartFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            imageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            listIndex = savedInstanceState.getInt(LIST_INDEX);
        }

        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);
        ImageView imageView = rootView.findViewById(R.id.body_part_image_view);
        imageView.setImageResource(imageIds.get(listIndex));
        imageView.setOnClickListener(v -> {
            if (listIndex < imageIds.size() - 1) {
                listIndex++;
            } else listIndex = 0;
            imageView.setImageResource(imageIds.get(listIndex));
        });
        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) imageIds);
        outState.putInt(LIST_INDEX, listIndex);
    }
}
