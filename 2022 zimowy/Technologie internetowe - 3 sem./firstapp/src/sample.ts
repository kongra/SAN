const s1 = 123;
const s2: string = (s1 as unknown) as string;

console.log(`Napis jest ${s2}`);

type Processor<T> = (obj: T) => void;

enum Color {
  Red,
}

const value = (() => 1 + 2)();

export { type Processor, Color };
