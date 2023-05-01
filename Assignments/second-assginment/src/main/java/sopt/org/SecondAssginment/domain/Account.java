package sopt.org.SecondAssginment.domain;

import lombok.Getter;
@Getter
public class Account {
    private Long id;
    private User user;
    private Long deposit;

    public Account(User user) {
        this.user = user;
        this.deposit = 0L;
    }

    public Account(User user, Long deposit) {
        this.user = user;
        this.deposit = deposit;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "accountNum: " + this.id + "\n" +
                "name: " + this.user.getName() + "\n" +
                "deposit: " + this.deposit +"\n";
    }
}
