/**
 * Created by marvin on 15-8-23.
 */
object Main {
  def main(args:Array[String]): Unit ={
    val x = new Matrix(Array(Array(1,2,3),Array(4,5,6)))
    val y = new Matrix(Array(Array(1),Array(1),Array(1)))
    print(MatrixUtils.multiply(x,y)(ThreadPoolStrategy).toString)
  }

  implicit val ts = ThreadPoolStrategy
}
