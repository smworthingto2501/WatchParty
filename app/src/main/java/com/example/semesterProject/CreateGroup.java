package com.example.semesterProject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateGroup extends AppCompatActivity {

    int groupid = -1;
    EditText titleView;
    EditText memberViewOne;
    EditText memberViewTwo;
    EditText memberViewThree;
    EditText memberViewFour;
    EditText memberViewFive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);

        titleView = (EditText) findViewById(R.id.groupTitle);
        memberViewOne = (EditText) findViewById(R.id.memberView1);
        memberViewTwo = (EditText) findViewById(R.id.memberView2);
        memberViewThree = (EditText) findViewById(R.id.memberView3);
        memberViewFour = (EditText) findViewById(R.id.memberView4);
        memberViewFive = (EditText) findViewById(R.id.memberView5);

        Intent intent = getIntent();
        int intentGroupid = intent.getIntExtra("groupid", -1);
        groupid = intentGroupid;

        if(groupid != -1) {
            Group group = GroupFragment.groups.get(groupid);
            String groupContent = group.getContent();
            String groupTitle = group.getTitle();
            titleView.setText(groupTitle);
        }


    }

    //@TODO
    //need way to add other members to group

    public void clickFunction(View view) {

        String members = "";

        if(memberViewOne.getText().toString() != null && memberViewOne.getText().toString().length() > 0) {
            members = members + memberViewOne.getText().toString() + "; ";
        }

        if(memberViewTwo.getText().toString() != null && memberViewTwo.getText().toString().length() > 0) {
            members = members + memberViewTwo.getText().toString() + "; ";
        }

        if(memberViewThree.getText().toString() != null && memberViewThree.getText().toString().length() > 0) {
            members = members + memberViewThree.getText().toString() + "; ";
        }

        if(memberViewFour.getText().toString() != null && memberViewFour.getText().toString().length() > 0) {
            members = members + memberViewFour.getText().toString() + "; ";
        }

        if(memberViewFive.getText().toString() != null && memberViewFive.getText().toString().length() > 0) {
            members = members + memberViewFive.getText().toString() + "; ";
        }

        String content = members;
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("groups", Context.MODE_PRIVATE, null);

        groupsHelper groupHelper = new groupsHelper(sqLiteDatabase);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        //String title = titleView.getText().toString();
        String title;
        //groupHelper.saveGroups(username, title, content);

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

}