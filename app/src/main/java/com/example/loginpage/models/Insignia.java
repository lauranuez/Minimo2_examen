package com.example.loginpage.models;

public class Insignia {
    private String name;
    private String url;

    public Insignia(String name, String url) {
        this.name = name;
        this.url = url;
    }
    public Insignia() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Insignia{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
