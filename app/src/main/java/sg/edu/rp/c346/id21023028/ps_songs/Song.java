package sg.edu.rp.c346.id21023028.ps_songs;

import androidx.annotation.NonNull;

public class Song {
    private int _id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song(String title, String singers, int year, int stars) {
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

    @NonNull
    @Override
    public String toString() {
        String msg = String.format("Song Title: %s\nSinger: %s\nYear: %4d\n%1d %5s",title,singers,year,stars,quickConvert(stars));
        return msg;
    }
}
