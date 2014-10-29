package org.denigma.bio

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partition, SparkConf, SparkContext}

object SimpleApp extends scala.App
{

  val sparkHome = "/home/antonkulaga/Soft/spark"

  val conf: SparkConf = new SparkConf()
    .setMaster("local")
    //.setMaster("yarn-client")
    .setSparkHome(sparkHome)
    .setAppName("Frequent words problem")
  implicit val sc = new SparkContext(conf)
  val prefix = "/home/antonkulaga/denigma/bio/"

  //val genome: String = file.reduce( (a,b)=>a+b)
  //val str = textFromFile(prefix+"test.txt")


  val str = textFromFile(prefix+"dataset_2_6.txt")


  //val pat = patternCount(str,"CTATTCCCT")
  //println(s"RESULT = pattern occurs $pat times")
  naiveFreqCount(str,4)


  def pattern2Number(pattern:String,dictionary:String):Double= if(pattern.size==0) 0 else
    dictionary.indexOf(pattern.head) match {
      case -1=> throw new NoSuchElementException("such element is not in a dictionary")
      case ind=>
        ind*Math.pow(dictionary.length,pattern.size-1)+pattern2Number(pattern.tail,dictionary)
    }

//  def atgPrint(pat:String = "ATGC") = {
//    val pr = patternArray(pat,4)
//    pr.foreach{ case s=>
//        s.toS
//    }
//  }

  def textFromFile(path:String): String =  sc.textFile(path).reduce( (a,b)=>a+b)


  //this.naiveFreqCount(8,genome)

  /**
   * Recursive function that counts how often the pattern appears in sequence
   * @param text
   * @param pattern
   * @param startIndex
   * @param count
   * @return
   */
  def patternCount(text:String,pattern:String,startIndex:Int = 0,count:Int = 0):Int = text.indexOf(pattern,startIndex) match {
    case -1=>  count
    case i if i<=text.size-pattern.length => this.patternCount(text,pattern,i+1,count+1)
    case _=>count
  }


  /**
   * Naive implementation that is slow but short
   * @param n size of kmer
   * @param genome genome string
   * @return
   */
  def naiveFreqCount(genome:String,n:Integer, inverted:Boolean = false) = {
    val forward = genome.sliding(n,1)
    val all = (if(inverted) forward ++ genome.reverse.sliding(n,1) else forward).toStream
    val ngrams: Map[String, Int] = all.groupBy(el=>el).map{ case (key,value)=>key->value.size}
    val max = ngrams.values.max
    val best = ngrams.filter{case (key,value)=>value==max}
    println(s"RESULT: best $n - grams are: \n ${best.toList}")
  }

  def normalFreqCount(n:Integer,genome:String, inverted:Boolean = false) = {

  }



    sc.stop()

}
