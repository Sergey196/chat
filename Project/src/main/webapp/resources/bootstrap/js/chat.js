/*data = "";

	message = function() {
		 
			$.ajax({
				url:'send',
				type:'POST',
				data:{message:$("#message").val()},	
			});	
			document.getElementById('message').value='';
	}*/

function chang_user() {
	var value = document.getElementById('login_users_list').value;
	
	$.ajax({
		url:'loading_user',
		type:'POST',
		data:value,
    	success: function(user) {	
    		document.getElementById('user_info').value = user;
    		loading_img(value);
    	},
    	error: function(e) {
        	alert('Error: ' + e);
    	}
	});
}

function loading_img(value) {
	$.ajax({
		url:'loading_img',
		type:'POST',
		data:value,
    	success: function(image) {	
    		document.getElementById("user_img").src = image;
    	},
    	error: function(e) {
        	alert('Error: ' + e);
    	}
	});
}

/*function chang_user() {
	$.ajax({
		url:'loading_users',
		type:'POST',
		contentType: "text/plain; charset=utf-8",
    	success: function(data){
        	//alert( "Прибыли данные: " + data );
    		document.getElementById('all_messages').value = decodeURIComponent(escape(atob(data)));
    	},
    	error: function(e) {
        	alert('Error: ' + e);
    	}
	});
}*/

function moveRect(e) {
	
    var index = document.getElementById("login_users_list").selectedIndex;
    var option = document.getElementById("login_users_list").options;
    var length = document.getElementById("login_users_list").length;
    
	if(e.keyCode == 9) {
		if(option[index].index == length - 1) {
			document.getElementById("login_users_list").selectedIndex = "1";
			chang_user();
		} else {
			document.getElementById("login_users_list").selectedIndex = option[index].index + 1;
			chang_user();
		}
	}
}

addEventListener("keydown", moveRect);

function load_login_users_list() {
	document.getElementById("login_users_list").selectedIndex = "1";
	chang_user();
}

setTimeout(load_login_users_list, 200);
