#include <stdio.h>

class FibCount {
public:
	const long value;
	const long count;

	FibCount(long v, long c) : value(v), count(c) {}

	FibCount(const FibCount & c) : value(c.value), count(c.count) {}
};

static FibCount* fibcount(long n) {
	if (n == 0 || n == 1) {
		return new FibCount(n, 1);
	}
	FibCount* c1 = fibcount(n-1);
	FibCount* c2 = fibcount(n-2);

	FibCount* result = new FibCount(c1->value + c2->value, c1->count + c2->count + 1);
	delete c1;
	delete c2;

	return result;
}

static void test(long n)
{
	FibCount* c = fibcount(n);
	printf ("%ld, %ld\n", c->value, c->count);
	delete c;
}

int main(void)
{
	test(37);
	test(38);
	test(39);
	return 0;
}
