/**
 * Created by marvin on 15-8-23.
 */
object MatrixUtils {
  def multiply(a:Matrix,
               b:Matrix)(implicit threading:ThreadStrategy = SameThreadStrategy):Matrix = {
    assert(a.colRank == b.rowRank)
    val buffer = new Array[Array[Double]](a.rowRank)
    for( i <- 0 until a.rowRank){
      buffer(i) = new Array[Double](b.colRank)
    }

    //计算过程
    def computeValue(row:Int,col:Int):Unit = {
      val pairwiseElements = a.row(row).zip(b.col(col))
      val products = for((x,y) <-pairwiseElements) yield x*y
      val result = products.sum
      buffer(row)(col) = result
    }

    //计算的线程调度策略
    val computations = for{
      i <- 0 until a.rowRank
      j <- 0 until b.colRank
    } yield threading.execute{() => computeValue(i,j)}

    //yield一组函数，foreach执行他们
    computations.foreach(_())
    threading
    new Matrix(buffer)
  }



}
