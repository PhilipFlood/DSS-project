var queryType;
var tableexists = false;
var tenGraphArray = [];
var totalFailuresEach = 0;
var totalFailures = 0;
var totalFailuresEachString = "";
var totalFailuresString = "";
var percentArray = [];
var XLabel1, XLabel2, XLabel3, XLabel4;
var XLabelArray = [];
var define;
var models;
var manufacturers = [];

function criteriaButton() {
	clearTable();
	document.getElementById("ToDate").val=""
	document.getElementById("FromDate").val=""
	
    if(document.getElementById('IMSIbox').checked){
        $('#returnTypeForIMSI').removeClass('notSearch');
        $('#searchIMSIAndDate').addClass('notSearch');
        $('#returnTypeForDate').addClass('notSearch');
        $('#searchModelAndDate').addClass('notSearch');
        $('#searchDate').addClass('notSearch');
        $('#searchCauseClass').addClass('notSearch');
        $('#searchPhoneModel').addClass('notSearch');          
    }
    else if (document.getElementById('IMSIDatebox').checked) {
        $('#searchIMSIAndDate').removeClass('notSearch');
        $('#searchIMSI').addClass('notSearch');
        $('#returnTypeForIMSI').addClass('notSearch');
        $('#returnTypeForDate').addClass('notSearch');
        $('#searchDate').addClass('notSearch');
        $('#searchCauseClass').addClass('notSearch');
        $('#searchPhoneModel').addClass('notSearch');
        
        $('#datebtn').click(function(){searchIMSIAndDate()});
        $('#searchDate').removeClass('notSearch');
    }
    else if (document.getElementById('Datebox').checked) {
        $('#returnTypeForDate').removeClass('notSearch');
        $('#searchIMSI').addClass('notSearch');
        $('#searchIMSIAndDate').addClass('notSearch');
        $('#returnTypeForIMSI').addClass('notSearch');
        $('#searchModelAndDate').addClass('notSearch');
        $('#searchCauseClass').addClass('notSearch');
        $('#searchPhoneModel').addClass('notSearch');
        $('#searchDate').removeClass('notSearch');
                
        $('#datebtn').click(function(){searchDate()});
    }
    else if (document.getElementById('Modelbox').checked) {
        $('#searchModelAndDate').removeClass('notSearch');
        $('#searchIMSI').addClass('notSearch');
        $('#searchIMSIAndDate').addClass('notSearch');
        $('#returnTypeForIMSI').addClass('notSearch');
        $('#returnTypeForDate').addClass('notSearch');
        $('#searchDate').addClass('notSearch');
        $('#searchCauseClass').addClass('notSearch');
        $('#searchPhoneModel').addClass('notSearch');   
        
        $('#datebtn').click(function(){ searchDateAndModel() });
        $('#searchDate').removeClass('notSearch');
        populateManufacturers();
    }
    else if (document.getElementById('CauseClassbox').checked) {
        $('#searchCauseClass').removeClass('notSearch');
        $('#searchIMSI').addClass('notSearch');
        $('#searchIMSIAndDate').addClass('notSearch');
        $('#returnTypeForIMSI').addClass('notSearch');
        $('#returnTypeForDate').addClass('notSearch');
        $('#searchDate').addClass('notSearch');
        $('#searchModelAndDate').addClass('notSearch');
        $('#searchPhoneModel').addClass('notSearch');
    }
    else if (document.getElementById('PhoneModelbox').checked) {
    	$('#searchPhoneModel').removeClass('notSearch');
    	$('#searchCauseClass').addClass('notSearch');
        $('#searchIMSI').addClass('notSearch');
        $('#searchIMSIAndDate').addClass('notSearch');
        $('#returnTypeForIMSI').addClass('notSearch');
        $('#returnTypeForDate').addClass('notSearch');
        $('#searchDate').addClass('notSearch');
        $('#searchModelAndDate').addClass('notSearch');
        
        populateManufacturers();
    }
    else {
        alert("Please select one of the search criteria.");
    }
}


function returnDateButton() {
	clearTable();
    if(document.getElementById('IMSIcount').checked){
    	$('#searchDate').removeClass('notSearch');
        queryType = "imsicount";
    }
    else if (document.getElementById('IMSIunique').checked) {
        $('#searchDate').removeClass('notSearch');
        queryType = "uniqueimsi";
    }
    else if (document.getElementById('Top10MOC').checked) {
        $('#searchDate').removeClass('notSearch');
        queryType = "Top10MOC";
    }
    else if (document.getElementById('Top10IMSI').checked) {
        $('#searchDate').removeClass('notSearch');
        queryType = "Top10IMSI";
    }
    else {
        alert("Please select a result set for your query.");
    }
}

