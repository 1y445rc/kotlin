@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  bunch startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and BUNCH_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\bunch-cli-1.0.100.jar;%APP_HOME%\lib\kotlin-stdlib-jdk8-1.3.31.jar;%APP_HOME%\lib\org.eclipse.jgit-5.4.0.201906121030-r.jar;%APP_HOME%\lib\slf4j-simple-1.7.2.jar;%APP_HOME%\lib\clikt-2.0.0.jar;%APP_HOME%\lib\kotlin-stdlib-jdk7-1.3.31.jar;%APP_HOME%\lib\kotlin-stdlib-1.3.31.jar;%APP_HOME%\lib\jsch-0.1.55.jar;%APP_HOME%\lib\jzlib-1.1.1.jar;%APP_HOME%\lib\JavaEWAH-1.1.6.jar;%APP_HOME%\lib\slf4j-api-1.7.2.jar;%APP_HOME%\lib\bcpg-jdk15on-1.61.jar;%APP_HOME%\lib\bcpkix-jdk15on-1.61.jar;%APP_HOME%\lib\bcprov-jdk15on-1.61.jar;%APP_HOME%\lib\kotlin-stdlib-common-1.3.31.jar;%APP_HOME%\lib\annotations-13.0.jar

@rem Execute bunch
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %BUNCH_OPTS%  -classpath "%CLASSPATH%" org.jetbrains.bunches.general.Bunch %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable BUNCH_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%BUNCH_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega