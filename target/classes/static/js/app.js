function confirmerSuppression(id) {
    const modal = document.getElementById('modal');
    modal.style.display = 'flex';

    // Si l'utilisateur confirme la suppression
    document.getElementById('modal-yes').onclick = () => {
        fetch(`/supprimer/${id}`, { method: 'DELETE' })
            .then(response => response.ok ? location.reload() : alert('Erreur de suppression')) // Si la suppression réussit, recharge la page
            .catch(() => alert('Erreur de suppression')); // Erreur en cas d'échec
        modal.style.display = 'none';
    };

    // Si l'utilisateur annule la suppression
    document.getElementById('modal-no').onclick = () => {
        modal.style.display = 'none';
    };
}

function confirmerModification(id) {
    const modalModifier = document.getElementById('modal-modifier');
    modalModifier.style.display = 'flex';

    // Récupère les données du programmeur pour la modification
    fetch(`/programmeur/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erreur de récupération des données');
            }
            return response.json(); // Retourne les données en JSON
        })
        .then(data => {
            if (data) {
                // Remplir le formulaire de modification avec les données récupérées
                document.getElementById('modifier-id').value = data.id || '';
                document.getElementById('modifier-nom').value = data.nom || '';
                document.getElementById('modifier-prenom').value = data.prenom || '';
                document.getElementById('modifier-anNaissance').value = data.anNaissance || '';
                document.getElementById('modifier-salaire').value = data.salaire || '';
                document.getElementById('modifier-prime').value = data.prime || '';
                document.getElementById('modifier-pseudo').value = data.pseudo || '';
            }
        })
        .catch(error => {
            console.error('Erreur:', error);
            alert('Erreur de récupération des données');
        });

    // Soumettre le formulaire de modification
    document.getElementById('form-modifier').onsubmit = function (event) {
        event.preventDefault(); // Empêche la soumission par défaut

        // Récupère les valeurs du formulaire
        const id = document.getElementById('modifier-id').value;
        const nom = document.getElementById('modifier-nom').value;
        const prenom = document.getElementById('modifier-prenom').value;
        const anNaissance = document.getElementById('modifier-anNaissance').value;
        const salaire = document.getElementById('modifier-salaire').value;
        const prime = document.getElementById('modifier-prime').value;
        const pseudo = document.getElementById('modifier-pseudo').value;

        // Crée un objet avec les nouvelles données
        const programmeurModifie = {
            id: id,
            nom: nom,
            prenom: prenom,
            anNaissance: anNaissance,
            salaire: salaire,
            prime: prime,
            pseudo: pseudo
        };

        // Envoie les données modifiées au serveur
        fetch(`/modifier/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(programmeurModifie)
        })
            .then(response => response.ok ? location.reload() : alert('Erreur de mise à jour')) // Si la mise à jour réussit, recharge la page
            .catch(() => alert('Erreur de mise à jour')); // Erreur en cas d'échec

        modalModifier.style.display = 'none';
    };

    // Si l'utilisateur annule la modification
    document.getElementById('modal-modifier-no').onclick = function () {
        modalModifier.style.display = 'none';
    };
}

// Gestion des KPI
document.addEventListener("DOMContentLoaded", function () {
    // Récupération des données des programmeurs
    fetch('/data')
        .then(response => response.json())
        .then(data => {
            // Récupération des noms et salaires
            const programmeursNom = data.map(p => p.nom);
            const programmeursSalaire = data.map(p => p.salaire);

            // Mettre à jour le nombre de programmeurs
            document.getElementById('nombre-programmeurs').innerText = data.length;

            // Création du graphique des salaires
            const ctx = document.getElementById('salaireChart').getContext('2d');
            new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: programmeursNom,
                    datasets: [{
                        label: 'Salaire des programmeurs',
                        data: programmeursSalaire,
                        backgroundColor: 'rgba(54, 162, 235, 0.2)',
                        borderColor: 'rgba(54, 162, 235, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        })
        .catch(error => {
            console.error('Erreur de récupération des programmeurs:', error);
        });
});

// Ferme les modales si l'utilisateur clique en dehors
window.onclick = (event) => {
    const modalModifier = document.getElementById('modal-modifier');
    const modalSuppression = document.getElementById('modal');

    if (event.target === modalModifier || event.target === modalSuppression) {
        event.target.style.display = 'none';
    }
};
