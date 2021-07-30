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
     * @throws ControllerException thrown when the controller encounters unexpected behavior
     */
    public void run() throws ControllerException {
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
                case NOOP:
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
     */
    private void awaitInputAddDvd() {
        try {
            DVD target = view.awaitInputCreateDvd();
            dao.addDvd(target);
        }
        catch(UserIOException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    /**
     * Process the REMOVE menu selection.
     * This function will stall the application until it receives input
     */
    private void awaitInputRemoveDvd() {
        try {
            String dvdTitle = view.awaitInputGetDvdTitle();
            if(!dao.removeDvd(dvdTitle)) {
                throw new UserIOException("Failed to remove " + dvdTitle + ": DVD does not exist in storage");
            }
        }
        catch(UserIOException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    /**
     * Process the EDIT menu selection.
     * This function will stall the application until it receives input
     */
    private void awaitInputEditDvd() {
        DVD target;
        try {
            String title = view.awaitInputGetDvdTitle();
            target = dao.getDvdInfo(title);
            view.awaitInputEditDvd(target);
            dao.removeDvd(title);
            dao.addDvd(target);
        }
        catch (UserIOException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    /**
     * Process the LIST_ALL menu selection.
     * This function will stall the application until it receives input
     */
    private void awaitInputListDvds() {
        Collection<DVD> dvds = dao.getAllDvds();
        if(dvds != null) {
            try {
                view.displayDvdCollection(dvds);
            }
            catch(UserIOException e) {
                view.displayErrorMessage(e.getMessage());
            }
        }
        else {
            view.displayErrorMessage("No DVDs in the library");
        }
    }

    /**
     * Process the GET menu selection.
     * This function will stall the application until it receives input
     */
    private void awaitInputGetDvdInfo() {
        DVD target;
        try {
            String title = view.awaitInputGetDvdTitle();
            target = dao.getDvdInfo(title);
            view.displayDvd(target);
        }
        catch(UserIOException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    /**
     * Retrieves the user's input menu selection
     * @return An enumerated value representing the user's selection
     */
    private EMenuSelection awaitInputGetMenuSelection() {
        try {
            return view.awaitInputGetMenuSelection();
        }
        catch(UserIOException e) {
            view.displayErrorMessage(e.getMessage());
            return EMenuSelection.NOOP;
        }
    }
}
