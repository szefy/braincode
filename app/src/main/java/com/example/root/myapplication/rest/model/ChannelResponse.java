package com.example.root.myapplication.rest.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private String url;
    private int views;
    private int followers;
    private List<Map<String,String>> links;

    public List<Map<String, String>> getLinks() {
        return links;
    }

    public void setLinks(List<Map<String, String>> links) {
        this.links = links;
    }

    public boolean isMature() {
        return mature;
    }

    public void setMature(boolean mature) {
        this.mature = mature;
    }

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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBroadcaster_language() {
        return broadcaster_language;
    }

    public void setBroadcaster_language(String broadcaster_language) {
        this.broadcaster_language = broadcaster_language;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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
                ", url='" + url + '\'' +
                ", views=" + views +
                ", followers=" + followers +
                ", links=" + links +
                '}';
    }
}
