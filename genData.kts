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
val y: Array<Array<DoubleArray>> = timer {
  Array(M, {
    Array(N, {
      //Array(J, {runif()}) // this has higher storage cost
      val tmp = DoubleArray(J)
      tmp.indices.forEach{j -> tmp[j] = runif()}
      tmp
    })
  })
}

println("writing")
timer{
  writeFile(y, "data/dat_kotlin.ser")
}

//val x = Array(N, {runif()})
//val x = DoubleArray(N)
//x.indices.forEach{ i -> x[i] = runif() }
//timer{
//  writeFile(x, "data/dat_kotlin_x.ser")
//}

// to run as script: 
// kotlinc -script genData.kts


