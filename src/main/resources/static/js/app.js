function confirmerSuppression(id) {
    const modal = document.getElementById('modal');
    modal.style.display = 'flex';

    // Si l'utilisateur confirme la suppression
    document.getElementById('modal-yes').onclick = () => {
        fetch(`/supprimer/${id}`, {method: 'DELETE'})
            .then(response => response.ok ? location.reload() : alert('Erreur de suppression'))  // Si la suppression réussit, recharge la page
            .catch(() => alert('Erreur de suppression'));  // Erreur en cas d'échec
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
            return response.json();  // Retourne les données en JSON
        })
        .then(data => {
            if (data) {
                // Remplie le formulaire de modification avec les données récupérées
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

    // Soumet le formulaire de modification
    document.getElementById('form-modifier').onsubmit = function (event) {
        event.preventDefault();  // Empêche la soumission par défaut

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
            .then(response => response.ok ? location.reload() : alert('Erreur de mise à jour'))  // Si la mise à jour réussit, recharge la page
            .catch(() => alert('Erreur de mise à jour'));  // Erreur en cas d'échec

        modalModifier.style.display = 'none';
    };

    // Si l'utilisateur annule la modification
    document.getElementById('modal-modifier-no').onclick = function () {
        modalModifier.style.display = 'none';
    };
}

function validateForm() {
    // Récupère les valeurs des champs du formulaire
    const nom = document.getElementById("nom").value;
    const prenom = document.getElementById("prenom").value;
    const anNaissance = document.getElementById("anNaissance").value;
    const salaire = document.getElementById("salaire").value;
    const prime = document.getElementById("prime").value;
    const pseudo = document.getElementById("pseudo").value;

    // Vérifie si tous les champs sont remplis
    if (!nom || !prenom || !anNaissance || !salaire || !prime || !pseudo) {
        alert("Tous les champs doivent être remplis.");
        return false;  // Empêche la soumission si un champ est vide
    }

    return true;
}

// Ferme les modales si l'utilisateur clique en dehors
window.onclick = (event) => {
    const modalModifier = document.getElementById('modal-modifier');
    const modalSuppression = document.getElementById('modal');

    if (event.target === modalModifier || event.target === modalSuppression) {
        event.target.style.display = 'none';
    }
};
