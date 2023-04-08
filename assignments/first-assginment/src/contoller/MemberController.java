package contoller;

import service.MemberService;
import service.TransactionService;

public class MemberController implements Controller{
    MemberService memberService = new MemberService();
    TransactionService transactionService = new TransactionService();

    public void run() {
        Long accountKey = memberService.run();
        if( accountKey > -1L){
            System.out.println("기능을 선택해주세요.");
            System.out.println("1. 예금 출금     2. 예금 입금     3. 송금     4. 나가기");
            int workCode = sc.nextInt();
            transactionService.run(workCode, accountKey);
        }
    }
}
