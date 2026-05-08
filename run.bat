@echo off
echo Starting Maven build and tests for Bai9.3...

:: Run Maven clean and package
call mvn -B clean package --file Bai9.3/pom.xml

:: Check the exit code
if %ERRORLEVEL% EQU 0 (
    echo ------------------------------------------------
    echo BUILD SUCCESSFUL!
    echo The artifact can be found in: Bai9.3/target/
    echo ------------------------------------------------
) else (
    echo ------------------------------------------------
    echo BUILD FAILED!
    echo Please check the logs above for errors.
    echo ------------------------------------------------
    exit /b 1
)
