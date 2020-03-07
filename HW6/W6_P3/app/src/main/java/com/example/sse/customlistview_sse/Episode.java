package com.example.sse.customlistview_sse;

public class Episode {
    /**
     * Episode Class that contains all info of each ListView Item:
     *
     *     episodeImage
     *     episodeName;
     *     episodeDescriptions
     *     episodeRatings
     */

    private Integer episodeImage;
    private String episodeName;
    private String episodeDescriptions;
    private Float episodeRatings;

    public Episode(Integer episodeImage, String episodeName, String episodeDescriptions, Float episodeRatings) {
        this.episodeImage = episodeImage;
        this.episodeName = episodeName;
        this.episodeDescriptions = episodeDescriptions;
        this.episodeRatings = episodeRatings;
    }


    public Integer getEpisodeImage() {
        return episodeImage;
    }

    public void setEpisodeImage(Integer episodeImage) {
        this.episodeImage = episodeImage;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public String getEpisodeDescriptions() {
        return episodeDescriptions;
    }

    public void setEpisodeDescriptions(String episodeDescriptions) {
        this.episodeDescriptions = episodeDescriptions;
    }

    public Float getEpisodeRatings() {
        return episodeRatings;
    }

    public void setEpisodeRatings(Float episodeRatings) {
        this.episodeRatings = episodeRatings;
    }
}
