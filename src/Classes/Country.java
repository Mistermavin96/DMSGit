package Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Country {
    private int Country_ID;
    private String Country_Name;
    private final static ObservableList<Country> allCountries = FXCollections.observableArrayList();

    public Country(int Country_ID, String Country_Name) {
        this.Country_ID = Country_ID;
        this.Country_Name = Country_Name;
    }

    public void setCountry_ID(int country_ID) {
        Country_ID = country_ID;
    }

    public void setCountry_Name(String country_Name) {
        Country_Name = country_Name;
    }

    public int getCountry_ID() {
        return Country_ID;
    }

    public String getCountry_Name() {
        return Country_Name;
    }

    public static void addCountry(Country newCountry) {
        allCountries.add(newCountry);
    }

    public static ObservableList<Country> getAllCountries() {
        return allCountries;
    }

    @Override
    public String toString() {
        return Country_Name;
    }
}
