$(document).ready(function() {
$("#alertSuccess").hide();
$("#alertError").hide();
});
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
/*var status = validateEmployeeForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }*/
// If valid------------------------
var type = ($("#hidEmployeeIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "EmployeeAPI",
 type : type,
 data : $("#formemployee").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onItemSaveComplete(response.responseText, status);
 }
 });
});
function onItemSaveComplete(response, status)
{
	//Your code
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	
	} else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else	
	{
	$("#alertError").text("Unknown error while saving..");
	$("#alertError").show();
	}
	
	$("#hidSupIDSave").val("");
	$("#formSupply")[0].reset();
}



$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidEmpIDSave").val($(this).closest("tr").find('#hidEmpIDUpdate').val());
	$("#employeeid").val($(this).closest("tr").find('td:eq(0)').text());
	$("#employeename").val($(this).closest("tr").find('td:eq(1)').text());
	$("#salary").val($(this).closest("tr").find('td:eq(2)').text());
	$("#contact").val($(this).closest("tr").find('td:eq(3)').text());
	$("#adress").val($(this).closest("tr").find('td:eq(4)').text());
	$("#nic").val($(this).closest("tr").find('td:eq(5)').text());

});


$(document).on("click", ".btnRemove", function(event)
{
$.ajax(
{
url : "EmployeeAPI",
type : "DELETE",
data : "Empid=" + $(this).data("Empid"),
dataType : "text",
complete : function(response, status)
{
onItemDeleteComplete(response.responseText, status);
}
});
});



function onItemDeleteComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully deleted.");
$("#alertSuccess").show();
$("#divItemsGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{
$("#alertError").text("Error while deleting.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while deleting..");
$("#alertError").show();
}
}

// CLIENT-MODEL==================================================================================
function validateItemForm() {
	// CODE
	if ($("#employeeid").val().trim() == "") {
		return "Insert employeeid.";
	}
	// NAME
	if ($("#employeename").val().trim() == "") {
		return "Insert employeename!";
	}
	if ($("#salary").val().trim() == "") {
		return "Insert employeesalary!";
    }
	// PRICE-------------------------------
	if ($("#supplyprice").val().trim() == "") {
		return "Insert supplyprice !";
	}
	// is numerical value
	var tmpPrice = $("#supplyprice").val().trim();
	if (!$.isNumeric(tmpPrice)) {
		return "Insert a numerical value for supplyprice !";
	}
	// convert to decimal price
	$("#supplyprice").val(parseFloat(tmpPrice).toFixed(2));
	// DESCRIPTION------------------------
	if ($("#total").val().trim() == "") {
		return "Insert supply quntity !";
		
	}
	if ($("#nic").val().trim() == "") {
		return "Insert employee nic !";
	}
	return true;
}
