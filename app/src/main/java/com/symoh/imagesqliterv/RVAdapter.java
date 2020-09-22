package com.symoh.imagesqliterv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVViewHolderClass> {
    ArrayList<ModelClass> modelClass;

    public RVAdapter(ArrayList<ModelClass> modelClass) {
        this.modelClass = modelClass;
    }

    @NonNull
    @Override
    public RVViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RVViewHolderClass(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolderClass holder, int position) {
        ModelClass modelClass1 = modelClass.get(position);
        holder.textViewTv.setText(modelClass1.getImageName());
        holder.imageViewTv.setImageBitmap(modelClass1.getImage());

    }

    @Override
    public int getItemCount() {
        return modelClass.size();
    }

    public static  class  RVViewHolderClass extends RecyclerView.ViewHolder{
        TextView textViewTv;
        ImageView imageViewTv;

        public RVViewHolderClass(@NonNull View itemView) {
            super(itemView);
            imageViewTv = itemView.findViewById(R.id.srImg);
            textViewTv = itemView.findViewById(R.id.imgTitle);
        }
    }
}
