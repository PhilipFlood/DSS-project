var tableexists = false;
var selectedLibraryID;
var selectedPlaylistID;
var selectedTrackID;
var selectedRow;

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
}
function DatasetButton() {
	clearTable();
	$('#uploadData').removeClass('notSearch');
	$('#librarybtns').addClass('notSearch');
	$('#playlistbtns').addClass('notSearch');
	$('#trackbtns').addClass('notSearch');
}
function DeleteUserButton(){
	clearTable();
	$('#uploadData').addClass('notSearch');
	$('#librarybtns').addClass('notSearch');
	$('#playlistbtns').addClass('notSearch');
	$('#trackbtns').addClass('notSearch');
}
function librarybtn(){
	$('#librarybtns').removeClass('notSearch');
	$('#playlistbtns').addClass('notSearch');
	$('#trackbtns').addClass('notSearch');
}
function playlistbtn(){
	$('#librarybtns').addClass('notSearch');
	$('#playlistbtns').removeClass('notSearch');
	$('#trackbtns').addClass('notSearch');
}
function trackbtn(){
	$('#librarybtns').addClass('notSearch');
	$('#playlistbtns').addClass('notSearch');
	$('#trackbtns').removeClass('notSearch');
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
        		selectedRow.style.backgroundColor = "transparent"
        	}
        	this.style.backgroundColor = "#B0BED9"
            selectedRow = this
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

        var trackID = document.createElement('th');
        var name = document.createElement('th');
        var artist = document.createElement('th');
        var album = document.createElement('th');
        var genre = document.createElement('th');
        row.appendChild(trackID);
        row.appendChild(name);
        row.appendChild(artist);
        row.appendChild(album);
        row.appendChild(genre);
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
            rowNum.appendChild(col1);
            rowNum.appendChild(col2);
            rowNum.appendChild(col3);
            rowNum.appendChild(col4);
            rowNum.appendChild(col5);
            col1.innerHTML = json[i][0];
            col2.innerHTML = json[i][1];
            col3.innerHTML = json[i][2];
            col4.innerHTML = json[i][3];
            col5.innerHTML = json[i][4];
            i++;
            numRows++;
        }
        $('#Table').dataTable();         
        $('#Table tbody').on( 'click', 'tr', function () {
        	selectedTrackID = this.cells[0].innerHTML
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
	
}

function deletePlaylist(){
	
}

function deleteTrack(){
	
}

//RENAMING OBJECTS
function renamePlaylist(){
	
}

function renameTrack(){
	
}

//COPY
function copyTrack(){
	
}

function displayfailuredata(){
	clearTable();
	document.getElementById("TableTitle").innerHTML = "Erroneous Data:";
	document.getElementById("TableTitle").style.visibility = "visible"
	$('#uploadData').addClass('notSearch');
	$('#userControls').addClass('notSearch');
	$('#createuser').addClass('notSearch');
	$('#edituser').addClass('notSearch');
	$('#edituserinfo').addClass('notSearch');
	$('#deleteuser').addClass('notSearch');
	
    var restUrl = "rest/query/failedEntries";
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
    var table = document.getElementById("Table");
    var header = table.createTHead();
    var row = header.insertRow(0);
    var tBody = document.createElement("tbody");
    table.appendChild (tBody); 
    
    //var id = document.createElement('th');
    var date = document.createElement('th');
    var eventId = document.createElement('th');
    var failureClass = document.createElement('th');
    var tac = document.createElement('th');
    var mcc = document.createElement('th');
    var mnc = document.createElement('th');
    var cellId = document.createElement('th');
   // var duration = document.createElement('th');
    var causeCode = document.createElement('th');
    var neVersion = document.createElement('th');
    var imsi = document.createElement('th');  
   // row.appendChild(id);
    row.appendChild(date);
    row.appendChild(eventId);
    row.appendChild(failureClass);
    row.appendChild(tac);
    row.appendChild(mcc);
    row.appendChild(mnc);
    row.appendChild(cellId);
   // row.appendChild(duration);
    row.appendChild(causeCode);
    row.appendChild(neVersion);
    row.appendChild(imsi);    
    //id.innerHTML = "ID";
    date.innerHTML = "Date";
    eventId.innerHTML = "Event ID";
    failureClass.innerHTML = "Failure Class";
    tac.innerHTML = "TAC";
    mcc.innerHTML = "MCC";
    mnc.innerHTML = "MNC";
    cellId.innerHTML = "Cell ID";
   // duration.innerHTML = "Duration";
    causeCode.innerHTML = "Cause Code";
    neVersion.innerHTML = "Ne Version";
    imsi.innerHTML = "IMSI";
    
    if(json != null && json.length != 0) {
        var str = json.toString();
        var arrayOfElements = str.split(',');
        var arrayLength = arrayOfElements.length;
        var i = 0;
        var numRows = 0;
        while ( i < arrayLength) {
        	var rowNum = tBody.insertRow(numRows);
            //var col1 = document.createElement('td');
            var col2 = document.createElement('td');
            var col3 = document.createElement('td');
            var col4 = document.createElement('td');
            var col5 = document.createElement('td');
            var col6 = document.createElement('td');
            var col7 = document.createElement('td');
            var col8 = document.createElement('td');
            //var col9 = document.createElement('td');
            var col10 = document.createElement('td');
            var col11 = document.createElement('td');
            var col12 = document.createElement('td');       
           
            //rowNum.appendChild(col1);
            rowNum.appendChild(col2);
            rowNum.appendChild(col3);
            rowNum.appendChild(col4);
            rowNum.appendChild(col5);
            rowNum.appendChild(col6);
            rowNum.appendChild(col7);
            rowNum.appendChild(col8);
            //rowNum.appendChild(col9);
            rowNum.appendChild(col10);
            rowNum.appendChild(col11);
            rowNum.appendChild(col12);
            //col1.innerHTML = arrayOfElements[i];
            i++;
            col2.innerHTML = arrayOfElements[i];
            i++;
            col3.innerHTML = arrayOfElements[i];
            i++;
            col4.innerHTML = arrayOfElements[i];
            i++;
            col5.innerHTML = arrayOfElements[i];
            i++;
            col6.innerHTML = arrayOfElements[i];
            i++;
            col7.innerHTML = arrayOfElements[i];
            i++;
            col8.innerHTML = arrayOfElements[i];
            i++;
            //col9.innerHTML = arrayOfElements[i];
            i++;
            col10.innerHTML = arrayOfElements[i];
            i++;
            col11.innerHTML = arrayOfElements[i];
            i++;
            col12.innerHTML = arrayOfElements[i];
            i++;
            numRows++;
        }
        $('#Table').dataTable(); 
        tableexists = true;
    }
    else {
        alert("Result not found.");
    }
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

$(function() {
	$(".clickable-row").click(function() {
	        window.document.location = $(this).data("href");
	});	
});

