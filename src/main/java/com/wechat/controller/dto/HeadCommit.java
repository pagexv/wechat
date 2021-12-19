package com.wechat.controller.dto;

import lombok.Data;

@Data
public class HeadCommit {
    String id;
    String message;
    Author author;
    Author committer;

}
