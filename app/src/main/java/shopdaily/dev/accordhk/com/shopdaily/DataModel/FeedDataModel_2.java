/*
Names:  Lo Chi Chiu          Student ID: 2019 8281     Email address: ccload@ust.hk
Names:  Lee Wing Lam     Student ID: 20124034       Email address: wlleeac@ust.hk
Names:  Chan Kai Hong    Student ID: 20124591       Email address: khchanbc@ust.hk

 */
package shopdaily.dev.accordhk.com.shopdaily.DataModel;

public class FeedDataModel_2 {

    private String shopName;
    private boolean react;
    private Integer react_number_01;
    private Integer react_number_02;
    private Integer react_number_03;
    private String shop_location;
    private String shop_full_location;
    private double[] shop_coordinate = new double[2];
    private int user_shop_distance;
    private Boolean[] reactList = new Boolean[3];


    public void setShop_full_location(String value) {
        this.shop_full_location = value;
    }

    public void setUser_shop_distance(int value) {
        this.user_shop_distance = value;
    }

    public void setShopName(String name) {
        this.shopName = name;
    }

    public void setReact_number_01(Integer number) {
        this.react_number_01 = number;
    }

    public void setReact_number_02(Integer number) {
        this.react_number_02 = number;
    }

    public void setReact_number_03(Integer number) {
        this.react_number_03 = number;
    }

    public void setShop_location(String location) {
        this.shop_location = location;
    }

    public void setCoorindate(double X, double Y) {
        this.shop_coordinate[0] = X;
        this.shop_coordinate[1] = Y;
    }


    public Boolean[] getReactList (){
        return reactList;
    }
    public String getShop_full_location() {
        return this.shop_full_location;
    }

    public int getUser_shop_distance() {
        return this.user_shop_distance;
    }

    public String getShopName() {
        return this.shopName;
    }

    public Integer getReact_number_01() {
        return this.react_number_01;
    }

    public Integer getReact_number_02() {
        return this.react_number_02;
    }

    public Integer getReact_number_03() {
        return this.react_number_03;
    }

    public boolean isReact() {
        return react;
    }

    public String getShop_location() {
        return this.shop_location;
    }

    public double getShop_X_corrdinate() {
        return this.shop_coordinate[0];
    }

    ;

    public double getShop_Y_corrdinate() {
        return shop_coordinate[1];
    }

    ;

    public void restoreReactNumber() {
        react_number_01 = react_number_02 = react_number_03 = 0;
        for (int i=0;i< reactList.length;i++){
            reactList[i]=false;
        }
        react=false;
    }

    public void react(int react_number) {

        react = true;
        reactList[react_number]=true;
        switch (react_number) {
            case 0: {

                react_number_01++;
                break;
            }
            case 1: {
                react_number_02++;
                break;
            }
            case 2: {
                react_number_03++;
                break;
            }
        }
    }




}
