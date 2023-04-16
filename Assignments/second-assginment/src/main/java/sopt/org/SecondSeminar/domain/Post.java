package sopt.org.SecondSeminar.domain;

import lombok.Getter;

@Getter
public class Post {

    private Long id;
    private User user;
    private String title;
    private String text;

    public Post(User user, String title, String text) {
        this.user = user;
        this.title = title;
        this.text = text;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id: " + this.id + "\n" +
                "gender: " + this.user.getGender() + "\n" +
                "name: " + this.user.getName() + "\n" +
                "contact: " + this.user.getContact() + "\n" +
                "age: " + this.user.getAge() + "\n" +
                "title: " + this.title + "\n" +
                "text: " + this.text + "\n";
    }
}
