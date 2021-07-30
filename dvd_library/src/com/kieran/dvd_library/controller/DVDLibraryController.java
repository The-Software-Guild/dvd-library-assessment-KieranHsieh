package com.kieran.dvd_library.controller;

import com.kieran.dvd_library.dao.DVDLibraryDao;
import com.kieran.dvd_library.dto.DVD;
import com.kieran.dvd_library.ui.EMenuSelection;
import com.kieran.dvd_library.ui.UserIOException;
import com.kieran.dvd_library.view.DVDLibraryView;

import java.util.Collection;

/**
 * The controller layer for the DVD Library Application
 */
public class DVDLibraryController {
    /**
     * The view used by the controller
     */
    private DVDLibraryView view;
    /**
     * The data access object used by the controller
     */
    private DVDLibraryDao dao;

    /**
     * Constructs a new DVDLibraryController with a given view and data access type
     * @param view The view to use
     * @param dao The data access object to use
     */
    public DVDLibraryController(DVDLibraryView view, DVDLibraryDao dao) {
        this.view = view;
        this.dao = dao;
    }

    /**
     * Runs the DVDLibrary application
     * @throws UserIOException thrown when the application encounters unknown or invalid user input
     * @throws ControllerException thrown when the controller encounters unexpected behavior
     */
    public void run() throws UserIOException, ControllerException {
        // Populate the controller's DAO
        if(!this.dao.load()) {
            throw new ControllerException("Failed to load DAO");
        }

        // Main loop
        boolean finished = false;
        while(!finished) {
            // Query the user's selection
            EMenuSelection selection = awaitInputGetMenuSelection();
            switch (selection) {
                case ADD:
                    awaitInputAddDvd();
                    break;
                case EDIT:
                    awaitInputEditDvd();
                    break;
                case REMOVE:
                    awaitInputRemoveDvd();
                    break;
                case LIST_ALL:
                    awaitInputListDvds();
                    break;
                case GET:
                    awaitInputGetDvdInfo();
                    break;
                case EXIT:
                    finished = true;
                    break;
                default:
                    // Throw an exception upon unimplemented menu selections
                    throw new UnsupportedOperationException("Unrecognized command");
            }
        }

        // Make sure we are able to correctly save the application
        if(!dao.save()) {
            throw new ControllerException("Failed to save DAO");
        }
    }

    /**
     * Process the ADD menu selection.
     * This function will stall the application until it receives input
     * @throws UserIOException thrown when the user's provided input is invalid
     */
    private void awaitInputAddDvd() throws UserIOException {
        DVD target = view.awaitInputCreateDvd();
        dao.addDvd(target);
    }

    /**
     * Process the REMOVE menu selection.
     * This function will stall the application until it receives input
     * @throws UserIOException thrown when the user's provided input is invalid
     */
    private void awaitInputRemoveDvd() throws UserIOException {
        String dvdTitle = view.awaitInputGetDvdTitle();
        dao.removeDvd(dvdTitle);
    }

    /**
     * Process the EDIT menu selection.
     * This function will stall the application until it receives input
     * @throws UserIOException thrown when the user's provided input is invalid
     */
    private void awaitInputEditDvd() throws UserIOException {
        String title = view.awaitInputGetDvdTitle();
        DVD target = dao.getDvdInfo(title);
        view.awaitInputEditDvd(target);
    }

    /**
     * Process the LIST_ALL menu selection.
     * This function will stall the application until it receives input
     * @throws UserIOException thrown when the user's provided input is invalid
     */
    private void awaitInputListDvds() throws UserIOException {
        Collection<DVD> dvds = dao.getAllDvds();
        view.displayDvdCollection(dvds);
    }

    /**
     * Process the GET menu selection.
     * This function will stall the application until it receives input
     * @throws UserIOException thrown when the user's provided input is invalid
     */
    private void awaitInputGetDvdInfo() throws UserIOException {
        String title = view.awaitInputGetDvdTitle();
        DVD target = dao.getDvdInfo(title);
        view.displayDvd(target);
    }

    /**
     * Retrieves the user's input menu selection
     * @return An enumerated value representing the user's selection
     * @throws UserIOException thrown when the user's input is invalid
     */
    private EMenuSelection awaitInputGetMenuSelection() throws UserIOException {
        return view.awaitInputGetMenuSelection();
    }
}
