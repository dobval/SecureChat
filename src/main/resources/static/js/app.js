console.log('app.js loaded');

const socket      = new SockJS('/chat');
const stompClient = StompJs.Stomp.over(socket);

stompClient.connect({}, frame => {
  console.log('STOMP Connected:', frame);
  stompClient.subscribe('/topic/messages', msg => {
    console.log('Message arrived:', msg.body);
    const { sender, content, timestamp } = JSON.parse(msg.body);
    appendMessage(sender, content, timestamp);
  });
}, error => {
  console.error('STOMP error:', error);
});

function sendMessage() {
  console.log('sendMessage() called');
  const sender  = document.querySelector('#sender').value;
  const content = document.querySelector('#content').value;
  console.log('Sending:', sender, content);
  stompClient.send('/app/send', {}, JSON.stringify({ sender, content }));
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
