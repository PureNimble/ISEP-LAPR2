#! /bin/sh

echo "LOG: Publish Content to public version of the repository"

cp -r src ../esoft-tp-classes-support-public/
cp -r pom.xml ../esoft-tp-classes-support-public/
cp -r .gitignore ../esoft-tp-classes-support-public/

for folder in `find docs -name "tp*" -type d`;
do
  echo "Processing folder: $folder"
  #folder_name=${tp_folder##*/}
  folder_name=${folder##*/}
  #echo "folder_name:"$folder_name

  tp_folder=${folder%/*}
  #echo "tp_folder:"$tp_folder

  tp_name=${tp_folder##*/}
  #echo "tp_name:"$tp_name

  mkdir -p ../esoft-tp-classes-support-public/docs/$folder_name/puml
  mkdir -p ../esoft-tp-classes-support-public/docs/$folder_name/svg
  mkdir -p ../esoft-tp-classes-support-public/docs/$folder_name/pdf

  cp -r $folder/svg ../esoft-tp-classes-support-public/docs/$folder_name
  cp -r $folder/puml ../esoft-tp-classes-support-public/docs/$folder_name
  cp -r $folder/pdf ../esoft-tp-classes-support-public/docs/$folder_name
done

echo "Finished"