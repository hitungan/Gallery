package com.example.library;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ImageHolder>{

    ArrayList<Tempeletes> tempelete;
    Context context;

    public RecyclerAdapter(ArrayList<Tempeletes> tempelete, Context context){
        this.tempelete=tempelete;
        this.context=context;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.template, viewGroup,false);

        ImageHolder img = new ImageHolder(view);

        return img;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder imageHolder, int i) {

        final Uri ur = tempelete.get(i).getUri();

        Glide.with(context).load(ur).placeholder(R.drawable.imgholder).centerCrop().into(imageHolder.gambar);


        imageHolder.gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = PindahLayout.newintent(context,ur);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tempelete.size();
    }

    public static class ImageHolder extends RecyclerView.ViewHolder{

        ImageView gambar;


        public ImageHolder(View itemView){
            super(itemView);
            gambar=itemView.findViewById(R.id.gambar1);

        }

    }

}
