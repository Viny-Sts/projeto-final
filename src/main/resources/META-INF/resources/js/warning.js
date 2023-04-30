// just warns the user about the ongoing disconnection
function disconnect(url) {
    const confirmed = window.confirm("This action will disconnect you. Click 'Ok' to sign out or 'Cancel' to remain connected.");

    if (confirmed) {
        location.href = url;

    } else {
        // the code won't do anything, user remains connected
    }
}
