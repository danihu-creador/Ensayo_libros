@echo off
call "%~dp0..\herramientas\env.bat"
cd /d "%~dp0"
echo.
echo === EJECUTANDO target\app.jar. Abre http://localhost:8080 ===
echo.
java -jar target\app.jar
pause
