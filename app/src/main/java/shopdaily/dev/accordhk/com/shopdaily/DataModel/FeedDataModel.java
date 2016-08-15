/*
Names:  Lo Chi Chiu          Student ID: 2019 8281     Email address: ccload@ust.hk
Names:  Lee Wing Lam     Student ID: 20124034       Email address: wlleeac@ust.hk
Names:  Chan Kai Hong    Student ID: 20124591       Email address: khchanbc@ust.hk

 */
package shopdaily.dev.accordhk.com.shopdaily.DataModel;

import android.content.Intent;
import android.content.res.TypedArray;

import java.io.Serializable;

public class FeedDataModel implements Serializable {

    private String shopName;
    private boolean react;
    private Integer react_number_01;
    private Integer react_number_02;
    private Integer react_number_03;
    private String shop_location;
    private String shop_full_location;
    private double[] shop_coordinate = new double[2];
    private String user_shop_distance;
    private Boolean[] reactList = new Boolean[3];

    // not completed:
    //phase 01:
    private int[] photo_list = new int[3];
//    private TypedArray myPhoto_list;

    private String store_info;
    private String[] hashTag_list = new String[8];
    private String original_price;
    private String discount_price;
    private String discount;
    private String product_category;
    private int comment;
    private int share;


    //phase 02:
    // comment_list:
    private Integer[] user_icon_list = new Integer[10];
    private String[] user_name_list = new String[10];
    private String[] user_comment_list = new String[10];
    private Integer[] user_timeline_list = new Integer[10];

    //////////////////////
    //<
    public void setComment(int data) {
        this.comment = data;
    }

    public void setShare(int data) {
        this.share = data;
    }

    public void setProduct_category(String data) {
        this.product_category = data;
    }
    public void setStore_info(String data) {
        this.store_info = data;
    }

    //>
    public void setPhoto_list(int[] data) {
        this.photo_list = data;
    }


    public void setHashTag_list(String[] data) {
        this.hashTag_list = data;
    }

    public void setOriginal_price(String value) {
        this.original_price = value;
    }

    public void setDiscount_price(String data) {
        this.discount_price = data;
    }

    public void setDiscount(String data) {
        this.discount = data;
    }


    public void setUser_icon_list(Integer[] data) {
        this.user_icon_list = data;
    }

    public void setUser_name_list(String[] data) {
        this.user_name_list = data;
    }

    public void setUser_comment_list(String[] date) {
        this.user_comment_list = date;
    }

    public void setUser_timeline_list(Integer[] data) {
        this.user_timeline_list = data;
    }

    ///////////////////////////////////////////////////////////
    public void setShop_full_location(String value) {
        this.shop_full_location = value;
    }

    public void setUser_shop_distance(String value) {
        this.user_shop_distance = value;
    }

    public void setShopName(String name) {
        this.shopName = name;
    }

    public void setReact_number_01(Integer number) {
        this.react_number_01 = number;
    }

    public void setReact_number_02(Integer number) {
        this.react_number_02 = number;
    }

    public void setReact_number_03(Integer number) {
        this.react_number_03 = number;
    }

    public void setShop_location(String location) {
        this.shop_location = location;
    }

    public void setCoordinate(double X, double Y) {
        this.shop_coordinate[0] = X;
        this.shop_coordinate[1] = Y;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public int getComment() {
        return this.comment;
    }

    public int getShare() {
        return this.share;
    }

    public int[] getPhoto_list() {
        return this.photo_list;
    }

    public String getStore_info() {
        return this.store_info;
    }

    public String[] getHashTag_list() {
        return this.hashTag_list;
    }

    public String getOriginal_price() {
        return this.original_price;
    }

    public String getDiscount_price() {
        return this.discount_price;
    }

    public String getDiscount() {
        return this.discount;
    }

    public String getProduct_category() {
        return this.product_category;
    }

    public Integer[] getUser_icon_list() {
        return this.user_icon_list;
    }

    public String[] getUser_name_list() {
        return this.user_name_list;
    }

    public String[] getUser_comment_list() {
        return this.user_comment_list;
    }

    public Integer[] getUser_timeline_list() {
        return this.user_timeline_list;
    }

    ///////////////////////////////////////////////////


    public Boolean[] getReactList() {
        return reactList;
    }

    public String getShop_full_location() {
        return this.shop_full_location;
    }

    public String getUser_shop_distance() {
        return this.user_shop_distance;
    }

    public String getShopName() {
        return this.shopName;
    }

    public Integer getReact_number_01() {
        return this.react_number_01;
    }

    public Integer getReact_number_02() {
        return this.react_number_02;
    }

    public Integer getReact_number_03() {
        return this.react_number_03;
    }

    public boolean isReact() {
        return react;
    }

    public String getShop_location() {
        return this.shop_location;
    }

    public double getShop_X_coordinate() {
        return this.shop_coordinate[0];
    }


    public double getShop_Y_coordinate() {
        return shop_coordinate[1];
    }


    public void restoreReactNumber() {
        react_number_01 = react_number_02 = react_number_03 = 0;
        for (int i = 0; i < reactList.length; i++) {
            reactList[i] = false;
        }
        react = false;
    }

    public void react(int react_number) {

        react = true;
        reactList[react_number] = true;
        switch (react_number) {
            case 0: {

                react_number_01++;
                break;
            }
            case 1: {
                react_number_02++;
                break;
            }
            case 2: {
                react_number_03++;
                break;
            }
        }
    }


}
