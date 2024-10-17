package com.example.springweb.requests.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Task {
    private long id;
    private long userId;
    private String name;
    private String description;
    private long updateAt;
    private long createAt;
    private boolean completed;
}
