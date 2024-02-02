var sendButton = document.querySelector(`#send-button`)
var messageInput = document.querySelector(`#message-input`)

var restaurantId = messageInput.getAttribute('data-id')


function sendMessage() {
    console.log('Send button clicked')
    var review = {
        'restaurantId': restaurantId,
        'messageContents': messageInput.value
    }
    console.log('Review to be sent:', review);

    messageInput.value = ''
    fetch(`/restaurants/${restaurantId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(review)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Review actually sent:', data)
        });
}

sendButton.addEventListener('click', sendMessage)

messageInput.addEventListener('keydown', function (event) {
    if (event.key === 'Enter') {
        event.preventDefault()
        sendMessage()
    }
});

// setInterval(getMessages, 500)

// function getMessages() {
//     var conversationBox = document.querySelector('#review-box')
//     fetch(`/restaurants/${restaurantId}`)
//         .then(response => response.json())
//         .then(data => {
//             conversationBox.innerHTML = ''
//             data.forEach(review => {
//                 conversationBox.innerHTML += `<div>
// 			  <span><b>${review.user.firstName}</b>: ${review.review}</span>
// 			</div>`
//             })
//         })
// }
