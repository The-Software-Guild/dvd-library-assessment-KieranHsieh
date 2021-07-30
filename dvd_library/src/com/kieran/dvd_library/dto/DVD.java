package com.kieran.dvd_library.dto;

import com.kieran.dvd_library.util.StringUtils;

/**
 * An abstraction for a DVD entry into the DVD Library
 */
public class DVD {
    /**
     * The title of the DVD
     */
    private String title;
    /**
     * The release date of the DVD
     */
    private String releaseDate;
    /**
     * The MPAA rating for the DVD
     */
    private String mpaaRating;
    /**
     * The name of the director of the DVD
     */
    private String directorName;
    /**
     * The name of the studio that created the DVD
     */
    private String studio;
    /**
     * The rating/notes given by the users
     */
    private String userRatingAndNote;
    /**
     * The minimum amount of space given for the DVD's title.
     * The title is allowed to exceed this length, but the resulting
     * output will not be formatted correctly
     */
    private static final int TITLE_STRING_LEN = 25;
    /**
     * The minimum amount of space given for the DVD's release date.
     * The release date is allowed to exceed this length, but the resulting
     * output will not be formatted correctly
     */
    private static final int RELEASE_DATE_STRING_LEN = 14;
    /**
     * The minimum amount of space given for the DVD's MPAA rating.
     * The MPAA rating is allowed to exceed this length, but the resulting
     * output will not be formatted correctly
     */
    private static final int MPAA_RATING_STRING_LEN = 14;
    /**
     * The minimum amount of space given for the DVD's director's name.
     * The director's name is allowed to exceed this length, but the resulting
     * output will not be formatted correctly
     */
    private static final int DIRECTOR_STRING_LEN = 20;
    /**
     * The minimum amount of space given for the DVD's studio's name.
     * The studio's name is allowed to exceed this length, but the resulting
     * output will not be formatted correctly
     */
    private static final int STUDIO_STRING_LEN = 15;

    /*
     * ACCESSORS
     */

    /**
     * Retrieves the title of the DVD
     * @return The title of the DVD
     */
    public String getTitle() {
        return this.title;
    }
    /**
     * Retrieves the release date of the DVD
     * @return The release date of the DVD
     */
    public String getReleaseDate() {
        return this.releaseDate;
    }
    /**
     * Retrieves the MPAA rating of the DVD
     * @return The MPAA rating of the DVD
     */
    public String getMpaaRating() {
        return this.mpaaRating;
    }
    /**
     * Retrieves the director's name of the DVD
     * @return The director's name of the DVD
     */
    public String getDirectorName() {
        return this.directorName;
    }
    /**
     * Retrieves the studio's name of the DVD
     * @return The studio's name of the DVD
     */
    public String getStudio() {
        return this.studio;
    }
    /**
     * Retrieves the user rating for the DVD
     * @return The user rating for the DVD
     */
    public String getUserRatingAndNote() {
        return this.userRatingAndNote;
    }

    /*
     * MUTATORS
     */

    /**
     * Sets the tile of the DVD
     * @param title The new title of the DVD
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Sets the release date of the DVD
     * @param releaseDate The new release date of the DVD
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    /**
     * Sets the MPAA rating of the DVD
     * @param mpaaRating The new MPAA rating of the DVD
     */
    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }
    /**
     * Sets the director name for the DVD
     * @param directorName The new director name for the DVD
     */
    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
    /**
     * Sets the studio name for the DVD
     * @param studioName The new studio name for the DVD
     */
    public void setStudio(String studioName) {
        this.studio = studioName;
    }
    /**
     * Sets the user rating for the DVD
     * @param userRatingAndNote The new user rating for the DVD
     */
    public void setUserRatingAndNote(String userRatingAndNote) {
        this.userRatingAndNote = userRatingAndNote;
    }

    /*
     * UTILITY
     */

    /**
     * Retrieves the string used to format DVDs in DVD's toString method
     *
     * The resulting string, when formatted will look like this (with extra white space depending on
     * the formatting constants):
     *
     * TITLE | RELEASE_DATE | MPAA_RATING | DIRECTOR_NAME | STUDIO_NAME | USER_RATING
     *
     * @return The string used to format DVDs
     */
    public static String getFormatString() {
        return StringUtils.buildLeftAlignedFormatStr(TITLE_STRING_LEN) + " | " +
                StringUtils.buildLeftAlignedFormatStr(RELEASE_DATE_STRING_LEN) + " | " +
                StringUtils.buildLeftAlignedFormatStr(MPAA_RATING_STRING_LEN) + " | " +
                StringUtils.buildLeftAlignedFormatStr(DIRECTOR_STRING_LEN) + " | " +
                StringUtils.buildLeftAlignedFormatStr(STUDIO_STRING_LEN) + " | " + "%s";
    }

    /**
     * Returns the minimum length of the returned String when toString is called
     * @return The minimum length of the returned String when toString
     */
    public static int getFormatStringFormattedLength() {
        return (TITLE_STRING_LEN + RELEASE_DATE_STRING_LEN + MPAA_RATING_STRING_LEN + DIRECTOR_STRING_LEN + STUDIO_STRING_LEN) +
                (3 * 5) + "User Rating/Note".length();
    }

    /**
     * Converts the DVD into a string
     * The resulting string depends on the implementation of DVD.getFormatString()
     * @return The DVD in a string format
     */
    @Override
    public String toString() {
        return String.format(getFormatString(),
                this.title, this.releaseDate, this.mpaaRating, this.directorName, this.studio, this.userRatingAndNote);
    }
}
