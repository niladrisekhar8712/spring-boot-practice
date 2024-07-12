package com.practice.articles.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.util.UUID;

public class Article {
    private final UUID id;
    @NonNull private final String title;
    @NonNull private final String body;

    public Article(@JsonProperty("id") UUID id,
                   @JsonProperty("title") String title,
                   @JsonProperty("body") String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }


    public UUID getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
