package com.ctpconsulting;

import com.google.gdata.data.dublincore.Identifier;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author edewit
 */
@ManagedBean
@SessionScoped
public class PreviewBean {

   private List<Identifier> selectedBookIds;

   public String selectBook(List<Identifier> bookIds) {
      this.selectedBookIds = bookIds;
      return "/preview.xhtml";
   }

   public List<Identifier> getSelectedBookIds() {
      return selectedBookIds;
   }
}
