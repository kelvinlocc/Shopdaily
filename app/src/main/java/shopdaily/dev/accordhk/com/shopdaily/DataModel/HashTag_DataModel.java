package shopdaily.dev.accordhk.com.shopdaily.DataModel;

/**
 * Created by KelvinLo on 7/7/2016.
 */
public class HashTag_DataModel {
    private String rank;
    private String country;
    private String population;

    public HashTag_DataModel(String rank, String country, String population) {
        this.rank = rank;
        this.country = country;
        this.population = population;
    }

    public String getRank() {
        return this.rank;
    }

    public String getCountry() {
        return this.country;
    }

    public String getPopulation() {
        return this.population;
    }
}