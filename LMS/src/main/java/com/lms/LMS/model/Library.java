package com.lms.LMS.model;

import java.util.List;

public class Library {
    private String id;
    private String name;
    private List<Member> members;
    private List<ReadableItems> ReadableItems;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<ReadableItems> getReadableItems() {
        return ReadableItems;
    }

    public void setReadableItems(List<ReadableItems> readableItems) {
        ReadableItems = readableItems;
    }
}
