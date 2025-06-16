@setlocal enableextensions
@cd /d "%~dp0"
echo off
".\..\..\jdk-21.0.2\bin\java.exe" -Djava.util.logging.config.file=console.cfg -cp ./../libs/*; org.l2jmobius.tools.gsregistering.BaseGameServerRegister
pause
