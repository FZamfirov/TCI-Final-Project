package Entities;

import java.util.ArrayList;

public class Music implements Item {

    private String artist;
    private Integer year;
    private String format;
    private String genre;
    private String name;


    public Music(){}


    @Override
    public String getGenre() {
        return genre;
    }

    @Override
    public String getFormat() {
        return format;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getYear() {
        return year;
    }

    @Override
    public void setGenre(String genre) {
        this.genre=genre;
    }

    @Override
    public void setFormat(String format) {
       this.format=format;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public void setYear(Integer year) {
       this.year=year;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

}
