function disconnect() {
    if (window.confirm("This action will disconnect you. Click 'Ok' to sign out or 'Cancel' to remain connected.")) {
        location.href = '/';
    }
}
