package com.example.retrofit2;

public class recycleview_list
{
    private Integer image;
    private String text;
    private String cost;
    public String getText() {
        return text;
    }

    public recycleview_list(Integer image, String text,String cost) {
        this.image = image;
        this.text = text;
        this.cost=cost;
    }
    public recycleview_list(Integer image, String text) {
        this.image = image;
        this.text = text;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
