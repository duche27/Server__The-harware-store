package com.guille.hardware.store.models;

public enum Sections {

    SPORTS,
    GAMES,
    FORNITURE,
    TOYS,
    IRON,
    CLOTHING,
    SECURITY;

    public String section;

    Sections() {
        this.setSection(section);
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}