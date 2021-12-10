package com.example.semesterProject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateGroup extends AppCompatActivity {

    int groupid = -1;
    EditText textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);

        textView2 = (EditText) findViewById(R.id.groupView);
        Intent intent = getIntent();
        int intentGroupid = intent.getIntExtra("groupid", -1);
        groupid = intentGroupid;

        if(groupid != -1) {
            Group group = GroupFragment.groups.get(groupid);
            String groupContent = group.getContent();
            textView2.setText(groupContent);
        }


    }

    //@TODO
    //need way to add other members to group

    public void clickFunction(View view) {

        String content = textView2.getText().toString();
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("groups", Context.MODE_PRIVATE, null);

        groupsHelper groupHelper = new groupsHelper(sqLiteDatabase);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        String title;

        if(groupid == -1) {
            title = "GROUP_" + (GroupFragment.groups.size() + 1);
            groupHelper.saveGroups(username, title, content);
        } else {
            title = "GROUP_" + (groupid + 1);
            groupHelper.updateGroup(title, content, username);
        }
        //goes to homepage
        Intent intent = new Intent(getApplicationContext(), appHome.class);
        startActivity(intent);
    }

}