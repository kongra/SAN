let x = 1;
x += 1;
console.log(`Wartość x wynosi ${x}`);
function foo(y, s) {
  return (y + Number.parseFloat(s)) * 3;
}
// TYPE-INFERENCE (TYPE DEDUCTION)
// GRADUAL TYPING
export default foo;
