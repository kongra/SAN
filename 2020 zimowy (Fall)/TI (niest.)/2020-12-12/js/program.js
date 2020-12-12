const PIZZAS = [
  {name: 'Funghi',     price: 22},
  {name: 'Margherita', price: 18},
  {name: 'Napoletana', price: 25}
];

const pizzasSelect = document.getElementById('pizzasSelect');

function addPizza(pizza) {
  let opt = document.createElement('option');
  opt.text = pizza.name;
  pizzasSelect.add(opt);
}

PIZZAS.forEach(addPizza);

addPizza(PIZZAS[0]);
addPizza(PIZZAS[0]);
addPizza(PIZZAS[1]);
