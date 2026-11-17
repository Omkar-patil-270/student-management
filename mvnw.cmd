@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM
@REM    https://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.
@REM ----------------------------------------------------------------------------

@REM ----------------------------------------------------------------------------
@REM Maven Start Up Batch script
@REM
@REM Required ENV vars:
@REM JAVA_HOME - location of a JDK home dir
@REM
@REM Optional ENV vars
@REM MAVEN_BATCH_ECHO - set to 'on' to enable the echoing of the input commands
@REM MAVEN_BATCH_PAUSE - set to 'on' to pause at the end of this script
@REM MAVEN_OPTS - parameters to pass to the Java VM when running Maven
@REM     e.g. to debug Maven itself, use
@REM set MAVEN_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000
@REM MAVEN_SKIP_RC - flag to disable loading of mavenrc files
@REM ----------------------------------------------------------------------------

@REM Begin all REM lines with '@' in case MAVEN_BATCH_ECHO is 'on'
@echo off
@setlocal EnableExtensions EnableDelayedExpansion

set ERROR_CODE=0

@REM To isolate internal variables from possible post configs, we use another set for the wrapper
@set WRAPPER_JAR="%~dp0\.mvn\wrapper\maven-wrapper.jar"
@set WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

@REM Provide a "Maven Home" support for the script
set "MAVEN_HOME=%~dp0"

@REM ==== START VALIDATION ====
if not "%JAVA_HOME%"=="" goto OkJHome
for %%i in (java.exe) do set "JAVACMD=%%~$PATH:i"
if not "%JAVACMD%"=="" goto checkJCmd

:noJavaHome
echo Error: JAVA_HOME not found in your environment. >&2
echo Please set the JAVA_HOME variable in your environment to match the >&2
echo location of your Java installation. >&2
goto error

:OkJHome
set "JAVACMD=%JAVA_HOME%\bin\java.exe"

:checkJCmd
if exist "%JAVACMD%" goto chkMHome

echo Error: JAVA_HOME is set to an invalid directory. >&2
echo JAVA_HOME = "%JAVA_HOME%" >&2
echo Please set the JAVA_HOME variable in your environment to match the >&2
echo location of your Java installation. >&2
goto error

:chkMHome
if not "%MAVEN_HOME%"=="" goto valMHome

:valMHome
:stripMHome
if not "_%MAVEN_HOME:~-1%"=="_\" goto checkMCmd
set "MAVEN_HOME=%MAVEN_HOME:~0,-1%"
goto stripMHome

:checkMCmd
@REM Find the wrapper distribution
set "WRAPPER_PROPERTIES=%MAVEN_HOME%\.mvn\wrapper\maven-wrapper.properties"
if exist "%WRAPPER_PROPERTIES%" goto parseProperties
echo Error: %WRAPPER_PROPERTIES% not found. >&2
goto error

:parseProperties
for /F "usebackq tokens=1,* delims==" %%A in ("%WRAPPER_PROPERTIES%") do (
    if "%%A"=="distributionUrl" set "DISTRIBUTION_URL=%%B"
)

if not "%DISTRIBUTION_URL%"=="" goto checkMavenHome
echo Error: distributionUrl not found in %WRAPPER_PROPERTIES%. >&2
goto error

:checkMavenHome
for /F "tokens=*" %%A in ('powershell -NoProfile -Command "([System.IO.Path]::GetFileNameWithoutExtension('%DISTRIBUTION_URL%'))"') do set "MVN_DIR_NAME=%%A"
set "MAVEN_USER_HOME=%USERPROFILE%\.m2"
set "MAVEN_WRAPPER_DIR=%MAVEN_USER_HOME%\wrapper\dists\%MVN_DIR_NAME%"
set "MVN_CMD=%MAVEN_WRAPPER_DIR%\apache-maven-3.9.6\bin\mvn.cmd"

if exist "%MVN_CMD%" goto runMvn

echo Downloading Maven from %DISTRIBUTION_URL% ...
powershell -NoProfile -Command "New-Item -ItemType Directory -Force -Path '%MAVEN_WRAPPER_DIR%' | Out-Null; $zip = Join-Path '%MAVEN_WRAPPER_DIR%' 'maven.zip'; Invoke-WebRequest -Uri '%DISTRIBUTION_URL%' -OutFile $zip; Expand-Archive -Path $zip -DestinationPath '%MAVEN_WRAPPER_DIR%' -Force; Remove-Item $zip -Force"

if not exist "%MVN_CMD%" (
    for /d %%D in ("%MAVEN_WRAPPER_DIR%\apache-maven-*") do (
        if exist "%%D\bin\mvn.cmd" set "MVN_CMD=%%D\bin\mvn.cmd"
    )
)

:runMvn
"%MVN_CMD%" %*
goto end

:error
set ERROR_CODE=1

:end
@endlocal & set ERROR_CODE=%ERROR_CODE%
cmd /C exit /B %ERROR_CODE%
