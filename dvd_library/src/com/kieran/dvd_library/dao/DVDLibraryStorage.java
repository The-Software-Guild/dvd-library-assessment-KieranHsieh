package com.kieran.dvd_library.dao;

import com.kieran.dvd_library.dto.DVD;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * A container which store's DVDs
 */
public class DVDLibraryStorage implements Iterable<DVD> {
    /**
     * The underlying storage container for the DVDLibraryStorage object
     */
    private HashMap<String, DVD> library = new HashMap<>();

    /**
     * Constructs a new DVDLibraryStorage object with no elements
     */
    public DVDLibraryStorage() { }

    /**
     * Constructs a DVDLibraryStorage object given a list of DVDs
     * @param dvds The DVDs used to populate the storage's internal container
     */
    public DVDLibraryStorage(List<DVD> dvds) {
        for(DVD dvd : dvds) {
            library.put(dvd.getTitle(), dvd);
        }
    }

    /**
     * Constructs a DVDLibraryStorage object given a collection of DVDs
     * @param dvds The DVDs used to populate the storage's internal container
     */
    public DVDLibraryStorage(Collection<DVD> dvds) {
        for(DVD dvd : dvds) {
            library.put(dvd.getTitle(), dvd);
        }
    }

    /**
     * Checks if the storage is empty
     * @return True if the storage contains no elements, and false otherwise
     */
    public boolean isEmpty() {
        return library.isEmpty();
    }

    /**
     * Adds a DVD to the storage
     * @param dvd The DVD to add
     */
    public void addDvd(DVD dvd) {
        if(dvd != null) {
            library.put(dvd.getTitle(), dvd);
        }
    }

    /**
     * Removes a DVD from storage
     * @param title The Title of the DVD to remove
     * @return If the title was present in the storage
     */
    public boolean removeDvd(String title) {
        return library.remove(title) != null;
    }

    /**
     * Retrieves a DVD from storage
     * @param title The title of the DVD to retrieve
     * @return The DVD object if it was found, and null if it was not
     */
    public DVD getDvd(String title) {
        return library.get(title);
    }

    /**
     * Retrieves all DVDs contained in the storage
     * @return A collection of DVDs owned by the storage
     */
    public Collection<DVD> getAllDvds() {
        return library.values();
    }

    /**
     * Constructs an iterator over the DVDLibraryStorage's DVD elements
     * @return The constructed iterator
     */
    @Override
    public Iterator<DVD> iterator() {
        return library.values().iterator();
    }
}
