package org.vs.documents.postcards.dto;

/**
 * Response DTO returned after decoding a steganography image.
 * Contains the decoded message and status information.
 */
public class CardReadResponse {

    private String title;
    private String message;
    private String cardFrontImage;
    private String cardStampImage;

    private boolean messageFound;
    private String status;
    private String errorMessage;

    public CardReadResponse() {
    }

    public CardReadResponse(String message, boolean messageFound, String status) {
        this.message = message;
        this.messageFound = messageFound;
        this.status = status;
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

    public boolean isMessageFound() {
        return messageFound;
    }

    public void setMessageFound(boolean messageFound) {
        this.messageFound = messageFound;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCardFrontImage() { return cardFrontImage; }

    public void setCardFrontImage(String cardFrontImage) { this.cardFrontImage = cardFrontImage; }

    public String getCardStampImage() {
        return cardStampImage;
    }

    public void setCardStampImage(String cardStampImage) {
        this.cardStampImage = cardStampImage;
    }
}