function returnIMSIButton() {
	clearTable();
    if(document.getElementById('EventAndCause').checked){
        $('#searchIMSI').removeClass('notSearch');
        queryType = "imsieventcause";
    }
    else if (document.getElementById('UniqueCause').checked) {
        $('#searchIMSI').removeClass('notSearch');
        queryType = "uniquecause";
    }
    else {
        alert("Please select a result set for your query.");
    }
}

function searchIMSI() {
    clearTable();
    var imsi = document.getElementById('SearchIMSI').value;
    
    var table = document.getElementById("Table");
    var header = table.createTHead();
    var row = header.insertRow(0);
    var tBody = document.createElement("tbody");
    table.appendChild (tBody); 
    
    var i = 0;
    var numRows = 0;    
    
    if(queryType == "imsieventcause"){
            var restUrl = "rest/query/searchEventCause/" + imsi;
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
            var eventId = document.createElement('th');
            var causeCode = document.createElement('th');
            var description = document.createElement('th');
            row.appendChild(eventId);
            row.appendChild(causeCode);
            row.appendChild(description);
            eventId.innerHTML = "Event ID";
            causeCode.innerHTML = "Cause Code";
            description.innerHTML = "Description";
            if(json != null && json.length != 0) {
                var str = json.toString();
                var arrayOfElements = str.split(',');
                var arrayLength = arrayOfElements.length;
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
                    col3.innerHTML = arrayOfElements[i];
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
    else if(queryType == "uniquecause"){
        var restUrl1 = "rest/query/searchUniqueCauseCodeByIMSI/" + imsi;
        var json1 = (function () {
            var json = null;
            $.ajax({
                'async': false,
                'global': false,
                'url': restUrl1,
                'dataType': "json",
                'success': function (data) {
                    json = data;
                }
            });
            return json;
        })();
        var causeCode = document.createElement('th');
        row.appendChild(causeCode);
        causeCode.innerHTML = "Cause Code";
        if(json1 != null && json1.length != 0) {
            var str = json1.toString();
            var arrayOfElements = str.split(',');
            var arrayLength = arrayOfElements.length;
            while ( i < arrayLength) {
            	var rowNum = tBody.insertRow(numRows);
                var col1 = document.createElement('td');
                rowNum.appendChild(col1);
                col1.innerHTML = arrayOfElements[i];
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
}

function searchIMSIAndDate() {
    clearTable();
    var to = document.getElementById('ToDate').value;
    to = to + ":00";
    var from = document.getElementById('FromDate').value;
    from = from + ":00"; 
    var imsi = document.getElementById('IMSIdateField').value;
    
    var table = document.getElementById("Table");
    var header = table.createTHead();
    var row = header.insertRow(0);
    var tBody = document.createElement("tbody");
    table.appendChild (tBody); 
   
    var restUrl = "rest/query/imsifc/" + imsi + "/" + from + "/" + to;
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
    if(json == null){
        json = 0;
    }    
    var count = document.createElement('th');
    row.appendChild(count);
    count.innerHTML = "Number of Failures";
    
    var rowNum = tBody.insertRow(0);
    var col1 = document.createElement('td');
    rowNum.appendChild(col1);
    col1.innerHTML = json;
    
    $('#Table').dataTable();
    tableexists = true;
}

function searchDate() {
    clearTable();
    var to = document.getElementById('ToDate').value;
    to = to + ":00";
    var from = document.getElementById('FromDate').value;    
    from = from + ":00";
    
    var table = document.getElementById("Table");
    var header = table.createTHead();
    var row = header.insertRow(0);
    var tBody = document.createElement("tbody");
    table.appendChild (tBody); 
    
    var i = 0;
    var numRows = 0; 
    
    if (queryType == "imsicount") {
        var restUrl = "rest/query/searchIMSIByDates/" + from + "/" + to;
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
        if(json != null){
            var str = json.toString();
            var arrayOfElements = str.split(',');
            var arrayLength = arrayOfElements.length;
            
            var imsi = document.createElement('th');
            var count = document.createElement('th');
            var duration = document.createElement('th');
            row.appendChild(imsi);
            row.appendChild(count);
            row.appendChild(duration);
            imsi.innerHTML = "IMSI";
            count.innerHTML = "Duration";
            duration.innerHTML = "Count";
            
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
                col3.innerHTML = arrayOfElements[i];
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
    else if(queryType == "uniqueimsi") {
        var restUrl2 = "rest/query/searchIMSIByDates/" + from + "/" + to;
        var json2 = (function () {
            var json = null;
            $.ajax({
                'async': false,
                'global': false,
                'url': restUrl2,
                'dataType': "json",
                'success': function (data) {
                    json = data;
                }
            });
            return json;
        })();
        if(json2 != null){
            var str2 = json2.toString();
            var arrayOfElements2 = str2.split(',');
            
            var arrayLength2 = arrayOfElements2.length;
            imsi = document.createElement('th');
            row.appendChild(imsi);
            imsi.innerHTML = "IMSI";
            
            while ( i < arrayLength2) {
            	var rowNum = tBody.insertRow(numRows);
                col1 = document.createElement('td');
                rowNum.appendChild(col1);
                col1.innerHTML = arrayOfElements2[i];
                i = i + 3;
                numRows++;
            }
            $('#Table').dataTable();
            tableexists = true;
        }
        else {
            alert("No results found.");
        }
    }
    else if(queryType == "Top10MOC") {
        
    	var restUrl3 = "rest/query/10MarketOperatorCell/" + from + "/" + to;
        var json3 = (function () {
            var json = null;
            $.ajax({
                'async': false,
                'global': false,
                'url': restUrl3,
                'dataType': "json",
                'success': function (data) {
                    json = data;
                }
            });
            return json;
        })();
        if(json3 != null){
            var str3 = json3.toString();
            var arrayOfElements3 = str3.split(',');
            
            var arrayLength3 = arrayOfElements3.length;
            
            var top = document.createElement('th');
            var mcc = document.createElement('th');
            var mnc = document.createElement('th');
            var cellId = document.createElement('th');
            var operator = document.createElement('th');
            var market = document.createElement('th');
            var numFailures = document.createElement('th');
           
            row.appendChild(top);
            row.appendChild(mcc);
            row.appendChild(mnc);
            row.appendChild(cellId);
            row.appendChild(operator);
            row.appendChild(market);
            row.appendChild(numFailures);
            
            mcc.innerHTML = "MCC";
            mnc.innerHTML = "MNC";
            cellId.innerHTML = "Cell ID";
            operator.innerHTML = "Operator";
            market.innerHTML = "Market";
            numFailures.innerHTML = "Failures";
            
            while ( i < arrayLength3) {
            	var rowNum3 = tBody.insertRow(numRows);
                var col1 = document.createElement('td');
                var col2 = document.createElement('td');
                var col3 = document.createElement('td');
                var col4 = document.createElement('td');
                var col5 = document.createElement('td');
                var col6 = document.createElement('td');
                var col7 = document.createElement('td');
                
                rowNum3.appendChild(col1);
                rowNum3.appendChild(col2);
                rowNum3.appendChild(col3);
                rowNum3.appendChild(col4);
                rowNum3.appendChild(col5);
                rowNum3.appendChild(col6);
                rowNum3.appendChild(col7);
                
                col1.innerHTML = i/7 + 1;
                col2.innerHTML = arrayOfElements3[i];
                i++;
                col3.innerHTML = arrayOfElements3[i];
                i++;
                col4.innerHTML = arrayOfElements3[i];
                XLabel1 = arrayOfElements3[i];
                i++;
                col5.innerHTML = arrayOfElements3[i];
                XLabel2 = arrayOfElements3[i];
                i++;
                col6.innerHTML = arrayOfElements3[i];
                XLabel3 = arrayOfElements3[i];
                XLabel4 = XLabel1 + " " + XLabel2 + " " + XLabel3;
                XLabelArray.push(XLabel4);
                i++;
                col7.innerHTML = arrayOfElements3[i];
                tenGraphArray.push(arrayOfElements3[i]);
                i++;
                totalFailures = totalFailures + parseInt(arrayOfElements3[i]);
                i++;
                numRows++;
            }
            totalFailures = ( totalFailures / tenGraphArray.length );

            document.getElementById("tenGraph").style.visibility = "visible";
            $('#Table').dataTable();
            tableexists = true;
            define = "Failures";
        }
        else {
            alert("No results found.");
        }
    }
    else if(queryType == "Top10IMSI") {
        var restUrl4 = "rest/query/top10failuresbyIMSI/" + from + "/" + to;
        var json4 = (function () {
            var json = null;
            $.ajax({
                'async': false,
                'global': false,
                'url': restUrl4,
                'dataType': "json",
                'success': function (data) {
                    json = data;
                }
            });
            return json;
        })();
        if(json4 != null){
            var str4 = json4.toString();
            var arrayOfElements4 = str4.split(',');
            var arrayLength4 = arrayOfElements4.length;
            var top = document.createElement('th');
            var imsi = document.createElement('th');
            row.appendChild(top);
            row.appendChild(imsi);
            imsi.innerHTML = "IMSI";
            while ( i < arrayLength4) {
                var rowNum4 = tBody.insertRow(numRows);
                var col1 = document.createElement('td');
                var col2 = document.createElement('td');
                rowNum4.appendChild(col1);
                rowNum4.appendChild(col2);
                col1.innerHTML = i + 1;
                col2.innerHTML = arrayOfElements4[i];
                i++;
                numRows++;
            }
            $('#Table').dataTable();
            tableexists = true;
        }
        else {
            alert("No results found.");
        }
    }
}

function tenGraph(){
	document.getElementById("graphTitle").innerHTML = "Number Of Failures for each Node";
	$("#canvas").empty();
	$(document).ready(function(){
		var ctx = $("#canvas").get(0).getContext("2d");
		//bar data
		var bardata_Num = {
			labels: XLabelArray,
			datasets: [
			{
//				title: "Number of failures",
//				label: 'Number of failures',
				fillColor: '#382765',
				data: tenGraphArray
			}
			]
		};

		//draw
		Chart.defaults.global.responsive = true;
		Chart.defaults.global.tooltipTemplate = "<%= label %> has <%= value %> <%= define %> this is <%= parseFloat(value/totalFailures*100.00).toFixed(2) %> % of all <%= define %>";
		
		var barchart = new Chart(ctx).Bar(bardata_Num);	
		
	});
}

function searchDateAndModel() {
	clearTable();
    var to = document.getElementById('ToDate').value;
    to = to + ":00";
    var from = document.getElementById('FromDate').value;
    from = from + ":00"; 
    var model = document.getElementById('ModelField').value;
    
    var restUrl = "rest/query/searchMobileType/" + model + "/" + from + "/" + to;
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
    if(json == null){
        json = 0;
    }
    var table = document.getElementById("Table");
    var header = table.createTHead();
    var row = header.insertRow(0);
    var tBody = document.createElement("tbody");
    table.appendChild (tBody); 
    
    count = document.createElement('th');
    row.appendChild(count);
    count.innerHTML = "Number of Failures";
    
    var rowNum = tBody.insertRow(0);
    var col1 = document.createElement('td');
    rowNum.appendChild(col1);
    col1.innerHTML = json;
    $('#Table').dataTable(); 
    tableexists = true;
}

function searchCauseClass() {
    clearTable();
    var causeClass = document.getElementById('SearchCauseClass').value;
    var restUrl = "rest/query/searchIMSIByCauseCode/" + causeClass;
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
    
    var imsi = document.createElement('th');
    row.appendChild(imsi);
    imsi.innerHTML = "IMSI";
    
    if(json != null && json.length != 0) {
        var str = json.toString();
        var arrayOfElements = str.split(',');
        var arrayLength = arrayOfElements.length;
        var i = 0;
        var numRows = 0;
        
        while ( i < arrayLength) {
        	var rowNum = tBody.insertRow(0);
            var col1 = document.createElement('td');
            rowNum.appendChild(col1);
            col1.innerHTML = arrayOfElements[i];
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

function searchPhoneModel() {
	clearTable();
    var phoneModel = document.getElementById('SearchPhoneModel').value;
    
    var table = document.getElementById("Table");
    var header = table.createTHead();
    var row = header.insertRow(0);
    var tBody = document.createElement("tbody");
    table.appendChild (tBody); 
    var i = 0;
    var numRows = 0;  
    
    var restUrl = "rest/query/fceibpm/" + phoneModel;
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
    var eventId = document.createElement('th');
    var causeCode = document.createElement('th');
    var count = document.createElement('th');
    row.appendChild(eventId);
    row.appendChild(causeCode);
    row.appendChild(count);
    eventId.innerHTML = "Event ID";
    causeCode.innerHTML = "Cause Code";
    count.innerHTML = "Number of Occurences";
    if(json != null && json.length != 0) {
        var str = json.toString();
        var arrayOfElements = str.split(',');
        var arrayLength = arrayOfElements.length;
        while ( i < arrayLength) {
        	var rowNum = tBody.insertRow(numRows);
            var col1 = document.createElement('td');
            var col2 = document.createElement('td');
            var col3 = document.createElement('td');
            rowNum.appendChild(col1);
            rowNum.appendChild(col2);
            rowNum.appendChild(col3);
            col1.innerHTML = arrayOfElements[i];
            XLabel1 = arrayOfElements[i];
            i++;
            col2.innerHTML = arrayOfElements[i];
            XLabel2 = arrayOfElements[i];
            XLabel3 = XLabel1 + " " + XLabel2;
            XLabelArray.push(XLabel3);
            i++;
            col3.innerHTML = arrayOfElements[i];
            tenGraphArray.push(arrayOfElements[i]);
            totalFailures = totalFailures + parseInt(arrayOfElements[i]);
            i++;
            numRows++;
        }
        $('#Table').dataTable(); 
        tableexists = true;
        
        totalFailures = ( totalFailures / tenGraphArray.length );
        document.getElementById("tenGraph").style.visibility = "visible";
        define = "Occurances";
    }
    else {
    	$('#Table').dataTable(); 
    	tableexists = true;
        alert("Result not found.");
    }
}

function populateManufacturers(){
    for(j = 0;j<models.length;j++){
    	manufacturers.push(models[j][1]);
    }
    var uniqueArray = manufacturers.filter(function(elem, pos) {
        return manufacturers.indexOf(elem) == pos;
      }); 
    
    $("#ManufacturerField").select2({ data: uniqueArray });
    $("#SearchPhoneManufacturer").select2({ data: uniqueArray });  
    
    populatemodels(document.getElementById("SearchPhoneManufacturer"));
    populatemodels(document.getElementById("ManufacturerField"));
}

function populatemodels(select){
	var modelstodisplay = [];
	var childselect
	
	for(i =0;i<models.length;i++){
		if(models[i][1] == select.value){
			modelstodisplay.push(models[i][0]);
		}
	}
	
	if(select.id == "SearchPhoneManufacturer"){childselect = document.getElementById("SearchPhoneModel");}
	else{childselect = document.getElementById("ModelField");}
	
    for(x=childselect.options.length-1;x>=0;x--)
    {
        childselect.remove(x);
    }
    
	$("#"+childselect.id).select2({ data: modelstodisplay });
}

function clearTable() {
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

function clearFields() {
    document.getElementById("DateForm").reset();
    document.getElementById("DateAndModelForm").reset();
    document.getElementById("IMSIForm").reset();
}

/*function checkAccessLevel() {
	document.getElementById('loggedin').innerHTML = localStorage.getItem("username");
	var level = localStorage.getItem('usertype');
	if(level == 1){
        $('.SE').addClass("notUsed");
        $('.NME').addClass("notUsed");
        document.getElementById('typeofuser').innerHTML = "C.S.R"
    }
    else if(level == 2){
    	$('.NME').addClass("notUsed");
    	document.getElementById('typeofuser').innerHTML = "S.E"
    }
    else if(level == 3){
    	document.getElementById('typeofuser').innerHTML = "N.M.E"
    }
    else{
    	window.location.assign("login.html");   	
    }
}*/

$(function() {
    var allImsi = (function () {
        var json = null;
        $.ajax({
            'async': false,
            'global': false,
            'url': "rest/query/allimsi",
            'dataType': "json",
            'success': function (data) {
                json = data;
            }
        });
        return json;
    })();
    var allModels = (function () {
        var json = null;
        $.ajax({
            'async': false,
            'global': false,
            'url': "rest/query/allmodels",
            'dataType': "json",
            'success': function (data) {
                json = data;
            }
        });
        models = json;    
        return json;
    })();   
    $("#SearchIMSI").select2({ data: allImsi });
    $("#IMSIdateField").select2({ data: allImsi });   
    $("#IMSIbox").click(criteriaButton);
    $("#IMSIDatebox").click(criteriaButton)
    $("#Datebox").click(criteriaButton)
    $("#Modelbox").click(criteriaButton)
    $("#CauseClassbox").click(criteriaButton)
    $("#PhoneModelbox").click(criteriaButton)
    $("#IMSIunique").click(returnDateButton)
    $("#IMSIcount").click(returnDateButton)
	$("#Top10MOC").click(returnDateButton)
	$("#Top10IMSI").click(returnDateButton)
	$("#EventAndCause").click(returnIMSIButton)
	$("#UniqueCause").click(returnIMSIButton)
    $('#FromDate').datetimepicker({
    	format: "d-m-Y h:m"
    });  
    $('#ToDate').datetimepicker({
    	format: "d-m-Y h:m"
    });  
    $('#FromDateField').datetimepicker({
    	format: "d-m-Y h:m"
    });  
    $('#ToDateField').datetimepicker({
    	format: "d-m-Y h:m"
    });   
    $('#FromIDateField').datetimepicker({
    	format: "d-m-Y h:m"
    });  
    $('#ToIDateField').datetimepicker({
    	format: "d-m-Y h:m"
    });  
    $('input').on('keydown', function(event) {
        var x = event.which;
        if (x === 13) {
            event.preventDefault();
        }
    });
  });