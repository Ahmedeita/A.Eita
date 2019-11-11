package com.example.intentshare;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intentshare.Config.Config;
import com.example.intentshare.Config.DbHelper;

import java.sql.SQLData;

public class ToDoActivity extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    private DoAdapter mAdpter;
   TextView hourstxt;
   EditText taskText;
   int hours;
   Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        i = getIntent();
        Log.i("a", "onCreate: asd");
        DbHelper dbHelper=new DbHelper(ToDoActivity.this);
        mDatabase=dbHelper.getWritableDatabase();
        RecyclerView recyclerView=findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(ToDoActivity.this));
        mAdpter =new DoAdapter(this,getAllTasks());
        Log.i("a", "onCreate: asd22222");
        recyclerView.setAdapter(mAdpter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                removeItem((long)viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);

        hourstxt =findViewById(R.id.hoursTxt);
        Button btnplus = findViewById(R.id.hplus);
        Button btnmins = findViewById(R.id.hmin);
        Button btnadd = findViewById(R.id.addBtn);
        taskText=findViewById(R.id.editText);



        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increase();
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }


        });
        btnmins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrease();
            }
        });


    }




    private void addTask() {
        if(taskText.getText().toString().trim().length()==0)
        { return;}
        String task=taskText.getText().toString();
        ContentValues cv=new ContentValues();
        cv.put(Config.DataEntry.column_Name,task);
        cv.put(Config.DataEntry.column_Amount,hours);
        mDatabase.insert(Config.DataEntry.table_Name,null,cv);
        mAdpter.swapCursor(getAllTasks());
        taskText.getText().clear();

    }
    private void removeItem(long id) {
        mDatabase.delete(Config.DataEntry.table_Name, Config.DataEntry._ID+"+"+id,null);
        mAdpter.swapCursor(getAllTasks());
    }
    private Cursor getAllTasks()
    {
        return mDatabase.query(Config.DataEntry.table_Name,null,null,
                null,
                null,
                null,
                Config.DataEntry.column_Name);
    }
    public void increase() {
        if(hours<12){
            hours++;
        }else {
            hours=1;
        }
        hourstxt.setText(Integer.toString(hours));
       }

    public void decrease() {
        if(hours>1){
            hours--;
        }else {
            hours=12;
        }

        hourstxt.setText(Integer.toString(hours));
    }


}
