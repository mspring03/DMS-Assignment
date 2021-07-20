package com.example.dmsprojectforjava;

import com.google.gson.JsonObject;

import java.util.List;

public class ServerRequest {
    private List<JsonObject> posts;

    public List<JsonObject> getPosts() {
        return posts;
    }
}
