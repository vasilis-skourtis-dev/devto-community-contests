package org.vs.documents.postcards.dto;


import org.springframework.web.multipart.MultipartFile;

public class CardCreateRequest {
    private String title;
    private String message;
//    private MultipartFile image; // Spring automatically maps the file here!
    private MultipartFile cardFrontImage; // Spring automatically maps the file here!
    private MultipartFile cardStampImage; // Spring automatically maps the file here!

    public CardCreateRequest() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MultipartFile getCardFrontImage() {
        return cardFrontImage;
    }

    public void setCardFrontImage(MultipartFile cardFrontImage) {
        this.cardFrontImage = cardFrontImage;
    }

    public MultipartFile getCardStampImage() {
        return cardStampImage;
    }

    public void setCardStampImage(MultipartFile cardStampImage) {
        this.cardStampImage = cardStampImage;
    }
}


