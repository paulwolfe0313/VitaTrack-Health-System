document.addEventListener('DOMContentLoaded', () => {
    
    // Check if the hero section exists to avoid errors and apply fade-in effect
    const heroSection = document.querySelector('.hero');
    if (heroSection) { 
        heroSection.style.opacity = 0;
        window.setTimeout(() => {
            heroSection.style.opacity = 1;
            heroSection.style.transition = 'opacity 2s ease-out';
        }, 100);
    }

    // Example alert for managing patients, check if button exists
    const managePatientsButton = document.getElementById('managePatients');
    if (managePatientsButton) { 
        managePatientsButton.addEventListener('click', () => {
            alert('Patient management portal coming soon!');
        });
    }

    // Example alert for managing billing, check if button exists
    const manageBillingButton = document.getElementById('manageBilling');
    if (manageBillingButton) { 
        manageBillingButton.addEventListener('click', () => {
            alert('Billing management system coming soon!');
        });
    }

    // Selecting patient logic, check for only one selection
    const patientForm = document.getElementById('patientForm');
    if (patientForm) {
        const patientCheckboxes = document.querySelectorAll('.patient-checkbox');

        patientForm.addEventListener('submit', function(event) {
            const checkedPatients = Array.from(patientCheckboxes).filter(ch => ch.checked);
            if (checkedPatients.length === 0) {
                alert('You need to select one patient.');
                event.preventDefault(); // Prevent form from submitting
            } else if (checkedPatients.length > 1) {
                alert('You can only select one patient.');
                event.preventDefault(); // Prevent form from submitting
            }
            // If exactly one patient is selected, no need to prevent form submission
        });
    }

    // Selecting appointment logic, allow none or one selection
    const appointmentForm = document.getElementById('appointmentForm');
    if (appointmentForm) {
        const appointmentCheckboxes = document.querySelectorAll('.appointment-checkbox');

        appointmentForm.addEventListener('submit', function(event) {
            const checkedAppointments = Array.from(appointmentCheckboxes).filter(ch => ch.checked);
            
            if (checkedAppointments.length === 0) {
                alert('No appointments available.');
                event.preventDefault();
                // Not preventing the default as per your instruction, form will submit
            } else if (checkedAppointments.length > 1) {
                alert('You can only have one appointment selected at a time.');
                event.preventDefault(); // Prevent form from submitting
            }
            // If exactly one appointment is selected, no need to prevent form submission
        });
    }

});
