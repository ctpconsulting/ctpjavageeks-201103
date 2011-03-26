package com.ctpconsulting.library.repo;

import com.ctpconsulting.library.model.Book;
import com.ctpconsulting.library.model.CheckOut;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Repository to query and persist CheckOut.
 * @author edewit
 */
@Stateless
public class LibraryRepository {

   @PersistenceContext(unitName="persistance")
   EntityManager entityManager;

   public CheckOut checkOutBook(String isbn, String userName) {
      Query query = entityManager.createNamedQuery("findBookByIsbn");
      query.setParameter("isbn", isbn);

      Book book = null;
      try {
         book = (Book) query.getSingleResult();
      } catch (NoResultException e) {
         book = new Book(isbn);
         entityManager.persist(book);
      }

      CheckOut checkOut = new CheckOut(book, userName);
      entityManager.persist(checkOut);
      return checkOut;
   }
}
