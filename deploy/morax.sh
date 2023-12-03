#!/usr/bin/env bash

cd /Users/${USER}/codebase/salon-friday/fridayapps || exit

appName=app
projectName=morax
#ng build  ${appName} --configuration=production
cd build/
cp ../deploy/app.yaml ${appName}.jar

# Define the file to modify
FILE="${appName}.yaml"
OLD_LINE="service: default"
NEW_LINE="service: default"

# Use sed to replace the line in the file
sed -i '' "s/$OLD_LINE/$NEW_LINE/g" $FILE

rm -rf www
mkdir www
cp -r ${appName}/ www/
#
gcloud auth activate-service-account --key-file=/Users/${USER}/.google/salonfriday/deployer@salonfriday.json
gcloud config set project ${projectName}
gcloud app deploy ${appName}.yaml --project=${projectName} -q

gcloud app versions delete $(gcloud app versions list --format='value(id)' --filter="traffic_split = 0" --service=default) -q
