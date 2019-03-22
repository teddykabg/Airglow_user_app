package me.tremor.Airglow_user.models;

import java.util.ArrayList;
import java.util.List;

public class ShortEvent {
    int id;
    Disco disco;
    String title;
    String photo_img;
    String description;
    List<ShortEvent> list;
    List<EntryTypes> entry_types;

    public List<EntryTypes> getEntry_types() {
        return entry_types;
    }

    public void setEntry_types(List<EntryTypes> entry_types) {
        this.entry_types = entry_types;
    }

    public List<ShortEvent> getList() {
        return list;
    }

    public void setList(List<ShortEvent> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ShortEvent{" +
                "id=" + id +
                ", disco='" + disco + '\'' +
                ", title='" + title + '\'' +
                ", photo_img='" + photo_img + '\'' +
                '}';

    }
    public List<ShortEvent> toList(ShortEvent sh){
        this.list= new ArrayList<>();
        this.list.add(sh);
        return this.list;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Disco getDisco() {
        return disco;
    }

    public void setDisco(Disco disco) {
        this.disco = disco;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto_img() {
        return photo_img;
    }

    public void setPhoto_img(String photo_img) {
        this.photo_img = photo_img;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
