function authenticate(){
		var userval = $( "#username" ).val();
		var passval = $( "#pw" ).val();
		var fulluser = userval + ' ' + passval;
		
		 $.ajax({
			async: false,
			type : 'POST',
			url : 'rest/users/authenticate',
			data : JSON.stringify(fulluser) ,
			success : function isSearchData(data) {
				//alert(JSON.stringify(data));
				localStorage.setItem("username",data.username);			
				if(data.userType==0){								
					localStorage.setItem("usertype",data.userType);					
					window.location.assign("AdminMenu.html");
				}
				else if(data.userType==3){
					localStorage.setItem("usertype",data.userType);	
					window.location.assign("SearchMenu.html");
				}
				else if(data.userType==2){
					localStorage.setItem("usertype",data.userType);					
					window.location.assign("SearchMenu.html");
				}
				else if(data.userType==1){
					localStorage.setItem("usertype",data.userType);					
					window.location.assign("SearchMenu.html");
				}
				else if(data.userType==4){
					//alert("Your username or password is incorrect");
					document.getElementById('username').value = "";
					document.getElementById('pw').value = "";
					document.getElementById('msg').innerHTML = "Your username or password is incorrect!";						
				}
			},
			contentType : 'application/json'
		}); 	
}

function logout(){
	localStorage.setItem("usertype",5);		
	window.location.assign("login.html");
}

function adduser(){
		var userval = $( "#username" ).val();
		var passval = $( "#pw" ).val();
		var e = document.getElementById("userlevel");
		var levelval = e.options[e.selectedIndex].value;
		
		var fulluser = userval + ' ' + passval + ' ' + levelval;
		
		if(passval.length == 0 || userval.length == 0 ||  $( "#confirm" ).val().length==0){
			alert("You must fill in all fields");		
		}
		else if(passval != $( "#confirm" ).val()){
			alert("passwords do not match");
			document.getElementById('pw').value = "";
			document.getElementById('confirm').value = "";
		}
		else if(userval.indexOf(" ") > 0 || passval.indexOf(" ") > 0){
			alert("usernames and passwords cannot contain spaces");
		}
		else { 
			$.ajax({
				async: false,
				type : 'POST',
				url : 'rest/users/adduser',
				data : JSON.stringify(fulluser) ,
				success : function isSearchData(data) {
					clearTable();
					displayusers();
					if(data.userType==4){
						alert("user already exists");	
					}
					else{									
					
					$('#createuser').addClass('notSearch');
					document.getElementById('username').value = "";
					document.getElementById('userlevel').value = "";
					document.getElementById('pw').value = "";
					document.getElementById('confirm').value = "";
					//alert("User Created!");
					}
				},
				contentType : 'application/json'
			 }); 
		}	
}

function deleteuser(){
		$.ajax({
			async: false,
			type : 'POST',
			url : 'rest/users/deleteuser',
			data : JSON.stringify(document.getElementById("name").value) ,
			success : function isSearchData(data) {
				clearTable();
				displayusers();
				if(data.userType==4){
					alert("User doesn't exist");	
					document.getElementById('name').value = "";
				}
				else{
					//alert("User has been deleted");	
					document.getElementById('name').value = "";
				}
			},
			contentType : 'application/json'
		 });
}

function finduser(){	
	$.ajax({
		async: false,
		type : 'POST',
		url : 'rest/users/search',
		data : JSON.stringify(document.getElementById("findusername").value) ,
		success : function isSearchData(data) {
			//alert(JSON.stringify(data));
			
			if (typeof data === "undefined") {
				alert("User does not exist");
			}
			else{
				clearTable();
				$('#edituserinfo').removeClass('notSearch');
				
				
				document.getElementById("editusername").value = data.username;
				document.getElementById("editpw").value = data.password;
				var e = document.getElementById("editlevel");
				e.value = data.userType;
			}
		},
		contentType : 'application/json'
	 });
}

function edituser(){
	var olduser = $( "#findusername" ).val();
	var userval = $( "#editusername" ).val();
	var passval = $( "#editpw" ).val();
	var e = document.getElementById("editlevel");
	var levelval = e.options[e.selectedIndex].value;

	var fulluser = olduser + ' ' + userval + ' ' + passval + ' ' + levelval;
	
	if(passval.length == 0 || userval.length == 0){
		alert("You must fill in all fields");		
	}
	else if(userval.indexOf(" ") > 0 || passval.indexOf(" ") > 0){
		alert("usernames and passwords cannot contain spaces");	
	}
	
	$.ajax({
		async: false,
		type : 'POST',
		url : 'rest/users/edituser',
		data : JSON.stringify(fulluser) ,
		success : function isSearchData(data) {
			clearTable();
			displayusers();
			if(data.userType==4){
				alert("Username already exists");
			}
			else{
				document.getElementById('findusername').value = "";	
				document.getElementById('editusername').value = "";	
				document.getElementById('editpw').value = "";	
				$('#edituserinfo').addClass('notSearch');
				//alert("User has been edited");
			}
		},
		contentType : 'application/json'
	 });
}

function displayusers(){
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
    var row = table.insertRow(0);
    var eventId = document.createElement('th');
    var causeCode = document.createElement('th');
    var description = document.createElement('th');
    row.appendChild(eventId);
    row.appendChild(causeCode);
    row.appendChild(description);
    eventId.innerHTML = "UserName";
    causeCode.innerHTML = "Password";
    description.innerHTML = "User Type";
    if(json != null && json.length != 0) {
        var str = json.toString();
        var arrayOfElements = str.split(',');
        var arrayLength = arrayOfElements.length;
        var i = 0;
        var numRows = 1;
        while ( i < arrayLength) {
            var rowNum = table.insertRow(numRows);
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
            col3.innerHTML = arrayOfElements[i];
            i++;
            numRows++;
        }
    }
    else {
        alert("Result not found.");
    }
}

/*function clearTable() {
	var Table = document.getElementById("Table");
	Table.innerHTML = "";
}*/