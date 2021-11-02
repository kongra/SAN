const n = 1;

const foo = function() {
  console.log('Działa funkcja foo');
};

foo();

(function() {
  console.log('Działa funkcja nienazwana');
})();

const goo = (n) => {
  console.log('Działa funkcja nienazwana wywołana z parametrem n', n);
  return n + 3;
};

goo();

((n) => {
  return n + 5;
})(3);

function makeCounter(start = 0) {
  let value = start;

  return (command) => {
    if (command == 'reset') {
      value = 0;
    } else {
      value += 1;
    }
    return value;
  };
}

const c1 = makeCounter();
const c2 = makeCounter(100);

c1('reset')
// c1.reset()

function onButtonClicked() {
  const v1 = c1();
  console.log('onButtonClicked:', v1);
  document.getElementById('counter').innerHTML = v1;  
}

function onReset() {
  const v1 = c1('reset');
  document.getElementById('counter').innerHTML = v1;
}