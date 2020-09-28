package com.zee.projectosos.adapter;


import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.zee.projectosos.R;
import com.zee.projectosos.model.basicDataType;

import java.util.ArrayList;


public class NestedImageAdapter extends RecyclerView.Adapter<NestedImageAdapter.ZeeHolder>{

    private ArrayList<Image> items;
    Context context ;

    public NestedImageAdapter(ArrayList<Image> items, Context context)
    {
        this.items = items;
        this.context = context ;

    }



    @Override
    public ZeeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.nested_image_recycler_layout, parent, false);

        return new ZeeHolder(view);
    }

    @Override
    public void onBindViewHolder(NestedImageAdapter.ZeeHolder holder, final int position) {


            Image ind_image = items.get(position) ;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Glide.with(context)
                        .load(ind_image.getUri())
                        .into(holder.nested_image) ;
            } else {
                Glide.with(context)
                        .load(ind_image.getPath())
                        .into(holder.nested_image) ;
            }



    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ZeeHolder extends RecyclerView.ViewHolder {

        ImageView nested_image ;

        public ZeeHolder(View itemView) {
            super(itemView);

            nested_image = itemView.findViewById(R.id.nested_image) ;

        }

    }

}