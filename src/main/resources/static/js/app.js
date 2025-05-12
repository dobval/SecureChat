console.log('app.js loaded');

const token = localStorage.getItem("jwt"); // assuming token was saved after login

window.addEventListener("load", connect);

let stompClient;

function connect() {
  const token = localStorage.getItem("jwt");
  if (!token) {
    alert("Not logged in. Redirecting...");
    window.location.href = "/forms/login.html";
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
window.sendMessage = sendMessage;
