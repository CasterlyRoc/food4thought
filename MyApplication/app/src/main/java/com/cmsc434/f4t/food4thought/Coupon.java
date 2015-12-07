package com.cmsc434.f4t.food4thought;

public class Coupon {

    private String mBusiness;
    private String mTitle;
    private String mDescription;
    private int mCost;
    private int mPosition;

    public Coupon(String business, String title, String description, int cost, int position) {

        this.mBusiness = business;
		this.mTitle = title;
		this.mDescription = description;
        this.mCost = cost;
        this.mPosition = position;
    }
    public String getBusiness() {

            return mBusiness;
    }
    public String getTitle() {

            return mTitle;
    }
    public String getDescription() {

            return mDescription;
    }
    public int getCost() {

        return mCost;
    }
    public int getPosition() {

        return mPosition;
    }
/*
   


    @Override
    public String toString() {

        return this.mText;
    }*/
}
