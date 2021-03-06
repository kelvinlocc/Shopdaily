package shopdaily.dev.accordhk.com.shopdaily.Uility;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.Transformer;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Exchanger;

/**
 * Created by KelvinLo on 7/19/2016.
 */
public class API {
    //    private String serverIP = "192.168.0.105"; // my localhost
    //4 cat ip address:
    private String serverIP = "192.168.1.156";
    //    private String serverIP = "59.148.112.198"; // remote access
    // http://10.89.191.191:8080/test
    private String baseURL = "http://shopdailydev.accordhkcloudapi.com/api/";    //ustograph/";
    //    private String baseURL = "http://" + serverIP + ":3000/";    //loginapp-master_database
    private AQuery aq;
    private Context mContext;

    // 10.89.191.191/users/register
    public API(Context mContext) {
        this.mContext = mContext;
        aq = new AQuery(mContext);
    }

    public String getServerIP() {
        return baseURL;
    }

    public String getBaseURL() {
        return baseURL;
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
                String url = baseURL + "member_login";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", 654321);
                params.put("lang_id", 1);
                params.put("member_email", email);
                params.put("member_password", password);
                params.put("member_facebook_id", 0); // pass 0 if not login with facebook;
                ajaxPOSTCall(url, params, listener);
            }
        }).start();
    }

    public void member_registration(final String email, final String password, final String facebook_id, final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "member_registration";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", 654321);
                params.put("lang_id", 1);
                params.put("member_email", email);
                params.put("member_password", password);
                params.put("member_facebook_id", 0); // pass 0 if not login with facebook;
                ajaxPOSTCall(url, params, listener);

            }
        }).start();
    }

    public void member_profile_image_upload(final String member_session, final String upload_file, final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "member_profile_image_upload";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", 654321);
                params.put("lang_id", 1);
                params.put("member_session", member_session);
                params.put("upload_file", upload_file);
                ajaxPOSTCall(url, params, listener);

            }
        }).start();
    }


    public void shop_registration(final String member_session, final String shop_name_en, final String shop_name_tc, final String shop_name_sc, final String shop_contact_person, final String shop_br_number, final String shop_address_en, final String shop_address_tc, final String shop_address_sc, final String shop_location_x, final String shop_location_y, final String shop_operation_hour_en, final String shop_operation_hour_tc, final String shop_operation_hour_sc, final String shop_district, final String shop_category_list, final String shop_hash_list, final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "shop_registration";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", 654321);
                params.put("lang_id", 1);
                params.put("member_session", member_session);
                params.put("shop_name_en", shop_name_en);
                params.put("shop_name_tc", shop_name_tc);
                params.put("shop_name_sc", shop_name_sc);

                params.put("shop_contact_person", shop_contact_person);
                params.put("shop_br_number", shop_br_number);
                params.put("shop_address_en", shop_address_en);
                params.put("shop_address_tc", shop_address_tc);
                params.put("shop_address_sc", shop_address_sc);
                params.put("shop_location_x", shop_location_x);
                params.put("shop_location_y", shop_location_y);
                params.put("shop_operation_hour_en", shop_operation_hour_en);
                params.put("shop_operation_hour_tc", shop_operation_hour_tc);
                params.put("shop_operation_hour_sc", shop_operation_hour_sc);
                params.put("shop_category_list", shop_category_list);
                params.put("shop_hash_list", shop_hash_list);

                ajaxPOSTCall(url, params, listener);
            }
        }).start();
    }


    public void forgetPassword(final String email, final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "member_forgot_password";
                Map<String, Object> params = new HashMap<>();
                params.put("api_key", 654321);
                params.put("lang_id", 1);
                params.put("member_email", email);
                ajaxPOSTCall(url, params, listener);


            }
        }).start();
    }

    public void changePassword(final String member_session, final String member_new_password, final onAjaxFinishedListener listener) {
        new Thread((new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "member_change_password";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", 654321);
                params.put("lang_id", 1);
                params.put("member_session", member_session);
                params.put("member_new_password", member_new_password);
                ajaxPOSTCall(url, params, listener);

            }
        })).start();
    }

    public void getQA_list(final String api_key, final String lang_id, final String page_no, final String page_size, final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "qa_list";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", api_key);
                params.put("lang_id", lang_id);
                params.put("page_no", page_no);
                params.put("page_size", page_size);
                ajaxPOSTCall(url, params, listener);
            }
        }).start();
    }

    public void update_member_profile(final String api_key, final String lang_id, final String member_session, final String member_email, final String member_password, final String member_facebook_id, final String member_nick_name, final String member_gender, final String member_birthday, final String push_flag, final String shop_id, final String push_key_gcm, final String push_token_string, final String mobile_type, final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "member_profile";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", api_key);
                params.put("lang_id", lang_id);
                params.put("member_session", member_session);
                params.put("member_email", member_email);
                params.put("member_password", member_password);
                params.put("member_facebook_id", member_facebook_id);
                params.put("member_nick_name", member_nick_name);
                params.put("member_gender", member_gender);
                params.put("member_birthday", member_birthday);
                params.put("push_flag", push_flag);
                params.put("shop_id", shop_id);
                params.put("push_key_gcm", push_key_gcm);
                params.put("push_token_string", push_token_string);
                params.put("mobile_type", mobile_type);
                ajaxPOSTCall(url, params, listener);
            }
        }).start();
    }

    public void getShopList(final String api_key, final String lang_id, final String member_session, final String page_no, final String page_size, final String filter_price_range_from, final String filter_price_range_to, final String filter_shop_district, final String filter_category_list, final String filter_hottest_item, final String filter_hash_list, final String filter_keyword_list, final String filter_location_x, final String filter_location_y, final String filter_shop_owner, final String filter_distance_range, final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "shop_list";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", api_key);
                params.put("lang_id", lang_id);
                params.put("member_session", member_session);
                params.put("page_no", page_no);
                params.put("page_size", page_size);
                params.put("filter_price_range_from", filter_price_range_from);
                params.put("filter_price_range_to", filter_price_range_to);
                params.put("filter_shop_district", filter_shop_district);
                params.put("filter_category_list", filter_category_list);
                params.put("filter_hottest_item", filter_hottest_item);
                params.put("filter_hash_list", filter_hash_list);
                params.put("filter_keyword_list", filter_keyword_list);
                params.put("filter_location_x", filter_location_x);
                params.put("filter_location_y", filter_location_y);
                params.put("filter_shop_owner", filter_shop_owner);
                params.put("filter_distance_range", filter_distance_range);
                ajaxPOSTCall(url, params, listener);
            }
        }).start();
    }


    // haven't finish
    public void getBookmark(final String member_session, final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String url = baseURL + "shop_bookmark";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", 654321);
                params.put("lang_id", 1);
                params.put("member_session", member_session);
                params.put("shop_or_feed", 1); //1 is shop_re, 2 is feed
            }
        }

        ).start();
    }

    //get feed detail for comment
    public void getFeedComment(final String api_key, final String lang_id, final String member_session, final String shop_feed_id, final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String url = baseURL + "shop_feed_detail";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", api_key);
                params.put("lang_id", lang_id);
                params.put("member_session", member_session);
                params.put("shop_feed_id", shop_feed_id);
                ajaxPOSTCall(url, params, listener);
            }
        }

        ).start();
    }

    public void shopAction(final String api_key, final String lang_id, final String member_session, final String shop_id, final String shop_feed_id, final String shop_action, final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "shop_action";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", api_key);
                params.put("lang_id", lang_id);
                params.put("member_session", member_session);
                params.put("shop_id", shop_id);
                params.put("shop_feed_id", shop_feed_id);
                params.put("shop_action", shop_action);
                ajaxPOSTCall(url, params, listener);
            }
        }

        ).start();
    }


    // add a comment on feed
    public void shopComment(final String api_key, final String lang_id, final String member_session, final String shop_id, final String shop_feed_id, final String shop_comment, final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "shop_comment";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", api_key);
                params.put("lang_id", lang_id);
                params.put("member_session", member_session);
                params.put("shop_id", shop_id);
                params.put("shop_feed_id", shop_feed_id);
                params.put("shop_comment", shop_comment);
                ajaxPOSTCall(url, params, listener);
            }
        }
        ).start();
    }

    public void getFeedList(final String api_key, final String lang_id, final String member_session, final String page_no, final String page_size, final String shop_id, final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "shop_feed_list";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", api_key);
                params.put("lang_id", lang_id);
                params.put("member_session", member_session);
                params.put("page_no", page_no);
                params.put("page_size", page_size);
                params.put("shop_id", shop_id);
                ajaxPOSTCall(url, params, listener);
            }
        }

        ).start();
    }

    public void ShopBookmark(final String api_key, final String lang_id, final String member_session, final String shop_id, final String shop_feed_id, final String bookmark_action, final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "shop_bookmark";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", api_key);
                params.put("lang_id", lang_id);
                params.put("member_session", member_session);
                params.put("shop_id", shop_id);
                params.put("shop_feed_id", shop_feed_id);
                params.put("bookmark_action", bookmark_action);
                ajaxPOSTCall(url, params, listener);
            }
        }

        ).start();
    }

    public void getBookmarkList (final String api_key,final String lang_id,final String member_session,final String shop_or_feed,
                                 final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "shop_bookmark_list";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", api_key);
                params.put("lang_id", lang_id);
                params.put("member_session", member_session);
                params.put("shop_or_feed", shop_or_feed);
                ajaxPOSTCall(url, params, listener);
            }
        }

        ).start();
    }


    public void shopImageUpload (final String api_key,final String lang_id,final String member_session,final String shop_id,final String shop_feed_id,final String sequence_id,final String upload_file,
                                 final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "shop_image_upload";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", api_key);
                params.put("lang_id", lang_id);
                params.put("member_session", member_session);
                params.put("shop_id", shop_id);
                params.put("shop_feed_id", shop_feed_id);
                params.put("sequence_id", sequence_id);
                params.put("upload_file", upload_file);
                ajaxPOSTCall(url, params, listener);
            }
        }

        ).start();
    }

    public void shopFeedCreate (final String api_key,final String lang_id,final String member_session,final String feed_detail_en,final String feed_detail_tc,final String feed_detail_sc,final String start_datetime,final String end_datetime,final String original_price,final String special_price,final String discount_rate,final String feed_category_id,final String feed_hashtag, final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "shop_feed_create";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", api_key);
                params.put("lang_id", lang_id);
                params.put("member_session", member_session);
                params.put("feed_detail_en", feed_detail_en);
                params.put("feed_detail_tc", feed_detail_tc);
                params.put("feed_detail_sc", feed_detail_sc);
                params.put("start_datetime", start_datetime);
                params.put("end_datetime", end_datetime);
                params.put("original_price", original_price);
                params.put("special_price", special_price);
                params.put("discount_rate", discount_rate);
                params.put("feed_category_id", feed_category_id);
                params.put("feed_hashtag", feed_hashtag);
                ajaxPOSTCall(url, params, listener);
            }
        }

        ).start();
    }

    public void shopCampaign (final String api_key,final String lang_id,final String member_session,final String shop_id,final String campaign_title,final String campaign_start_datetime,final String shop_feed_id,
                              final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "shop_campaign";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", api_key);
                params.put("lang_id", lang_id);
                params.put("member_session", member_session);
                params.put("shop_id", shop_id);
                params.put("campaign_title", campaign_title);
                params.put("campaign_start_datetime", campaign_start_datetime);
                params.put("shop_feed_id", shop_feed_id);
                ajaxPOSTCall(url, params, listener);
            }
        }

        ).start();
    }

    public void myTimeline(final String api_key,final String lang_id,final String member_session,final String page_no,final String page_size,
                           final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "my_timeline";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", api_key);
                params.put("lang_id", lang_id);
                params.put("member_session", member_session);
                params.put("page_no", page_no);
                params.put("page_size", page_size);
                ajaxPOSTCall(url, params, listener);
            }
        }

        ).start();
    }

    public void memberFeedLikeList (final String api_key,final String lang_id,final String member_session,final String page_no,final String page_size,
                                    final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "member_feed_like_list";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", api_key);
                params.put("lang_id", lang_id);
                params.put("member_session", member_session);
                params.put("page_no", page_no);
                params.put("page_size", page_size);
                ajaxPOSTCall(url, params, listener);
            }
        }

        ).start();
    }

    public void purchaseTokens(final String api_key,final String lang_id,final String member_session,final String purchase_package,final String purchased_amount,final String purchase_note,final String appstore_transaction_id,
                               final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "token_purchased";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", api_key);
                params.put("lang_id", lang_id);
                params.put("member_session", member_session);
                params.put("api_key", api_key);
                params.put("lang_id", lang_id);
                params.put("member_session", member_session);
                params.put("purchase_package", purchase_package);
                params.put("purchased_amount", purchased_amount);
                params.put("purchase_note", purchase_note);
                params.put("appstore_transaction_id", appstore_transaction_id);
                ajaxPOSTCall(url, params, listener);
            }
        }

        ).start();
    }

    public void packageUsage (final String api_key,final String lang_id,final String member_session,final String package_function_id,final String remark,

                     final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "package_use";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", api_key);
                params.put("lang_id", lang_id);
                params.put("member_session", member_session);
                params.put("package_function_id", package_function_id);
                params.put("remark", remark);
                ajaxPOSTCall(url, params, listener);
            }
        }

        ).start();
    }
    public void getDistrictList (final String api_key,final String lang_id,final String page_no,final String page_size,
                                 final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "district_list";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", api_key);
                params.put("lang_id", lang_id);
                params.put("page_no", page_no);
                params.put("page_size", page_size);
                ajaxPOSTCall(url, params, listener);
            }
        }

        ).start();
    }
    public void getHashList (final String api_key,final String lang_id,final String page_no,final String page_size,final String hash_text,

                             final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "hash_list";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", api_key);
                params.put("lang_id", lang_id);
                params.put("page_no", page_no);
                params.put("page_size", page_size);
                params.put("hash_text", hash_text);
                ajaxPOSTCall(url, params, listener);
            }
        }

        ).start();
    }
    public void temp(final String api_key, final String lang_id, final String member_session, final String page_no, final String page_size, final String shop_id,
                     final onAjaxFinishedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = baseURL + "shop_bookmark";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("api_key", api_key);
                params.put("lang_id", lang_id);
                params.put("member_session", member_session);
                params.put("page_no", page_no);
                params.put("page_size", page_size);
                params.put("shop_id", shop_id);
                ajaxPOSTCall(url, params, listener);
            }
        }

        ).start();
    }



    // ajax call:::------------------------------------------

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
                                Log.i("check_", "listener != null");
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
                if (listener != null)
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
