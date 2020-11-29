'use strict';

function pow(x, y) {
  if (typeof x !== 'number') {
    console.log('x is not a number ' + x);
  }

  if (typeof y !== 'number') {
    console.log('y is not a number ' + y);
  }

  return x ** y;
}

function register(email, passwd) {
  if (typeof email !== 'string') {
    console.log('email is not a string ' + email);
  }

  if (typeof passwd !== 'string') {
    console.log('passwd is not a string ' + passwd);
  }

  // ...
}

// Funkcja vs Procedura
// 1. Funkcja zwraca wynik, a procedura nie zwraca.
// 2. Funkcja jest w matematyce, a procedura jest realizacją funkcji w programie.

const goo1 = function(x) {
  return 2 * x + 3;
};

const goo2 = (x) => {
  return 2 * x + 3;
};

const goo3 = (x) => 2 * x + 3;

const FOO = 4;
const GOO = (y) => 2 * x;

function goo4(x) {
  return 2 * x + 3;
}

function test1(x, y) {
  const z = x + y + 3;
  console.log('stała z wynosi ' + z);

  return z ** 2;
}

function silnia(n) {
  let i = 1n;
  let result = 1n;

  while (i <= n) {
    result = result * i;
    i = i + 1n;
  }

  return result;
}

// var i; vs let i;

function test2(n) {
  let v1 = n;
  console.log('wartość v1 wynosi ' + v1);

  {
    let v2 = n + 1;
    console.log('wartość v2 wynosi ' + v2);
  }

  console.log('wartość v2 wynosi ' + v2);
}

function test2(n) {
  var v1 = n;
  console.log('wartość v1 wynosi ' + v1);

  {
    var v2 = n + 1;
    console.log('wartość v2 wynosi ' + v2);
  }

  console.log('wartość v2 wynosi ' + v2);
}

function test3(n) {
  var v1 = n;
  console.log('wartość v1 wynosi ' + v1);

  {
    var v1 = n + 1;
    console.log('wartość v1 wynosi ' + v1);
  }

  console.log('wartość v1 wynosi ' + v1);
}

function test4(n) {
  const v1 = n;
  console.log('wartość v1 wynosi ' + v1);

  {
    const v1 = n + 1;
    console.log('wartość v1 wynosi ' + v1);
  }

  console.log('wartość v1 wynosi ' + v1);
}

// Zadanie. Utwórz generator sekwencji liczb naturalnych.
function makeSeq(start) {
  let state = start;
  return function(reset) {
    if (reset) {
      state = start;
    } else {
      const result = state;
      state += 1;
      return result;
    }
  };
}

const RESET = true;

const seq1 = makeSeq(1);
// seq1() => 1
// seq1() => 2
// seq1() => 3
// ...
// seq1(RESET)

const seq2 = makeSeq(100);
// seq2() => 100
// seq2() => 101
// seq2() => 102
// ...
