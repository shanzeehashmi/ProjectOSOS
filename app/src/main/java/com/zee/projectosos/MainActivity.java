package com.zee.projectosos;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;
import com.zee.projectosos.adapter.MainAdapter;
import com.zee.projectosos.model.basicDataType;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainAdapter.OnClickRecyclerListener {

    RecyclerView main_recylcer;
    MainAdapter adapter ;
    ArrayList<basicDataType> items_data  = new ArrayList<>() ;
    ArrayList<Image> images = new ArrayList<>();
    int pos_to_update = Integer.MIN_VALUE ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        main_recylcer = findViewById(R.id.main_recylcer) ;

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               addItems() ;
            }
        });


        adapter = new MainAdapter(items_data, MainActivity.this) ;
        main_recylcer.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false) ;
        main_recylcer.setLayoutManager(llm);
        main_recylcer.setAdapter(adapter);




    }

    @Override
    protected void onResume() {
        super.onResume();

        setListeners();
    }

    private void setListeners()
    {
        adapter.setOnClickRecyclerListener(this);

    }


    @Override
    public void onClickAddImage(int position) {

        pos_to_update = position ;

        ImagePicker.with(this)
                .setFolderMode(true)
                .setFolderTitle("Album")
                .setRootDirectoryName(Config.EXTRA_CONFIG)
                .setDirectoryName("OSOS")
                .setMultipleMode(true)
                .setShowNumberIndicator(true)
                .setMaxSize(20)
                .setLimitMessage("You can select up to 20 images")
                .setRequestCode(100)
                .start();




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Config.RC_PICK_IMAGES && resultCode == Activity.RESULT_OK && data != null) {
            ArrayList<Image> selected_image = data.getParcelableArrayListExtra(Config.EXTRA_IMAGES) ;

            if(selected_image != null)
            {
               images = selected_image ;
               items_data.get(pos_to_update).setImages(selected_image);
               adapter.notifyItemChanged(pos_to_update);

            }
            else
            {
                Toast.makeText(MainActivity.this,"Image Pick Error, Re-Attach Image", Toast.LENGTH_SHORT).show();
            }



        }

    }



    private void addItems()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edittext_layout,null) ;
        builder.setView(view) ;

        final EditText title_edit_text = view.findViewById(R.id.title_edit_text) ;


        builder.setCancelable(false);

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if(title_edit_text.getText().toString().trim().length()>0)
                {
                    items_data.add(new basicDataType(""+title_edit_text.getText().toString().trim(),null)) ;
                    adapter.notifyDataSetChanged();
                }
                else
                {
                    addItems();
                }


            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }



}


