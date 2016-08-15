package shopdaily.dev.accordhk.com.shopdaily.DataModel;

import android.graphics.Bitmap;

public class GridViewDataModel {
    private String name="";
    private String location;
//    private Bitmap picture;


    public void setName(String name){
        this.name=name;
    }

    public void setLocation(String loc){
        this.location=loc;
    }

//    public void setPic(Bitmap bm){
//        this.picture=bm;
//    }


    public String getName(){
        return this.name;
    }

    public String getLocation(){
        return this.location;
    }

//    public Bitmap getPic(){
//        return this.picture;
//    }
}
