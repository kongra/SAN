class Animal:
  # initializer/constructor
  def __init__(self, species, name):
    self.species = species
    self.name    = name

  # method
  def sayHello(self):
    print ('Hello! My name is ' + self.name +
           '! I am ' + self.species + '!')

# a1 is a zebra named 'Dora'
a1 = Animal('zebra', 'Dora')
a1.sayHello()

# print(a1.species)
# print(a1.name)

# a2 is an elephant called 'Jumbo'
a2 = Animal('elephant', 'Jumbo')
a2.sayHello()

# print(a2.species)
# print(a2.name)
