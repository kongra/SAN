let Fun = {};

Fun.delay = (expr) => {
  let pending = true;
  let value;

  return () => {
    if (pending) {
      value = expr();
      pending = false;
      expr = null;
    }
    return value;
  };
};

const deref = d => d();

const x = 5;
const y = Fun.delay(() => {
  console.log("Trwają b. długie obliczenia");
  return x + 7;
});

console.log(deref(y));
console.log(deref(y));
console.log(deref(y));

const cons = (a, b) => {
  return {"first": a, "rest": b};
};

const first = c => c.first;
const rest  = c => deref(c.rest);

const inc = n => n + 1;

const iterate = (f, start) => {
  return cons(
    start,
    Fun.delay(() => iterate(f, f(start))));
};

const N = iterate(inc, 0);

const nth = (c, n) => {
  while (true) {
    if (n === 0) {
      return first(c);
    }
    c = rest(c);
    n -= 1;
  }
};
