@rem dottylatest_Java.bat

@echo off

cd %~dp0

net session >nul 2>&1
if NOT %ERRORLEVEL% == 0 goto noadmin

call java -cp "dottylatest.jar;lib\\*" de.jvr.dottylatest.Dottylatest

timeout /T 4

exit

:noadmin
echo Error, please run the script with admin-rights!
echo.
pause
goto finished

:finished

