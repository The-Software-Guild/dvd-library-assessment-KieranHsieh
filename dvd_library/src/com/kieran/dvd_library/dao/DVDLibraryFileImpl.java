package com.kieran.dvd_library.dao;

import com.kieran.dvd_library.dto.DVD;

import java.io.*;
import java.util.Scanner;

/**
 * An implementation of DVDLibraryDAO that saves and loads
 * from a specified file
 */
public class DVDLibraryFileImpl extends DVDLibraryDao {
    /**
     * The file outputted to by the DVDLibraryDAO
     */
    private String outputFile;
    /**
     * The delimiter used during DVD serialization
     *
     * This value is "::" unless otherwise specified in DVDLibraryFileImpl's constructor
     */
    private String delimiter;
    /**
     * An index into a streamified DVD serialized string representing the DVD's title
     */
    private static final int TITLE_IDX = 0;
    /**
     * An index into a streamified DVD serialized string representing the DVD's release date
     */
    private static final int RELEASE_DATE_IDX = 1;
    /**
     * An index into a streamified DVD serialized string representing the DVD's MPAA rating
     */
    private static final int MPAA_RATING_IDX = 2;
    /**
     * An index into a streamified DVD serialized string representing the DVD's director's name
     */
    private static final int DIRECTOR_NAME_IDX = 3;
    /**
     * An index into a streamified DVD serialized string representing the DVD's studio's name
     */
    private static final int STUDIO_NAME_IDX = 4;
    /**
     * An index into a streamified DVD serialized string representing the DVD's user rating/note
     */
    private static final int USER_RATING_IDX = 5;
    /**
     * Constructs a new DVDLibraryFileImpl with a specified storage container and output file
     * @param storage The storage container to use
     * @param outputFile The target file that the DAO saves to and loads from
     */
    public DVDLibraryFileImpl(DVDLibraryStorage storage, String outputFile) {
        super(storage);
        this.outputFile = outputFile;
        this.delimiter = "::";
    }

    /**
     * Constructs a new DVDLibraryFileImpl with a specified storage container, output file, and delimiter
     * @param storage The storage container to use
     * @param outputFile The target file that the DAO saves to and loads from
     * @param delimiter The delimiter used when serializing/deserializing DVDs
     */
    public DVDLibraryFileImpl(DVDLibraryStorage storage, String outputFile, String delimiter) {
        super(storage);
        this.outputFile = outputFile;
        this.delimiter = delimiter;
    }

    /**
     * Processes a DVD object into a serialized String format.
     *
     * The resulting string is in the format
     * TITLEdelimiterRELEASE_DATEdelimiterMPAA_RATINGdelimiterDIRECTOR_NAMEdelimiterSTUDIO_NAMEdelimiterUSER_RATING
     * @param dvd The DVD to stringify
     * @return The DVD in a serializable string format
     */
    private String getSerializedDvdStr(DVD dvd) {
        return dvd.getTitle() + delimiter +
                dvd.getReleaseDate() + delimiter +
                dvd.getMpaaRating() + delimiter +
                dvd.getDirectorName() + delimiter +
                dvd.getStudio() + delimiter +
                dvd.getUserRatingAndNote();
    }

    /**
     * Processes a String and builds a resulting DVD object from it's contents
     * @param serializedStr The String to build a DVD from
     * @return The resulting DVD object
     */
    private DVD getDeserializedDvd(String serializedStr) {
        DVD dvd = new DVD();
        // Get tokens from serialized string
        String[] tokens = serializedStr.split(delimiter);

        // Set the DVD's values
        dvd.setTitle(tokens[TITLE_IDX]);
        dvd.setReleaseDate(tokens[RELEASE_DATE_IDX]);
        dvd.setMpaaRating(tokens[MPAA_RATING_IDX]);
        dvd.setDirectorName(tokens[DIRECTOR_NAME_IDX]);
        dvd.setStudio(tokens[STUDIO_NAME_IDX]);
        dvd.setUserRatingAndNote(tokens[USER_RATING_IDX]);
        return dvd;
    }

    /**
     * Save's the DVDLibraryFileImpl's storage container into an output file
     * @return True if saving was successful, and false otherwise
     */
    @Override
    public boolean save() {
        PrintWriter writer;
        try {
            writer = new PrintWriter(new FileWriter(outputFile));
        }
        // Something went wrong and we cannot open/modify the file
        catch(IOException e) {
            return false;
        }

        // Write storage to file
        for(DVD dvd : super.storage) {
            String serializedDvdStr = getSerializedDvdStr(dvd);
            writer.println(serializedDvdStr);
        }
        // Clean up
        writer.flush();
        writer.close();
        return true;
    }

    /**
     * Populates the DAO by reading from it's target output file.
     * @return True if loading from the File was successful, and false otherwise
     */
    @Override
    public boolean load() {
        // Try to open the file
        Scanner fileReader;
        try {
            fileReader = new Scanner(new BufferedReader(new FileReader(outputFile)));
        }
        catch(IOException e) {
            return false;
        }

        // Read DVDs from the file
        String curLine;
        DVD curDvd;
        while(fileReader.hasNextLine()) {
            curLine = fileReader.nextLine();
            curDvd = getDeserializedDvd(curLine);
            storage.addDvd(curDvd);
        }
        return true;
    }
}
