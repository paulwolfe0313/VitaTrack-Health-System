document.addEventListener('DOMContentLoaded', () => {
    const heroSection = document.querySelector('.hero');
    heroSection.style.opacity = 0;
    window.setTimeout(() => {
        heroSection.style.opacity = 1;
        heroSection.style.transition = 'opacity 2s ease-out';
    }, 100);

    document.getElementById('managePatients').addEventListener('click', () => {
        alert('Patient management portal coming soon!');
    });

    document.getElementById('manageBilling').addEventListener('click', () => {
        alert('Billing management system coming soon!');
    });
    
});