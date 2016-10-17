@echo off
setlocal & pushd
set BASE=%~dp0
set CP=%BASE%\classes;%BASE%\lib\*
java -Dapp.mode=prod -Dprofile=%ACT_PROFILE% -cp "%CP%" demo.InjectionApp
endlocal & popd
