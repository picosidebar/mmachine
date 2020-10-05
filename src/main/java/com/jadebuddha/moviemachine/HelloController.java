package com.jadebuddha.moviemachine;

import org.apache.commons.text.similarity.FuzzyScore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class HelloController {

    public static final Logger LOG = LoggerFactory.getLogger(HelloController.class);

    private static final String TMDB_API_KEY = "5d717da2350d73952b7b54f6ad8db677";
    private static final String TMDB_API_URL = "https://api.themoviedb.org/3/search/movie?api_key="+TMDB_API_KEY+"&query=";
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz ";
    private static final String VIDEO_DIRECTORY = "/Volumes/video/testing";

    @Resource
    RestTemplate restTemplate;

    @RequestMapping("/")
    public List<Video> index() throws IOException, ParseException {

        return convertToVideo(getDirectoryList(VIDEO_DIRECTORY));
    }


    public List<String> getDirectoryList(String directory) {
        if(LOG.isWarnEnabled()) {
            LOG.warn(String.format("%n%n directory: %s%n", directory));
        }
        List<String> files = new ArrayList<>();
        final File folder = new File(directory);
        if(folder.exists()) {
            for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
                if(LOG.isWarnEnabled()) {
                    LOG.warn(String.format("%n%n FILE ENTRY: %s%n", fileEntry.getName()));
                }
                files.add(fileEntry.getName());
            }
        }
        return files;
    }

    public List<Video> convertToVideo(List<String> titles) throws IOException, ParseException {
        List<Video> videos = new ArrayList<>();
        for(String title : titles) {
            TMDBResponse tmdbResponse = lookUpVideo(title);
            if(tmdbResponse != null) {
                Result bestResult = findBestResult(tmdbResponse, title);
                Video bestVideo = new Video();
                bestVideo.setTitle(bestResult.getTitle());
                bestVideo.setYear(bestResult.getReleaseDate());
                bestVideo.setType(title);
                videos.add(bestVideo);
            }
        }
        return videos;
    }

    private Result findBestResult(TMDBResponse response, String title) throws ParseException {
        String searchTitle = parseSearchTitle(title);
        String searchYear = parseTitleYear(title);
        Result bestResult = null;
        double bestScore = 0.0;
        for (Result r : response.getResults()) {
            double fuzzyScore = new FuzzyScore(Locale.getDefault()).fuzzyScore(searchTitle, r.getTitle());
            // Exact match w/ year
            if(searchTitle.equals(r.getTitle()) && doesYearMatch(searchYear,r.getReleaseDate())) {
                return r;
            } else if(fuzzyScore > bestScore) {
                bestResult = r;
                bestScore = fuzzyScore;
            }
        }
        return bestResult;
    }

    public String parseSearchTitle(String title) {
        String searchTitle = "";
        if(title.contains("(")) {
            searchTitle = title.substring(0, title.indexOf("(") - 1).trim();
        } else {
            searchTitle = title;
        }

        return searchTitle;
    }

    private String parseTitleYear(String title) {
        String year = "";
        if(title.contains("(") && title.contains(")")) {
            year = title.substring(title.indexOf("(") + 1, title.indexOf(")"));
        }
        if(LOG.isWarnEnabled()) {
            LOG.warn("%parse year: %s%n",year);
        }
        return year;
    }

    public boolean doesYearMatch(String year, String tmdbDate) throws ParseException {
        Date releaseDate = new SimpleDateFormat("yyyy-mm-dd").parse(tmdbDate);
        boolean doesYearMatch = year.equals(String.valueOf(releaseDate.getYear()+1900));
        if(LOG.isWarnEnabled()) {
            LOG.warn("%ndoes year match: %s%n",doesYearMatch);
        }
        return doesYearMatch;
    }

    public TMDBResponse lookUpVideo(String title) throws IOException, ParseException {
        LOG.warn(String.format("%n%ntitle: %s%n", TMDB_API_URL + parseSearchTitle(title)));
        if("".equals(parseSearchTitle(title))) {
            return null;
        }
        TMDBResponse response = restTemplate.getForObject(TMDB_API_URL + parseSearchTitle(title), TMDBResponse.class);

        Result bestResult = null;
        String searchTitle = parseSearchTitle(title);
        String year = parseTitleYear(title);
        if(searchTitle == null || "".equals(searchTitle)) {
            return null;
        }

            if(LOG.isWarnEnabled()) {
                LOG.warn(String.format("%nyear: '%s'%n", year));
                LOG.warn(String.format("%nsearch titles: '%s'%n", searchTitle));
                LOG.warn(String.format("%nurl: %s%n", TMDB_API_URL + searchTitle));
            }

                if(response != null && response.getTotalResults() > 0) {
                    for(Result r : response.getResults()) {
                        if(doesYearMatch(year,r.getReleaseDate())) {
                            bestResult = r;
                            if(r.getTitle().equals(searchTitle)) {
                                bestResult = r;
                                break;
                            }
                        }

                    }
                    LOG.warn(String.format("%nresultCount: %s%n",TMDB_API_URL + response.getTotalResults()));
                }
                    Video video = new Video();
                if(bestResult != null) {
                    video.setTitle(bestResult.getTitle());
                    video.setYear(bestResult.getReleaseDate());
                    video.setType(title);
                }
        return response;
    }

}