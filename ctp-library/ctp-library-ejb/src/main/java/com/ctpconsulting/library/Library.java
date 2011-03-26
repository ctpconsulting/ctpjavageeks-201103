package com.ctpconsulting.library;

import com.ctpconsulting.library.model.Book;
import java.rmi.Remote;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author edewit
 */
@WebService
public interface Library extends Remote {

   /**
    * Check out a book for a sepecific user.
    * @param isbn the isbn number of the book to checkout
    * @param user the user to register as the one that checkout the book
    */
   void checkOutBook(String isbn, String user);

   /**
    * Get all of the books in the google books library from the ctpconsutling user.
    * @return all book that we have in our library
    * @throws Exception bad google
    */
   //List<VolumeEntry> getBooks() throws Exception;

   /**
    * Search the ctp library, hosted on google, that contain the specified sarchTerm.
    * @param searchTerms the searchTerms to find
    * @return the list of Books or VolumeEntries that match the specified searchTerm
    * @throws Exception when something bad happend
    */
   //List<VolumeEntry> search(String searchTerms) throws Exception;
   List<Book> search(String searchTerms) throws Exception;
}
