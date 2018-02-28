import scala.io.Source
import java.io._

object GenData {
  type Matrix = Array[Array[Double]]
  val path = "data/dat.ser"

  def timer[R](block: => R) = {
    val t0 = System.nanoTime()
    val result = block
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) / 1E9 + "s")
    result
  }


  def readFile[T](path:String):T = {
    val fin = new FileInputStream(path)
    val ois = new ObjectInputStream(fin)
    val out = ois.readObject().asInstanceOf[T]
    ois.close()
    out
  }

  def writeFile[T](x:T, path:String) {
    val fout = new FileOutputStream(path)
    val oos = new ObjectOutputStream(fout)
    oos.writeObject(x)
    oos.close()
    /* Print text to file
    val writer = new PrintWriter(new File(dest))

    x.foreach{ line =>
      writer.write(line.mkString(",") + "\n")
    }
    writer.close()
    */
  }

  def main(args:Array[String]) {
    //val N = args.head.toInt
    val N = 50000
    val J = 32
    val M = 10

    //val N = 20000
    //val J = 200
    //val M = 3

    val z = timer {
      Array.tabulate(M){m => 
        Array.tabulate(N){n => 
          Array.tabulate(J)(j => {
            scala.util.Random.nextDouble
          })
        }
      }
    }
    timer{ writeFile(z, path) }

    // val x = Array.tabulate(N){ n => scala.util.Random.nextDouble }
    // timer{ writeFile(x, "data/dat_x.ser") }

    timer{ val xx = readFile[Array[Matrix]](path) }
  }
  
}

/**
val path = "data/dat.ser"
val x = GenData.readFile[Array[GenData.Matrix]](path)
 */
