# jvmSpeed

Compare matrix init, writing objects, and reading objects size and speed.

Note that in Kotlin, `Array<Double>` performs differently than `DoubleArray`.
See this section from the [Kotlin website][1].

> Kotlin also has specialized classes to represent arrays of primitive types
> without boxing overhead: ByteArray, ShortArray, IntArray and so on. These
> classes have no inheritance relation to the Array class, but they have the same
> set of methods and properties. Each of them also has a corresponding factory
> function:

Scala, however, doesn't have this issue. `Array[Double]` compiles down
to a `double[]` in Java.

[1]: https://kotlinlang.org/docs/reference/basic-types.html
