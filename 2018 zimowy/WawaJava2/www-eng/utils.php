<?php
declare(strict_types = 1);

function str2float(string $s) : float {
  return floatval(str_replace(',', '.', $s));
}

function foo(?string $s) : float {
  if ($s == NULL)
    return 1;
  else
    return $s + 1;
}

echo foo("123");

// double goo(String s) {
// 	return Double.parseDouble(s) + 1;
// }

// goo("123");
// goo(null);