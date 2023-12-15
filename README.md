# DottyLatest  
Windows 10+, 64 bit only  
(Has a rudimentary Linux support)  
  
A Java runtime is required.  
SBT if using Scala.  

#### Status 
Execution (as an admin) | Internal command used  | Status  
------------ | ------------- | -------------  
dottylatest.exe | java -cp "dottylatest.jar;lib\\*" de.jvr.dottylatest.Dottylatest | ok \*1)  
dottylatest_Java.lnk \*2)| java -cp "dottylatest.jar;lib\\*" de.jvr.dottylatest.Dottylatest | ok  
dottylatest_Scala.lnk \*2)| scala -cp dottylatest.jar de.jvr.dottylatest.Dottylatest | ok  
  
\*1) Using Loader, the internal command used is defined by the content of "loader.ini" (default setup)  
Uses internally: java -cp ... process.  
Is very slow!  
Cannot use internally: scala -cp ... because process does not inherit admin rights.  
  
\*2) *.lnk just calls *.bat but forces admin-rights
  
#### Description  
This simple tool extracts the entry with the latest date from the  
dotty nightly-build [download-website](https://repo1.maven.org/maven2/org/scala-lang/scala3-compiler_3)   
and sets the environment-variable "DOTTYLATEST" accordingly.  
  
This environment-variable can be referenced in any "build.sbt"-file with:  
  
```
ThisBuild / scalaVersion := sys.env.get("DOTTYLATEST").getOrElse("3.3.1"),  
```
or  
```
inThisBuild(
	List(
		scalaVersion := sys.env.get("DOTTYLATEST").getOrElse("3.3.1")
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
Run as an **admin**: "dottylatest_Java.bat"  
or   
Run as an **admin**: "dottylatest_Scala.bat"  (currently not usable)
or  
Run as an **admin**: "dottylatest.exe"
(Slow, be patient...)  
  
Using loader ([Loader Github page](https://github.com/jvr-ks/loader)):  
The dottylatest_loader is configured via the file "loader.ini".  
"showresult=1" must be set, because dottylatest is a console app,
nothing would be displayed otherwise!  
The display timeout "showresulttime=3000" (millisceonds, default is 3000) can be set there too.  
The Java runtime information is displayed too, like every console app does!  
  
Asks for admin rights:  
Create a shortcut to the file "dottylatest.exe", make the shortcut runnable by admin only!  
**The shortcut dottylatest_loader.lnk can be attached to the taskbar!**  
(Yes, it's so slow ...)  
  
Linux/WSL: use "dottylatest_Java.sh" (chmod a+x ...)  
    
#### Latest changes:  
  
Version (>=)| Change  
------------ | -------------
0.048 | -> 3.3.1 as default
0.047 | Using the clipboard (loader changed) to catch app messages which is much faster
0.046 | -> 3.3.0 as default
0.040 | -> 3.2.2 as default
0.032 | Executable renamed "dottylatest_loader.exe" -> "dottylatest.exe", new Updater version  
0.031 | Linux: writing export-command to file "/etc/profile.d/dottylatest.sh"
0.030 | ~~Linux: writing export-command to file "dottylatest.txt"~~  
0.025 | Adding a timeout wrapper
0.021 | Code changed to Dotty (Scala 3)  
0.020 | 64 bit version only  
0.018 | Env name changed to: dottylatest -> **DOTTYLATEST**  
0.017 | Extracting actual version via the date in the file-name  
0.016 | Not using of a batch-file anymore  
  
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
  
#### Libraries  
The "lib"-subdirectory contains the Scala libraries needed to run Scala apps with the Java runtime. 
Use Updater to download them during install from:  
https://repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.8/scala-library-2.13.8.jar  
https://repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.3.1/scala3-library_3-3.3.1.jar   
  
#### Remarks  
The *.default-files are used by the Updater.  
  
##### License: MIT, -> MIT_License.txt  
Copyright (c) 2021 J. v.Roos  
  
##### Virusscan of support files
[Virusscan of updater*.exe -> Updater project](https://github.com/jvr-ks/updater)  
[Virusscan of loader*.exe -> Loader project](https://github.com/jvr-ks/loader)  

<a name="virusscan">


##### Virusscan at Virustotal 
[Virusscan at Virustotal, dottylatest.exe file, Check here](https://www.virustotal.com/gui/url/bb933786c2b6081ab4b95b2a2b44f36e6d4e7edd8dfab934ba8e0f043aa9bed8/detection/u-bb933786c2b6081ab4b95b2a2b44f36e6d4e7edd8dfab934ba8e0f043aa9bed8-1702642495
)  
[Virusscan at Virustotal, dottylatest.jar file, Check here](https://www.virustotal.com/gui/url/5595920b360671628290dadc663aa3aad104bbef9e79cdc09af799ed506ab352/detection/u-5595920b360671628290dadc663aa3aad104bbef9e79cdc09af799ed506ab352-1702642496
)  
Use [CTRL] + Click to open in a new window! 
