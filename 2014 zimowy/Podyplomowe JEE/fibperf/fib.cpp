#include <stdio.h>

class FibCount {
public:
	const long value;
	const long count;

	FibCount(long v, long c) : value(v), count(c) {}

	FibCount(const FibCount & c) : value(c.value), count(c.count) {}
};

static FibCount fibcount(long n) {
	if (n == 0 || n == 1) {
		return FibCount(n, 1);
	}
	FibCount c1 = fibcount(n-1);
	FibCount c2 = fibcount(n-2);
	return FibCount(c1.value + c2.value, c1.count + c2.count);
}

static void test(long n)
{
	FibCount c = fibcount(n);
	printf ("%ld, %ld\n", c.value, c.count);
}

int main(void)
{
	test(37);
	test(38);
	test(39);
	return 0;
}

// typedef struct {
// 	long value;
// 	long count;
// } FibCount;

// static FibCount fibcount(long n)
// {
// 	if (n == 0 || n == 1) {
// 		FibCount result = { n, 1 };
// 		return result;
// 	}
// 	FibCount c1 = fibcount(n-1);
// 	FibCount c2 = fibcount(n-2);
// 	FibCount result = { c1.value + c2.value, c1.count + c2.count };
// 	return result;
// }

// static void test(long n)
// {
// 	FibCount c = fibcount(n);
// 	printf ("%ld, %ld\n", c.value, c.count);
// }

// int main(void)
// {
// 	test(37);
// 	test(38);
// 	test(39);
// }
