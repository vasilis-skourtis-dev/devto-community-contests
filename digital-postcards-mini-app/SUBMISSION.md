*This is a submission for the [DEV Weekend Challenge: Community](https://dev.to/challenges/weekend-2026-02-28)*

## The Community

The [DEV.to](https://dev.to) community — a space where developers connect, share ideas, learn, and support one another.

This app was built for the warm-hearted people in it: those who want to go beyond a comment or a reaction and send something a little more personal. A digital postcard, crafted with care, carrying words that only the right person will find.

## What I Built

 **Digital Postcards** is a web app that lets people of various communities exchange beautiful digital postcards with *hidden secret messages* baked invisibly into the image via steganography. The image looks like a regular photo to non community members, while the message is hidden in plain sight. A couple, a family, a class, a team, an organization could be a community. The main inspiration though is the parent-child relationship, allowing a parent to pass messages to his offspring(s), preserving them via digital photos instead of crystals, similarly to Jor-El and Kal-El. 

Close your eyes for a moment. What do you remember from your childhood? When the world turns cold and difficult, what is the memory that keeps you warm, kind, and fiercely tolerant? If you search for the root of your true inner strength, you will rarely find a fleeting notification or a quick text message. Instead, you will find beautiful words from beautiful people—the quiet encouragement of those who loved and cared for you, preserved like ink on paper.

**Digital Postcards** was built to capture and protect that exact feeling. It is an application designed to help you express your deepest compassion and support to your beloved ones, transforming fleeting thoughts into eternal digital keepsakes. By stripping away the noise of modern apps, we brought back the intentionality of a physical postcard: just seven carefully chosen lines, the nostalgic weight of handwriting, and a quiet space to say, "I am thinking of you."

But true vulnerability requires a safe harbor. Because the most meaningful words are often the most private, we wove the ancient art of steganography into the very pixels of the digital stamp. This allows you to encode a hidden message entirely invisible to the naked eye. To the outside world, it is simply a beautiful, vintage greeting. But to the one who receives it, it unlocks a secret whisper—ensuring that externals cannot view the message, and only your recipient learns exactly what you hold in your heart.

---

### Creating a Postcard

1. Go to the **Create** page
2. Upload any cover photo — a landscape, a portrait, a memory
3. The card flips to the back — write your secret message and give it a title
4. Click the stamp (**CREATE**) — the message is encoded invisibly into the image using LSB steganography
5. A PNG is downloaded — it looks like your photo, but it carries your words inside
6. Share it anywhere: email, chat, social media

### Reading a Postcard

1. On the home page, an animated sealed envelope waits
2. Click the **wax seal** and upload the received image
3. The app decodes any hidden message from the pixel data
4. The postcard opens — the card flips automatically to reveal the message on the back

---

The visuals lean fully into the physical postcard metaphor:
- An **animated envelope** with a wax seal on the home page
- A **flippable dual-sided card** on the create and view pages
- A **stamp-style CREATE button** on the back of the card

## Demo

- **Live app:** [postcards.guardanangel.org](https://postcards.guardanangel.org)

<!-- TODO: Add a screen recording / GIF / video walkthrough here -->

## Code

<!-- TODO: Replace with your GitHub repo below -->
<!-- {% github YOUR_GITHUB_REPO_URL %} -->

### Key files

| File | Role |
|---|---|
| `SteganographyService.java` | Pure Java LSB encode & decode engine — no external libraries |
| `CardController.java` | HTTP routes: upload handling, file download response |
| `CardService.java` | Orchestrates encode/decode, validates input |
| `home.html` | Envelope page — upload an image to read |
| `create.html` | Dual-side postcard — pick a cover, write and encode a message |
| `view.html` | Dual-side postcard — reveal the decoded message |

## How I Built It

| Layer | Technology |
|---|---|
| Backend | Java 8, Spring Boot 2.7 |
| Templating | Thymeleaf |
| Steganography | Pure Java — LSB on RGB pixel channels |
| Frontend | HTML, CSS (3D transforms, envelope animation), vanilla JS |
| Build | Maven |
| Runtime | Embedded Tomcat, port 9090 |
| Database | None — fully stateless, all processing in-memory |

### How the steganography works

Each character of the secret message is encoded in UTF-8. Every bit is stored in the **least significant bit** of one color channel (R, G, or B) of a pixel. Since the change is only ±1 in a 0–255 range, the visual difference is imperceptible to the human eye.

A **32-bit header** is written first to encode the message length, followed by the message bits themselves.

```
Pixel before encoding:  R=10110101  G=11001100  B=00110111
                                ↑           ↑           ↑
                         bit stored    bit stored    bit stored
```

Capacity: `image width × height × 3` bits — a 900×600 image can store roughly **202 KB** of text.

---

<!-- Thanks for participating! -->
