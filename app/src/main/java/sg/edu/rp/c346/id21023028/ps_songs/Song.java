package sg.edu.rp.c346.id21023028.ps_songs;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Song implements Serializable {
    private int _id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song(int _id,String title, String singers, int year, int stars) {
        this._id = _id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getSingers() {
        return singers;
    }

    public int getYear() {
        return year;
    }

    public int getStars() {
        return stars;
    }

    private String quickConvert(int stars){
        if (stars >1){
            return "Stars";
        }
        else{
            return "Star";
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSingers(String singers) {
        this.singers = singers;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @NonNull
    @Override
    public String toString() {
        String msg = String.format("Song Title: %s\nSinger: %s\nYear: %4d\n%1d %5s",title,singers,year,stars,quickConvert(stars));
        return msg;
    }

    //L10 stars display method
    public String starsDisplay() {
        StringBuilder starBuilder = new StringBuilder();
        for (int i = 0; i < stars; i++) {
            starBuilder.append("* ");
        }
        return starBuilder.toString().trim();
    }


}
