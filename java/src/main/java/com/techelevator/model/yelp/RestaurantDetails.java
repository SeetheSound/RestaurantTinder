package com.techelevator.model.yelp;

import java.util.List;

/**
 * Restaurant Details is the representation of the endpoint response to https://api.yelp.com/v3/businesses/{business_id};
 * We model it after Yelps response, then we use it on the Rest Controller endpoint to return business details.
 */

public class RestaurantDetails {
    private String id;
    private String alias;
    private String name;
    private String imageUrl;
    private boolean isClaimed;
    private boolean isClosed;
    private String url;
    private String phone;
    private String displayPhone;
    private int reviewCount;
    private List<Category> categories;
    private double rating;
    private  Location location;
    private List<String> photos;
    private String price;
    private List<Hours> hours;
    private List<String> transactions;

    //getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isClaimed() {
        return isClaimed;
    }

    public void setClaimed(boolean claimed) {
        isClaimed = claimed;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDisplayPhone() {
        return displayPhone;
    }

    public void setDisplayPhone(String displayPhone) {
        this.displayPhone = displayPhone;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Hours> getHours() {
        return hours;
    }

    public void setHours(List<Hours> hours) {
        this.hours = hours;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<String> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "RestaurantDetails{" +
                "id='" + id + '\'' +
                ", alias='" + alias + '\'' +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", isClaimed=" + isClaimed +
                ", isClosed=" + isClosed +
                ", url='" + url + '\'' +
                ", phone='" + phone + '\'' +
                ", displayPhone='" + displayPhone + '\'' +
                ", reviewCount=" + reviewCount +
                ", categories=" + categories +
                ", rating=" + rating +
                ", location=" + location +
                ", photos=" + photos +
                ", price='" + price + '\'' +
                ", hours=" + hours +
                ", transactions=" + transactions +
                '}';
    }
}
