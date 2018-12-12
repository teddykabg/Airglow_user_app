package me.tremor.Airglow_user.models;

import java.util.List;

public class IdEvent {
    List<Integer> events;
    boolean has_next;//la pagina ha un successivo?
    int pages;//no.tot pagine

    public List<Integer> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return "IdEvent{" +
                "events=" + events +
                ", has_next=" + has_next +
                ", pages=" + pages +
                '}';
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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
