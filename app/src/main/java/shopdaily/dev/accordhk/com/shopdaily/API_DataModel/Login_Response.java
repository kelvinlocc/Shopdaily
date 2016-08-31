package shopdaily.dev.accordhk.com.shopdaily.API_DataModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Owner on 8/30/2016.
 */
public class Login_Response {

    @SerializedName("return_status")
    public int status;

    @SerializedName("data")
    public Login_Response_Data data;




}