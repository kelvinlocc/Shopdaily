package shopdaily.dev.accordhk.com.shopdaily.API_DataModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Owner on 8/30/2016.
 */
public class Shop_Response {
    @SerializedName("shop_id")
    public String shop_id;
    @SerializedName("member_id")
    public String member_id;
    @SerializedName("shop_name_en")
    public String shop_name_en;
    @SerializedName("shop_name_tc")
    public String shop_name_tc;
    @SerializedName("shop_name_sc")
    public String shop_name_sc;
    @SerializedName("shop_contact_person")
    public String shop_contact_person;
    @SerializedName("shop_br_number")
    public String shop_br_number;
    @SerializedName("shop_address_en")
    public String shop_address_en;
    @SerializedName("shop_address_tc")
    public String shop_address_tc;
    @SerializedName("shop_address_sc")
    public String shop_address_sc;
    @SerializedName("shop_location_x")
    public String shop_location_x;
    @SerializedName("shop_location_y")
    public String shop_location_y;
    @SerializedName("shop_operation_hour_en")
    public String shop_operation_hour_en;
    @SerializedName("shop_operation_hour_tc")
    public String shop_operation_hour_tc;
    @SerializedName("shop_operation_hour_sc")
    public String shop_operation_hour_sc;
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

}
