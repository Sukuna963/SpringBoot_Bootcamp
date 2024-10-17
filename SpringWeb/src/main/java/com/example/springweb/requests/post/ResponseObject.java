package com.example.springweb.requests.post;

import lombok.Data;

@Data
public class ResponseObject {
    Task task;
    Error error;
    String status;
}
