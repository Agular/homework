public class p136 {
    public static void main(String[] args) {
            int counter = 1;
            int temp = 1;
            while (counter < 1500) {
                temp++;
                int jagatav = temp;
                while (jagatav % 2 == 0) {
                    jagatav = jagatav / 2;
                }
                while (jagatav % 3 == 0) {
                    jagatav = jagatav / 3;
                }
                while (jagatav % 5 == 0) {
                    jagatav = jagatav / 5;
                }
                if (jagatav == 1) {
                    counter++;
                }
                //System.out.println(temp);
            }
            System.out.println(temp);
    }
}