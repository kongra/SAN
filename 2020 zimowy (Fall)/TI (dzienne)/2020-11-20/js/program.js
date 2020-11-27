'use strict';

class Seq {

  constructor(start) {
    this.start = start;
    this.state = start;
  }

  next() {
    const result = this.state;
    this.state += 1;
    return result;
  }

}

class ResettableSeq extends Seq {
  constructor(...args) {
    super(...args);
    console.log('Działa ResettableSeq::constructor(...)');
  }

  next() {
    console.log('Działa ResettableSeq::next()');
    return super.next();
  }

  reset() {
    this.state = this.start;
  }
}

let s1 = new Seq(100);
let s2 = new ResettableSeq(200);
