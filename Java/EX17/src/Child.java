import java.util.ArrayList;
import java.util.List;

/**
 * EX17.
 */
public class Child {
    /**
     * Return the name of the child.
     *
     * @return name .
     */
    public String getName() {
        return name;
    }

    /***/
    String name;
    /***/
    List<Child> playmates;

    /**
     * The constructor for child class.
     *
     * @param name The name of the child.
     */
    public Child(String name) {
        this.name = name;
        this.playmates = new ArrayList<Child>();
    }

    /**
     * @param children THe children the child plays with.
     */
    public void playsWith(Child... children) {
        for (Child c : children) {
            if (c != null && c.name != null) {
                this.playmates.add(c);
            }
        }
    }

    /**
     * Return the children that the initial child plays with in the sandbox.
     *
     * @param child Child whose playmates we are looking for.
     * @return The list containing all the children playing with the first child.
     */
    public static List<Child> getSandbox(Child child) {
        System.out.println(child.name);
        List<Child> sandbox = new ArrayList<Child>();
        sandbox.add(child);
        for (Child playmate : child.playmates) {
            List<Child> playmates = getSandbox(playmate);
            sandbox.addAll(playmates);
        }
        return sandbox;
    }
}
