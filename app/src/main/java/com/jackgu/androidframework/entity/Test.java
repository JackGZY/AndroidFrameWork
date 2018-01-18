package com.jackgu.androidframework.entity;

import java.util.List;

/**
 * Created by JACK-GU on 2018/1/12.
 */

public class Test {

    /**
     * title : The Basics - Networking
     * description : Your app fetched this from a remote endpoint!
     * movies : [{"title":"Star Wars","releaseYear":"1977"},{"title":"Back to the Future",
     * "releaseYear":"1985"},{"title":"The Matrix","releaseYear":"1999"},{"title":"Inception",
     * "releaseYear":"2010"},{"title":"Interstellar","releaseYear":"2014"}]
     */

    private String title;
    private String description;
    private List<MoviesBean> movies;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MoviesBean> getMovies() {
        return movies;
    }

    public void setMovies(List<MoviesBean> movies) {
        this.movies = movies;
    }

    public static class MoviesBean {
        /**
         * title : Star Wars
         * releaseYear : 1977
         */

        private String title;
        private String releaseYear;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getReleaseYear() {
            return releaseYear;
        }

        public void setReleaseYear(String releaseYear) {
            this.releaseYear = releaseYear;
        }
    }

    @Override
    public String toString() {
        return "Test{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", movies=" + movies +
                '}';
    }
}
