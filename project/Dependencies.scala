import sbt._

object Version {
  val hadoop       = "2.5.1"
  //val slf4j        = "1.7.6"
  //val logback      = "1.1.2"
  val scalaTest    = "2.2.1"
  val spark        = "1.1.0"

  //val mockito      = "1.9.5"
}

object Library {
  val sparkCore      = "org.apache.spark"  %% "spark-core"      % Version.spark
  val hadoopClient   = "org.apache.hadoop" %  "hadoop-client"   % Version.hadoop
  val yarnClient     = "org.apache.hadoop" %  "hadoop-yarn-client"   % Version.hadoop
  //val slf4jApi       = "org.slf4j"         %  "slf4j-api"       % Version.slf4j
  //val logbackClassic = "ch.qos.logback"    %  "logback-classic" % Version.logback
  val scalaTest      = "org.scalatest"     %% "scalatest"       % Version.scalaTest
  //val mockitoAll     = "org.mockito"       %  "mockito-all"     % Version.mockito
}

object Dependencies {

  import Library._

  val sparkHadoop = Seq(
    sparkCore,
    hadoopClient,
    yarnClient,
    scalaTest % "test"//,
  )
}