let x: number = 1;
x += 1;

console.log(`Wartość x wynosi ${x}`);

function foo(y: number, s: string): number {
  return (y + Number.parseFloat(s)) * 3;
}

// TYPE-INFERENCE (TYPE DEDUCTION)
// GRADUAL TYPING
const s1 = 'aaa';
console.log(typeof (s1));

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

export { foo, goo, www };
