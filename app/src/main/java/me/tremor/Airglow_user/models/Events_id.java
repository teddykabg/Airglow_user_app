package me.tremor.Airglow_user.models;

import java.util.List;

public class Events_id {
    List<Integer> events;
    boolean has_next;
    String msg;
    int pages;
    List<Event> eventss;

    public List<Event> getEventss() {
        return eventss;
    }

    public void setEventss(List<Event> eventss) {
        this.eventss = eventss;
    }

    public List<Integer> getEvents() {
        return events;
    }

    public void setEvents(List<Integer> events) {
        this.events = events;
    }

    public boolean isHas_next() {
        return has_next;
    }

    public void setHas_next(boolean has_next) {
        this.has_next = has_next;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
