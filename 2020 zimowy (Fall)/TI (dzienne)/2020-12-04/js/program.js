'use strict';

function isValidEmail(s) {
  return /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(s);
}

const emailInput   = document.getElementById('email-input');
const runmeButton  = document.getElementById('runme');
const glassOverlay = document.getElementsByClassName('glass-overlay')[0];

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

runmeButton.addEventListener('click', event => {
  glassOverlay.style.display = 'block';
  glassOverlay.style.top     = 0;
  glassOverlay.style.height  = '100vh';
});

function sigmoid (alpha, t) {
  return 1 / (1 + Math.exp(-alpha * (t - 0.5)));
}

function animateOverlay(step, animSteps) {
  const startTime = performance.now();
  const pos = sigmoid(20, step / animSteps);
  const top = (100 * pos) + "vh";
  const h   = (100 - (100 * pos)) + "vh";

  glassOverlay.style.top    = top;
  glassOverlay.style.height = h;

  if (step + 1 === animSteps) {
    // End of the animation
    glassOverlay.style.display = 'none';
    console.log('Overlay ukryty');
    return;
  }

  const elapsedTime = performance.now() - startTime;
  setTimeout(() => {
    animateOverlay(step + 1, animSteps);
  }, 16 - elapsedTime);
}

glassOverlay.addEventListener('click', event => {
  // glassOverlay.style.display = 'none';

  const animMsecs = 1500;
  const animSteps = animMsecs * 60 / 1000;

  setTimeout(() => {
    animateOverlay(0, animSteps);
  }, 16);
});
