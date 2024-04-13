document.addEventListener('DOMContentLoaded', () => {
    

    const heroSection = document.querySelector('.hero');
    if (heroSection) { 
        heroSection.style.opacity = 0;
        window.setTimeout(() => {
            heroSection.style.opacity = 1;
            heroSection.style.transition = 'opacity 2s ease-out';
        }, 100);
    }
    
   
});
