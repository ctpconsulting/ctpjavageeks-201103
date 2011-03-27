package com.ctpconsulting.library;

import com.ctpconsulting.library.model.Book;
import com.ctpconsulting.library.repo.LibraryRepository;
import com.google.gdata.client.books.BooksService;
import com.google.gdata.client.books.VolumeQuery;
import com.google.gdata.data.books.VolumeEntry;
import com.google.gdata.data.books.VolumeFeed;
import com.google.gdata.data.dublincore.Title;
import com.google.gdata.util.AuthenticationException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * The Library Service is responsible for all modifications on the library. Searching with google data api and checking out
 * books with the LibraryRepository.
 *
 * @author edewit
 */
@Stateless
@WebService(endpointInterface = "com.ctpconsulting.library.Library")
@Remote(Library.class)
@Path("/library")  
@Produces("application/json")  
@Consumes("application/json")  
public class LibraryService implements Library {

   /**
    * The name of the server hosting the book GDATA feeds.
    */
   public static final String BOOKS_GDATA_SERVER = "http://books.google.com";

   /**
    * The URL of the volumes feed
    */
   public static final String VOLUMES_FEED = BOOKS_GDATA_SERVER
           + "/books/feeds/volumes";

   /**
    * The URL of the user library feeds
    */
   public static final String USER_LIBRARY_FEED = BOOKS_GDATA_SERVER
           + "/books/feeds/users/ctpgeek/collections/library/volumes";

   private BooksService service;

   @EJB
   LibraryRepository libraryRepository;

   @PostConstruct
   public void init() throws AuthenticationException {
      service = new BooksService("ctp-library");
      //looks kinda hardcoded :D
      service.setUserCredentials("ctpgeek", "ctp09geek");
   }

   /**
    * Get all of the books in the google books library from the ctpconsutling user.
    * @return all book that we have in our library
    * @throws Exception bad google
    */
   public List<VolumeEntry> getBooks() throws Exception {
      VolumeFeed volumeFeed = service.getFeed(new URL(USER_LIBRARY_FEED), VolumeFeed.class);
      return volumeFeed.getEntries();
   }

   /**
    * Search the ctp library, hosted on google, that contain the specified sarchTerm.
    * @param searchTerms the searchTerms to find
    * @return the list of Books or VolumeEntries that match the specified searchTerm
    * @throws Exception when something bad happened
    */
   public List<VolumeEntry> searchGoogle(String searchTerms) throws Exception {
      VolumeQuery query = new VolumeQuery(new URL(USER_LIBRARY_FEED));

      // exclude no-preview book (by default, they are included)
      query.setMinViewability(VolumeQuery.MinViewability.PARTIAL);
      query.setFullTextQuery(searchTerms);

      VolumeFeed volumeFeed = service.query(query, VolumeFeed.class);
      return volumeFeed.getEntries();
   }

   @GET
   @Path("{search}")
   @Override
   public List<Book> search(@PathParam("search")String searchTerms) throws Exception {
      List<VolumeEntry> result = searchGoogle(searchTerms);
      List<Book> books = new ArrayList<Book>();
      for (VolumeEntry book : result) {
         books.add(new Book(book.getIdentifiers().get(0).getValue()));
      }
      return books;
   }

   /**
    * Check out a book for a sepecific user.
    * @param isbn the isbn number of the book to checkout
    * @param user the user to register as the one that checkout the book
    */
   public void checkOutBook(String isbn, String user) {
      libraryRepository.checkOutBook(isbn, user);
   }
}
