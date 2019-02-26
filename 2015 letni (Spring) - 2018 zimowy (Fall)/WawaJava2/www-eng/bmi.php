<?php
declare(strict_types = 1);

require_once 'utils.php';

$firstName = $_POST["firstName"];
$height    = str2float($_POST["height"]);
$weight    = str2float($_POST["weight"]);

$result = NULL;
if ($height > 0 && $weight > 0) {
  $result = $weight / ($height * $height);
} else {
  $result = '<span style="color:red">Your data is wrong</span>';
}

?>
<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <title>BMI Results</title>
  </head>
  <body>
    <h1>Your BMI is:&nbsp;<?php echo $result; ?></h1>
  </body>
</html>

<!-- Put me into: c:\xampp\htdocs\ -->
