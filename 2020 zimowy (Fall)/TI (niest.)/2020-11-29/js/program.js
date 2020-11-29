'use strict';

function silnia(n) {
  if (n === 0n || n === 1n) {
    return 1n;
  }
  return n * silnia(n-1n);
}

const silnia1 = function(n) {
  if (n === 0n || n === 1n) {
    return 1n;
  }
  return n * silnia1(n-1n);
}

const x1 = (function(x) {
  return 2 * x + 3;
})(3);

const x2 = ((x) => {
  return 2 * x + 3;
})(3);

const x3 = (x => {
  return 2 * x + 3;
})(3);

const x4 = (x => 2 * x + 3)(3);