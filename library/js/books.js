/*$(document).ready(function() {
   $('#submit').click(function() {*/
       /*alert("Hello world!");*/

function getBooks() {

$.ajaxSetup({
        beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "text/javascript,text/html,application/xml,text/xml,*/*");
        }
    });

	 var result =  $.ajax( {
			type:'Get',
			url:'http://ctpgeeks.biteme.ch/test-data/books.xml',
			success:parseNewXML,
			error:parsePersistentXML
	  });
	  
	  //return result;
	  
}
  /*});
});*/


function parseNewXML(xml){
	
	localStorage["library.offline"] = xml;
	
	parseXML(xml);
}

function parsePersistentXML(xml){
	
	xml = localStorage["library.offline"];
	alert(xml);
	parseXML(xml);
}

function parseXML(xml) {
	/*$(xml).find("tns:book").each(function(){
			$('#result').append($(this).find("tns:title").text());
		}
	)*/
	/*$('#main').append( $(xml).find('bookList').toArray());*/
	/*
	 objectArray = new Array();
    	 $(xml).find('bookList').toArray().each(function(i){
      rowObj = new Object();
      rowObj.col1 = $(this).find('title').text();
      rowObj.col2 = $(this).find('isbn').text();
      rowObj.col3 = $(this).find('state').text();
      temp = new Array();
      temp = [
		"testit 1",
		"isbn123",
		".3" ,
		".4" ,
		".5"  ,
		".6" 
			];
			alert();
      objectArray.push(temp);
	});
	return objectArray;*/

	

	objectArray = new Array();
	
	$(xml).find("book").each(function (i) {

        //$('#main').append('<div class="book"><div class="title">' + $(this).find("title").text() + '</div><div class="description">' + $(this).find("description").text() + '</div>');

	 	$('#main').append('<div class="book2">'+objectArray+'</div>');
	 	temp = new Array();
	 	temp = [
		$(this).find("title").text(),
		$(this).find("isbn").text(),
		$(this).find("author").text() ,
		$(this).find("description").text(),
		$(this).find("state").text()  ,
		$(this).find("borrower").text() 
			] ;
		addRow(temp);
    });
    
	/*booksRaw = 	[[
		"testit 1",
		"isbn123",
		".3" ,
		".4" ,
		".5"  ,
		".6" 
			] ,[
		"testit 2",
		"isbn456",
		"2.3" ,
		"2.4" ,
		"2.5"  ,
		"2.6" 
			] ];
	return booksRaw;*/
	
	
	/*return $(xml).find('bookList').toArray();*/
	/*
	$(xml).find("book").each(function () {

        $('#result').append('<div class="book"><div class="title">' + $(this).find("title").text() + '</div><div class="description">' + $(this).find("description").text() + '</div>');
        
	 
    });
	
	
	$('#result').append($(xml).find('book').attr('cover'));
	$('#result').append('<br/ > end of list');*/
}
