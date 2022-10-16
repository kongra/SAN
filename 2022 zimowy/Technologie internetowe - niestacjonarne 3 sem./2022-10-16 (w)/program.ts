let x: number = 1;
x += 1;

console.log(`Wartość x wynosi ${x}`);

function foo(y: number, s: string): number {
  return (y + Number.parseFloat(s)) * 3;
}

// TYPE-INFERENCE (TYPE DEDUCTION)
// GRADUAL TYPING

export default foo;
