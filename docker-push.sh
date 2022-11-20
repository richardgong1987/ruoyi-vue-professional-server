mvn clean

mvn clean package

set -eu

cp -f ruoyi-admin/target/ruoyi-admin.jar docker/app.jar

cd docker

docker-compose build

docker login --username=richardgong1987 --password-stdin  < ~/DockerPassword.txt


docker-compose push

