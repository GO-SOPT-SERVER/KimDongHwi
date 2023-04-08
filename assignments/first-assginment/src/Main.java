import contoller.MainController;
import repository.AccountRepository;
import repository.MemberRepository;

public class Main {
    public static void main(String[] args) {
        MemberRepository.getInstance();
        AccountRepository.getInstance();
        MainController mainController = new MainController();

        mainController.run();


        System.out.println("Hello world!");
    }
}