package me.tremor.Airglow_user.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import me.tremor.Airglow_user.utils.AppUtils;

public class Event implements Parcelable {

    private static int increment = 0;

    private int id;
    private int disco;//id disco
    private String title;
    private String photo_img;//URL immagine
    private String description;

    public Event() {
        disco = ++increment;
    }

    public Event(int id, int disco, String title, String photo_img, String description) {
        this.id = id;
        this.disco = disco;
        this.title = title;
        this.photo_img = photo_img;
        this.description = description;
    }

    protected Event(Parcel in) {
        disco = in.readInt();
        title = in.readString();
        description = in.readString();
        photo_img = in.readString();
       // source = in.readParcelable(Source.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(disco);

        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(photo_img);
        //dest.writeParcelable(source, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDisco() {
        return disco;
    }

    public void setDisco(int disco) {
        this.disco = disco;
    }

    public String getPhoto_img() {
        return photo_img;
    }

    public void setPhoto_img(String photo_img) {
        this.photo_img = photo_img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


   /* public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
*/

    public static DiffUtil.ItemCallback<Event> DIFF_CALLBACK = new DiffUtil.ItemCallback<Event>() {
        @Override
        public boolean areItemsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        Event event = (Event) obj;
        return event.disco == this.disco;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", disco=" + disco +
                ", title='" + title + '\'' +
                ", photo_img='" + photo_img + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}