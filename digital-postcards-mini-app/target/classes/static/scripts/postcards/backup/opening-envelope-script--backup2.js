document.addEventListener('DOMContentLoaded', () => {
    const fileInput = document.getElementById('file-upload');
    const animationToggle = document.getElementById('envelope-toggle');
    const cardImage = document.getElementById('card-image');
    const cardElement = document.getElementById('card-element');
    const form = document.getElementById('readForm'); // We grab the form to read its action URL
    const modeToggle = document.getElementById('mode-toggle');

    // Optional: Listen for Mode Change
    if (modeToggle) {
        modeToggle.addEventListener('change', (e) => {
            console.log("Mode changed to:", e.target.checked ? "READ" : "WRITE");
        });
    }

    // --- MAIN UPLOAD LOGIC ---
    fileInput.addEventListener('change', function() {
        if (this.files && this.files.length > 0) {

            const file = this.files[0];

            // 1. Create a local preview URL
            const streamUrl = URL.createObjectURL(file);
            cardImage.src = streamUrl;

            // 2. Wait for the browser to render the image inside the DOM
            cardImage.onload = function() {

                // 3. Trigger the CSS Animation (Envelope Opens)
                animationToggle.checked = true;

                // 4. Listen strictly for the end of the animation
                cardElement.addEventListener('transitionend', function handleTransition(event) {

                    // CSS transitions fire multiple events (opacity, color, etc).
                    // We only care when the physical 'transform' (sliding up) finishes.
                    if (event.propertyName === 'transform') {
                        console.log("Animation completely finished. Starting POST request...");

                        // 5. Fire the background upload
                        performBackgroundUpload(file);
                    }
                }, { once: true }); // {once: true} ensures this event fires only exactly one time
            };
        }
    });

    // --- THE BACKGROUND POST REQUEST (Fetch API) ---
    function performBackgroundUpload(file) {

        // A. Build the Multipart Form Payload manually
        const formData = new FormData();

        // The name 'image' here MUST match your Spring Boot @RequestParam("image")
        formData.append('image', file);

        // Get the destination URL dynamically from your Thymeleaf form action
        const postUrl = form.getAttribute('action');

        console.log(`Sending file to ${postUrl}...`);

        // B. Send the request without reloading the page
        fetch(postUrl, {
            method: 'POST',
            body: formData // Fetch automatically sets the multipart/form-data boundary for you!
        })
            .then(response => {
                if (response.ok) {
                    // The server returned a 200 OK!
                    console.log("Server received the file successfully!");

                    // Depending on what your Spring controller returns (JSON, HTML string, etc),
                    // you can process it here. Or, you can manually redirect the user:
                    // window.location.href = "/success-page";
                } else {
                    console.error("Server responded with an error:", response.status);
                }
            })
            .catch(error => {
                // This catches network errors (e.g., server offline, CORS issues)
                console.error("Network upload failed:", error);
            });
    }
});