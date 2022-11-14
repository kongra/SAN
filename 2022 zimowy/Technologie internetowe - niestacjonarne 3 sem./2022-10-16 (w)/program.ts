import User from './user.js';

let x: number = 1;
x += 1;

console.log(`Wartość x wynosi ${x}`);

function foo(y: number, s: string): number {
  return (y + Number.parseFloat(s)) * 3;
}

// TYPE-INFERENCE (TYPE DEDUCTION)
// GRADUAL TYPING
const s1 = 'aaa';
console.log('--1', typeof (s1));

const n1 = 123;
console.log(typeof (n1));

type MaybeNumber = number | null;

function goo(a: number, b: number): MaybeNumber {
  if (b === 0) {
    // throw new Error('Pamiętaj ......., nie dziel przez 0');
    return null;
  }

  return 2 * (a / b);
}

const www: MaybeNumber = goo(3, 4);

console.log(`Wywołanie goo(0, 0) ${goo(0, 0)}`);

function moo(y: number): number | 'abcd' {
  if (y === 4) {
    return y + 2;
  }
  return 'abcd';
}

type SingleRoot = [number];
type TwoRoots = [number, number]; // Tuple
type NoRoots = [];

function quadraticRoots(a: number, b: number, c: number): SingleRoot | TwoRoots | NoRoots {
  const delta = b * b - 4 * a * c;
  if (delta < 0) {
    return [];
  } if (delta === 0) {
    return [-b / (2 * a)];
  }
  const x1 = (-b - Math.sqrt(delta)) / (2 * a);
  const x2 = (-b + Math.sqrt(delta)) / (2 * a);
  return [x1, x2];
}

const t1: [string, number] = ['John', 256];
console.log('--2', typeof t1);

const tab1 = [1, 2, 3, 4, 5];
tab1.forEach((y) => {
  console.log(y + 4);
});

type Color = 'red' | 'blue' | 'green';

type ColoredPoint = {
  x: number,
  y: number,
  color?: Color
};

const obj1: ColoredPoint = { x: 124, y: 256, color: 'red' };
const s2: number = obj1.x + obj1.y;
console.log(s2);

const obj2: ColoredPoint = { x: 12, y: 10 };
console.log(obj2);

console.log(obj2.color);

const obj3: Partial<ColoredPoint> = {};
console.log(obj3);

type Thickness = { thickness: number };

type ColoredPointWithThickness = ColoredPoint & Thickness;
const p1: ColoredPointWithThickness = {
  x: 1, y: 2, color: 'green', thickness: 2,
};
console.log(p1);

interface PointColored {
  x: number;
  y: number;
  color?: string;
}

const obj4: PointColored = { x: 12, y: 10 };
console.log(obj4);

interface PointColoredWithThickness extends PointColored {
  thickness: number;
}

const p2: PointColoredWithThickness = {
  x: 10, y: 5, color: 'red', thickness: 2,
};

console.log(p2.thickness);

const u1: User = new User('john@gmail.com', 'John', 'Kowalski');

console.log(u1.getRepr());

class B2C extends User {
  address: string;

  constructor(email: string, firstName: string, lastName: string, address: string) {
    super(email, firstName, lastName);
    this.address = address;
  }
}

const b2c: User = new B2C('adam@gmail.com', 'Adam', 'Kowalski', 'Piotrkowska 256, Łódź');
console.log(b2c);

type Printer<T> = (obj: T) => void;

function printArray<T>(arr: T[], printer: Printer<T>) {
  arr.forEach(printer);
}

printArray([1, 2, 3, 4], (obj) => console.log(obj + 123));

type UserPrinter<T extends User> = (obj: T) => void;

function printUserArray<T extends User>(arr: T[]) {
  arr.forEach((u) => console.log('--3', u.getRepr()));
}

printUserArray([u1, b2c]);

interface NewType<T extends NewType<T>> {
  value: T | undefined;
}

interface NewString extends NewType<NewString> {
}

const s3: NewString = { value: undefined };
s3.value = s3;
console.log(s3);

export {
  foo, goo, www, moo, quadraticRoots, ColoredPointWithThickness, PointColoredWithThickness,
  Color,
  B2C,
  UserPrinter,
  printUserArray,
  NewType,
  NewString,
};
