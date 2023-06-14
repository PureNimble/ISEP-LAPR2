@echo off
echo Generating SVGs for PlantUML Diagrams

set "sourceDirectory=docs\sprintD"
set "outputDirectory=..\svg"

for /R "%sourceDirectory%" %%F in (*.puml) do (
  echo Processing: %%F
  java -jar libs\plantuml-1.2023.1.jar "-SdefaultFontSize=20" -tsvg  "%%F"  -o "%outputDirectory%"
)

echo SVG generation complete.