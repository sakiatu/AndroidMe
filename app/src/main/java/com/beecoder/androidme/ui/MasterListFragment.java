package com.beecoder.androidme.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.beecoder.androidme.R;
import com.beecoder.androidme.data.AndroidImageAssets;

public class MasterListFragment extends Fragment {

    private MasterListAdapter adapter;

    public interface OnImageClickListener {
        void onImageSelected(int position);
    }

    private OnImageClickListener onImageClickListener;

    public MasterListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        adapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());
        GridView gridView = rootView.findViewById(R.id.master_list_grid_view);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener((parent, view, position, id) -> onImageClickListener.onImageSelected(position));
        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            onImageClickListener = (OnImageClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnImageClickListener");
        }
    }
}
