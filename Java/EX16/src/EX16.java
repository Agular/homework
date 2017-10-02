/**
 * CREATED BY RALUGA ON 21.04.2016.
 * THANKS FOR THE TEMPLATE.
 * BEGINNING OF THE END.
 */
public class EX16 {
    /**
     * @param n THe given number.
     * @return The occurrences of 9 and 8 in the initial number.
     */
    public static int count98(int n) {
        dance();
        final int zero = 0;
        final int nine = 9;
        final int ten = 10;
        final int eight = 8;
        final int one = 1;
        if (n == zero) {
            return zero;
        } else {
            if (n % ten == nine || n % ten == eight) {
                return one + count98(n / ten);
            } else {
                return count98(n / ten);
            }
        }
    }

    /**
     * @param n THe given number.
     * @return The occurrences of 9 and 8 in the initial number
     * and if they occur by adding two higher positions.
     */
    public static int count98Harder(int n) {
        dance();
        final int nine = 9;
        final int ten = 10;
        final int eight = 8;
        final int hundred = 100;
        final int one = 1;
        final int two = 2;
        final int zero = 0;
        if (n == zero) {
            return zero;
        } else {
            if (n % ten == nine || n % ten == eight) {
                if (n / hundred > zero) {
                    int l1 = (n / ten) % ten;
                    int l2 = (n / hundred) % ten;
                    if (l1 + l2 == nine || l1 + l2 == eight) {
                        return two + count98Harder(n / ten);
                    } else {
                        return one + count98Harder(n / ten);
                    }
                } else {
                    return one + count98Harder(n / ten);
                }
            } else {
                return count98Harder(n / ten);
            }
        }
    }

    /**
     * FOR TESTING.
     * TEST TEST TEST TEST CAPS LOCK TEST TEST.
     *
     * @param args STUFF
     */
    public static void main(String[] args) {
        //System.out.println(count98(9919)); // => 3
        //System.out.println(count98Harder(90818)); // => 5
        //TEST YOUR IMPLEMENTATION HERE
        //dance();
        //dance();
    }

    /***/
    public static void dance() {
        System.out.println("*does some cool samba moves*");
    }
}
