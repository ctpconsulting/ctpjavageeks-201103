/*$(document).ready(function() {
   $('#submit').click(function() {*/
       /*alert("Hello world!");*/

function getBooks() {

$.ajaxSetup({
        beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "text/javascript,text/html,application/xml,text/xml,*/*");
        }
    });

	   $.ajax( {
			type:'Get',
			url:'http://thunder.local/test-data/books.xml',
			success:parseXML,
			error:function(data) {
				/*$('#result').html("ERROR");*/
				return "ERROR";
			}
	  });
}
  /*});
});*/


function parseXML(xml) {
	/*$(xml).find("tns:book").each(function(){
			$('#result').append($(this).find("tns:title").text());
		}
	)*/
	/*$('#result').append( $(xml).find('bookList').toArray());*/
	return $(xml).find('bookList').toArray();
	/*
	$(xml).find("book").each(function () {

        $('#result').append('<div class="book"><div class="title">' + $(this).find("title").text() + '</div><div class="description">' + $(this).find("description").text() + '</div>');
        
	 
    });
	
	
	$('#result').append($(xml).find('book').attr('cover'));
	$('#result').append('<br/ > end of list');*/
}
