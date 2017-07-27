package dummy.popdesign.cllg_project;

/**
 * Created by ABHISHEK on 19-07-2017.
 */

public class Info_for_Main {
    String urlphoto;
    String name;
    String date;

    public Info_for_Main()
    {}
    public Info_for_Main(String urlphoto, String name, String date, String description, String uid, String photo, String likes, String urldp) {
        this.urlphoto = urlphoto;
        this.name = name;
        this.date = date;
        this.description = description;
        this.uid = uid;
        this.photo = photo;
        this.likes = likes;
        this.urldp = urldp;
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

    String uid;
    String photo;

    String likes;
    String urldp;

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

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getUrldp() {
        return urldp;
    }

    public void setUrldp(String urldp) {
        this.urldp = urldp;
    }
}
