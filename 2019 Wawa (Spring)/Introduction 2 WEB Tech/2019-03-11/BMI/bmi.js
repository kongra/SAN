function bmi(mass, height) {
  return mass / (height * height);
}

function updateBMIResult() {
  let mass   = parseFloat(document.getElementById("bmi-mass"  ).value);
  let height = parseFloat(document.getElementById("bmi-height").value);
  let result = bmi(mass, height);
  document.getElementById("bmi-result").innerHTML = "Your BMI is " + result;
}
