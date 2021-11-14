'use strict'; // Warto to zrobić

let n = 4;

// {
//   return 5;
// }

// Wyrażenie (ang. EXPRESSION) - MA WARTOŚĆ
let i = n + 3;

// Instrukcja (ang. STATEMENT) - NIE MA WARTOŚCI, LECZ WYKONUJE PEWNE DZIAŁANIA
if (i == 8) { // - Instrukcja warunkowa
  console.log('...');
} else {
  i += 3;
}

let m = i == 8 ? 4 : 'NoNo';

// TERNARY CONDITIONAL - Wyrażenie warunkowe
// <predicate> ? <consequent> : <alternative>

// TYPE INFERENCE - wnioskowanie o typach (symboli, wyrażeń, funkcji)

// Zadanie. Napisz procedurę liczącą sumę wartości od 1 do n.
function sumNaturals(n) {
  let result = 0;
  for (let i = 1; i <= n; i++) {
    result += i;
    console.log('n => ' + n + ', i => ' + i + ', result => ' + result);
  }

  return result;
}

console.log('Suma liczb od 1 do 7 wynosi ' + sumNaturals(7));

// ZMIENNE W JS DEFINIUJEMY Z WYKORZYSTANIEM var LUB let
// ZDECYDOWANIE (OD ES6) UŻYWAMY let

if (5 == 5) {
  // var x1 = 2.45;
  // var x1 = 5.6;

  let x1 = 2.45;
  x1 = 'aaa';

  console.log(x1);
}

// console.log(x1);

// WSZĘDZIE, GDZIE JEST TO MOŻLIWE, UŻYWAJMY const ZAMIAST let
const N1 = 5;

// const N2;
// N2 = 4;

const N2 = 4;

function test1(n) {
  console.log('n => ' + n);
}

const tab1 = [1, 2, 3, 4, 'aaa'];
tab1[0];

const obj1 = { name: 'The Name', age: 5 };
obj1.address = "Piotrkowska Street, Łódź";
obj1['company'] = 'ACME';
obj1[6] = 'www';
