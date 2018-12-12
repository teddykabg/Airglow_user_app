package me.tremor.Airglow_user.models;

public class ShortEvent {
    int id;
    Disco disco;
    String title;
    String photo_img;
    String description;



    @Override
    public String toString() {
        return "ShortEvent{" +
                "id=" + id +
                ", disco='" + disco + '\'' +
                ", title='" + title + '\'' +
                ", photo_img='" + photo_img + '\'' +
                '}';
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
