# Digital Postcards

> A web application created to keep the members of a community joyful by exchanging warm messages, showing their compassion and care.

Send digital postcards with **invisible secret messages** hidden inside the image itself — using **LSB steganography**. The image looks like a regular photo. The words are hidden in plain sight, waiting for the right person to find them.

**Live demo:** [postcards.guardanangel.org](https://postcards.guardanangel.org)

---

## Features

- **Create a postcard** — upload a cover photo, flip to the back, write a secret message, and download a PNG with the message invisibly encoded inside
- **Read a postcard** — upload a received image; the hidden message is decoded and revealed on a flippable postcard
- **LSB Steganography** — pure Java implementation, no external libraries; message bits stored in the least significant bits of R, G, B channels
- **Postcard UI** — animated wax-seal envelope, CSS 3D card flip, stamp-style create button
- **Stateless** — no database; all processing happens in-memory per request

---

## How Steganography Works

Each character of the message is encoded in UTF-8. Every bit is stored in the **least significant bit** of one RGB color channel of a pixel. A 32-bit header encodes the message length first, followed by the message bits.

```
Pixel:  R = 1011010[1]   G = 1100110[0]   B = 0011011[1]
                   ↑               ↑               ↑
            bit stored here   bit stored here   bit stored here
```

The change is only ±1 in a 0–255 range — imperceptible to the human eye.

**Capacity:** `width × height × 3` bits — a 900×600 image can hold ~202 KB of text.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 8, Spring Boot 2.7 |
| Templating | Thymeleaf |
| Steganography | Pure Java — LSB on RGB channels |
| Frontend | HTML, CSS (3D transforms, envelope animation), vanilla JS |
| Build | Maven |
| Runtime | Embedded Tomcat — port `9090` |

---

## Getting Started

### Prerequisites

- Java 8+
- Maven 3+

### Run locally

```bash
cd digital-postcards-mini-app
mvn spring-boot:run
```

Open [http://localhost:9090](http://localhost:9090)

### Build a JAR

```bash
mvn clean package
java -jar target/digital-postcards-mini-app-0.0.1-SNAPSHOT.jar
```

---

## Usage

### Creating a Postcard

1. Go to [/create](http://localhost:9090/create)
2. Click the cover area and pick any image — a preview appears
3. The card auto-flips to the back after a moment — write your secret message and a title
4. Click the **CREATE** stamp — the encoded PNG downloads instantly
5. Share the image with anyone (email, chat, social media)

### Reading a Postcard

1. Go to [/](http://localhost:9090/) — a sealed envelope is waiting
2. Click the **wax seal** and upload the received image
3. The hidden message is decoded and shown on the back of the postcard

---

## Project Structure

```
src/main/java/org/vs/documents/postcards/
├── PostCardsApplication.java
├── controller/
│   └── CardController.java        # Routes: /, /create, /cards/read, /cards/create
├── service/
│   ├── CardService.java           # Orchestrates encode/decode, validates input
│   └── SteganographyService.java  # LSB encode & decode engine
└── dto/
    ├── CardCreateRequest.java
    ├── CardCreateResponse.java
    └── CardReadResponse.java

src/main/resources/
├── templates/
│   ├── home.html                  # Envelope page — upload to read
│   ├── create.html                # Dual-side postcard — write & encode
│   └── view.html                  # Dual-side postcard — reveal decoded message
└── static/
    ├── styles/postcards/          # CSS (card flip, envelope animation)
    └── scripts/postcards/         # JS (file upload trigger, auto-flip)
```

---

*Built for the [DEV Weekend Challenge: Community](https://dev.to/challenges/weekend-2026-02-28)*




