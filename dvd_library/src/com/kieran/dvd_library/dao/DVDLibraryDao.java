package com.kieran.dvd_library.dao;

import com.kieran.dvd_library.dto.DVD;

import java.util.Collection;

/**
 * The data access object base class for the DVDLibrary application
 */
public abstract class DVDLibraryDao {
    /**
     * The underlying storage used by the DAO
     */
    protected DVDLibraryStorage storage;

    /**
     * Saves the DAO.
     * The behavior of this function is implementation defined
     * @return True if saving the DAO was successful, and false otherwise
     */
    public abstract boolean save();
    /**
     * Loads the DAO.
     *  The behavior of this function is implementation defined
     * @return True if loading the DAO was successful, and false otherwise
     */
    public abstract boolean load();

    /**
     * Constructs a new DVDLibraryDao with a given storage container
     * @param storage The storage container
     */
    public DVDLibraryDao(DVDLibraryStorage storage) {
        this.storage = storage;
    }

    /**
     * Adds a DVD to the DAO
     * @param dvd The DVD to add
     */
    public void addDvd(DVD dvd) {
        storage.addDvd(dvd);
    }

    /**
     * Removes a DVD from the DAO
     * @param title The title of the DVD to add
     */
    public void removeDvd(String title) {
        storage.removeDvd(title);
    }

    /**
     * Gets the DVD object from the DAO's storage given a title
     * @param title The Title of the DVD to retrieve
     * @return The retrieved object if it exists, or null otherwise
     */
    public DVD getDvdInfo(String title) {
        return storage.getDvd(title);
    }

    /**
     * Gets all DVDs contained in the DAO's storage
     * @return A collection of DVDs
     */
    public Collection<DVD> getAllDvds() {
        return storage.getAllDvds();
    }
}
