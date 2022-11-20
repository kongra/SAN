class User {
    email;
    firstName;
    lastName;
    constructor(email, firstName, lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    getRepr() {
        return `${this.email}, ${this.firstName}, ${this.lastName}`;
    }
}
export default User;
