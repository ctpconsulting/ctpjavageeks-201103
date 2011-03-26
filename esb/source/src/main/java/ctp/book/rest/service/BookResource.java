package ctp.book.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ctp.book.rest.model.Author;
import ctp.book.rest.model.Book;

@Path("/ctp/booklib")
public class BookResource {
	
	@GET
	@Produces("text/xml")
	@Path("/book/{isbn}")
	public Book loadBook(@PathParam("isbn") String isbn){
		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle("test");
		book.setDescription("test");
		book.setAuthor(new Author());
		book.getAuthor().setName("Dan Brown");
		book.getAuthor().setTwitter("danbrown");
		return book;
	}
	
	@GET
	@Produces("text/xml")
	@Path("/books")
	public Book loadBooks(){
		Book book = new Book();
		book.setIsbn("123");
		book.setTitle("test");
		book.setDescription("test");
		book.setAuthor(new Author());
		book.getAuthor().setName("Dan Brown");
		book.getAuthor().setTwitter("danbrown");
		return book;
	}

}
