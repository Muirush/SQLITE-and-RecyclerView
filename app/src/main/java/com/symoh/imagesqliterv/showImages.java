package com.symoh.imagesqliterv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class showImages extends AppCompatActivity {
  private DatabaseHandler databaseHandler;
    private RecyclerView recyclerView;
    private RVAdapter rvAdapter;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_images);
        recyclerView = findViewById(R.id.imagesRv);
        databaseHandler = new DatabaseHandler(this);

        getData();



    }

    public void getData(){
        rvAdapter = new RVAdapter(databaseHandler.getAll());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rvAdapter);

    }
}
