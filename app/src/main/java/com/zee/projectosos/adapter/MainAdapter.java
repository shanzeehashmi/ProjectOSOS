package com.zee.projectosos.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoanglam.imagepicker.model.Image;
import com.zee.projectosos.MainActivity;
import com.zee.projectosos.R;
import com.zee.projectosos.model.basicDataType;

import java.util.ArrayList;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ZeeHolder>{

    private ArrayList<basicDataType> items;
    Context context ;

    public MainAdapter(ArrayList<basicDataType> items, Context context)
    {
        this.items = items;
        this.context = context ;

    }



    @Override
    public ZeeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.main_recycler_layout, parent, false);

        return new ZeeHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ZeeHolder holder, final int position) {

        basicDataType data = items.get(position) ;
        ArrayList<Image> nested_images = data.getImages() ;

        holder.title.setText(data.getTitle());

     holder.add_nested_image.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(onClickRecyclerListener != null)
             {
                 onClickRecyclerListener.onClickAddImage(position);
             }
         }
     });

     if(nested_images != null && nested_images.size()>0)
     {
         NestedImageAdapter adapter ;
         adapter = new NestedImageAdapter(nested_images, context) ;
         holder.nested_recycler.setHasFixedSize(true);
         holder.nested_recycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
         holder.nested_recycler.setAdapter(adapter);
     }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ZeeHolder extends RecyclerView.ViewHolder {

        TextView title ;
        ImageView add_nested_image ;
        RecyclerView nested_recycler ;


        public ZeeHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title) ;
            add_nested_image = itemView.findViewById(R.id.add_nested_image) ;
            nested_recycler = itemView.findViewById(R.id.nested_recycler) ;


        }


    }

    private OnClickRecyclerListener onClickRecyclerListener ;

    public interface OnClickRecyclerListener
    {
        void onClickAddImage(int position) ;
    }


    public void setOnClickRecyclerListener(OnClickRecyclerListener onClickRecyclerListener) {
        this.onClickRecyclerListener = onClickRecyclerListener;
    }
}