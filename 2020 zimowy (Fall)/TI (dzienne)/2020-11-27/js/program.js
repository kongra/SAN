'use strict';

function isValidEmail(s) {
  return /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(s);
}

const emailInput = document.getElementById('email-input');

function addKeyupListener(target, latencyMsecs, valueReader, lstnr) {
  target.addEventListener('keyup', event => {
    const value1 = valueReader();
    setTimeout(event1 => {
      const value2 = valueReader();
      if (value1 === value2) {
        lstnr(value1);
      }
    }, latencyMsecs);
  })
}

addKeyupListener(emailInput, 1000, () => emailInput.value, email => {
  // console.log('Email jest prawidłowy? ' + isValidEmail(email));
  const cl = emailInput.classList;
  if (isValidEmail(email)) {
    if (cl.contains('input-error')) {
      cl.remove('input-error');
    }
  } else {
    if (!cl.contains('input-error')) {
      cl.add('input-error');
    }
  }

});
