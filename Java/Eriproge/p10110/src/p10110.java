import java.util.Scanner;

/**
 * @author: raluga
 * @author: alesmi
 */
class p10110 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long line = scanner.nextLong();
        while(line!=0){
            long sqrt =(long) Math.sqrt(line);
            if(sqrt*sqrt==line){
                System.out.println("yes");
            }else{
                System.out.println("no");
            }
            line = scanner.nextLong();
        }
    }
}
