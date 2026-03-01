*This is a submission for the [DEV Weekend Challenge: Community](https://dev.to/challenges/weekend-2026-02-28)*

## The Community
A couple, a family, a class, a team, or an organization can all be a community. The primary inspiration for this project, however, is the parent-child relationship. It allows parents to pass down messages to their offspring, preserving them within digital photos instead of crystals—much like how Jor-El prepared Kal-El (Superman) for his journey to Earth.

Ultimately, the largest community this serves is humanity itself. Countless use cases can emerge across various groups, such as sending beautiful, encouraging messages to strangers. Like a digital "message in a bottle," these postcards can reach someone you've never met, bringing a smile to their face and making their day.

## What I Built
**Digital Postcards** is a web app that lets people across various communities exchange beautiful digital postcards with hidden secret messages baked invisibly into the image via steganography. To non-community members, the image looks like a regular photo, keeping the true message hidden in plain sight.

Close your eyes for a moment. What do you remember from your childhood? When the world turns cold and difficult, what is the memory that keeps you warm, kind, and fiercely tolerant? If you search for the root of your true inner strength, you will rarely find a fleeting notification or a quick text message. Instead, you will find beautiful words from beautiful people—the quiet encouragement of those who loved and cared for you, preserved like ink on paper.

This project was built to capture and protect that exact feeling. It is an application designed to help you express your deepest compassion and support to your beloved ones, transforming fleeting thoughts into eternal digital keepsakes. By stripping away the noise of modern apps, we brought back the intentionality of a physical postcard: just seven carefully chosen lines, the nostalgic weight of handwriting, and a quiet space to say, "I am thinking of you."

But true vulnerability requires a safe harbor. Because the most meaningful words are often the most private, we wove the ancient art of steganography into the very pixels of the digital stamp. This allows you to encode a hidden message entirely invisible to the naked eye. To the outside world, it is simply a beautiful, vintage greeting. But to the one who receives it, it unlocks a secret whisper—ensuring that externals cannot view the message, and only your recipient learns exactly what you hold in your heart.


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



<!-- Thanks for participating! -->


![Image description](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/5byk51tbrkxh059bniem.gif)