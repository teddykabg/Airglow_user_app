package me.tremor.Airglow_user.models;

public class Disco {
    String name;
    Float lat;
    Float lon;

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /* 'id': disco.id,
            'name': disco.name,
            'description': disco.description,
            'position': {
        'lat': disco.latitude,
                'lon': disco.longitude,
    },
            'address': disco.address,
            'logo': disco.logo,
            'parking': disco.parking,
            'smoking_area': disco.smoking_area,
            'wardrobe_capacity': disco.wardrobe_capacity,
            'average_user_volume': disco.average_user_volume,*/
}

