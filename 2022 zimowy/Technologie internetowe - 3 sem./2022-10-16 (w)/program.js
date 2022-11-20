import User from './user.js';
let x = 1;
x += 1;
console.log(`Wartość x wynosi ${x}`);
function foo(y, s) {
    return (y + Number.parseFloat(s)) * 3;
}
// TYPE-INFERENCE (TYPE DEDUCTION)
// GRADUAL TYPING
const s1 = 'aaa';
console.log('--1', typeof (s1));
const n1 = 123;
console.log(typeof (n1));
function goo(a, b) {
    if (b === 0) {
        // throw new Error('Pamiętaj ......., nie dziel przez 0');
        return null;
    }
    return 2 * (a / b);
}
const www = goo(3, 4);
console.log(`Wywołanie goo(0, 0) ${goo(0, 0)}`);
function moo(y) {
    if (y === 4) {
        return y + 2;
    }
    return 'abcd';
}
function quadraticRoots(a, b, c) {
    const delta = b * b - 4 * a * c;
    if (delta < 0) {
        return [];
    }
    if (delta === 0) {
        return [-b / (2 * a)];
    }
    const x1 = (-b - Math.sqrt(delta)) / (2 * a);
    const x2 = (-b + Math.sqrt(delta)) / (2 * a);
    return [x1, x2];
}
const t1 = ['John', 256];
console.log('--2', typeof t1);
const tab1 = [1, 2, 3, 4, 5];
tab1.forEach((y) => {
    console.log(y + 4);
});
const obj1 = { x: 124, y: 256, color: 'red' };
const s2 = obj1.x + obj1.y;
console.log(s2);
const obj2 = { x: 12, y: 10 };
console.log(obj2);
console.log(obj2.color);
const obj3 = {};
console.log(obj3);
const p1 = {
    x: 1, y: 2, color: 'green', thickness: 2,
};
console.log(p1);
const obj4 = { x: 12, y: 10 };
console.log(obj4);
const p2 = {
    x: 10, y: 5, color: 'red', thickness: 2,
};
console.log(p2.thickness);
const u1 = new User('john@gmail.com', 'John', 'Kowalski');
console.log(u1.getRepr());
class B2C extends User {
    address;
    constructor(email, firstName, lastName, address) {
        super(email, firstName, lastName);
        this.address = address;
    }
}
const b2c = new B2C('adam@gmail.com', 'Adam', 'Kowalski', 'Piotrkowska 256, Łódź');
console.log(b2c);
function printArray(arr, printer) {
    arr.forEach(printer);
}
printArray([1, 2, 3, 4], (obj) => console.log(obj + 123));
function printUserArray(arr) {
    arr.forEach((u) => console.log('--3', u.getRepr()));
}
printUserArray([u1, b2c]);
const s3 = { value: undefined };
s3.value = s3;
console.log(s3);
var EColor;
(function (EColor) {
    EColor[EColor["Red"] = 0] = "Red";
    EColor[EColor["Green"] = 1] = "Green";
    EColor[EColor["Blue"] = 2] = "Blue";
})(EColor || (EColor = {}));
export { foo, goo, www, moo, quadraticRoots, B2C, printUserArray, };
