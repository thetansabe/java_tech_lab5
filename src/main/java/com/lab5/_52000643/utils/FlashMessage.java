package com.lab5._52000643.utils;

public class FlashMessage {
    private String type;
    private String intro;
    private String message;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FlashMessage(String type, String intro, String message) {
        this.type = type;
        this.intro = intro;
        this.message = message;
    }

    @Override
    public String toString() {
        return "FlashMessage{" +
                "type='" + type + '\'' +
                ", intro='" + intro + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
