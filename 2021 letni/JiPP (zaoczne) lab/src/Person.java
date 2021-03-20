import java.util.Objects;

public class Person {

  private final String firstName;

  private final String lastName;

  private final String email;

  public Person(String firstName, String lastName, String email) {
    this.firstName = Objects.requireNonNull(firstName);
    this.lastName = Objects.requireNonNull(lastName);
    this.email = Objects.requireNonNull(email);
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Person)) return false;
    Person person = (Person) o;
    return email().equals(person.email());
  }

  @Override
  public final int hashCode() {
    return Objects.hash(email());
  }

  @Override
  public String toString() {
    return "Person{" +
        "firstName='" + firstName() + '\'' +
        ", lastName='" + lastName() + '\'' +
        ", email='" + email() + '\'' +
        '}';
  }

  public String firstName() {
    return firstName;
  }

  public String lastName() {
    return lastName;
  }

  public String email() {
    return email;
  }
}
