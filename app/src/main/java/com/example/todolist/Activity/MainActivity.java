package com.example.todolist.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.todolist.Bean.TodoDataEntity;
import com.example.todolist.R;
import com.example.todolist.Tools.GetDataTool;
import com.example.todolist.Tools.MyAdapterTool;
import com.example.todolist.Tools.SaveDataTool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    Button searchButton,deleteButton;
    ListView todo;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchButton = findViewById(R.id.search_button);
        deleteButton = findViewById(R.id.delete_button);
        todo = findViewById(R.id.todo);
        text = findViewById(R.id.text);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TodoDataEntity.listAll(TodoDataEntity.class).isEmpty()){
                    showTodo();
                }else {
                    cachedThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                String string = GetDataTool.getSyn("https://jsonplaceholder.typicode.com/users/1/todos");
                                SaveDataTool.saveData(string);
                                todo.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        showTodo();
                                    }
                                });
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
        todo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int id1 = position;
                TodoDataEntity todoDataEntity = TodoDataEntity.findById(TodoDataEntity.class,id1+1);
                todoDataEntity.setCompleted(true);
                todoDataEntity.save();
                showTodo();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TodoDataEntity.listAll(TodoDataEntity.class).isEmpty()){
                    cachedThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                String string = GetDataTool.getSyn("https://jsonplaceholder.typicode.com/users/1/todos");
                                SaveDataTool.resaveData(string);
                                todo.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        showTodo();
                                    }
                                });
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }


    public void showTodo(){
        List<TodoDataEntity> todoData = TodoDataEntity.listAll(TodoDataEntity.class);
        System.out.println(todoData.size());
        LayoutInflater layoutInflater = getLayoutInflater();
        MyAdapterTool myAdapterTool = new MyAdapterTool(layoutInflater,todoData);
        todo.setAdapter(myAdapterTool);
    }
}
