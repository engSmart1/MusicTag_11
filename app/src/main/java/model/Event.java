package model;

import java.io.Serializable;

/**
 * Created by Hytham on 2/15/2017.
 */

public class Event implements Serializable {
    private static final long id = 1L;
    private String title;
    private String artist;
    private String headliner;
    private String venueName;
    private String city;
    private String country;
    private String street;
    private String postalCode;
    private String url;
    private String webSite;
    private String venueImage;
    private String bandImage;
    private String startDate;
    private String description;
    private String musicGenere;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getBandImage() {
        return bandImage;
    }

    public void setBandImage(String bandImage) {
        this.bandImage = bandImage;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeadliner() {
        return headliner;
    }

    public void setHeadliner(String headliner) {
        this.headliner = headliner;
    }

    public static long getId() {
        return id;
    }

    public String getMusicGenere() {
        return musicGenere;
    }

    public void setMusicGenere(String musicGenere) {
        this.musicGenere = musicGenere;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getVenueImage() {
        return venueImage;
    }

    public void setVenueImage(String venuImage) {
        this.venueImage = venuImage;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }
}
