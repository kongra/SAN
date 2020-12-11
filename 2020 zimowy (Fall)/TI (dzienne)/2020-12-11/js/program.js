'use strict';

const Fun = {};

Fun.delay = (expr) => {
  let pending = true;
  let value;

  return () => {
    if (pending) {
      value   = expr();
      pending = false;
      expr    = null;
    }
    return value;
  };
};

Fun.deref = d => d();

function aVeryLongOne() {
  console.log('It takes so long ...');
  return 5;
}

const n = Fun.delay(() => aVeryLongOne());
console.log('Nothing has happened yet.');
console.log('The value of n is ' + Fun.deref(n));
console.log('The value of n is ' + Fun.deref(n));
console.log('The value of n is ' + Fun.deref(n));
console.log('The value of n is ' + Fun.deref(n));