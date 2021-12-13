package com.example.semesterProject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateGroup extends AppCompatActivity {

    int groupid = -1;
    EditText titleView;
    EditText memberView1;
    EditText memberView2;
    EditText memberView3;
    EditText memberView4;
    EditText memberView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);

        titleView = (EditText) findViewById(R.id.groupTitle);
        memberView1 = (EditText) findViewById(R.id.memberView1);
        memberView2 = (EditText) findViewById(R.id.memberView2);
        memberView3 = (EditText) findViewById(R.id.memberView3);
        memberView4 = (EditText) findViewById(R.id.memberView4);
        memberView5 = (EditText) findViewById(R.id.memberView5);

        Intent intent = getIntent();
        int intentGroupid = intent.getIntExtra("groupid", -1);
        groupid = intentGroupid;

        if(groupid != -1) {
            Group group = GroupFragment.groups.get(groupid);
            String groupContent = group.getContent();
            String groupTitle = group.getTitle();
            titleView.setText(groupTitle);

            String member;

            String[] splitGroup = groupContent.split(";");
            for(int i = 0; i < splitGroup.length; i++) {
                if(splitGroup[i].length() > 0) {

                    if(i == 0){
                        memberView1.setText(splitGroup[i].trim());
                    }
                    if(i == 1){
                        memberView2.setText(splitGroup[i].trim());
                    }
                    if(i == 2){
                        memberView3.setText(splitGroup[i].trim());
                    }
                    if(i == 3){
                        memberView4.setText(splitGroup[i].trim());
                    }
                    if(i == 4){
                        memberView5.setText(splitGroup[i].trim());
                    }

                }

            }


        }

        Button deleteButton = findViewById(R.id.deleteGroupButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFunction(view);
            }
        });


    }

    public void clickFunction(View view) {

        String members = "";

        if(memberView1.getText().toString() != null && memberView1.getText().toString().length() > 0) {
            members = members + memberView1.getText().toString() + "; ";
        }

        if(memberView2.getText().toString() != null && memberView2.getText().toString().length() > 0) {
            members = members + memberView2.getText().toString() + "; ";
        }

        if(memberView3.getText().toString() != null && memberView3.getText().toString().length() > 0) {
            members = members + memberView3.getText().toString() + "; ";
        }

        if(memberView4.getText().toString() != null && memberView4.getText().toString().length() > 0) {
            members = members + memberView4.getText().toString() + "; ";
        }

        if(memberView5.getText().toString() != null && memberView5.getText().toString().length() > 0) {
            members = members + memberView5.getText().toString() + "; ";
        }

        String content = members;
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("groups", Context.MODE_PRIVATE, null);

        groupsHelper groupHelper = new groupsHelper(sqLiteDatabase);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        String title;

        if(groupid == -1) {
            title = titleView.getText().toString();
            groupHelper.saveGroups(username, title, content);
        } else {
            title = titleView.getText().toString();
            groupHelper.updateGroup(title, content, username);
        }
        //goes to homepage
        Intent intent = new Intent(getApplicationContext(), appHome.class);
        startActivity(intent);
    }

    public void deleteFunction(View view) {
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("groups", Context.MODE_PRIVATE, null);

        groupsHelper groupHelper = new groupsHelper(sqLiteDatabase);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String title;

        title = titleView.getText().toString();
        groupHelper.deleteGroup(title, username);

        Intent intent = new Intent(getApplicationContext(), appHome.class);
        startActivity(intent);
    }

}