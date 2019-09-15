#!/bin/bash
rm -rf ../dist
mkdir ../dist
(cd ..
af=0
ant || af=1
if [ $af -eq 1 ];then
  echo Compile error
  exit
fi
)
(cd ..
af=0
ant test || af=1
if [ $af -eq 1 ];then
  echo Test error
  exit
fi
)
proguard4.8/bin/proguard.sh @finalproguard3.pro
java -jar JarSplicePlus/JarSplicePlus.jar -i ../dist/RogueCave-opt.jar ../dist/RogueCave-build_lib/* -n ../lib/lwjgl-2.9.0/native/{linux,windows,macosx}/* -o ../dist/RogueCave.jar -m com.elezeta.roguecave.gui.RogueCaveLauncher -p -Xms256m -Xmx512m
cd ..
cd dist
java -jar RogueCave-build.jar --saveDefaultFiles
rm -rf RogueCave-opt.jar RogueCave-build.jar RogueCave-build_lib
cp ../doc/readme.txt .
cp ../doc/license.txt .
zip -r -9 RogueCave.zip RogueCave.jar config.cfg readme.txt license.txt
