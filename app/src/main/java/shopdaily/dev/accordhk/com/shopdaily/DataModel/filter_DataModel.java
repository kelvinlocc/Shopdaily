package shopdaily.dev.accordhk.com.shopdaily.DataModel;

/**
 * Created by KelvinLo on 7/7/2016.
 */
public class filter_DataModel {
    private Boolean isExpanded;

    private String top_bar_category;
    private String[] itemList;

    public filter_DataModel() {
    }

    public void setItemList(String[] data) {
        itemList = data;
    }

    public void setExpand(boolean setValue) {
        this.isExpanded = setValue;
    }

    public void setTop_bar_category(String value) {
        this.top_bar_category = value;
    }

    public String[] getItemList() {
        return this.itemList;
    }

    public String getTop_bar_category() {
        return this.top_bar_category;
    }

    public Boolean getIsExpanded() {
        return this.isExpanded;
    }


}