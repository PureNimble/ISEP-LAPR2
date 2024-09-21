#! /bin/sh
#cd /Users/nuno/Documents/RD/PHD/Tese/PhDThesis-NMB[Lyx]/LaTeXDocument
echo "LOG: Generate Plantuml Diagrams"
exportFormat="svg"
#monochrome="true"
extra="-SdefaultFontSize=20"
#extra="-SdefaultFontName=Times New Roman -SdefaultFontSize=10"

#processing single files
#for plantuml_file in `find ../docs -name "*.puml" -type f`;
#do
#  path=${plantuml_file%/*}
#  filename=${plantuml_file##*/}
#  echo "Processing file: $plantuml_file"
#
#  path="$path/svg"

#  echo "PATH:$path"
#  mkdir -p $path

  #-Smonochrome=$monochrome
#	java -jar ../libs/plantuml-1.2023.1.jar -t$exportFormat $plantuml_file -o "../svg"
#done

for folder in `find docs -name "puml" -type d`;
do
  #-Smonochrome=$monochrome
  echo "Processing folder: $folder"
	java -jar libs/plantuml-1.2023.1.jar $extra -t$exportFormat $folder -o "../svg"
done

echo "Finished"