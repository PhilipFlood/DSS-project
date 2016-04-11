var tableexists = false;

function upload() {
	var x = document.getElementById("filepath").value;
	alert(x);
	$.ajax({
			type : 'POST',
			url : 'rest/load',
			data:JSON.stringify(x),
		
			success : handlePost,
			contentType : 'application/json'
	});
	//window.location="http://localhost:8080/EventManager1/failed.html";
	displayfailuredata();
}

function handlePost(){
	
}

function admincheck(){
	var level = localStorage.getItem('usertype');
	document.getElementById('loggedin').innerHTML = localStorage.getItem("username");	
	if(level > 0 && level < 4){
		window.location.assign("SearchMenu.html");
    }
	else if(level < 0 || level >3){
		window.location.assign("login.html");
	}
}

function DatasetButton() {
		$('#uploadData').removeClass('notSearch');
		$('#userControls').addClass('notSearch');
		$('#createuser').addClass('notSearch');
		$('#edituser').addClass('notSearch');
		$('#edituserinfo').addClass('notSearch');
		$('#deleteuser').addClass('notSearch');
		clearTable();
		document.getElementById('msg').innerHTML = "";
}

function UserFunctionsButton(){
		$('#uploadData').addClass('notSearch');
		$('#userControls').removeClass('notSearch');
		$('#createuser').addClass('notSearch');
		$('#edituser').addClass('notSearch');
		$('#edituserinfo').addClass('notSearch');
		$('#deleteuser').addClass('notSearch');
		clearTable();
		displayusers();
}

function AddUserButton(){
		clearTable();
		$('#uploadData').addClass('notSearch');
		$('#userControls').removeClass('notSearch');
		$('#createuser').removeClass('notSearch');
		$('#edituser').addClass('notSearch');
		$('#edituserinfo').addClass('notSearch');
		$('#deleteuser').addClass('notSearch');
}

function EditUserButton(){
	clearTable();
	displayusers();
	$('#uploadData').addClass('notSearch');
	$('#userControls').removeClass('notSearch');
	$('#createuser').addClass('notSearch');
	$('#edituser').removeClass('notSearch');
	$('#deleteuser').addClass('notSearch');
	$('#edituserinfo').addClass('notSearch');
}

function DeleteUserButton(){
	clearTable();
	displayusers();
	$('#uploadData').addClass('notSearch');
	$('#userControls').removeClass('notSearch');
	$('#createuser').addClass('notSearch');
	$('#edituser').addClass('notSearch');
	$('#edituserinfo').addClass('notSearch');
	$('#deleteuser').removeClass('notSearch');
}

function displayusers(){
	document.getElementById("TableTitle").innerHTML = "Users:";
	document.getElementById("TableTitle").style.visibility = "visible"
    var restUrl = "rest/users/allUsers";
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
    
    var username = document.createElement('th');
    var password = document.createElement('th');
    var usertype = document.createElement('th');
    row.appendChild(username);
    row.appendChild(password);
    row.appendChild(usertype);
    username.innerHTML = "UserName";
    password.innerHTML = "Password";
    usertype.innerHTML = "User Type";
    if(json != null && json.length != 0) {
        var str = json.toString();
        var arrayOfElements = str.split(',');
        var arrayLength = arrayOfElements.length;
        var i = 0;
        var numRows = 0;
        while ( i < arrayLength) {
        	var rowNum = tBody.insertRow(numRows);
            var col1 = document.createElement('td');
            var col2 = document.createElement('td');
            var col3 = document.createElement('td');
            rowNum.appendChild(col1);
            rowNum.appendChild(col2);
            rowNum.appendChild(col3);
            col1.innerHTML = arrayOfElements[i];
            i++;
            col2.innerHTML = arrayOfElements[i];
            i++;
            if(arrayOfElements[i] == 0){
            	col3.innerHTML = "Administrator";
            }
            else if(arrayOfElements[i] == 1){
            	col3.innerHTML = "Customer Service Rep";
            }
            else if(arrayOfElements[i] == 2){
            	col3.innerHTML = "Support Engineer";
            }
            else if(arrayOfElements[i] == 3){
            	col3.innerHTML = "Network Management Engineer";
            }
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
	document.getElementById("TableTitle").innerHTML = "";
	if(tableexists ==true){
	var div = document.getElementById("Table_wrapper");
	var newtable = document.createElement("TABLE");
	newtable.setAttribute("id", "Table");
	newtable.setAttribute("class", "table table-striped");
	
	div.parentNode.appendChild(newtable);
	div.parentNode.removeChild(div);
	tableexists = false;
	}
}
