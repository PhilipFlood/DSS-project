var tableexists = false;
var selectedLibraryID;
var selectedPlaylistID;
var selectedTrackID;
var selectedRow;
var renamechoice;

//upload library
function upload() {
	clearTable();
	var filename = document.getElementById("filepath").value;
	var username = localStorage.getItem("username")
	var restUrl = "rest/query/persist/" + username + "/" + filename;
	$.ajax({
        'async': false,
        'global': false,
        'url': restUrl,
        'dataType': "json",
        'success': function (data) {
            alert("your library has been uploaded")
        }
    });	
}

//get user that's logged in
function admincheck(){
	var uname = localStorage.getItem("username")
	document.getElementById('loggedin').innerHTML = uname
	if(uname == ""){
		window.location.assign("login.html");
	}
}

//navigation 
function clearMain(){
	clearTable();
	document.getElementById("TableTitle").style.visibility = "hidden"
	$('#uploadData').addClass('notSearch');
	$('#librarybtns').addClass('notSearch');
	$('#playlistbtns').addClass('notSearch');
	$('#trackbtns').addClass('notSearch');
	$('#rename').addClass('notSearch');
	$('#copyplaylist').addClass('notSearch');
}
function DatasetButton() {
	clearTable();
	$('#uploadData').removeClass('notSearch');
	$('#librarybtns').addClass('notSearch');
	$('#playlistbtns').addClass('notSearch');
	$('#trackbtns').addClass('notSearch');
	$('#rename').addClass('notSearch');
	$('#copyplaylist').addClass('notSearch');
}
function librarybtn(){
	$('#librarybtns').removeClass('notSearch');
	$('#playlistbtns').addClass('notSearch');
	$('#trackbtns').addClass('notSearch');
	$('#rename').addClass('notSearch');
	$('#copyplaylist').addClass('notSearch');
}
function playlistbtn(){
	$('#librarybtns').addClass('notSearch');
	$('#playlistbtns').removeClass('notSearch');
	$('#trackbtns').addClass('notSearch');
	$('#rename').addClass('notSearch');
	$('#copyplaylist').addClass('notSearch');
}
function trackbtn(){
	$('#librarybtns').addClass('notSearch');
	$('#playlistbtns').addClass('notSearch');
	$('#trackbtns').removeClass('notSearch');
	$('#rename').addClass('notSearch');
	$('#copyplaylist').addClass('notSearch');
}
function renamebtn(type){
	renamechoice = type
	$('#rename').removeClass('notSearch');
	$('#copyplaylist').addClass('notSearch');
}
function copybtn(type){
	playlistarr = getavailableplaylists();
	$("#allplaylists").select2({ data: playlistarr });
	$('#copyplaylist').removeClass('notSearch');
	$('#rename').addClass('notSearch');
}
//DISPLAYING TABLES
function displayLibraries(){
	clearMain()
	document.getElementById("TableTitle").innerHTML = "Libraries:";
	document.getElementById("TableTitle").style.visibility = "visible"
	var username = localStorage.getItem("username");
    var restUrl = "rest/query/searchLibrary/"+username
   
    var json = (function () {
        var json = null;
        $.ajax({
            'async': false,
            'global': false,
            'url': restUrl,
            'dataType': "json",
            'success': function (data) {
                json = data;
            }
        });
        return json;
    })();
    
    if(json != null && json.length != 0) {
    	
    	var table = document.getElementById("Table");
        var header = table.createTHead();
        var row = header.insertRow(0);
        var tBody = document.createElement("tbody");
        table.appendChild (tBody); 
        
        var LibraryID = document.createElement('th');
        row.appendChild(LibraryID);
        LibraryID.innerHTML = "Library ID";
    	

        var i = 0;
        var numRows = 0;
        while ( i < json.length) {
        	var rowNum = tBody.insertRow(numRows);
            var col1 = document.createElement('td');
            rowNum.appendChild(col1);
            col1.innerHTML = json[i];
            i++;
            numRows++;
        }
        $('#Table').dataTable();         
        $('#Table tbody').on( 'click', 'tr', function () {
        	selectedLibraryID = this.cells[0].innerHTML
        	//alert(selectedLibraryID)
        	if(selectedRow != null){
        		selectedRow.style.backgroundColor = "transparent";
        	}
        	this.style.backgroundColor = "#B0BED9";
            selectedRow = this;
            librarybtn();
        } );  
    }
    else {
        alert("Library not found.");
        $('#Table').dataTable();
    }
}

