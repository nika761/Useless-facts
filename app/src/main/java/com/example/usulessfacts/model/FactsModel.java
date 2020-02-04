package com.example.usulessfacts.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FactsModel {

    @Expose
    @SerializedName("permalink")
    private String permalink;
    @Expose
    @SerializedName("language")
    private String language;
    @Expose
    @SerializedName("source_url")
    private String source_url;
    @Expose
    @SerializedName("source")
    private String source;
    @Expose
    @SerializedName("text")
    private String text;
    @Expose
    @SerializedName("id")
    private String id;

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
