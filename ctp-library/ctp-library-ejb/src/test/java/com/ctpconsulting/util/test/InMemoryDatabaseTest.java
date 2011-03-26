package com.ctpconsulting.util.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;

/**
 * Special abstract test that does in memory database testing, so as not to
 * "test on iron".
 */
public abstract class InMemoryDatabaseTest {

   protected static EntityManager entityManager;

   private static DatabaseConnection databaseConnection;

   /**
    * Override this method to import a dataset.
    *
    * @return The dataset to import.
    */
   public abstract String getDataSet();

   /**
    * Setup the test by creating the database. For each test class this is done
    * again, so as to not leave stale data, and be able to use a different
    * testset per class.
    *
    * @throws Exception
    *             If something went wrong.
    */
   @Before
   public void setUpTest() throws Exception {
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      if (databaseConnection == null) {
         databaseConnection = setupDatabase();

      }
      createEntityManager();
      fillDatabase(databaseConnection, getDataSet());
   }

   /**
    * Drops an XML file to the filesystem after each testmethod has executed,
    * useful in debugging situations.
    *
    * @throws Exception
    *             If something went wrong.
    */
   @After
   public void dropDataToXML() throws Exception {
      IDataSet fullDataSet = databaseConnection.createDataSet();
      FlatXmlDataSet.write(fullDataSet, new FileOutputStream("target/" + this.getClass().getName() + ".xml"));
   }

   /**
    * Setup the database.
    *
    * @return A DatabaseConnection to the schema.
    * @throws IOException
    *             If the insert script could not be loaded.
    * @throws SQLException
    *             If the something went wrong inserting into the database.
    */
   protected DatabaseConnection setupDatabase()
           throws IOException, SQLException, DatabaseUnitException {
      DatabaseConnection con = new DatabaseConnection(DriverManager.getConnection("jdbc:derby:test;create=true", "sa", ""));
      // used to avoid problems with boolean
      con.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new DefaultDataTypeFactory());
      return con;

   }

   /**
    * Fills the database.
    *
    * @param databaseConnection
    *            the database connection
    * @param insertScript
    *            the insertScript
    * @throws java.io.IOException
    *             If the database could not be filled.
    * @throws java.sql.SQLException
    *             If something went wrong inserting into the database.
    * @throws org.dbunit.DatabaseUnitException
    *             If something went wrong with the database.
    */
   private void fillDatabase(DatabaseConnection databaseConnection, String insertScript) throws IOException, SQLException,
           DatabaseUnitException {
      if (insertScript != null) {
         FlatXmlDataSet fx = new FlatXmlDataSet(new File(insertScript));
         DatabaseOperation.DELETE_ALL.execute(databaseConnection, fx);
      }
   }

   /**
    * Creates the entityManager
    */
   private void createEntityManager() {
      if (entityManager == null) {
         try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("inMemory");
            entityManager = factory.createEntityManager();

         } catch (Exception ex) {
            ex.printStackTrace();
         }
      }
   }
}
