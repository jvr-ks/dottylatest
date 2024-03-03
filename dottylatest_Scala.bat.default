@rem dottylatest_Scala.bat

@echo off

cd %~dp0

net session >nul 2>&1
if NOT %ERRORLEVEL% == 0 goto noadmin

rem call scala -classpath dottylatest.jar;lib\\* de.jvr.dottylatest.Dottylatest

rem use distinct libraries
call scala -classpath dottylatest.jar;lib/scala3-library_3-3.2.2.jar;lib/scala-library-2.13.13.jar de.jvr.dottylatest.Dottylatest

timeout /T 4

exit

:noadmin
echo Error, please run the script with admin-rights!
echo.
pause
goto finished

:finished
