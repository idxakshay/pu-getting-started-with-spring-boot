package com.idx.pu.dto;

public class StudentDto {

    private String regNo;
    private String name;

    private String mobile;

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public StudentDto(String regNo, String name, String mobile) {
        this.regNo = regNo;
        this.name = name;
        this.mobile = mobile;
    }
}
