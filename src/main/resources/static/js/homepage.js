// JavaScript for Autocomplete Suggestions From Chat GPT - Figure Out What it Means Hahaha

document.addEventListener('DOMContentLoaded', function () {
    var searchInput = document.getElementById('searchInput');
    var suggestionsDiv = document.getElementById('suggestions');

    searchInput.addEventListener('input', function () {
        var keyword = searchInput.value.trim();
        if (keyword !== '') {
            // Send request to get suggestions
            var xhr = new XMLHttpRequest();
            xhr.open('GET', '/restaurants/suggestions?keyword=' + encodeURIComponent(keyword), true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    // Update suggestions div with received data
                    var suggestions = JSON.parse(xhr.responseText);
                    displaySuggestions(suggestions);
                }
            };
            xhr.send();
        }
    });

    function displaySuggestions(suggestions) {
        var suggestionsHtml = '<div class="suggestions-container">';
        suggestions.forEach(function (restaurant) {
            suggestionsHtml += '<a href="/restaurants/' + restaurant.id + '">' + restaurant.name + '</a>';
        });
        suggestionsHtml += '</div>';
        suggestionsDiv.innerHTML = suggestionsHtml;

        document.addEventListener('click', function (event) {
            var isClickInsideSuggestions = suggestionsDiv.contains(event.target);
            var isClickInsideSearchInput = searchInput.contains(event.target);

            if (!isClickInsideSuggestions && !isClickInsideSearchInput) {
                suggestionsDiv.innerHTML = ''; // Hide the suggestion box
            }
        });
    }
});