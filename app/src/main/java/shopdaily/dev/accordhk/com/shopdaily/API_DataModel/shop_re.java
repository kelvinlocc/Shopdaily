package shopdaily.dev.accordhk.com.shopdaily.API_DataModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Owner on 9/16/2016.
 */
public class shop_re {
    @SerializedName("shop_id")
    public String shop_id;
    @SerializedName("member_id")
    public String member_id;
    @SerializedName("shop_contact_person")
    public String shop_contact_person;
    @SerializedName("shop_br_number")
    public String shop_br_number;
    @SerializedName("shop_location_x")
    public String shop_location_x;
    @SerializedName("shop_location_y")
    public String shop_location_y;
    @SerializedName("shop_district")
    public String shop_district;
    @SerializedName("shop_category_list")
    public String shop_category_list;
    @SerializedName("shop_hash_list")
    public String shop_hash_list;
    @SerializedName("total_count_like")
    public String total_count_like;
    @SerializedName("total_count_cool")
    public String total_count_cool;
    @SerializedName("total_count_love")
    public String total_count_love;
    @SerializedName("total_count_share")
    public String total_count_share;
    @SerializedName("total_count_comment")
    public String total_count_comment;
    @SerializedName("total_count_view")
    public String total_count_view;
    @SerializedName("shop_level")
    public String shop_level;
    @SerializedName("shop_status")
    public String shop_status;
    @SerializedName("approval_date")
    public String approval_date;
    @SerializedName("create_datetime")
    public String create_datetime;
    @SerializedName("longitude")
    public String longitude;
    @SerializedName("latitude")
    public String latitude;
    @SerializedName("shop_name")
    public String shop_name;
    @SerializedName("shop_address")
    public String shop_address;
    @SerializedName("shop_operation_hour")
    public String shop_operation_hour;

    public shop_feed feed;

    public ArrayList<String> imageArray;

}
