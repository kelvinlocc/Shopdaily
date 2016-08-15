package shopdaily.dev.accordhk.com.shopdaily.Uility.expanable_listview;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by kyle.jablonski on 10/6/15.
 */
public class Item implements Parcelable {


    public String title;
    public String description;
    public boolean isExpanded;
    public String[] filter_item;
    public String[] filter_item_list;
    public String[] filter_item_price;
    public String[] filter_item_location;
    public String[] filter_item_category;


    public Item() {
    }

    public Item(Parcel in) {
        title = in.readString();
        description = in.readString();
        isExpanded = in.readInt() == 1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(isExpanded ? 1 : 0);
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public void setIsExpanded(boolean value) {
        isExpanded = value;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setFilter_item(String[] data) {
        filter_item = data;
    }
    public void setFilter_item_list (String [] date){filter_item_list = date;}
    public String[] getFilter_item_list(){return filter_item_list;}

    public void setFilter_item_price(String[] data) {
        filter_item_price = data;
    }

    public void setFilter_item_location(String[] data) {
        filter_item_location = data;
    }

    public void setFilter_item_category(String[] data) {
        filter_item_category = data;
    }

    public String[] getFilter_item() {
        return filter_item;
    }




}
