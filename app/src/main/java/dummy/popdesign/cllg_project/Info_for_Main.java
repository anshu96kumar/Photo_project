package dummy.popdesign.cllg_project;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ABHISHEK on 19-07-2017.
 */

public class Info_for_Main {
    String urlphoto;
    String name;
    String date;
    String key;
    String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    String uid;
    String photo;

    int likes;
    String urldp;
    Map<String,Boolean> LikedBy=new HashMap<>();

    public Map<String, Boolean> getLikedBy() {
        return LikedBy;
    }

    public void setLikedBy(Map<String, Boolean> likedBy) {
        LikedBy = likedBy;
    }

    public Info_for_Main()
    {}
    public Info_for_Main(String urlphoto, String name, String date, String description, String uid, String photo, int likes, String urldp) {
        this.urlphoto = urlphoto;
        this.name = name;
        this.date = date;
        this.description = description;
        this.uid = uid;
        this.photo = photo;
        this.likes = likes;
        this.urldp = urldp;
    }

    public Info_for_Main(String uid, String description, String photo, int likes,String key,String time) {
        this.description = description;
        this.uid = uid;
        this.photo = photo;
        this.likes = likes;
        this.key=key;
        this.time=time;
    }

    String description;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public String getUrlphoto() {
        return urlphoto;
    }

    public void setUrlphoto(String urlphoto) {
        this.urlphoto = urlphoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getUrldp() {
        return urldp;
    }

    public void setUrldp(String urldp) {
        this.urldp = urldp;
    }
}
