class User {
  email: string;

  firstName: string;

  lastName: string;

  constructor(email: string, firstName: string, lastName: string) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  getRepr() : string {
    return `${this.email}, ${this.firstName}, ${this.lastName}`;
  }
}

export default User;
