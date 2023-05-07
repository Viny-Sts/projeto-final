
function disconnect(url) {
    // just warns the user about the ongoing disconnection.

    const ok = window.confirm("This action will disconnect you. Click 'Ok' to sign out or 'Cancel' to remain connected.");
    if (ok) {
        location.href = url;
    } else {
        // the code won't do anything, the user will remain connected.
    }
}
