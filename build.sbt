name := "learn-bio-spark"

// 2.11 doesn't seem to work
scalaVersion := "2.10.4"

libraryDependencies ++= Dependencies.sparkHadoop

libraryDependencies += "org.bdgenomics.adam" % "adam-parent" % "0.14.0"

//releaseSettings

//scalariformSettings
