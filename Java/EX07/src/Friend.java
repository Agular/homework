import java.util.ArrayList;
import java.util.List;

/**
 * The cool Friend class.
 * Add your buddies with the wicked class.
 */
public class Friend implements Comparable {
    /**
     * The last name of your Friend.
     */
    private String lastName;
    /**
     * The list including the first names of your Friends.
     */
    private List<String> names;
    /**
     * The alphabetically first name of the string.
     */
    private String firstName;

    /**
     * Set the last name of your Friend.
     *
     * @param lastName The last name of your friend.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Add the name(s) of your Friend.
     *
     * @param name The name of your friend.
     */
    public void addName(String name) {
        if (names == null) {
            names = new ArrayList<>();
            names.add(name);
            firstName += name;
        } else {
            this.names.add(name);
            firstName += " " + name;
        }
    }

    /**
     * Get the last name of your Friend.
     *
     * @return lastName.
     */
    public final String getLastName() {
        return lastName;
    }

    /**
     * Get all the names of your friends with their last name.
     *
     * @return All the names and the last name in the end.
     */
    public final String getFullName() {
        return names.toString().replaceAll(",", "").replace("[", "").replace("]", "") + " " + lastName;
    }

    /**
     * Get all the first names of your Friend.
     *
     * @return All the first names in a list.
     */
    public final List<String> getNames() {
        return names;
    }

    /**
     * The comparing algorithm for the class.
     *
     * @param o The object.
     * @return The value of the return.
     */
    public int compareTo(Object o) {
        // int u = (int) 4.5;
        Friend f = (Friend) o;
        // if this.name < o.name: return -1
        return this.firstName.toLowerCase().compareTo(f.firstName.toLowerCase());
    };
}
