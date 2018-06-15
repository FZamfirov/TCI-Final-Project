package Entities;

public interface Item {

    public String getGenre();
    public String getFormat();
    public String getName();
    public Integer getYear();

    public void  setGenre(String genre);
    public void setFormat(String format);
    public void setName(String name);
    public void setYear(Integer year);
}


