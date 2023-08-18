package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.news.model.newsApiResponse;
import com.example.news.model.newsHeadlines;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener , View.OnClickListener {
RecyclerView recyclerView;
customAdapter adapter;

ProgressDialog dailog;
Button b1,b2,b3,b4,b5,b6,b7;
SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dailog=new ProgressDialog(this);
        dailog.setTitle("Loading News");
        dailog.show();

        searchView=findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dailog.setTitle("Fetching news");
                dailog.show();
                RequestManager manager=new RequestManager(MainActivity.this);
                manager.getNewsHeadlines(listener,"general",query);
                closeKeyboard();
                searchView.clearFocus();
                return true;
            }

            private void closeKeyboard() {
                InputMethodManager inputMethodManager= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(searchView.getWindowToken(),0);
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

             b1=findViewById(R.id.b1);
             b1.setOnClickListener(this);
             b2=findViewById(R.id.b2);
             b2.setOnClickListener(this);
             b3=findViewById(R.id.b3);
        b3.setOnClickListener(this);
             b4=findViewById(R.id.b4);
        b4.setOnClickListener(this);
             b5=findViewById(R.id.b5);
        b5.setOnClickListener(this);
             b6=findViewById(R.id.b6);
        b6.setOnClickListener(this);
             b7=findViewById(R.id.b7);
        b7.setOnClickListener(this);


         RequestManager manager=new RequestManager(this);
         manager.getNewsHeadlines(listener,"general",null);
    }

    private final  onFetchDataListener<newsApiResponse> listener=new onFetchDataListener<newsApiResponse>() {
        @Override
        public void onFetchData(List<newsHeadlines> list, String message) {
            if(list.isEmpty()){
                Toast.makeText(MainActivity.this, "No data found!! ", Toast.LENGTH_SHORT).show();
            }
            showNews(list);
            dailog.dismiss();
        }

        @Override
        public void Error(String message) {
            Toast.makeText(MainActivity.this, "An error occurred!!", Toast.LENGTH_SHORT).show();
        }
    };

    private void showNews(List<newsHeadlines> list) {
     recyclerView=findViewById(R.id.recycler_main);
     recyclerView.setHasFixedSize(true);
     recyclerView.setLayoutManager(new GridLayoutManager(this,1));
     adapter=new customAdapter(this,list,this);
     recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(newsHeadlines headlines) {
         startActivity(new Intent(MainActivity.this,DetailsActivity.class)
                 .putExtra("data",headlines));
    }

    @Override
    public void onClick(View v) {

        Button button=(Button) v;
        String category=button.getText().toString();
         dailog.setTitle("Loading "+category+" news");
         dailog.show();
        RequestManager manager=new RequestManager(this);
        manager.getNewsHeadlines(listener,category,null);

    }
}