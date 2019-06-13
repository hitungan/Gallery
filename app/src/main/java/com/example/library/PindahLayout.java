package com.example.library;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class PindahLayout extends AppCompatActivity {

    private static final String ARGS = "ARGS_Uri";

    public static Intent newintent(Context text, Uri uri){
        Intent intent = new Intent(text,PindahLayout.class);
        intent.putExtra(ARGS,uri.toString());

        return intent;
    }

    ImageView img;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layoutbaru);

        Uri uri = Uri.parse(getIntent().getStringExtra(ARGS));

        img = findViewById(R.id.bebas);
        Glide.with(this).load(uri).placeholder(R.drawable.imgholder).centerInside().into(img);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
