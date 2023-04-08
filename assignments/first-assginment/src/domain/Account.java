package domain;

/**
 * 계좌번호(key), 비밀번호, 돈, 주인 id
 */
public class Account {

    private final Long accountNum;
    private final String password;
    private final Long balance;
    private final Long hostID; //fk

    public Account(Long accountNum, String password, Long hostID) {
        this.password = password;
        this.hostID = hostID;
        this.balance = 0L;
        this.accountNum = accountNum;
    }

    public Account(String password, Long hostID, Long balance, Long accountNum) {
        this.password = password;
        this.hostID = hostID;
        this.balance = balance;
        this.accountNum = accountNum;
    }

    public String getPassword() {
        return password;
    }

    public Long getBalance() {
        return balance;
    }

    public Long getHostID() {
        return hostID;
    }

}
