document.addEventListener('DOMContentLoaded', (event) => {
    const toggleButton = document.querySelector('#dark-mode-toggle');

    toggleButton.addEventListener('click', function() {
        document.body.classList.toggle('dark-mode');
    });
});