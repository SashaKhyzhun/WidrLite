package com.alexanderkhyzhun.widrlite.ui.adapters.models;

/**
 * @author Alexander Khyzhun
 * Created on 16 June, 2019
 * <p>
 * Should be in Java because service for real-time chatting has bugs with serialization,
 * didn't tried appent serialization on Kotlin to check it it will be working.
 * </p>
 */
public class MemberData {

    private String name;
    private String color;

    public MemberData(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public MemberData() {
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

}

