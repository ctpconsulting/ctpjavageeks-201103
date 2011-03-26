package com.ctpconsulting.library.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author edewit
 */
@Entity
public class CheckOut implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @Temporal(TemporalType.DATE)
   @Column(name="checkedOutAt")
   private Date at = new Date();

   @ManyToOne(cascade = {CascadeType.REMOVE})
   @JoinColumn(name="bookId")
   private Book book;

   private String userName;

   protected CheckOut() {}

   public CheckOut(Book book, String userName) {
      this.book = book;
      this.userName = userName;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Date getAt() {
      return at;
   }

   public void setAt(Date at) {
      this.at = at;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public Book getBook() {
      return book;
   }

   public void setBook(Book book) {
      this.book = book;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (id != null ? id.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      if (!(object instanceof CheckOut)) {
         return false;
      }
      CheckOut other = (CheckOut) object;
      if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "com.ctpconsulting.library.model.CheckOut[id=" + id + "]";
   }

}
