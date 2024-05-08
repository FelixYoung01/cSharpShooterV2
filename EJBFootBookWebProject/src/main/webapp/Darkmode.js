document.addEventListener('DOMContentLoaded', (event) => {
    const toggleButton = document.querySelector('#dark-mode-toggle');

    const currentTheme = localStorage.getItem('theme');
    if (currentTheme === 'dark-mode') {
        document.body.classList.add('dark-mode');
    }

    toggleButton.addEventListener('click', function() {
        document.body.classList.toggle('dark-mode');

        let theme = 'light-mode';
        if (document.body.classList.contains('dark-mode')) {
            theme = 'dark-mode';
        }
        localStorage.setItem('theme', theme);
    });
});