package Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class creates the First Level Division Object which represents states or provinces. The class also provides methods for handling these divisions. This class uses a lambda in the filterByID method.
 */
public class FirstLevelDivision {
    int Division_ID;
    String Division_Name;
    int Country_ID;
    private final static ObservableList<FirstLevelDivision> allFirstLevelDivisions = FXCollections.observableArrayList();

    /**
     * This method is the constructor for the FirstLevelDivision object.
     * @param Division_ID The unique identifier for the Division.
     * @param Division_Name The Name of the Division.
     * @param Country_ID The unique identifier for the Country the Division is in.
     */
    public FirstLevelDivision(int Division_ID, String Division_Name, int Country_ID) {
        this.Division_ID = Division_ID;
        this.Division_Name = Division_Name;
        this.Country_ID = Country_ID;
    }

    /**
     * This method is the getter for the unique identifier for the Country associated with this Division.
     * @return The Country ID for the Divison.
     */
    public int getCountry_ID() {
        return Country_ID;
    }

    /**
     * This method is the getter for the unique identifier for the Division.
     * @return The Division ID.
     */
    public int getDivision_ID() {
        return Division_ID;
    }

    /**
     * This method is the getter for the Name of the Division.
     * @return The Division Name.
     */
    public String getDivision_Name() {
        return Division_Name;
    }

    /**
     * This method adds the Division to the list of all Divisions.
     * @param newFirstLevelDivision The Division to be added to the list.
     */
    public static void addFirstLevelDivision(FirstLevelDivision newFirstLevelDivision) { allFirstLevelDivisions.add(newFirstLevelDivision); }

    /**
     * This method is the getter for the list of all the Divisions.
     * @return The observable list of all Divisions.
     */
    public static ObservableList<FirstLevelDivision> getAllFirstLevelDivisions() {
        return allFirstLevelDivisions;
    }

    /**
     * This method is an override of the default ToString method, modifying it to return the Divison Name, this is mostly for combo box support.
     * @return The Division Name.
     */
    @Override public String toString() { return Division_Name; }

    /**
     * This method filters the observable list using a lambda as a predicate to only divisions belonging to countries matching the ID.
     * @param ID The ID to filter to.
     * @return The observable list filtered to the ID.
     */
    public static ObservableList<FirstLevelDivision> filterByID(int ID) { return allFirstLevelDivisions.filtered(t -> t.Country_ID == ID); }

    /**
     * This method returns the Division itself based on its unique identifier
     * @param DivID The ID of the Division to be returned.
     * @return The Divison matching the ID, if none found, returns null.
     */
    public static FirstLevelDivision getDivisionbyDivisionID(int DivID) {
        for (int i = 0; i < getAllFirstLevelDivisions().size(); i++) {
            if (getAllFirstLevelDivisions().get(i).Division_ID == DivID) {
                return getAllFirstLevelDivisions().get(i);
            }
        }
        return null;
    }

    /**
     * This method returns the Country associated with the Divison.
     * @param DivID The Division ID to be checked for.
     * @return The Country associated with the Division.
     */
    public static Country getCountryByDivisionId(int DivID) {
        int CountryID = 0;
        for (int i = 0; i < getAllFirstLevelDivisions().size(); i++) {
            if (getAllFirstLevelDivisions().get(i).Division_ID == DivID) {
                CountryID = getAllFirstLevelDivisions().get(i).Country_ID;
            }
        }
        return Country.getAllCountries().get(CountryID-1);
    }
}