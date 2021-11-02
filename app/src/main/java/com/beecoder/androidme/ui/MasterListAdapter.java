package com.beecoder.androidme.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class MasterListAdapter extends BaseAdapter {
    private Context context;
    private List<Integer> imageIds;

    public MasterListAdapter(Context context, List<Integer> imageIds) {
        this.context = context;
        this.imageIds = imageIds;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // If the view is not recycled, this creates a new ImageView to hold an image
            imageView = new ImageView(context);
            // Define the layout parameters
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        // Set the image resource and return the newly created ImageView
        imageView.setImageResource(imageIds.get(position));
        return imageView;
    }

    @Override
    public int getCount() {
        return imageIds.size();
    }
}
