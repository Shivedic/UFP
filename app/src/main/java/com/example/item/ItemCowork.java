package com.example.item;

import java.util.ArrayList;

public class ItemCowork {

    private String pId;
    private String propertyPurpose;
    private String propertyName;
    private String propertyDescription;
    private String propertyPhone;
    private String propertyAddress;
    private String propertyMapLatitude;
    private String propertyMapLongitude;
    private String propertyThumbnailB;
    //private String propertyBed;
    //private String propertyBath;
    private String propertyStartTime;
    private String propertyEndTime;
    private String propertyWeekStart;
    private String propertyWeekEnd;
    private String propertyWeekClose;
    private String propertyArea;
    private String propertyAmenities;
    private String propertyPrice;
    private String rateAvg;
    private String propertyFloorPlan;
    private String propertyTotalRate;
    private String propertyFur;
    private String propertyOffers;
    private ArrayList<Review> reviews;

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public String getPropertyWeekClose() {
        return propertyWeekClose;
    }

    public void setPropertyWeekClose(String propertyWeekClose) {
        this.propertyWeekClose = propertyWeekClose;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public String getPropertyOffers() {
        return propertyOffers;
    }

    public void setPropertyOffers(String propertyOffers) {
        this.propertyOffers = propertyOffers;
    }

    public String getPropertyFur() {
        return propertyFur;
    }

    public void setPropertyFur(String propertyFur) {
        this.propertyFur = propertyFur;
    }

    public String getPropertyVery() {
        return propertyVery;
    }

    public void setPropertyVery(String propertyVery) {
        this.propertyVery = propertyVery;
    }

    private String propertyVery;
    boolean isRight=false;

    public boolean isRight() {
        return isRight;
    }
    public void setRight(boolean right) {
        isRight = right;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getpropertyTotalRate() {
        return propertyTotalRate;
    }

    public void setpropertyTotalRate(String propertyTotalRate) {
        this.propertyTotalRate = propertyTotalRate;
    }

    public String getPropertyPurpose() {
        return propertyPurpose;
    }

    public void setPropertyPurpose(String propertyPurpose) {
        this.propertyPurpose = propertyPurpose;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public String getPropertyPhone() {
        return propertyPhone;
    }

    public void setPropertyPhone(String propertyPhone) {
        this.propertyPhone = propertyPhone;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public String getPropertyMapLatitude() {
        return propertyMapLatitude;
    }

    public void setPropertyMapLatitude(String propertyMapLatitude) {
        this.propertyMapLatitude = propertyMapLatitude;
    }

    public String getPropertyMapLongitude() {
        return propertyMapLongitude;
    }

    public void setPropertyMapLongitude(String propertyMapLongitude) {
        this.propertyMapLongitude = propertyMapLongitude;
    }

    public String getPropertyThumbnailB() {
        return propertyThumbnailB;
    }

    public void setPropertyThumbnailB(String propertyThumbnailB) {
        this.propertyThumbnailB = propertyThumbnailB;
        }

    public String getPropertyStartTime() {
        return propertyStartTime;
    }

    public void setPropertyStartTime(String propertyStartTime) {
        this.propertyStartTime = propertyStartTime;
    }

    public String getPropertyEndTime() {
        return propertyEndTime;
    }

    public void setPropertyEndTime(String propertyEndTime) {
        this.propertyEndTime = propertyEndTime;
    }

    public String getPropertyWeekEnd() {
        return propertyWeekEnd;
    }

    public void setPropertyWeekEnd(String propertyWeekEnd) {
        this.propertyWeekEnd = propertyWeekEnd;
    }

    public String getPropertyWeekStart() {
        return propertyWeekStart;
    }

    public void setPropertyWeekStart(String propertyWeekStart) {
        this.propertyWeekStart = propertyWeekStart;
    }

    /*
                                    public String getPropertyBed() {
                                        return propertyBed;
                                    }

                                    public void setPropertyBed(String propertyBed) {
                                        this.propertyBed = propertyBed;
                                    }

                                    public String getPropertyBath() {
                                        return propertyBath;
                                    }

                                    public void setPropertyBath(String propertyBath) {
                                        this.propertyBath = propertyBath;
                                    }
                                */
    public String getPropertyArea() {
        return propertyArea;
    }

    public void setPropertyArea(String propertyArea) {
        this.propertyArea = propertyArea;
    }

    public String getPropertyAmenities() {
        return propertyAmenities;
    }

    public void setPropertyAmenities(String propertyAmenities) {
        this.propertyAmenities = propertyAmenities;
    }

    public String getPropertyPrice() {
        return propertyPrice;
    }

    public void setPropertyPrice(String propertyPrice) {
        this.propertyPrice = propertyPrice;
    }

    public String getRateAvg() {
        return rateAvg;
    }

    public void setRateAvg(String rateAvg) {
        this.rateAvg = rateAvg;
    }

    public String getPropertyFloorPlan() {
        return propertyFloorPlan;
    }

    public void setPropertyFloorPlan(String floorPlan) {
        this.propertyFloorPlan = floorPlan;
    }

}
