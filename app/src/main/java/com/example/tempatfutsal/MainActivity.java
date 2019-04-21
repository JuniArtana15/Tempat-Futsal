package com.example.tempatfutsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvCategory;
    private ArrayList<Futsal> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list.addAll(FutsalData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        ListFutsalAdapter listFutsalAdapter = new ListFutsalAdapter(this);
        listFutsalAdapter.setListFutsal(list);
        rvCategory.setAdapter(listFutsalAdapter);
        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedFutsal(list.get(position));
            }
        });
    }

    private void showSelectedFutsal(Futsal futsal){
        Toast.makeText(this, "Kamu memilih "+futsal.getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
        intent.putExtra("image_url", futsal.getPhoto());
        intent.putExtra("image_name", futsal.getName());
        intent.putExtra("image_description", futsal.getDescription());
        startActivity(intent);
    }

    private void showRecyclerGrid(){
        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        GridFutsalAdapter gridFutsalAdapter = new GridFutsalAdapter(this);
        gridFutsalAdapter.setListFutsal(list);
        rvCategory.setAdapter(gridFutsalAdapter);
        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedFutsal(list.get(position));
            }
        });
    }

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardViewFutsalAdapter cardViewFutsalAdapter = new CardViewFutsalAdapter(this);
        cardViewFutsalAdapter.setListFutsal(list);
        rvCategory.setAdapter(cardViewFutsalAdapter);
        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedFutsal(list.get(position));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_list:
                showRecyclerList();
                break;
            case R.id.action_grid:
                showRecyclerGrid();
                break;
            case R.id.action_cardview:
                showRecyclerCardView();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}