# DottyLatest  
Windows 10+, 64 bit only  
(Has a rudimentary Linux support)  
  
A Java runtime is required.  
SBT if using Scala.  

#### Latest changes:  
  
Version (>=)| Change  
------------ | ------------- 
0.055 | -> 3.8.1 as default 
0.052 | Environment variables names changed to: **DOTTYLATEST** and **DOTTYLATESTRC**
0.047 | Using the clipboard (loader changed) to catch app messages which is much faster
  
#### Description  
This simple tool extracts the entry with the highest release number from the  
dotty nightly-build [https://repo1.maven.org/maven2/org/scala-lang/scala3-compiler_3/ download-website](https://repo1.maven.org/maven2/org/scala-lang/scala3-compiler_3)   
and sets the environment-variable accordingly (changed from version 0.052):  
- "DOTTYLATEST" (stable versions only),  
- "DOTTYLATESTRC" (including release candidates).  
**The app messages are also copied to the clipboard!**  
  
This environment-variable can be referenced in any "build.sbt"-file with:  
  
```
ThisBuild / scalaVersion := sys.env.get("DOTTYLATEST").getOrElse("3.8.1"),  
```
or  
```
inThisBuild(
	List(
		scalaVersion := sys.env.get("DOTTYLATEST").getOrElse("3.8.1")
	)
)
```  
or use "DOTTYLATESTRC" to work with latest "RC" / "NIGHTLY" version:
```
inThisBuild(
	List(
		scalaVersion := sys.env.get("DOTTYLATESTRC").getOrElse("3.8.1")
	)
)
``` 
  
#### Download:  
Portable, run from any directory, but running from a subdirectory of the windows programm-directories   
(C:\Program Files, C:\Program Files (x86) etc.)  
requires admin-rights and **is not recommended**! 

Via Updater:  
  
[Download Updater 64 bit](https://github.com/jvr-ks/dottylatest/raw/main/updater.exe)  
  
<a href="#virusscan">Virusscan look below.</a>  

(Updater virusscan: take a look at the [Updater repository](https://github.com/jvr-ks/updater))  
   
Answer "Start / restart now" with "NO",  
because "Dottylatest" must be run as an administrator,  
it can not start from Updater!    
  
#### Usage  
Run : "dottylatest.exe" (requests **admin** rights to execute "SET X")  
or  
Run : "dottylatest_Java.lnk" or as an **admin** "dottylatest_Java.bat"  
  
(Currently not usable:  
Run as an **admin**: "dottylatest_Scala.bat")  
  
Using loader ([Loader Github page](https://github.com/jvr-ks/loader)):  
The dottylatest_loader is configured via the file "loader.ini".  
"showresult=1" must be set, because dottylatest is a console app,
nothing would be displayed otherwise!  
The display timeout "showresulttime=3000" (millisceonds, default is 3000) can be set there too.  
The Java runtime information is displayed too, like every console app does!  
  
Asks for admin rights:  
Create a shortcut to the file "dottylatest.exe", make the shortcut runnable by admin only!  
**The shortcut dottylatest_loader.lnk can be attached to the taskbar!**  
  
Linux/WSL: use "dottylatest_Java.sh" (chmod a+x ...)  
  
#### Required files in the "lib" directory  
- [scala-library-2.13.15.jar](https://repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.15/scala-library-2.13.15.jar)  
- [scala3-library_3-3.8.1.jar](https://repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.8.1/scala3-library_3-3.8.1.jar )  
  
Use the [Download Updater 64 bit](https://github.com/jvr-ks/dottylatest/raw/main/updater.exe) to automatically download those files too.  
(The preferred install / update procedure!)  
  
The reason a Scala 2 library like scala-library-2.13.15 is required  
is because many Scala 3 projects use dependencies  
that were originally written in Scala 2.  
Scala 3 provides backwards compatibility with Scala 2 libraries,  
so scala-library-2.x is still required to run those dependencies correctly.  
The Scala 3 runtime (scala3-library) covers new features of Scala 3,  
but interoperability requires the additional Scala 2 runtime for older libraries and their APIs.  
  
#### Required JAVA runtime  
Tested with:
* [GraalVM Community Java 23.0.1](https://www.graalvm.org/release-notes/JDK_23/),  
  
May run with other Java versions (down to Java 8) too ...  
  
#### Status  
Execution (as an admin) | Internal command used  | Status  
------------ | ------------- | -------------  
dottylatest.exe | java -cp "dottylatest.jar;lib\\*" de.jvr.dottylatest.Dottylatest | ok \*1)  
dottylatest_Java.lnk \*2)| java -cp "dottylatest.jar;lib\\*" de.jvr.dottylatest.Dottylatest | ok  
dottylatest_Scala.lnk \*2)| scala -cp dottylatest.jar de.jvr.dottylatest.Dottylatest | fails  
  
\*1) Using Loader, the internal command used is defined by the content of "loader.ini" (default setup)  
Uses internally: java -cp ... process.  
Is a little slow!  
(Cannot use internally: scala -cp ... because process does not inherit admin rights.)  
  
\*2) *.lnk just calls *.bat but forces admin-rights
    
#### TODO:  
  
TODO | Done in  
------------ | -------------  
loader integration | 0.020  
updater integration | 0.019  
check if operations fail | 0.018   
  
  
#### Run with SBT  
(As admin)  
runSBT.lnk  
  
#### Why admin?  
Uses the Powerscript command setX /m  
to set the variable "dottylatest" in the system environment HKLM,  
which must be run as an admin!  
  
#### File format:  
All files are UTF-8 (no BOM)  
  
#### Remarks  
The *.default-files are used by the Updater.  
  
##### License: MIT, -> MIT_License.txt  
Copyright (c) 2021 J. v.Roos  
  
##### Virusscan of support files
[Virusscan of updater*.exe -> Updater project](https://github.com/jvr-ks/updater)  
[Virusscan of loader*.exe -> Loader project](https://github.com/jvr-ks/loader)  

<a name="virusscan">


##### Virusscan at Virustotal 
[Virusscan at Virustotal, dottylatest.exe file, Check here](https://www.virustotal.com/gui/url/bb933786c2b6081ab4b95b2a2b44f36e6d4e7edd8dfab934ba8e0f043aa9bed8/detection/u-bb933786c2b6081ab4b95b2a2b44f36e6d4e7edd8dfab934ba8e0f043aa9bed8-1771418015
)  
[Virusscan at Virustotal, dottylatest.jar file, Check here](https://www.virustotal.com/gui/url/5595920b360671628290dadc663aa3aad104bbef9e79cdc09af799ed506ab352/detection/u-5595920b360671628290dadc663aa3aad104bbef9e79cdc09af799ed506ab352-1771418017
)  
Use [CTRL] + Click to open in a new window! 
