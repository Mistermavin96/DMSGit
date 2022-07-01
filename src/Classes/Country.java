package Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class contains the Countries in the database, as well as methods for handling them.
 */
public class Country {
    private final int Country_ID;
    private final String Country_Name;
    private final static ObservableList<Country> allCountries = FXCollections.observableArrayList();

    /**
     * This method is the Constructor for the Country class.
     * @param Country_ID The unique identifier for the Country.
     * @param Country_Name The Name associated with the Country.
     */
    public Country(int Country_ID, String Country_Name) {
        this.Country_ID = Country_ID;
        this.Country_Name = Country_Name;
    }

    /**
     * This method is a getter for the Country's unique identifier.
     * @return The ID related to the Country.
     */
    public int getCountry_ID() {
        return Country_ID;
    }

    /**
     * This method is a getter for the Country's name.
     * @return The name of the Country.
     */
    public String getCountry_Name() {
        return Country_Name;
    }

    /**
     * This method adds a Country to a list containing all countries.
     * @param newCountry The Country to be added to the list.
     */
    public static void addCountry(Country newCountry) {
        allCountries.add(newCountry);
    }

    /**
     * This method returns a list containing all Countries.
     * @return The observable list of all Countries.
     */
    public static ObservableList<Country> getAllCountries() { return allCountries; }

    /**
     * This method overrides the default ToString method to the Name of the Country, for usage in combo boxes.
     * @return The name of the Country.
     */
    @Override public String toString() { return Country_Name; }
}
