function euler1() {
  var i = 0;
  let sum = 0;

  while (i < 1000) {
    if (i % 3 == 0 || i % 5 == 0) {
      sum += i;
    }
    // console.log(i);
    i += 1;
  }
  return sum;
}

var x = 4;
let y = 2;

function foo() {
  // console.log(i);
  for (let i = 0; i < 10; i++) {
    console.log(i);
    {
      let p = 3;
    }
  }
  // console.log(p);
}

let value = 0;
function next() {
  let current = value;
  value += 1;
  return current;
}

function makeSeq() {
  let value = 0;
  let next  = function() {
    let current = value;
    value += 1;
    return current;
  };

  return next;
}

var s1 = makeSeq();
var s2 = makeSeq();

const makeSequence = () => {
  let value = 0;
  return () => {
    let current = value;
    value += 1;
    return current;
  };
};

var s3 = makeSequence();
var s4 = makeSequence();
