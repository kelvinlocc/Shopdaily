package shopdaily.dev.accordhk.com.shopdaily.Uility;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by KelvinLo on 7/19/2016.
 */
public class RESTService {
    //    private String serverIP = "192.168.0.105"; // my localhost
    //4 cat ip address:
    private String serverIP = "192.168.1.156";
    //    private String serverIP = "59.148.112.198"; // remote access
    // http://10.89.191.191:8080/test
    private String baseURL = "http://" + serverIP + "/server_sd/";    //ustograph/";
    //    private String baseURL = "http://" + serverIP + ":3000/";    //loginapp-master_database
    private AQuery aq;
    private Context mContext;

    // 10.89.191.191/users/register
    public RESTService(Context mContext) {
        this.mContext = mContext;
        aq = new AQuery(mContext);
    }

    public void setServerIP(String newIP) {
        serverIP = newIP;
        baseURL = "http://" + serverIP + "/server_sd/"; //ustograph/";
    }

    public String getServerIP() {
        return serverIP;
    }

    public String getBaseURL() {
        return baseURL;
    }

    //http://localhost/USTograph_server/register.php
    public void register(final String email, final String password,
                         final onAjaxFinishedListener listener) {
        Log.i("check_", "register :"+email+" "+password);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    Log.i("check_", "Thread.sleep(500);");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
//                String url = baseURL + "users/register";
                String url = baseURL + "register.php";
                Log.i("check_", "baseURL is :" + url);

                Map<String, Object> params = new HashMap<String, Object>();
                params.put("email", email);
                params.put("password", password);
                ajaxPOSTCall(url, params, listener);
            }
        }).start();
    }

    public void login(final String email, final String password,
                      final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                //String url = baseURL + "login.php";
                String url = baseURL + "login.php";
//                String url = baseURL + "users/login";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("email", email);
                params.put("password", password);
                ajaxPOSTCall(url, params, listener);
            }
        }).start();
    }

    public void  get_nick_name (final String email,final onAjaxFinishedListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                String url = baseURL + "get_nick_name.php";
                Map<String,Object> params = new HashMap<String,Object>();
                params.put("email",email);
                ajaxPOSTCall(url,params,listener);
            }
        }).start();
    }


    private void ajaxPOSTCall(String url, Map<String, Object> params, final onAjaxFinishedListener listener) {
        Log.i("check_", " ajaxPOSTCall: " + url);

        aq.ajax(url, params, String.class, new AjaxCallback<String>() {
            @Override
            public void callback(final String url, final String json, final AjaxStatus status) {
                Activity mAct = (Activity) mContext;
                mAct.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (listener != null)
                            try {
                                Log.i("check_","listener != null");
                                listener.onFinished(url, json, status);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        else {
                            Log.i("check_", " listener == null");
                        }
                    }
                });
            }
        });
    }

    public interface onAjaxFinishedListener {
        public void onFinished(String url, String json, AjaxStatus status) throws JSONException;
    }


    //
    private void ajaxGETCall(String url, final onAjaxFinishedListener listener) {
        aq.ajax(url, String.class, new AjaxCallback<String>() {
            @Override
            public void callback(final String url, final String json, final AjaxStatus status) {
                Activity mAct = (Activity) mContext;
                mAct.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (listener != null)
                            try {
                                listener.onFinished(url, json, status);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                    }
                });
            }
        });
    }
    private void service_ajaxGETCall(String url, final onAjaxFinishedListener listener) {
        aq.ajax(url, String.class, new AjaxCallback<String>() {
            @Override
            public void callback(final String url, final String json, final AjaxStatus status) {
                if(listener != null)
                    try {
                        listener.onFinished(url, json, status);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        });
    }

    public interface onJSONListener {
        public void onFinished(String json);
    }


}
