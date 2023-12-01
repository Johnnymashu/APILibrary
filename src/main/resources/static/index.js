$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/books/1"
    }).then(function(data) {
       $('.book-id').append(data.id);
       $('.book-title').append(data.title);
       $('.book-genre').append(data.genre);
       $('.book-author').append(data.author);
       $('.users').append(data.users);
    });
});