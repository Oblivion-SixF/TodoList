package com.example.todolist.Tools;


import com.example.todolist.Bean.TodoDataEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class SaveDataTool {

    public static void saveData(String string) throws JSONException {
        JSONArray jsonArray = new JSONArray(string);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int userId = jsonObject.getInt("userId");
            String title = jsonObject.getString("title");
            Boolean completed = jsonObject.getBoolean("completed");
            TodoDataEntity todoDataEntity = new TodoDataEntity(userId, title, completed);
            todoDataEntity.save();
        }

    }

    public static void resaveData(String string) throws JSONException {
        JSONArray jsonArray = new JSONArray(string);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int id = jsonObject.getInt("id");
            Boolean completed = jsonObject.getBoolean("completed");
            TodoDataEntity todoDataEntity = TodoDataEntity.findById(TodoDataEntity.class,id);
            todoDataEntity.setCompleted(completed);
            todoDataEntity.save();
        }
    }

}
