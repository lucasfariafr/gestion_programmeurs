function confirmerSuppression(id) {
    const modal = document.getElementById('modal');
    modal.style.display = 'flex';

    document.getElementById('modal-yes').onclick = () => {
        fetch(`/supprimer/${id}`, {method: 'DELETE'})
            .then(response => response.ok ? location.reload() : alert('Erreur de suppression'))
            .catch(() => alert('Erreur de suppression'));
        modal.style.display = 'none';
    };

    document.getElementById('modal-no').onclick = () => {
        modal.style.display = 'none';
    };
}

window.onclick = (event) => {
    if (event.target === document.getElementById('modal')) {
        document.getElementById('modal').style.display = 'none';
    }
};
