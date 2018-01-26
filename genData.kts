import java.util.Random
import java.io.*

fun <R>timer(block: () -> R):R {
  val t0 = System.nanoTime()
  val result = block()
  val t1 = System.nanoTime()
  println("Elapsed time: " + (t1 - t0) / 1E9 + "s")
  return result
}


val Rand = Random()

fun runif():Double {
  return Rand.nextDouble()
  //return 1.0
}

fun <T:Serializable>writeFile(x:T, dest:String) {
  val fout = FileOutputStream(dest)
  val oos = ObjectOutputStream(fout)
  oos.writeObject(x)
  //oos.use{ it -> it.writeObject(x) }
  oos.close()
}

// main 
//val n = args[0].toInt()
val N = 50000
val J = 32
val M = 10
val y = timer {
  Array(M, {
    Array(N, {
      Array(J, {runif()})
    })
  })
}

println("writing")
writeFile(y, "data/dat_kotlin.ser")

// to run as script: 
// kotlinc -script genData.kts


