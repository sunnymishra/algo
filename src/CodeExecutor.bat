@echo off && setlocal EnableDelayedExpansion

set argument1=%1
set filename=CodeExecutorFolderName.txt
set persistedFolderName=

if [%argument1%] == [] (
	echo Use command: CodeExecutor.bat {Java filename without .java}
	goto :EOF
)

if exist %filename% (
	REM Reading content inside the txt file
	for /f ^"usebackq^ eol^=^
	^ delims^=^" %%a in (%filename%) do (

		IF %%a == [] goto :EMPTY_FILE_LINE
		if exist "%cd%\%%a" (
			if not exist "%cd%\%%a\%argument1%.java" (
				echo File NOT Found "%cd%\%%a\%argument1%.java"
				goto :EMPTY_FILE_LINE
			)
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
set /p folderName= "Enter folderName which contains Java file. If it is subfolder inside 'src' folder then mention them separated by BackSlash eg. com\array = " 

if exist "%cd%\%folderName%" (
	echo Folder "%cd%\%folderName%" found
	echo %folderName%>%filename%
)else (
	echo "%cd%\%folderName%" not found. Create this folder first.
	goto :EMPTY_FILE_LINE
)
echo We are all set. Next time give following command: CodeExecutor.bat {Java filename without .java}
goto :EOF

:EXECUTE_JAVA
REM echo The persistedFolderName value =%persistedFolderName%.%argument1%
javac -cp ../lib/junit.jar;../lib/hamcrest.jar;.  "%persistedFolderName%\%argument1%.java"
IF NOT EXIST ..\bin\%persistedFolderName% (
	mkdir ..\bin\%persistedFolderName%	>NUL 2>&1
)
move %persistedFolderName%\*.class ..\bin\%persistedFolderName%\ > nul

REM Removing last character if it is BackSlash "\"
IF "%str:~-1%" == "\" (
	set str=%str:~0,-1%
)
REM Replacing BackSlash "\" with Dot "." because Java runtime needs Folder structure as Dot notation Package name
set persistedFolderName=%persistedFolderName:\=.%

java -cp ../lib/junit.jar;../lib/hamcrest.jar;../bin/ org.junit.runner.JUnitCore %persistedFolderName%.%argument1%
goto :EOF

:EOF
echo done