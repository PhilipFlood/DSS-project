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
				if(data.username != ""){
					localStorage.setItem("username",data.username);			
					window.location.assign("AdminMenu.html");
				}
				else{
					alert("User or password is incorrect")
				}
			},
			contentType : 'application/json'
		}); 	
}

function loginform(){
	document.getElementById('formtitle').innerHTML = "Login:";
	$('#loginform').removeClass('notSearch');
	$('#createuser').addClass('notSearch');
	document.getElementById('username').value = "";
	document.getElementById('pw').value = "";
}

function registerform(){
	document.getElementById('formtitle').innerHTML = "Register:";
	$('#createuser').removeClass('notSearch');
	$('#loginform').addClass('notSearch');
	document.getElementById('REGusername').value = "";
	document.getElementById('REGpw').value = "";
	document.getElementById('confirm').value = "";
}

function logout(){
	localStorage.setItem("username","");		
	window.location.assign("login.html");
}

function adduser(){
	var userval = $( "#REGusername" ).val();
	var passval = $( "#REGpw" ).val();
	var fulluser = userval + ' ' + passval;
	
	if(passval.length == 0 || userval.length == 0 ||  $( "#confirm" ).val().length==0){
		alert("You must fill in all fields");		
	}
	else if(passval != $( "#confirm" ).val()){
		alert("passwords do not match");
		document.getElementById('REGpw').value = "";
		document.getElementById('confirm').value = "";
	}
	else if(userval.indexOf(" ") > -1 || passval.indexOf(" ") > -1){
		alert("usernames and passwords cannot contain spaces");
	}
	else { 
		$.ajax({
			async: false,
			type : 'POST',
			url : 'rest/users/adduser',
			data : JSON.stringify(fulluser) ,
			success : function isSearchData(data){
				if(data.username == ""){
					alert("user already exists");	
					document.getElementById("REGusername").focus();
				}
				else{				
					alert("You have been registered!");
					document.getElementById('REGusername').value = "";
					document.getElementById('REGpw').value = "";
					document.getElementById('confirm').value = "";
					loginform()
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
