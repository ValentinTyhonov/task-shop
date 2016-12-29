window.onload = loadAllCategories();

document.getElementById('save').onclick = function() {
		
	var category = {
		name: document.getElementById('categoryname').value
	}
	document.getElementById('categoryname').value = '';
	document.getElementById("save").disabled = true;
			
	$.ajax({
		url: 'savecategory?' + $('input[name=csrf_name]').val() + "=" + $('input[name=csrf_value]').val(),
		method: 'POST',
		contentType: 'application/json; charset=UTF-8',
		dataType: 'json',
		data: JSON.stringify(category),
		success: function(res) {
		   	var all = '';
		            	
		   	for (var i = 0; i < res.length; i++) {
		        all += '<div class="box">'+res[i].name +' <a onclick="deleteCategory(' + res[i].id + ')">delete</a></div><br>';
		    }
		    document.querySelector('.all').innerHTML = all;
		}
	})
	
}

document.getElementById('categoryname').oninput = function () {
	
	var name = document.getElementById('categoryname').value;
	
	if(name == '') {
		loadAllCategories();
		document.getElementById("save").disabled = true;
	} else {
		document.getElementById("save").disabled = false;
		$.ajax({
				
			url: 'searchcategory?' + $('input[name=csrf_name]').val() + "=" + $('input[name=csrf_value]').val(),
	        method: 'POST',
	        contentType: 'application/json; charset=UTF-8',
	        dataType: 'json',
	        data: name,
	        success: function(res) {
	          	var all = '';
	          	
	          	
	          
	          	for (var i = 0; i < res.length; i++) {
	          		if(name == res[i].name) {
	          			document.getElementById("save").disabled = true;
	          		} else {
	          			document.getElementById("save").disabled = false;
	          		}
	          		all += '<div class="box">'+res[i].name +' <a onclick="deleteCategory(' + res[i].id + ')">delete</a></div><br>';
	          	}
	            document.querySelector('.all').innerHTML = all;
	         }
			
		})
	}
}

function loadAllCategories() {
	document.getElementById("save").disabled = true;
    $.ajax({

        url: 'loadcategories?' + $('input[name=csrf_name]').val() + "=" + $('input[name=csrf_value]').val(),
        method: 'POST',
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: function (res) {
            var all = '';

            for (var i = 0; i < res.length; i++) {
                all += '<div class="box">'+res[i].name +' <a onclick="deleteCategory(' + res[i].id + ')">delete</a></div><br>';
            }
            document.querySelector('.all').innerHTML = all;
        }
    })

}

function deleteCategory(index) {
	document.getElementById('categoryname').value = '';
	$.ajax({

        url: 'deletecategory?' + $('input[name=csrf_name]').val() + "=" + $('input[name=csrf_value]').val(),
        method: 'DELETE',
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        data: '' + index,
        success: function (res) {
            var all = '';

            for (var i = 0; i < res.length; i++) {
                all += '<div class="box">'+res[i].name +' <a onclick="deleteCategory(' + res[i].id + ')">delete</a></div><br>';
            }
            document.querySelector('.all').innerHTML = all;
        }
    })
	
}