package shopdaily.dev.accordhk.com.shopdaily.API_DataModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Owner on 8/25/2016.
 */
public class Login_Response_Data {
    @SerializedName("member_id")
    public String member_id;
    @SerializedName("member_email")
    public String member_email;
    @SerializedName("member_facebook_id")
    public String member_facebook_id;
    @SerializedName("member_password")
    public String member_password;
    @SerializedName("member_session")
    public String member_session;
    @SerializedName("member_status")
    public String member_status;
    @SerializedName("create_datetime")
    public String create_datetime;
    @SerializedName("last_login_datetime")
    public String last_login_datetime;
    @SerializedName("member_nick_name")
    public String member_nick_name;
    @SerializedName("member_gender")
    public String member_gender;
    @SerializedName("member_birthday")
    public String member_birthday;
    @SerializedName("member_profile_image")
    public String member_profile_image;
    @SerializedName("shop_id")
    public String shop_id;
    @SerializedName("push_flag")
    public String push_flag;
    @SerializedName("push_key_gcm")
    public String push_key_gcm;
    @SerializedName("push_token_string")
    public String push_token_string;
    @SerializedName("mobile_type")
    public String mobile_type;

//    @SerializedName("shop_re")
//    public Shop_Response shop_re;

    @SerializedName("package_coin_amount")
    public String package_coin_amount;
}
