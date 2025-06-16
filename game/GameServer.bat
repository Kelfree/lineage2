@setlocal enableextensions
@cd /d "%~dp0"
@echo off
:start
REM -------------------------------------
REM Default parameters for a basic server.
".\..\..\jdk-21.0.2\bin\java.exe" -server -Dfile.encoding=UTF-8 -Djava.util.logging.manager=org.l2jmobius.log.ServerLogManager -Dorg.slf4j.simpleLogger.log.com.zaxxer.hikari=warn -XX:+UseZGC -Xmx4g -Xms2g -cp ./../libs/*; org.l2jmobius.gameserver.GameServer
REM -------------------------------------
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
