package com.example.intentshare;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.example.intentshare.Config.Config;

public class DoAdapter extends RecyclerView.Adapter<DoAdapter.ToDoHolder> {
    private Context mContext;
    private Cursor mCursor;

    public DoAdapter(Context context, Cursor cursor) {
        mContext=context;
        mCursor=cursor;
    }

    @NonNull
    @Override
    public ToDoHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        View raw=layoutInflater.inflate(R.layout.recycleviewlayout, parent,false);
        return new ToDoHolder(raw);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoHolder holder, int i) {
        if(!mCursor.moveToPosition(i)){return;}
        String task=mCursor.getString(mCursor.getColumnIndex(Config.DataEntry.column_Name));
        int hour=mCursor.getInt(mCursor.getColumnIndex(Config.DataEntry.column_Amount));
        long id=mCursor.getLong(mCursor.getColumnIndex(Config.DataEntry._ID));

        holder.taskText.setText(task);
        int sss=22;
        Log.i("a", String.valueOf(sss));
        holder.HourTxt.setText(String.valueOf(hour));
        holder.itemView.setTag(id);
        Log.i("a", "onCreate: asd4");

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
    public void swapCursor(Cursor nCursor)
    {
        if(mCursor!=null){mCursor.close();}
        mCursor=nCursor;
        if(nCursor!=null)
        {
            notifyDataSetChanged();
        }
    }

    public class ToDoHolder extends RecyclerView.ViewHolder {
        public TextView taskText,HourTxt;
        public ToDoHolder(@NonNull View itemView) {
            super(itemView);
           taskText= itemView.findViewById(R.id.tasktxt);
           HourTxt = itemView.findViewById(R.id.numhour);
        }
    }

}
