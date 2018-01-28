package praks12.message;

import praks12.person.Person;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Message {

    private String content;
    private Person author;
    private HashMap<String, Person> personLikes;
    private LocalDateTime createdDateTime;

    public Message(Person author, String content) {
        this.author = author;
        this.content = content;
        personLikes = new HashMap<>();
        createdDateTime = LocalDateTime.now();
    }

    public void like(Person person) {
        if (!personLikes.containsKey(person.getName())) {
            personLikes.put(person.getName(), person);
            System.out.println("Person: " + person.getName() + " liked message: " + content);
        }
    }
}
