document.addEventListener('DOMContentLoaded', () => {
    

    const heroSection = document.querySelector('.hero');
    if (heroSection) { 
        heroSection.style.opacity = 0;
        window.setTimeout(() => {
            heroSection.style.opacity = 1;
            heroSection.style.transition = 'opacity 2s ease-out';
        }, 100);
    }

   
    const managePatientsButton = document.getElementById('managePatients');
    if (managePatientsButton) { 
        managePatientsButton.addEventListener('click', () => {
            alert('Patient management portal coming soon!');
        });
    }

    const manageBillingButton = document.getElementById('manageBilling');
    if (manageBillingButton) { 
        manageBillingButton.addEventListener('click', () => {
            alert('Billing management system coming soon!');
        });
    }

    
    const patientForm = document.getElementById('patientForm');
    if (patientForm) {
        const patientCheckboxes = document.querySelectorAll('.patient-checkbox');

        patientForm.addEventListener('submit', function(event) {
            const checkedPatients = Array.from(patientCheckboxes).filter(ch => ch.checked);
            if (checkedPatients.length === 0) {
                alert('You need to select one patient.');
                event.preventDefault(); 
            } else if (checkedPatients.length > 1) {
                alert('You can only select one patient.');
                event.preventDefault(); 
            }
        });
    }

    const appointmentForm = document.getElementById('appointmentForm');
    if (appointmentForm) {
        const appointmentCheckboxes = document.querySelectorAll('.appointment-checkbox');

        appointmentForm.addEventListener('submit', function(event) {
            const checkedAppointments = Array.from(appointmentCheckboxes).filter(ch => ch.checked);
            
            if (checkedAppointments.length === 0) {
                alert('No appointments available.');
                event.preventDefault();
            } else if (checkedAppointments.length > 1) {
                alert('You can only have one appointment selected at a time.');
                event.preventDefault(); 
            }
        });
    }
});
