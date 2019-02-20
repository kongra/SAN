<?php
  $firstName = "John";
  $lastName  = "Doe";
?>
<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <title>
      Hello PHP Page
    </title>
  </head>
  <body>
    <h1>Now I have my&nbsp;text here.</h1>

    <h1>Hello Dear User
      <?php
      echo $firstName; echo "&nbsp;";
      echo $lastName;
      ?>
       !!!</h1>
  </body>
</html>

<!-- Put me into: c:\xampp\htdocs\ -->