function displayPlaylists(){
	clearMain()
	document.getElementById("TableTitle").innerHTML = "Playlists in "+ selectedLibraryID+":";
	document.getElementById("TableTitle").style.visibility = "visible"
	var restUrl = "rest/query/searchPlaylists/"+selectedLibraryID
   
    //alert(restUrl)
    var json = (function () {
        var json = null;
        $.ajax({
            'async': false,
            'global': false,
            'url': restUrl,
            'dataType': "json",
            'success': function (data) {
                json = data;
            }
        });
        return json;
    })();
    
    
    if(json != null && json.length != 0) {
    	
    	var table = document.getElementById("Table");
        var header = table.createTHead();
        var row = header.insertRow(0);
        var tBody = document.createElement("tbody");
        table.appendChild (tBody); 
        
        var PlaylistID = document.createElement('th');
        var name = document.createElement('th');
        row.appendChild(PlaylistID);
        row.appendChild(name);
        PlaylistID.innerHTML = "Playlist ID";
        name.innerHTML = "Playlist Name";
    	
        var i = 0;
        var numRows = 0;
        while ( i < json.length) {
        	var rowNum = tBody.insertRow(numRows);
            var col1 = document.createElement('td');
            var col2 = document.createElement('td');
            rowNum.appendChild(col1);
            rowNum.appendChild(col2);
            col1.innerHTML = json[i][0];
            col2.innerHTML = json[i][1];
            i++;
            numRows++;
        }
        $('#Table').dataTable();         
        $('#Table tbody').on( 'click', 'tr', function () {
        	selectedPlaylistID = this.cells[0].innerHTML
        	if(selectedRow != null){
        		selectedRow.style.backgroundColor = "transparent"
        	}
        	this.style.backgroundColor = "#B0BED9"
            selectedRow = this
            playlistbtn();
        } );  
    }
    else {
        alert("Playlist not found.");
        $('#Table').dataTable();
    }
}

function displayTracks(){
	clearMain()
	document.getElementById("TableTitle").innerHTML = "Tracks in "+ selectedPlaylistID+":";
	document.getElementById("TableTitle").style.visibility = "visible"
	var restUrl = "rest/query/searchTracks/"+selectedPlaylistID   
	
    var json = (function () {
        var json = null;
        $.ajax({
            'async': false,
            'global': false,
            'url': restUrl,
            'dataType': "json",
            'success': function (data) {
                json = data;
            }
        });
        return json;
    })();
	
    
    if(json != null && json.length != 0) {
    	
    	var table = document.getElementById("Table");
        var header = table.createTHead();
        var row = header.insertRow(0);
        var tBody = document.createElement("tbody");
        table.appendChild (tBody); 

        var position = document.createElement('th');
        var trackID = document.createElement('th');
        var name = document.createElement('th');
        var artist = document.createElement('th');
        var album = document.createElement('th');
        var genre = document.createElement('th');
        row.appendChild(position);
        row.appendChild(trackID);
        row.appendChild(name);
        row.appendChild(artist);
        row.appendChild(album);
        row.appendChild(genre);
        trackID.innerHTML = "#";
        trackID.innerHTML = "Track ID";
        name.innerHTML = "Track Name";
        artist.innerHTML = "Artist";
        album.innerHTML = "Album";
        genre.innerHTML = "Genre";
    	
        var i = 0;
        var numRows = 0;
        while ( i < json.length) {
        	var rowNum = tBody.insertRow(numRows);
            var col1 = document.createElement('td');
            var col2 = document.createElement('td');
            var col3 = document.createElement('td');
            var col4 = document.createElement('td');
            var col5 = document.createElement('td');
            var col6 = document.createElement('td');
            rowNum.appendChild(col1);
            rowNum.appendChild(col2);
            rowNum.appendChild(col3);
            rowNum.appendChild(col4);
            rowNum.appendChild(col5);
            rowNum.appendChild(col6);
            col1.innerHTML = i+1;
            col2.innerHTML = json[i][0];
            col3.innerHTML = json[i][1];
            col4.innerHTML = json[i][2];
            col5.innerHTML = json[i][3];
            col6.innerHTML = json[i][4];
            i++;
            numRows++;
        }
        $('#Table').dataTable();         
        $('#Table tbody').on( 'click', 'tr', function () {
        	selectedTrackID = this.cells[1].innerHTML
        	if(selectedRow != null){
        		selectedRow.style.backgroundColor = "transparent"
        	}
        	this.style.backgroundColor = "#B0BED9"
            selectedRow = this
            trackbtn();
        } );  
    }
    else {
    	clearTable()
        alert("no tracks in playlist.");
    }
}

