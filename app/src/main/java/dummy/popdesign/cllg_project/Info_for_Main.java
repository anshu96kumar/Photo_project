package dummy.popdesign.cllg_project;

/**
 * Created by ABHISHEK on 19-07-2017.
 */

public class Info_for_Main {
    String urlphoto;
    String name;
    String date;
    String description;

    public Info_for_Main(String name, String date, String description, String likes) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.likes = likes;
    }

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
