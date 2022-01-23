// ĆWICZENIE 1:
// 1. Wykonaj działanie 1
// 2. zaczekaj 100ms
// 3. Wykonaj działanie 2
// 4. zaczekaj 50ms
// 5. Wykonaj działanie 3
// 6. zaczekaj 150ms
// 7. Wykonaj działanie 4

// console.log('Wykonuję działanie 1');
// delay 100;
// console.log('Wykonuję działanie 2');
// delay 50;

// console.log('Wykonuję działanie X');
// delay 300;

// console.log('Wykonuję działanie 3');
// delay 150;
// console.log('Wykonuję działanie 4');

// CALLBACK (PROCEDURE)
console.log('Wykonuję działanie 1');
setTimeout(() => {
  console.log('Wykonuję działanie 2');

  setTimeout(() => {
    console.log('Wykonuję działanie X');
    setTimeout(() => {
      console.log('Wykonuję działanie 3');
      setTimeout(() => {
        console.log('Wykonuję działanie 4');
      }, 1500);
    }, 500);
  }, 300);  
}, 1000);

// ĆWICZENIE 2:
// Zrealizuj następujące działanie programu:
// Jest przycisk RECORD służący do nagrywania dźwięku.
// 1. Użytkownik naciska przycisk RECORD => AKCJA UŻYTKOWNIKA
// 2. Odczekaj 16ms => AKCJA WYZWALANA CZASEM
// 3. Zmień ikonę na przycisku RECORD na taką, która przedstawia nagrywanie
//    Przycisk RECORD zmienia swój charakter na STOP
// 4. Wyświetl odliczanie 4,3,2,1,... każdy puls trwa 0.5s => AKCJE WYZWALANE CZASEM
// 5. Kiedy użytkownik wciśnie STOP - odczekaj 100ms i => AKCJA UŻYTKOWNIKA + AKCJA CZASU
// 6. Poczekaj, aż użytkownik wciśnie SAVE => AKCJA CZASU + AKCJA UŻYTKOWNIKA
// 7. Po czym UDERZ w end-point /save 
// 8. Po odebraniu odpowiedzi zmień ikonę STOP na RECORD => AKCJA WYZWALANA DZIAŁANIEM SERWERA

let recordButton = document.getElementById('record');
recordButton.addEventListener('click', () => {

});

// ĆWICZENIE 3:
// 1. Uderz w end-point /1/
// 2. Uderz w end-point /2/
// 3. Uderz w end-point /3/
// 4. Uderz w end-point /4/
// ** Każde uderzenie jest realizowane równolegle z wszystkimi pozostałymi
// 5. Odbierz odpowiedź od pierwszego, tj. tego, który zakońćzył jako pierwszy
// 6. Odczekaj 100ms
// 7. Kontynuuj... 

let finished = false;
hit('/1/', () => {
  if (!finished) {
    finished = true;
    setTimeout(() => {
      console.log("I really continue");
    }, 100);
  }
});
hit('/2/', () => {
  if (!finished) {
    finished = true;
    setTimeout(() => {
      console.log("I really continue");
    }, 100);
  }
});
hit('/3/', () => {
  if (!finished) {
    finished = true;
    setTimeout(() => {
      console.log("I really continue");
    }, 100);
  }
});
hit('/4/', () => {
  if (!finished) {
    finished = true;
    setTimeout(() => {
      console.log("I really continue");
    }, 100);
  }
});