/* Author: 
Stefan Malär
*/
	var booksList = new Array();

$(document).ready(function() {
	
	//initialize data tabel
	booksTable = $('#example').dataTable();
	
	//do some stuff with it
	booksTable.fnClearTable();

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
	
	alert(getBooks());
	booksRaw = parseXML(getBooks());
	
	$(booksRaw).each(function (k,v) {
		alert("1");
	 	$('#main').append('<div class="book2">'+v[0]+'</div>');
    });
    
        
	$.each(
		booksRaw,
		function( intIndex, row ){
 
			addRow(row);
	
		}
	);
     */

    getBooks();
    
} );




function addRow(row){
		booksTable.fnAddData([ "<a id='qwer' href='javascript:void(0);' onclick='addPopup(this,\""+row[1]+"\")'>"+ row[0] +"</a>",
		row[1],
		row[2] ]);
		booksList[row[1]] = row;
}


function addPopup(element,isbn){
	if($(".popup").length == 0){
		$("#content").append("<div id='"+isbn+"' class='popup'> Title: " 
			+ booksList[isbn][0] 
			+ "<br/>ISBN: "
			+ booksList[isbn][1]
			+ "<br/>Author: "
			+ booksList[isbn][2]
			+ "<br/>description: "
			+ booksList[isbn][3] 
			+ "<br/>state: "
			+ booksList[isbn][4]  
			+ "<br/>borrower: "
			+ booksList[isbn][5]  
			+"</div>");
		popup = $("#"+isbn);
		popup.css("left", $(element).offset().left).css("top",$(element).offset().top + 15);
		$(popup).click(function(){
			$(".popup").remove();
		});
	}
}



















