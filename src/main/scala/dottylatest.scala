// dottylatest.scala

// Scala 3

package de.jvr.dottylatest

import scala.language.postfixOps

import scala.io.Source
import scala.util.Try
import scala.util.control.NonFatal

import sys.process._

import java.net.URI;


object Dottylatest:

  val version = "0.058"
  
  val osName = System.getProperty("os.name") match {
    case n if n.startsWith("Linux") => "linux"
    case n if n.startsWith("Mac") => "mac"
    case n if n.startsWith("Windows") => "win"
    case _ => throw new Exception("Unknown platform!")
  }
    
  def withResources[T <: AutoCloseable, V](r: => T)(f: T => V): V = {
    val resource: T = r
    require(resource != null, "resource is null")
    var exception: Throwable = null
    try {
      f(resource)
    } catch {
      case NonFatal(e@_) =>
      exception = e
      throw e
    } finally {
      closeAndAddSuppressed(exception, resource)
    }
  }
  
  private def closeAndAddSuppressed(e: Throwable, resource: AutoCloseable): Unit = {
    if (e != null) {
      try {
        resource.close()
      } catch {
        case NonFatal(suppressed) =>
          e.addSuppressed(suppressed)
      }
    } else {
      resource.close()
    }
  }
  
  def gl(s: Source): String = s.mkString
  
  def readURL(): Either[String, String] = 
    val repoURL = """https://repo1.maven.org/maven2/org/scala-lang/scala3-compiler_3"""
    val connectTimeout: Int = 6000
    val readTimeout: Int = 6000
    
    import java.net.{URL, HttpURLConnection}
    try
      //deprecated: val connection = (new URL(repoURL)).openConnection.asInstanceOf[HttpURLConnection]
      val connection = (new URI(repoURL).toURL()).openConnection.asInstanceOf[HttpURLConnection]
      
      connection.setConnectTimeout(connectTimeout)
      connection.setReadTimeout(readTimeout)
      connection.setRequestMethod("GET")
      connection.setRequestProperty("User-Agent", """Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:98.0) Gecko/20100101 Firefox/98.0 browser""")
      val inputStream = connection.getInputStream
      val readResult = withResources(io.Source.fromInputStream(inputStream))(gl)
      Right(readResult)
    catch
      case NonFatal(e@_) => Left(s"Cannot open the URL $repoURL error is:\n" + e.toString + "\n\n")
  
  val extractNameAndSetRC: (Either[String, String]) => Unit = {
    case Right(value) => 
      val RegEXP = """.*?(\d+\.\d+\.\d+-RC\d+.*?)\/.*""".r
      
      val found = (RegEXP findAllIn value).matchData.map(_ group 1).toList.sorted
      
      if (found.size > 0)
        val latestDottyVersion = found.last
        
        osName match
          case "win" =>
            val cmd = Seq("SETX", "/M" ,"DOTTYLATESTRC",latestDottyVersion)
            val out = cmd.lazyLines
            println(out.mkString("\n"))
            
            println
            println(s"Dottylatest v${version}: Setting environment variable \"DOTTYLATESTRC\" to:")
            println(s"\"$latestDottyVersion\"")
            println
            
          case "linux" => 
            val cmdExport = "#!/bin/sh\nexport DOTTYLATESTRC=" + latestDottyVersion + "\n"
            java.nio.file.Files.write(java.nio.file.Paths.get("/etc/profile.d/dottylatest.sh"), cmdExport.getBytes(java.nio.charset.StandardCharsets.UTF_8))
            println
            println("Written to file \"/etc/profile.d/dottylatest.sh:\"")
            println(cmdExport)
            println
            println("Executing: chmod a+x /etc/profile.d/dottylatest.sh")          
            ("chmod a+x /etc/profile.d/dottylatest.sh").!
            println
            println("----------------------------------------------------------------------------------")
            println("USE \"source /etc/profile.d/dottylatest.sh\" or restart now!")
            println("----------------------------------------------------------------------------------")
            println
          case _ => println("ERROR, unsupported operating system!")
      else
        println("ERROR, no suitable dotty-name found in content of the webpage!")
    case Left(e) => println(e)
  }  
  
  val extractNameAndSetSTABLE: (Either[String, String]) => Unit = {
    case Right(value) => 
      val RegEXP = """.*?(\d+\.\d+\.\d+)\/.*""".r
      val found = (RegEXP findAllIn value).matchData.map(_ group 1).toList.sorted
      
      if (found.size > 0)
        val latestDottyVersion = found.last
        
        osName match
          case "win" =>
            val cmd = Seq("SETX", "/M" ,"DOTTYLATEST",latestDottyVersion)
            val out = cmd.lazyLines
            println(out.mkString("\n"))
            
            println
            println(s"Dottylatest v${version}: Setting environment variable \"DOTTYLATEST\" to:")
            println(s"\"$latestDottyVersion\"")
            println
 
            
          case "linux" => 
            val cmdExport = "#!/bin/sh\nexport DOTTYLATEST=" + latestDottyVersion + "\n"
            java.nio.file.Files.write(java.nio.file.Paths.get("/etc/profile.d/dottylatest.sh"), cmdExport.getBytes(java.nio.charset.StandardCharsets.UTF_8))
            println
            println("Written to file \"/etc/profile.d/dottylatest.sh:\"")
            println(cmdExport)
            println
            println("Executing: chmod a+x /etc/profile.d/dottylatest.sh")          
            ("chmod a+x /etc/profile.d/dottylatest.sh").!
            println
            println("----------------------------------------------------------------------------------")
            println("USE \"source /etc/profile.d/dottylatest.sh\" or restart now!")
            println("----------------------------------------------------------------------------------")
            println
          case _ => println("ERROR, unsupported operating system!")
      else
        println("ERROR, no suitable dotty-name found in content of the webpage!")
    case Left(e) => println(e)
  }
  
  def main(args: Array[String]): Unit =
    extractNameAndSetRC(readURL())
    extractNameAndSetSTABLE(readURL())
    sys.exit(0)

    
   



