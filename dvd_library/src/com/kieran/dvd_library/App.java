package com.kieran.dvd_library;

import com.kieran.dvd_library.controller.ControllerException;
import com.kieran.dvd_library.controller.DVDLibraryController;
import com.kieran.dvd_library.dao.DVDLibraryDao;
import com.kieran.dvd_library.dao.DVDLibraryFileImpl;
import com.kieran.dvd_library.dao.DVDLibraryStorage;
import com.kieran.dvd_library.ui.UserIO;
import com.kieran.dvd_library.ui.UserIOConsoleImpl;
import com.kieran.dvd_library.ui.UserIOException;
import com.kieran.dvd_library.view.DVDLibraryView;


/**
 * The entry point wrapper for the DVD Library application
 */
public class App {
    /**
     * The entry point for the DVD Library application
     * @param args The command line arguments passed in with the application. This value is ignored
     * @throws UserIOException thrown when invalid user input is detected or an error occurs when displaying output
     * @throws ControllerException thrown when the controller encounters an error
     */
    public static void main(String[] args) throws UserIOException, ControllerException {
        // Initialize dependencies
        UserIO io = new UserIOConsoleImpl();
        DVDLibraryDao dao = new DVDLibraryFileImpl(new DVDLibraryStorage(), "Test.txt");

        // Initialize the application view
        DVDLibraryView view = new DVDLibraryView(io);
        // Initialize the application controller
        DVDLibraryController controller = new DVDLibraryController(view, dao);

        // Start the application
        controller.run();
    }
}
