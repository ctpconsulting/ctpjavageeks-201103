/* Author: 
Stefan Malär
*/

$(document).ready(function() {
	
	//initialize data tabel
	booksTable = $('#example').dataTable();
	
	//do some stuff with it
	booksTable.fnClearTable();
	booksTable.fnAddData( [[
		"<a id='asdf' href='#'>testit</a>",
		".2",
		".3" 
			],
			[
		"<a id='qwer' href='javascript:void(0);' onclick='add(this)'>testit 2</a>",
		".3",
		".34" 
			] ]);
	
	
} );


function add(element){
	$("#content").append("<div id='popup'>content</div>");
	popup = $("#popup");
	popup.css("left", $(element).offset().left).css("top",$(element).offset().top + 15);
	$(popup).click(function(){
		$("#popup").remove();
	});
}



















