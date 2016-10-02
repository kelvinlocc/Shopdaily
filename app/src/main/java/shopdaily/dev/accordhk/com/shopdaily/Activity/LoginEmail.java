package shopdaily.dev.accordhk.com.shopdaily.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.Login_Response;
import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.Login_Response_Data;
import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.QA_Response;
import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.Shop_Response;
import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.myTimeline_response;
import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.shop_feed;
import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.API;
import shopdaily.dev.accordhk.com.shopdaily.Uility.AsteriskPassword;
import shopdaily.dev.accordhk.com.shopdaily.Uility.MyPreApp;


/**
 * Created by KelvinLo on 6/10/2016.
 */
public class LoginEmail extends Activity {

    private static String TAG = "LoginEmail";
    ProgressDialog dialog = null;

    private API myApi;
    MyPreApp myPreApp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_email);
        myApi = new API(this);
        myPreApp = new MyPreApp();

        TextView forget_password = (TextView) findViewById(R.id.txtPassword);

        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialog = ProgressDialog.show(LoginEmail.this, "", "redirecting...");
                    Intent toForgotPassword = new Intent(LoginEmail.this, ForgotPassword.class);
                    startActivity(toForgotPassword);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        final EditText input_email = (EditText) findViewById(R.id.input_email);


        final EditText input_password = (EditText) findViewById(R.id.input_password);
        input_password.setTransformationMethod(new AsteriskPassword());

        Button loginButton = (Button) findViewById(R.id.btn_login_email);
        input_email.setText("user5@gmail.com");
        input_password.setText("123123");
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = input_email.getText().toString().trim();
                String password = input_password.getText().toString().trim();
                Log.i(TAG, "email: " + email + " password: " + password);
                input_email.setText("");
                input_password.setText("");

                try {
                    myApi.login(email, password, new API.onAjaxFinishedListener() {
                        @Override
                        public void onFinished(String url, String json, AjaxStatus status) throws JSONException {


                            Log.i("check_", "onFinished myApi.login json: " + json);
                            Gson gson = new Gson();
                            Login_Response login_response = gson.fromJson(json, Login_Response.class);
                            //save data from myApi
                            myPreApp.setLoginResponse(login_response);
//                            Log.i(TAG, "onFinished:... "+myPreApp.getLoginResponse().data.shop_re.shop_id);

                            if (login_response.status == 1) {
                                Toast.makeText(LoginEmail.this, "Login Successfully! ", Toast.LENGTH_LONG).show();

                                Intent NextActivity = new Intent(LoginEmail.this, MainActivity.class);
                                startActivity(NextActivity);
                                finish();

                            } else {
//                                Log.i(TAG, "onFinished: "+"");
                                Toast.makeText(LoginEmail.this, "your email or password is not correct", Toast.LENGTH_LONG).show();
                            }
                            JSONObject jsonObject = new JSONObject(json);
                            jsonObject = jsonObject.getJSONObject("data");
                            jsonObject = jsonObject.getJSONObject("shop");
                            if (jsonObject != null) {
                                Shop_Response shop_response = gson.fromJson(jsonObject.toString(), Shop_Response.class);
                                Log.i(TAG, "onFinished: shop_br_number " + shop_response.shop_br_number);
                                myPreApp.setShopResponse(shop_response);
                            }
                            fetchingDataFromServer();
                        }

                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    ///////////// fetching
    QA_Response response;
    ArrayList<QA_Response> myQAList;
    ArrayList<myTimeline_response> timelineList;
    ArrayList<shop_feed> my_feed_list;
    ArrayList<String> image_list;
    public void fetchingDataFromServer() {
        Log.i(TAG, "fetchingDataFromServer: ");
        String api_key = "654321";
        String lang_id = "1";
        String page_no = "1";
        String page_size = "100"; //// TODO: 10/2/2016
        final Login_Response_Data data = myPreApp.getLoginResponse().data;
        //01 -- ql_list
        //02 -- timeline
        //03 -- feed list;
        myQAList = new ArrayList<>();
        timelineList = new ArrayList<>();
        my_feed_list = new ArrayList<>();

        myApi.getQA_list(api_key, lang_id, page_no, page_size, new API.onAjaxFinishedListener() {
            @Override
            public void onFinished(String url, String json, AjaxStatus status) throws JSONException {
                Log.i(TAG, "onFinished:@getQA_list json: " + json);
                Log.i(TAG, "onFinished: data.member_session"+data.member_session);
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject temp = jsonArray.getJSONObject(i);
                    response = new QA_Response();
                    Gson gson = new Gson();
                    response = gson.fromJson(temp.toString(), QA_Response.class);

                    myQAList.add(response);
                    Log.i(TAG, "onFinished: " + temp.getString("qa_id"));
                    Log.i(TAG, "onFinished: " + temp.getString("question_detail"));
                }
                myPreApp.setQA_list(myQAList);
            }
        });

        myApi.myTimeline(api_key, lang_id, data.member_session, page_no, page_size, new API.onAjaxFinishedListener() {
            @Override
            public void onFinished(String url, String json, AjaxStatus status) throws JSONException {
                Log.i(TAG, "onFinished: @myTimeline json: "+json);
                Log.i(TAG, "onFinished: data.member_session"+data.member_session);
                Gson gson = new Gson();
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    myTimeline_response timeline = gson.fromJson(jsonArray.get(i).toString(),myTimeline_response.class);
                    Log.i(TAG, "onFinished: timeline.shop_feed_action_id: @i "+","+timeline.shop_feed_action_id);
                    timelineList.add(timeline);

                }
                myPreApp.setMyTimelineList(timelineList);
                for (int i = 0; i < myPreApp.getMyTimelineList().size(); i++) {
                    Log.i(TAG, "onFinished: myPreApp.getMyTimelineList().get(i).shop_feed_action_id "+myPreApp.getMyTimelineList().get(i).shop_feed_action_id);
                }
            }
        });

        myApi.getFeedList(api_key,lang_id,data.member_session,page_no,page_size,data.shop_id, new API.onAjaxFinishedListener() {
            @Override
            public void onFinished(String url, String json, AjaxStatus status) throws JSONException {
                Log.i(TAG, "onFinished: getFeedList @myfeed list json"+json);
                Log.i(TAG, "onFinished: data.member_session"+data.member_session);
                Gson gson = new Gson();
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                // loop all image
                for (int i = 0; i < jsonArray.length(); i++) {
                    shop_feed feed = gson.fromJson(jsonArray.get(i).toString(),shop_feed.class);
                    Log.i(TAG, "onFinished: feed.shop_feed_id"+feed.shop_feed_id);

                    JSONObject temp = jsonArray.getJSONObject(i);
                    JSONArray imageArray = temp.getJSONArray("images");

                    image_list = new ArrayList<String>();
                    for (int j = 0; j < imageArray.length(); j++) {
                        // loop all image
                        Log.i(TAG, "onFinished: imageArray.get(i)"+imageArray.get(j));
                        image_list.add(imageArray.get(j).toString());
                    }
                    feed.image_list = image_list;
                    my_feed_list.add(feed);
                }
                myPreApp.setMyFeed_list(my_feed_list);
                for (int i = 0; i < myPreApp.getMyFeed_list().size(); i++) {
                    Log.i(TAG, "onFinished: myPreApp.getMyFeed_list().get(i).shop_feed_id"+myPreApp.getMyFeed_list().get(i).shop_feed_id);
                    for (int j = 0; j < myPreApp.getMyFeed_list().get(i).image_list.size(); j++) {
                        Log.i(TAG, "onFinished: @@feed_id: "+myPreApp.getMyFeed_list().get(i).shop_feed_id+","+myPreApp.getMyFeed_list().get(i).image_list.get(j));
                    }
                }

            }
        });



    }






    @Override
    public void onBackPressed() {
        Intent NextActivity = new Intent(LoginEmail.this, LoginPage.class);
        startActivity(NextActivity);
        return;
    }

}
