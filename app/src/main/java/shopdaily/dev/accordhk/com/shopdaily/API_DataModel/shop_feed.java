package shopdaily.dev.accordhk.com.shopdaily.API_DataModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Owner on 9/16/2016.
 */
public class shop_feed {
    @SerializedName("shop_feed_id")
    public String shop_feed_id;
    @SerializedName("shop_id")
    public String shop_id;
    @SerializedName("start_datetime")
    public String start_datetime;
    @SerializedName("end_time")
    public String end_time;
    @SerializedName("feed_status")
    public String feed_status;
    @SerializedName("create_datetime")
    public String create_datetime;
    @SerializedName("original_price")
    public String original_price;
    @SerializedName("special_price")
    public String special_price;
    @SerializedName("discount_rate")
    public String discount_rate;
    @SerializedName("feed_category_list")
    public String feed_category_list;
    @SerializedName("feed_hashtag")
    public String feed_hashtag;
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
    @SerializedName("ad_monthly_validity")
    public String ad_monthly_validity;
    @SerializedName("disable_datetime")
    public String disable_datetime;
    @SerializedName("feed_detail")
    public String feed_detail;

    public ArrayList<String> image_list;


}
