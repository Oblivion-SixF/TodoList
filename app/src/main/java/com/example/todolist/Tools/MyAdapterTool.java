package com.example.todolist.Tools;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.example.todolist.Bean.TodoDataEntity;
import com.example.todolist.R;

import java.util.List;

public class MyAdapterTool extends BaseAdapter {

    private List<TodoDataEntity> mData;
    private LayoutInflater mInflater;

    public MyAdapterTool(LayoutInflater inflater,List<TodoDataEntity> data){
        mData = data;
        mInflater = inflater;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.todoview,null);
        TodoDataEntity todoDataEntity = mData.get(position);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView com = (TextView) view.findViewById(R.id.com);
        title.setText(todoDataEntity.getTitle());
        if (todoDataEntity.getCompleted()){
            title.setTextColor(Color.BLUE);
            com.setText("已完成");
            com.setTextColor(Color.BLUE);
        }else {
            title.setTextColor(Color.RED);
            com.setText("未完成");
            com.setTextColor(Color.RED);
        }
        return view;
    }
}
