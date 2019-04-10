// function makeCounter(start) {
//   let   value   = start;
//   const counter = function() {
//     return value++;
//   };

//   return counter;
// }

const makeCounter = (value) => () => value++;
let c1 = makeCounter( 3);
let c2 = makeCounter(-3);
