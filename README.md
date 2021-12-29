# ArmyPostponement
Army Postponement App, a Spring project in the context of HUA DIT course 'Distributed Systems'

<p align="left"> <img src="https://komarev.com/ghpvc/?username=pauliee99&label=Profile%20views&color=0e75b6&style=flat" alt="pauliee99" /> </p>

<h3 align="left">Languages and Tools:</h3>
<p align="left">
  <a href="https://azure.microsoft.com/en-in/" target="_blank"> <img src="https://www.vectorlogo.zone/logos/microsoft_azure/microsoft_azure-icon.svg" alt="azure" width="40" height="40"/> </a>
  <a href="https://www.gnu.org/software/bash/" target="_blank"> <img src="https://www.vectorlogo.zone/logos/gnu_bash/gnu_bash-icon.svg" alt="bash" width="40" height="40"/> </a>
  <a href="https://getbootstrap.com" target="_blank"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/bootstrap/bootstrap-plain-wordmark.svg" alt="bootstrap" width="40" height="40"/> </a>
  <a href="https://www.w3schools.com/css/" target="_blank"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/css3/css3-original-wordmark.svg" alt="css3" width="40" height="40"/> </a>
  <a href="https://spring.io/" target="_blank"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="40" height="40"/> </a> 
  <a href="https://www.docker.com/" target="_blank"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/docker/docker-original-wordmark.svg" alt="docker" width="40" height="40"/> </a>
  <a href="https://cloud.google.com" target="_blank"> <img src="https://www.vectorlogo.zone/logos/google_cloud/google_cloud-icon.svg" alt="gcp" width="40" height="40"/> </a>
  <a href="https://git-scm.com/" target="_blank"> <img src="https://www.vectorlogo.zone/logos/git-scm/git-scm-icon.svg" alt="git" width="40" height="40"/> </a> 
  <a href="https://www.w3.org/html/" target="_blank"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/html5/html5-original-wordmark.svg" alt="html5" width="40" height="40"/> </a> 
  <a href="https://www.linux.org/" target="_blank"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/linux/linux-original.svg" alt="linux" width="40" height="40"/> </a>
  <a href="https://www.nginx.com" target="_blank"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/nginx/nginx-original.svg" alt="nginx" width="40" height="40"/> </a>
  <a href="https://www.mysql.com/" target="_blank"> <img src="https://www.vectorlogo.zone/logos/mysql/mysql-official.svg" alt="mysql" width="40" height="40"/> </a> 
  <a href="https://angular.io/" target="_blank"> <img src="https://www.vectorlogo.zone/logos/angular/angular-icon.svg" alt="angular" width="40" height="40"/> </a> </p>

## Connect to the database
* Creating docker container
```Bash
docker run --name mysqldb -v mysqldbvol:/var/lib/mysql -p 3306:3306 -e MYSQL_USER=user -e MYSQL_PASSWORD=password -e MYSQL_DATABASE=army_postponent -e MYSQL_ROOT_PASSWORD=password -d mysql/mysql-server:latest
```
* Connect to Dadabase
```Bash
docker exec -it mysqldb bash
mysql -h localhost -u root  -P 3306 -p
```
* To connect with Dbeaver:
<p>Driver Properties -> publicKeyRetrival=TRUE</p>
```Mysql
UPDATE mysql.user SET Host='%' WHERE Host='localhost' AND User='root';
UPDATE mysql.db SET Host='%' WHERE Host='localhost' AND User='root';
```
