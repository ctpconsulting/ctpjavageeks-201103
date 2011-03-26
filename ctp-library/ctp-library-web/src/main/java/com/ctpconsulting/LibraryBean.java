package com.ctpconsulting;

import com.ctpconsulting.library.Library;
import com.google.gdata.data.books.VolumeEntry;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * TODO Test and document.
 * @author edewit
 */
@ManagedBean
@ViewScoped
public class LibraryBean {

   @EJB
   private Library libraryService;

   private List<VolumeEntry> books;

   private String searchQuery;

   public String search() throws Exception {
      //books = libraryService.search(searchQuery);
      return null;
   }

   //TODO change user...
   public String checkOut(String isbn) {
      libraryService.checkOutBook(isbn, "edewit");
      return null;
   }

   public List<VolumeEntry> getBooks() throws Exception {
      if (books == null) {
         //books = libraryService.getBooks();
      }

      return books;
   }

   public void setSearchQuery(String searchQuery) {
      this.searchQuery = searchQuery;
   }

   public String getSearchQuery() {
      return searchQuery;
   }
}
