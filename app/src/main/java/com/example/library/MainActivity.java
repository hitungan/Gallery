package com.example.library;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_EXTERNAL_STORAGE = 1;
    boolean isBasic;
    RecyclerView recycle;
    RecyclerAdapter adapter;
//    String path = "/Basic";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE }, REQUEST_CODE_EXTERNAL_STORAGE);

        recycle = findViewById(R.id.recyclerview);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,3);


        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(layoutManager);

        isBasic = true;


    }

    @Override
    protected void onStart() {
        super.onStart();
        updateFolder();
    }

    public ArrayList<Tempeletes> loadImage(String path){
        ArrayList<Tempeletes> tempelet = new ArrayList<>();

        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + path);

        if(folder.exists()){
            File imagefiles[] = folder.listFiles();

            for (File imagef : imagefiles) {
                Tempeletes temp = new Tempeletes();
                temp.setUri(Uri.fromFile(imagef));
                tempelet.add(temp);
            }

        }
        return tempelet;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem folderitem = menu.findItem(R.id.items);
        if(isBasic){
            folderitem.setTitle("Basic");
        }
        else {
            folderitem.setTitle("Advance");
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.items:
                isBasic = !isBasic;
                invalidateOptionsMenu();
                updateFolder();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void updateFolder(){
        String path ;
        if(isBasic){
            path = "/Basic";
        }
        else {
            path = "/Advance";
        }

        adapter = new RecyclerAdapter(loadImage(path),this);
        recycle.setAdapter(adapter);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull  String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_EXTERNAL_STORAGE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {

                finish();

            }

        }

    }
}
