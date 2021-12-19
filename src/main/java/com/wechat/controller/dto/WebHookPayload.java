package com.wechat.controller.dto;


import lombok.Data;

@Data
public class WebHookPayload {
    HeadCommit head_commit;

    @Override
    public String toString() {
        return head_commit.getAuthor().getName() + "更新了题目: " + head_commit.getMessage();
    }
}
