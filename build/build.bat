@echo off
cd /d %~dp0
cd .
dir /s /B *.class > sources.txt
powershell -Command "(gc sources.txt) -replace 'C:\\Users\\Jared\\Documents\\Mewsubi\\Plugins\\DupeCrate\\build\\', '' | Out-File -encoding ASCII sources.txt"
jar cmf dupecrate.mf ../DupeCrate.jar plugin.yml @sources.txt
cd ..