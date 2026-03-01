document.addEventListener('DOMContentLoaded', () => {
    const fileInput = document.getElementById('file-upload');
    const animationToggle = document.getElementById('envelope-toggle');
    const cardImage = document.getElementById('card-image');
    const cardElement = document.getElementById('card-element');
    const form = document.getElementById('uploadImageForm');

    fileInput.addEventListener('change', function() {
        if (this.files && this.files.length > 0) {
            const file = this.files[0];
            cardImage.src = URL.createObjectURL(file);   //Loads the preview image instantly

            cardImage.onload = function() {
                animationToggle.checked = true;     //Triggers the Envelope Animation

                cardElement.addEventListener('transitionend', function handleTransition(event) {
                    if (event.propertyName === 'transform') {
                        console.log("Animation finished! Submitting form to server...");
                        form.submit();
                    }
                }, { once: true });
            };
        }
    });
});
