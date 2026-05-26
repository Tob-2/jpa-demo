package int242.jpadao.utils;

public class Main {
    public static void main(String[] args) {
        ActorRepository actorRepository = new ActorRepository();
        while (true) {
            System.out.println("--Main Menu----------");
            System.out.println("1) Create new actor");
            System.out.println("2) Find actor by id");
            System.out.println("3) Delete actor by id");
            System.out.println("4) List all actor");
            System.out.println("0) Exit");
            System.out.println("---------------------");
            System.out.print("Enter your choice: ");
            Scanner scanner = new Scanner(System.in);
            Integer choice = scanner.nextInt();
            System.out.println("----------------");
        };

