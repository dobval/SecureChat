console.log('app.js loaded');

const token = localStorage.getItem("jwt"); // assuming token was saved after login
//console.log("JWT token:", token); //Debugging login

let stompClient;

window.addEventListener("load", () => {
  if (window.location.pathname === "/chat") {
    connect();
  }
});

function login() { //used by login.html
	  console.log("login() called");
      const username = document.getElementById("loginUsername").value;
      const password = document.getElementById("loginPassword").value;

	  const loginUrl = `http://localhost:8080/auth/login`; //HARDCODED for DEBUG
	  console.log("Posting to", loginUrl);
	  
	  fetch(loginUrl, {
	      method: "POST",
	      headers: { "Content-Type": "application/json" },
	      body: JSON.stringify({ username, password }),
	    })
	      .then(res => {
	        console.log("Login response status:", res.status);
	        if (!res.ok) throw new Error(`HTTP ${res.status}`);
	        return res.json();
	      })
	      .then(data => {
	        console.log("Login succeeded, token:", data.token);
	        localStorage.setItem("jwt", data.token);
	        window.location.href = "/chat";
	      })
	      .catch(err => {
	        console.error("Login error:", err);
	        const errEl = document.getElementById("errorMessage");
	        if (errEl) errEl.innerText = "Login failed. Please try again.";
	      });
    }

function connect() {
	  const token = localStorage.getItem("jwt");
	  const currentPath = window.location.pathname;

	  if (!token && currentPath !== "/login") {
	    // looks better than alert
	    console.warn("Not logged in. Redirecting to login page...");

	    // small delay, page has fully loaded, user read the error
	    setTimeout(() => {
	      window.location.href = "/login";
	    }, 5000); // 5000ms
	    return;
	  }
	

  const socket = new SockJS(`/chat?token=${token}`);
  stompClient = Stomp.over(socket);

  stompClient.connect({}, () => {
    console.log("Connected");

    stompClient.subscribe("/topic/messages", (msg) => {
      const { sender, content, timestamp } = JSON.parse(msg.body);
      appendMessage(sender, content, timestamp);
    });
  });
}


function sendMessage() {
  const content = document.getElementById("content").value;
  stompClient.send("/app/send", {}, JSON.stringify({ content }));
}



function appendMessage(sender, content, timestamp) {
  const chatList = document.querySelector('#chat');
  const li       = document.createElement('li');
  const time     = timestamp ? new Date(timestamp).toLocaleTimeString() : '';
  li.textContent = `[${time}] ${sender}: ${content}`;
  chatList.appendChild(li);
}

// expose globally
//window.sendMessage = sendMessage;
