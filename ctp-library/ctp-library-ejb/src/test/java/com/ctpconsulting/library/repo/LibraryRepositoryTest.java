package com.ctpconsulting.library.repo;

import com.ctpconsulting.library.model.CheckOut;
import com.ctpconsulting.util.test.InMemoryDatabaseTest;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author edewit
 */
public class LibraryRepositoryTest extends InMemoryDatabaseTest {

   private LibraryRepository libraryRepository;

   @Before
   public void setup() {
      libraryRepository = new LibraryRepository();
      libraryRepository.entityManager = entityManager;
   }

   @After
   public void cleanUpCheckOuts() {
      entityManager.getTransaction().begin();
      entityManager.createQuery("delete from CheckOut").executeUpdate();
      entityManager.getTransaction().commit();
   }

   @Test
   public void shouldBeAbleToCheckOutExistingBook() throws Exception {
      CheckOut checkOut = checkOutBook("9780596001582");
      assertEquals(new Long(1), checkOut.getBook().getId());
   }

   @Test
   public void shouldBeAbleToCheckOutNonExistingBook() throws Exception {
      CheckOut checkOut = checkOutBook("non-existing");
      assertEquals(new Long(2), checkOut.getBook().getId());
   }

   private CheckOut checkOutBook(String isbn) {
      entityManager.getTransaction().begin();
      CheckOut tmp = libraryRepository.checkOutBook(isbn, "edewit");
      entityManager.getTransaction().commit();
      CheckOut checkOut = entityManager.find(CheckOut.class, tmp.getId());
      assertNotNull(checkOut);
      assertNotNull(checkOut.getBook());
      return checkOut;
   }

   @Override
   public String getDataSet() {
      return "src/test/resources/testcase_checkout.xml";
   }
}
