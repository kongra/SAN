function isEven(n) {
  return n % 2 == 0;
}

function collatzTrans(n) {
  return isEven(n) ? n / 2 : 3 * n + 1;
}

function collatzLen(n) {
  let result = 1;
  while(true) {
    if (n == 1) return result;
    n = collatzTrans(n);
    result++;
  }
}

function euler14(n) {
  let startTime = new Date();

  let maxLen = 0;
  let j      = 0;
  for (let i = 1; i < n; i++) {
    const len = collatzLen(i);
    if (len > maxLen) {
      maxLen = len;
      j = i;
    }
  }

  let endTime = new Date();

  const result = "Result for " + j + " is " + maxLen;
  console.log(result);
  console.log("It took " + ((endTime - startTime) / 1000.0) + " secs");
  return result;
}

function test1(n) {
  let startTime = new Date();
  for (let i=0; i < 1; i++) {
    euler14(n);
  }
  let endTime = new Date();
  console.log("It took " + ((endTime - startTime) / 1000.0) + " secs");
  return 5;
}

// function foo(x) {
//   return x + 3;
// }

// const y = foo(10);

// function goo() {
//   return new Promise(resolve => {
//     const p = test1();
//     p.then(value => resolve(value));
//   });
// }

// console.log('Tworzę obiekt p:Promise');
// const p = goo();
// console.log('p:Promise został utworzony:' + p);

// p.then((value) => {
//   console.log('Odebrałem wartość p: ' + value);
// });

