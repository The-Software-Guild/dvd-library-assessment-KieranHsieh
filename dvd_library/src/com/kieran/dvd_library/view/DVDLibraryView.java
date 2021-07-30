package com.kieran.dvd_library.view;

import com.kieran.dvd_library.dto.DVD;
import com.kieran.dvd_library.ui.EMenuSelection;
import com.kieran.dvd_library.ui.UserIO;
import com.kieran.dvd_library.ui.UserIOException;

import java.util.Collection;

/**
 * The main view layer of the DVD Library application
 */
public class DVDLibraryView {
    /**
     * The UserIO interface used by the view
     */
    private UserIO userIO;
    /**
     * A constant array of enum values, accessible by index
     */
    private static final EMenuSelection[] SELECTION_VALUES = EMenuSelection.values();

    /**
     * Constructs a new DVDLibraryView object using the provided UserIO interface
     * @param userIO The UserIO implementation used by the view
     */
    public DVDLibraryView(UserIO userIO) {
        this.userIO = userIO;
    }

    /**
     * Displays an error message
     * @param msg The message to display
     */
    public void displayErrorMessage(String msg) {
        try {
            userIO.displayMessage(msg);
        }
        catch(UserIOException ignored) { }
    }
    /**
     * Displays information about a DVD, or an error message if the DVD could not be found
     * @param dvd The DVD to display information for
     * @throws UserIOException thrown when something goes wrong displaying output
     */
    public void displayDvd(DVD dvd) throws UserIOException {
        if(dvd == null) {
            userIO.displayMessage("DVD could not be found!");
            return;
        }
        String outMsg = String.format(DVD.getFormatString(),
                "Title", "Release Date", "MPAA Rating", "Director", "Studio", "User Rating/Note") +
                "\n" +
                getDisplayBanner('=') +
                "\n" +
                dvd.toString();
        userIO.displayMessage(outMsg);
    }

    /**
     * Displays information for a collection of DVDs
     * @param dvds The DVDs to display information for
     * @throws UserIOException thrown when something goes wrong displaying output
     */
    public void displayDvdCollection(Collection<DVD> dvds) throws UserIOException {
        StringBuilder combinedString = new StringBuilder();
        combinedString.append(String.format(DVD.getFormatString(),
                "Title", "Release Date", "MPAA Rating", "Director", "Studio", "User Rating/Note")).append("\n");
        combinedString.append(getDisplayBanner('=')).append("\n");
        for(DVD dvd : dvds) {
            combinedString.append(dvd.toString()).append("\n").append(getDisplayBanner('-')).append("\n");
        }
        userIO.displayMessage(combinedString.toString());
    }

    /**
     * Stalls the program until the user input's required fields for editing values of a DVD
     * @param dvd THe DVD to edit
     * @throws UserIOException thrown when something goes wrong displaying output or retrieving user input
     */
    public void awaitInputEditDvd(DVD dvd) throws UserIOException {
        awaitInputUpdateDvdValues(dvd);
    }

    /**
     * Stalls the program until the user inputs a DVD title
     * @return The title inputted by the user
     * @throws UserIOException thrown when something goes wrong retrieving user input
     */
    public String awaitInputGetDvdTitle() throws UserIOException {
        return userIO.getInputString("Enter the title of the DVD: ");
    }

    /**
     * Stalls the program until a user enters the required fields for creating a new DVD
     * @return The created DVD
     * @throws UserIOException thrown when something goes wrong displaying output or retrieving user input
     */
    public DVD awaitInputCreateDvd() throws UserIOException {
        DVD dvd = new DVD();
        awaitInputUpdateDvdValues(dvd);
        return dvd;
    }

    /**
     * Displays the library menu and retrieves the selection made by the user
     * @return The selection made by the user
     * @throws UserIOException thrown when something goes wrong displaying output or retrieving user input
     */
    public EMenuSelection awaitInputGetMenuSelection() throws UserIOException {
        String menuMsg = String.format("Welcome to the DVD Library:\n" +
                "1) %s\n" +
                "2) %s\n" +
                "3) %s\n" +
                "4) %s\n" +
                "5) %s\n" +
                "6) %s\n" +
                "Please choose an Option: ",
                "Add a DVD", "Remove a DVD", "Edit a DVD", "List all DVDs", "Find DVD", "Exit");
        userIO.displayMessage(menuMsg);
        try {
            int selection = userIO.getInputNumber(null).intValue();
            if(selection > 6 || selection < 1) {
                throw new ArrayIndexOutOfBoundsException("Invalid menu selection. Select a number in the range [1, 6]");
            }
            return SELECTION_VALUES[selection - 1];
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new UserIOException(e.getMessage());
        }
    }

    /**
     * Queries user input for the properties of a DVD and updates the given DVD with the inputted values
     * @param dvd The DVD to update
     * @throws UserIOException thrown when something goes wrong displaying output or retrieving user input
     */
    private void awaitInputUpdateDvdValues(DVD dvd) throws UserIOException {
        if(dvd == null) {
            displayErrorMessage("DVD not found");
            return;
        }
        dvd.setTitle(userIO.getInputString("Enter the title of the DVD: "));
        dvd.setReleaseDate(userIO.getInputString("Enter the release date for the DVD: "));
        dvd.setDirectorName(userIO.getInputString("Enter the name of the director for the DVD: "));
        dvd.setStudio(userIO.getInputString("Enter the studio name of the DVD: "));
        dvd.setMpaaRating(userIO.getInputString("Enter the MPAA rating for the DVD: "));
        dvd.setUserRatingAndNote(userIO.getInputString("Enter the user rating/note for the DVD: "));
    }

    /**
     * Generates a banner of length DVD.getFormatStringFormattedLength(),
     * using bannerContents a the contents of the banner.
     * Ex. bannerContents = '-', length = 4, banner = "----"
     * @param bannerContents The contents of the banner
     * @return The generated banner
     */
    private String getDisplayBanner(char bannerContents) {
        return String.valueOf(bannerContents).repeat(Math.max(0, DVD.getFormatStringFormattedLength()));
    }
}
