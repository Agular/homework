/**
 * Created by Ragnar on 08.03.2016.
 * Soldier class.
 */
public class SoldierOfFortune {
    /**
     * The first name of the Soldier.
     */
    private String firstName;
    /**
     * The codename of the Soldier.
     */
    private String codeName;
    /**
     * The last name of the Soldier.
     */
    private String lastName;
    /**
     * The number of missions completed by the Soldier.
     */
    private int numberOfMissionsCompleted = 0;

    /**
     * Return the first name of the soldier.
     *
     * @return firstName.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the first name of the Soldier.
     *
     * @param firstName The first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the codename of the soldier.
     *
     * @return codename.
     */
    public String getCodeName() {
        return codeName;
    }

    /**
     * Set the codename of the soldier.
     *
     * @param codeName The given codename of the Soldier.
     */
    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    /**
     * Get the last name of the Soldier.
     *
     * @return lastName.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the last name of the Soldier.
     *
     * @param lastName The given last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * The constructor to create a SoldierOfFortune class.
     */
    public SoldierOfFortune() {
    }

    /**
     * The custom constructor to create the class with given parameters.
     *
     * @param firstName Given first name.
     * @param codeName  Given code name.
     * @param lastName  Given last name.
     */
    public SoldierOfFortune(String firstName, String codeName, String lastName) {
        this.firstName = firstName;
        this.codeName = codeName;
        this.lastName = lastName;
    }

    /**
     * The hidden/secret method: DANCE!
     */
    public void dance() {
        System.out.println("*breaks some awesome moves*");
    }

    /**
     * Return the names of the soldier as a String.
     *
     * @return Full name of the Soldier.
     */
    public String toString() {
        if (firstName == null && codeName == null && lastName == null) {
            return "";
        } else {
            String fullName = firstName + " " + "\"" + codeName + "\"" + " " + lastName;
            return fullName.replaceAll(" +", " ");
        }
    }

    /**
     * Get the number of missions completed by the Soldier.
     *
     * @return numberOfMissionsCompleted.
     */
    public int getNumberOfMissionsCompleted() {
        return numberOfMissionsCompleted;
    }

    /**
     * Set the number of missions completed by the Soldier.
     *
     * @param number The number of missions completed by the soldier.
     */
    public void setNumberOfMissionsCompleted(int number) {
        numberOfMissionsCompleted = number;
    }
}
