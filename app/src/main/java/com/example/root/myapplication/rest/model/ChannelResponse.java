package com.example.root.myapplication.rest.model;

import android.util.Base64;

import java.util.Date;

/**
 * Created by jbis on 2015-03-13.
 */
public class ChannelResponse {

    private boolean mature;
    private String status;
    private String broadcaster_language;
    private String display_name;
    private String game;
    private int delay;
    private String language;
    private int _id;
    private String name;
    private Date created_at;
    private Date updated_at;
    private String logo;
    private String url;
    private int views;
    private int followers;

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getBroadcaster_language() {
        return broadcaster_language;
    }

    public void setBroadcaster_language(String broadcaster_language) {
        this.broadcaster_language = broadcaster_language;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isMature() {
        return mature;
    }

    public void setMature(boolean mature) {
        this.mature = mature;
    }

    @Override
    public String toString() {
        return "ChannelResponse{" +
                "mature=" + mature +
                ", status='" + status + '\'' +
                ", broadcaster_language='" + broadcaster_language + '\'' +
                ", display_name='" + display_name + '\'' +
                ", game='" + game + '\'' +
                ", delay=" + delay +
                ", language='" + language + '\'' +
                ", _id=" + _id +
                ", name='" + name + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", logo=" + logo +
                ", url='" + url + '\'' +
                ", views=" + views +
                ", followers=" + followers +
                '}';
    }
}
