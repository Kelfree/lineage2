@setlocal enableextensions
@cd /d "%~dp0"
@echo off
:start
".\..\..\jdk-21.0.2\bin\java.exe" -Djava.awt.headless=true -server -Dfile.encoding=UTF-8 -Dorg.slf4j.simpleLogger.log.com.zaxxer.hikari=warn -XX:+UseZGC -Xms128m -Xmx256m -cp ./../libs/*; org.l2jmobius.loginserver.LoginServer
if ERRORLEVEL 2 goto restart
if ERRORLEVEL 1 goto error
goto end
:restart
echo.
echo Admin have restarted, please wait.
echo.
goto start
:error
echo.
echo Server have terminated abnormaly.
echo.
:end
echo.
echo Server terminated.
echo.
pause
