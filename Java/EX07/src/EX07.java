import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Uses the Friend class to make friends.
 * Has it own methods to search and read in the friend names from a file.
 */
public class EX07 {
    /**
     * List that holds all the friend variables.
     */
    private static List<Friend> friends;

    /**
     * Testing, testing.
     *
     * @param args XD.
     * @throws IOException The file was not found.
     */
    public static void main(String[] args) throws IOException {
        friends = readFriendsFromFile("example.txt");
        System.out.println(findFriendByLastName("Ain").getFullName()); // Ain Ain
        System.out.println(findFriendByLastName("Punn").getFullName()); // Baul Punn
        System.out.println(findFriendByLastName("Rukis").getFullName()); // Kaera Jaan Rukis
        System.out.println(findFriendByLastName("Tamm").getNames()); //[Drop, Table, User]
        System.out.println(findFriendByLastName("Kaera")); //null
        System.out.println(findFriendByLastName("Kaal").getFullName()); // mat Kaal
        System.out.println(findFriendByLastName("Baggins").getFullName());
    }

    /**
     * Read the friends from file and return each line as a friend class in a friends list.
     *
     * @param inputFilename The name of the file containing the friends.
     * @return The list containing all the Friend objects.
     * @throws IOException The file was not found.
     */
    public static List<Friend> readFriendsFromFile(String inputFilename) {
        if (inputFilename == null) {
            return null;
        }
        try {
            BufferedReader in = new BufferedReader(new FileReader(inputFilename));
            friends = new ArrayList<>();
            String line = in.readLine();
            while (line != null) {
                String[] names = line.trim().split("[ ]+");
                if (names.length < 2) {
                    line = in.readLine();
                    continue;
                }
                Friend friend = new Friend();
                friend.setLastName(names[names.length - 1]);
                for (int i = 0; i < names.length - 1; i++) {
                    friend.addName(names[i]);
                }
                friends.add(friend);
                line = in.readLine();
            }
            return friends;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Return the friend with the given lastName.
     *
     * @param lastName The search string.
     * @return The friend object if found. Null if the friend was not found.
     */
    public static Friend findFriendByLastName(String lastName) {
        if (lastName == null || friends == null) {
            return null;
        } else {
            Collections.sort(friends);
            for (int i = 0; i < friends.size(); i++) {
                if (friends.get(i).getLastName().equals(lastName)) {
                    return friends.get(i);
                }
            }
        }
        return null;
    }
}
