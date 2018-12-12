package me.tremor.Airglow_user;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.List;
/*
class Owner{
    public int reputation;
    public long user_id;
    public String user_type;
    public int accept_rate;
    public String profile_image;
    public String display_name;
    public String link;
}

class Item{
    public Owner owner;
    public boolean is_accepted;
    public int score;
    public long last_activity_date;
    public long last_edit_date;
    public long creation_date;
    public long answer_id;
    public long question_id;
}

public class StackApiResponse {
    public List<Item> items;
    public boolean has_more;
    public int backoff;
    public int quota_max;
    public int quota_remaining;
}
*/

public class StackApiResponse {
    public List<Item> events;
    public boolean has_next;

    public StackApiResponse() {
        this.events=new ArrayList<>();
    }
}

class Item{
    public String title;
    public int id;

    public Item(String title, int id) {
        this.title = title;
        this.id = id;
    }
}
