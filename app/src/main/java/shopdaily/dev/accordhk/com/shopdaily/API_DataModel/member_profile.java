package shopdaily.dev.accordhk.com.shopdaily.API_DataModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Owner on 9/13/2016.
 */
public class member_profile {
    @SerializedName("api_key")
    public String api_key;
    @SerializedName("lang_id")
    public String lang_id;
    @SerializedName("member_session")
    public String member_session;
    @SerializedName("member_email")
    public String member_email;
    @SerializedName("member_password")
    public String member_password;
    @SerializedName("member_facebook_id")
    public String member_facebook_id;
    @SerializedName("member_nick_name")
    public String member_nick_name;
    @SerializedName("member_gender")
    public String member_gender;
    @SerializedName("member_birthday")
    public String member_birthday;
    @SerializedName("push_flag")
    public String push_flag;
    @SerializedName("shop_id")
    public String shop_id;
    @SerializedName("push_key_gcm")
    public String push_key_gcm;
    @SerializedName("push_token_string")
    public String push_token_string;
    @SerializedName("mobile_type")
    public String mobile_type;
}
