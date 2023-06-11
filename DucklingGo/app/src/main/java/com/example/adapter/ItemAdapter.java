package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ducklinggo.R;
import com.example.models.ImageItem;
import com.example.sessions.PokemonSession;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<ImageItem> {

    ArrayList<ImageItem> im;

    public ItemAdapter(@NonNull Context context, ArrayList<ImageItem> imageItems) {
        super(context, R.layout.image_item, imageItems);
        im = imageItems;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ImageItem imageItem = getItem(position);

        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.image_item, parent, false);
        }

        ImageView imageView;
        TextView textView;

        imageView = view.findViewById(R.id.image_item_pokemon_image);
        textView = view.findViewById(R.id.image_item_pokemon_name);

        textView.setText(imageItem.getName());

        Picasso.get()
                .load(imageItem.getImageUrl())
                .into(imageView);



        return view;
    }


}
