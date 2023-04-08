package contoller;


public class MainController implements Controller{
    BankController bankController = new BankController();
    MemberController memberController = new MemberController();
    public void init() {
        System.out.println("이용자를 선택해 주십시오.  [1:은행원    2:고객]");
        int jobCode = sc.nextInt();
        if (jobCode == 1) {
            bankController.run();
        } else {
            memberController.run();
        }
    }

    public void run() {
        while (true){
            init();
        }
    }
}
