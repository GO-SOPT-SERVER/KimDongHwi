package domain;

/**
 * 이름, 주민번호, 계좌 ID
 */
public class Member {

    private Long id;
    private final String name;

    public Member(String name, Long personalNumber){
        this.name = name;
        this.id = personalNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
