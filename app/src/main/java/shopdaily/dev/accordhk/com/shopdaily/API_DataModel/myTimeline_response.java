package shopdaily.dev.accordhk.com.shopdaily.API_DataModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Owner on 10/2/2016.
 */

public class myTimeline_response {
    @SerializedName("shop_id")
    public String shop_id;
    @SerializedName("shop_feed_action_id")
    public String shop_feed_action_id;
    @SerializedName("shop_feed_id")
    public String shop_feed_id;
    @SerializedName("action_type")
    public String action_type;
    @SerializedName("member_id")
    public String member_id;
    @SerializedName("create_datetime")
    public String create_datetime;
    @SerializedName("campaign_id")
    public String campaign_id;
    @SerializedName("campaign_title")
    public String campaign_title;
    @SerializedName("campaign_readtime")
    public String campaign_readtime;
    @SerializedName("member_profile_image")
    public String member_profile_image;
    @SerializedName("member_shop_id")
    public String member_shop_id;
    public String myImages;
}