//DELETING OBJECTS
function deleteLibrary(){
	var restUrl = "rest/query/deleteLibrary/" + selectedLibraryID;
	$.ajax({
        'async': false,
        'global': false,
        'url': restUrl,
        'dataType': "json",
        'success': function (data) {
        	displayLibraries()
           // alert("your library has been deleted")
        }
    });	
}

function deletePlaylist(){
	var restUrl = "rest/query/deletePlaylist/" + selectedPlaylistID;
	$.ajax({
        'async': false,
        'global': false,
        'url': restUrl,
        'dataType': "json",
        'success': function (data) {
        	displayPlaylists()
           // alert("your library has been deleted")
        }
    });	
}

function deleteTrack(){
	var restUrl = "rest/query/deletePlaylistTrack/"+selectedPlaylistID +"/"+selectedTrackID
	$.ajax({
        'async': false,
        'global': false,
        'url': restUrl,
        'dataType': "json",
        'success': function (data) {
        	displayTracks()
           // alert("your library has been deleted")
        }
    });	
}

//RENAMING OBJECTS
function rename(){
	var newname = document.getElementById("renamefield").value
	if(renamechoice == 1){
		var restUrl = "rest/query/renamePlaylist/"+selectedPlaylistID +"/"+newname
		$.ajax({
	        'async': false,
	        'global': false,
	        'url': restUrl,
	        'dataType': "json",
	        'success': function (data) {
	        	displayPlaylists()
	           // alert("your library has been deleted")
	        }
	    });	
	}
	else{
		var restUrl = "rest/query/renameTrack/"+selectedTrackID +"/"+newname
		$.ajax({
	        'async': false,
	        'global': false,
	        'url': restUrl,
	        'dataType': "json",
	        'success': function (data) {
	        	displayTracks()
	           // alert("your library has been deleted")
	        }
	    });	
	}	
}

//COPY
function copyTrack(){
	
}

function getavailableplaylists(){
	alert("in funct")
	var array = (function () {
        var json = null;
        $.ajax({
            'async': false,
            'global': false,
            'url': "rest/query/searchPlaylists/"+selectedLibraryID ,
            'dataType': "json",
            'success': function (data) {
                json = data;
            }
        });
        return json;
    })();   
	
	alert(array)
	alert(array[0][1])
	var playlists = []
	for(i = 0; i< array.length; i++){
		playlists.push(array[i][1]);
	}
	alert(playlists)
		
	return array
}

function clearTable() {
	document.getElementById("TableTitle").style.visibility = "hidden"
		
	if(document.getElementById("Table_wrapper") != null){
		//alert("deleting table")
		var div = document.getElementById("Table_wrapper");
		var newtable = document.createElement("TABLE");
		newtable.setAttribute("id", "Table");
		newtable.setAttribute("class", "table table-striped");
		
		div.parentNode.appendChild(newtable);
		div.parentNode.removeChild(div);
	}
}
