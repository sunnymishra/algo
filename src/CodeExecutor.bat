@echo off && setlocal EnableDelayedExpansion

set argument1=%1
set filename=CodeExecutorFolderName.txt
set persistedFolderName=

if [%argument1%] == [] (
	echo Use command: EpiExecutor.bat {Java filename without .java}
	goto :EOF
)

if exist %filename% (
	for /f ^"usebackq^ eol^=^
	^ delims^=^" %%a in (%filename%) do (
		IF %%a == [] goto :EMPTY_FILE_LINE
		if exist "%cd%\%%a" (
			set persistedFolderName=%%a
			goto :EXECUTE_JAVA
		) else (
			goto :EMPTY_FILE_LINE
		)
	)
) else (
	goto :EMPTY_FILE_LINE
)


:EMPTY_FILE_LINE
if exist %filename% (
	del /f %filename%
)
set /p folderName= "Enter folderName which contains Java file = " 

if exist "%cd%\%folderName%" (
	echo Folder "%cd%\%folderName%" found
	echo %folderName%>%filename%
)else (
	echo "%cd%\%folderName%" not found. Create this folder first.
	goto :EMPTY_FILE_LINE
)
echo We are all set. Next time give following command: EpiExecutor.bat {Java filename without .java}
goto :EOF

:EXECUTE_JAVA
javac -cp ../lib/junit.jar;../lib/hamcrest.jar;.  "%persistedFolderName%\%argument1%.java"
java -cp ../lib/junit.jar;../lib/hamcrest.jar;. org.junit.runner.JUnitCore %persistedFolderName%.%argument1%
goto :EOF

:EOF
echo done