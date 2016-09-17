package shopdaily.dev.accordhk.com.shopdaily.API_DataModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Owner on 9/17/2016.
 */
public class feed_comment_response {
    @SerializedName("shop_feed_comment_id")
    public String shop_feed_comment_id;
    @SerializedName("shop_feed_comment")
    public String shop_feed_comment;
    @SerializedName("shop_feed_id")
    public String shop_feed_id;
    @SerializedName("shop_id")
    public String shop_id;
    @SerializedName("member_id")
    public String member_id;
    @SerializedName("create_datetime")
    public String create_datetime;
}
