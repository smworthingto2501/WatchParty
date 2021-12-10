package com.example.semesterProject;

public class Group {

    private String username;
    private String title;
    private String content;

    public Group(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }

    public String getUsername(){
        return username;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

}
