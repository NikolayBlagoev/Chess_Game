import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Board classic = new Board();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            command=command.toUpperCase();
            classic.interpet(command);


        }
    }
}




