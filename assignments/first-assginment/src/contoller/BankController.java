package contoller;

import service.BankService;

public class BankController implements Controller {
    BankService bankService = new BankService();
    public void run() {
        System.out.println("기능을 선택해주세요.");
        System.out.println("1. 신규 회원 등록     2. 신규 계좌 발급     3. 나가기");
        int workCode = sc.nextInt();
        bankService.run(workCode);
    }
}
