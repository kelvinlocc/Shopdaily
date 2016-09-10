package shopdaily.dev.accordhk.com.shopdaily.API_DataModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Owner on 9/10/2016.
 */
public class QA_Response {
    @SerializedName("qa_id")
    public String qa_id;
    @SerializedName("question_detail")
    public String question_detail;
    @SerializedName("answer_detail")
    public String answer_detail;
}
