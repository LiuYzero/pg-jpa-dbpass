$microservicename="pg-jpa-dbpaas-scheduler"

cd ..

mvn clean

mvn package -DskipTests

ssh paas@192.168.1.111 "mkdir -p /home/paas/microservices/$microservicename/"

scp .\target\*.jar paas@192.168.1.111:/home/paas/microservices/$microservicename/
scp .\scripts\* paas@192.168.1.111:/home/paas/microservices/$microservicename/

ssh paas@192.168.1.111 "cd /home/paas/microservices/$microservicename;chmod u+x *.sh;bash ./restart.sh"

mvn clean

cd scripts