@echo off

%~d0
cd %~p0

rem maven��clean
rem call mvn clean

rem maven��deploy
call mvn eclipse:eclipse -DdownloadSources=true -Declipse.useProjectReferences=false
rem call mvn eclipse:eclipse -DdownloadSources=true


pause