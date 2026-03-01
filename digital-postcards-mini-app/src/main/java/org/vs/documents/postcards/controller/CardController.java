package org.vs.documents.postcards.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.vs.documents.postcards.dto.CardCreateRequest;
import org.vs.documents.postcards.dto.CardCreateResponse;
import org.vs.documents.postcards.dto.CardReadResponse;
import org.vs.documents.postcards.service.CardService;

import java.io.IOException;
import java.util.Base64;


/**
 * Main controller for the Valentine Love Heart Cards application.
 *
 * Routes:
 *   GET  /             → Home page (upload image to read)
 *   POST /cards/read   → Process uploaded image, decode message, redirect to view
 *   GET  /view         → View decoded message
 *   GET  /create       → Create card form
 *   POST /cards/create → Encode message into image, return downloadable PNG
 */
@Controller
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    /**
     * Home page — upload an image to decode a hidden message.
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }

    /**
     * Process uploaded image: decode the hidden message and show it on the view page.
     */
    @PostMapping("/cards/read")
    public String readCard(@RequestParam(value = "image", required = false) MultipartFile imageFile, Model model) {
        String responseURI = "redirect:/";

        if (isValidInput(imageFile)) {
            CardReadResponse response = cardService.readCard(imageFile);
            addFrontImageToResponse(imageFile, response);
            model.addAttribute("response", response);
            responseURI = "view";
        }

        return responseURI;
//        return "redirect:/view";
    }

    private void addFrontImageToResponse(MultipartFile imageFile, CardReadResponse response) {
        String frontImageURI = "https://picsum.photos/id/1015/900/600"; //DEFAULT

        try {
            byte[] imageBytes = imageFile.getBytes();
            String base64String = Base64.getEncoder().encodeToString(imageBytes);
            String contentType = imageFile.getContentType(); // e.g., "image/png" or "image/jpeg"

            // Construct the full HTML-ready Data URI
            frontImageURI = "data:" + contentType + ";base64," + base64String;
        } catch (IOException e) {
            logExceptionError(e);
        }

        response.setCardFrontImage(frontImageURI);
    }

    private boolean isValidInput(MultipartFile imageFile) {
        return imageFile!=null && !imageFile.isEmpty();
    }

    private void logExceptionError(Exception e) {
        e.printStackTrace();
    }


    /**
     * Create page — form for writing a message and selecting a background image.
     */
    @GetMapping("/create")
    public String create(Model model) {
        CardCreateRequest cardCreateRequest = new CardCreateRequest();
        model.addAttribute("cardCreateRequest", cardCreateRequest);
        return "create";
    }

    @PostMapping("/cards/create")
    public ResponseEntity<byte[]> createCard(@ModelAttribute CardCreateRequest request) {
        CardCreateResponse response = cardService.createCard(request.getCardFrontImage(), request.getMessage());

        if ("SUCCESS".equals(response.getStatus()) && response.getImageData() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentDispositionFormData("attachment", response.getOriginalFilename());
            headers.setContentLength(response.getImageData().length);
            return new ResponseEntity<>(response.getImageData(), headers, HttpStatus.OK);
        } else {
            // On error, return a simple error response
            String errorMsg = response.getErrorMessage() != null
                    ? response.getErrorMessage()
                    : "Failed to create card.";
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(errorMsg.getBytes());
        }
    }

}
