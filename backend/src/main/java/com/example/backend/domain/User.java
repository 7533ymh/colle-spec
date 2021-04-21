package com.example.backend.domain;

import lombok.Data;

@Data
public class User {

    private int idx;
    private String id;
    private String pass;
    private String name;
    private String college;
    private String major;
    private int grade;
    private String sex;
    private String mail;
    private String phone;
    private String objective; // 목표 직종
    private String enterprise; // 목표 기업
    private int link; // 연동 유무
    private int score;
    private String rank;

}
