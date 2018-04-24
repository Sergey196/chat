
function load() {
	connect();
}

var stompClient = null;

function connect() {
    var socket = new SockJS('/controller/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
    	document.getElementById('all_messages').value = '';
    	load_messages()
        stompClient.subscribe('/topic/greetings', function (greeting) {
        	showMessage(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
}

function message() {
    stompClient.send("/app/sent", {}, JSON.stringify({'message': $("#message").val(), 'user': $('#nick').text()}));
}

function showMessage(message) {
	document.getElementById('all_messages').value = message;
	document.getElementById('message').value = '';
}

function load_messages() {	
	$.ajax({
		url:'messages_list',
		type:'POST',
		contentType: "text/plain; charset=utf-8",
    	success: function(data) {
        	//alert( "Прибыли данные: " + data );
    		document.getElementById('all_messages').value = decodeURIComponent(escape(atob(data)));
    	},
    	error: function(e) {
        	alert('Error: ' + e);
    	}
	});
}




