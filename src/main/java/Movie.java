public class Movie {

    private int id;
    private String link;
    private String movieName;
    private String director;
    private int year;
    private int jumpScareCount;

    public Movie(int id, String link, String movieName, String director, int year, int jumpScareCount) {
        this.id = id;
        this.link = link;
        this.movieName = movieName;
        this.director = director;
        this.year = year;
        this.jumpScareCount = jumpScareCount;
    }

    public int getId() {
        return id;
    }
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getJumpScareCount() {
        return jumpScareCount;
    }

    public void setJumpScareCount(int jumpScareCount) {
        this.jumpScareCount = jumpScareCount;
    }
}
