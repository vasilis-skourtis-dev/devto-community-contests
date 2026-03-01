        document.addEventListener('DOMContentLoaded', () => {
            const fileInput = document.getElementById('file-upload');
            const animationToggle = document.getElementById('envelope-toggle');
            const cardImage = document.getElementById('card-image');
            const cardElement = document.getElementById('card-element');
            const modeToggle = document.getElementById('mode-toggle');

            // Optional: Listen for Mode Change
            modeToggle.addEventListener('change', (e) => {
                console.log("Mode changed to:", e.target.checked ? "READ" : "WRITE");
                // You can add logic here (e.g., disable upload if in READ mode)
            });

            // File Upload Logic (Same as before)
            fileInput.addEventListener('change', function() {
                if (this.files && this.files.length > 0) {
                    const file = this.files[0];
                    const streamUrl = URL.createObjectURL(file);
                    cardImage.src = streamUrl;
                    cardImage.onload = function() {
                        animationToggle.checked = true;
                        cardElement.addEventListener('transitionend', () => {
                            console.log("Animation Finished. Ready to Upload.");
                        }, { once: true });
                    };

                    document.getElementById('readForm').submit();
                }
            });
        });