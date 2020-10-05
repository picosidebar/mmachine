package com.jadebuddha.moviemachine;

import java.util.ArrayList;

public class Movie {
    private String title;
    private String originaltitle;
    Ratings RatingsObject;
    private String userrating;
    private String top250;
    private String outline;
    private String plot;
    private String tagline;
    private String runtime;
    ArrayList< Object > thumb = new ArrayList < Object > ();
    Fanart FanartObject;
    private String mpaa;
    private String playcount;
    private String lastplayed;
    private String id;
    Uniqueid UniqueidObject;
    ArrayList < Object > genre = new ArrayList < Object > ();
    ArrayList < Object > country = new ArrayList < Object > ();
    Set SetObject;
    ArrayList < Object > credits = new ArrayList < Object > ();
    private String director;
    private String premiered;
    private String year;
    private String status;
    private String code;
    private String aired;
    private String studio;
    private String trailer;
    ArrayList < Object > actor = new ArrayList < Object > ();
    Resume ResumeObject;
    private String showlink;
    private String dateadded;


    // Getter Methods

    public String getTitle() {
        return title;
    }

    public String getOriginaltitle() {
        return originaltitle;
    }

    public Ratings getRatings() {
        return RatingsObject;
    }

    public String getUserrating() {
        return userrating;
    }

    public String getTop250() {
        return top250;
    }

    public String getOutline() {
        return outline;
    }

    public String getPlot() {
        return plot;
    }

    public String getTagline() {
        return tagline;
    }

    public String getRuntime() {
        return runtime;
    }

    public Fanart getFanart() {
        return FanartObject;
    }

    public String getMpaa() {
        return mpaa;
    }

    public String getPlaycount() {
        return playcount;
    }

    public String getLastplayed() {
        return lastplayed;
    }

    public String getId() {
        return id;
    }

    public Uniqueid getUniqueid() {
        return UniqueidObject;
    }

    public Set getSet() {
        return SetObject;
    }

    public String getDirector() {
        return director;
    }

    public String getPremiered() {
        return premiered;
    }

    public String getYear() {
        return year;
    }

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getAired() {
        return aired;
    }

    public String getStudio() {
        return studio;
    }

    public String getTrailer() {
        return trailer;
    }

    public Resume getResume() {
        return ResumeObject;
    }

    public String getShowlink() {
        return showlink;
    }

    public String getDateadded() {
        return dateadded;
    }

    // Setter Methods

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOriginaltitle(String originaltitle) {
        this.originaltitle = originaltitle;
    }

    public void setRatings(Ratings ratingsObject) {
        this.RatingsObject = ratingsObject;
    }

    public void setUserrating(String userrating) {
        this.userrating = userrating;
    }

    public void setTop250(String top250) {
        this.top250 = top250;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public void setFanart(Fanart fanartObject) {
        this.FanartObject = fanartObject;
    }

    public void setMpaa(String mpaa) {
        this.mpaa = mpaa;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    public void setLastplayed(String lastplayed) {
        this.lastplayed = lastplayed;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUniqueid(Uniqueid uniqueidObject) {
        this.UniqueidObject = uniqueidObject;
    }

    public void setSet(Set setObject) {
        this.SetObject = setObject;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setAired(String aired) {
        this.aired = aired;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public void setResume(Resume resumeObject) {
        this.ResumeObject = resumeObject;
    }

    public void setShowlink(String showlink) {
        this.showlink = showlink;
    }

    public void setDateadded(String dateadded) {
        this.dateadded = dateadded;
    }
}
