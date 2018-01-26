/* Speed test for updating only some fileds in a class
 * Study:
 *   - Create a class with two fields (two matrices: one large, one small)
 *     - check time taken to copy the object while updating only the large matrix 
 *     - check time taken to copy the object while updating only the small matrix
 *   - Conclusions:
 *     - if you only update the small matrix, even though the other matrix is large, there will be no penalty.
 */

def timer[R](block: => R) = {
  val t0 = System.nanoTime()
  val result = block
  val t1 = System.nanoTime()
  println("Elapsed time: " + (t1 - t0) / 1E9 + "s")
  result
}

type Matrix = Array[Array[Double]]
case class State(x:Matrix, y:Matrix)

def matMap(m: Matrix, f:Double => Double) = {
  m.map( row => row.map(f) )
}

def matMapMutable(m: Matrix, f:Double => Double) {
  val c = m(0).indices
  lazy val r = m.indices
  r.foreach{ i => 
    c.foreach{ j => 
      val xij = m(i)(j)
      m(i)(j) = f( xij )
    }
  }
}


def updateX(s:State) = {
  val newX = matMap(s.x, {d:Double => d + 1})
  s.copy(x=newX)
}

def updateY(s:State) = {
  //val newY = s.y.map( yi => yi.map(yij => yij + 1) )
  val newY = matMap(s.y, {d:Double => d + 1})
  s.copy(y=newY)
}


def updateXMutable(s:State) {
  matMapMutable(s.x, {d:Double => d + 1})
}

def updateYMutable(s:State) {
  matMapMutable(s.y, {d:Double => d + 1})
}


val x = Array.tabulate(50000)(n => Array.tabulate(32)(j => 0.0))
val y = Array.tabulate(5)(n => Array.tabulate(3)(j => 0.0))

val t = State(x.clone,y.clone)

val t1 = timer{ updateX(t) }
val t2 = timer{ updateY(t) }

timer{ updateXMutable(t) }
timer{ updateYMutable(t) }
