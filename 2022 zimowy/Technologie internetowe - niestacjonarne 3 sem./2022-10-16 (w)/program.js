let x = 1;
x += 1;
console.log(`Wartość x wynosi ${x}`);
function foo(y, s) {
    return (y + Number.parseFloat(s)) * 3;
}
// TYPE-INFERENCE (TYPE DEDUCTION)
// GRADUAL TYPING
const s1 = 'aaa';
console.log(typeof (s1));
const n1 = 123;
console.log(typeof (n1));
function goo(a, b) {
    if (b === 0) {
        // throw new Error('Pamiętaj ......., nie dziel przez 0');
        return null;
    }
    return 2 * (a / b);
}
console.log(`Wywołanie goo(0, 0) ${goo(0, 0)}`);
export { foo, goo };
