package ClassesAndFunctions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FirstLevelDivision {
    int Division_ID;
    String Division_Name;
    int Country_ID;
    private final static ObservableList<FirstLevelDivision> allFirstLevelDivisions = FXCollections.observableArrayList();

    public FirstLevelDivision(int Division_ID, String Division_Name, int Country_ID) {
        this.Division_ID = Division_ID;
        this.Division_Name = Division_Name;
        this.Country_ID = Country_ID;
    }

    public int getCountry_ID() {
        return Country_ID;
    }

    public void setCountry_ID(int country_ID) {
        Country_ID = country_ID;
    }

    public int getDivision_ID() {
        return Division_ID;
    }

    public String getDivision_Name() {
        return Division_Name;
    }

    public void setDivision_ID(int division_ID) {
        Division_ID = division_ID;
    }

    public void setDivision_Name(String division_Name) {
        Division_Name = division_Name;
    }

    public static void addFirstLevelDivision(FirstLevelDivision newFirstLevelDivision) {
        allFirstLevelDivisions.add(newFirstLevelDivision);
    }

    public static ObservableList<FirstLevelDivision> getAllFirstLevelDivisions() {
        return allFirstLevelDivisions;
    }

    @Override
    public String toString() { return Division_Name; }

    public static ObservableList<FirstLevelDivision> filterByID(int ID) {
        return allFirstLevelDivisions.filtered(t -> t.Country_ID == ID);
    }

    public static FirstLevelDivision getDivisionbyDivisionID(int DivID) {
        int i = 0;
        while (i < getAllFirstLevelDivisions().size()) {
            if (getAllFirstLevelDivisions().get(i).Division_ID == DivID) {
                return getAllFirstLevelDivisions().get(i);
            }
            i++;
        }
        return null;
    }

    public static Country getCountryByDivisionId(int DivID) {
        int i = 0;
        int CountryID = 0;
        while (i < getAllFirstLevelDivisions().size()) {
            if (getAllFirstLevelDivisions().get(i).Division_ID == DivID) {
                 CountryID = getAllFirstLevelDivisions().get(i).Country_ID;
            }
            i++;
        }
        return Country.getAllCountries().get(CountryID-1);
    }
}
