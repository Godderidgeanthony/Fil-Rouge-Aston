************************  Installations sur la VM ************************

#### On identifie les MAJ ànstaller ####

sudo apt-get update

#### On met àour les déndance ####

sudo apt-get upgrade

#### On modifie le mot de passe root ####

sudo passwd root

#### On se connecte en mode root ####

su root 

#### On accorde les privilès root aux users ####
1
vi /etc/sudoers
Ajouter la ligne suivante sous # User privilege specification
hydcloud ALL=(ALL:ALL) ALL
Reboot

#### Permettre àpt d'utiliser les paquets https ####

sudo apt install apt-transport-https ca-certificates curl software-properties-common

#### Ajouter la clée séritéPG du dét officiel de Docker au systè d'exploitation ####

wget -qO https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

#### Ajouter le rérentiel Docker aux sources APT ####

sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu focal stable"

#### On met àour la BDD des paquets avec les paquets Docker àartir du rérentiel qui vient d'êe ajouté###

sudo apt update

#### On véfie la source d'installation ####

apt-cache policy docker-ce
**** Nous devons obtenir le réltat suivant :
Candidate : 5.20.10.5~3-0~ubuntu-focal 500 ****

#### On lance l'installation de Docker ####

sudo apt install docker-ce

#### On véfie que le service est lancé###

systemctl status docker

#### On ajoute les droits d'administration Docker àhacun des membres du groupe ####

@root
sudo usermod -aG docker hydcloud

#### On véfie l'appartenance du user au groupe Docker ####

id -nG

#### On installe Git ####

sudo apt-get install git

Optionnel #### Installer les drivers VirtualBox ####

Aprèavoir insé l'ISO
sudo mount /dev/cdrom /media
sudo ./VBoxLinuxAdditions.run

#### On installe node.js et Angular ####

sudo apt install nodejs npm
npm install -g @angular/cli

#### Installation de nginx ####

sudo apt install nginx-core

#### Installation de Springboot ####

sudo apt install openjdk-8-jdk
sudo apt install wget openssl vim software-properties-common -y
sudo apt install maven

*** Install dependencies for SDKMAN!: ***

sudo apt install unzip zip

*** Install dependencies for SDKMAN!: ***
*** Install SDKMAN!: ***

sudo apt update
curl -s https://get.sdkman.io/ | bash

*** Follow the instructions printed on the console: ***

source "/home/hydcloud/.sdkman/bin/sdkman-init.sh"
*** Verify SDKMAN! is installed: *** 

sdk help

*** Install Spring CLI: *** 

sdk install springboot

*** Verify the installation: ***

spring version

************************ Dockerfile build ************************

#### Optionnel : Cré une branche Git ####

Dans le dossier àynchroniser
git init
git checkout -b "Nom de la branche"
git remote add "Nom de la branche" "URL"
git add .
git commit -m "commentaire"
git push -u "Nom de la branche déarédans le Checkout" "Nom de la branche de destination"
 
#### On crénotre premier environnement local pour la construction de l'image ####

On git clone àa racine les repo : 
git clone https://github.com/Godderidgeanthony/TestFilrouge.git

#### Docker Front ###

Directory
/home/hydcloud/Docker/TestFilrouge/Projet-Fil-Rouge

#### On lance nmp install ####

/home/hydcloud/Docker/TestFilrouge/Projet-Fil-Rouge
npm install

#### Contruction de l'image - Front ####

vi Dockerfile

Ajout des lignes suivante : 

### STAGE 1: Build ###
FROM node:12.7-alpine AS build
WORKDIR /usr/src/app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build

### STAGE 2: Run ###
FROM nginx:1.17.1-alpine
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build /usr/src/app/dist/angular8-springboot-client /usr/share/nginx/html

#### On modifier les IP dans les appli ####

/home/hydcloud/Docker/TestFilrouge/Projet-Fil-Rouge/src/app
employee.service.ts
produit.service.ts

#### On construit l'image ####

ng build --prod

#### On build l'image ####

sudo docker build -t image-front .

#### Containerisation de l'image ####

sudo docker run --name container-front -d -p 4200:4200 -t image-front

#### Docker Back ###

Directory
/home/hydcloud/Docker/TestFilrouge/ProjetFilRouge

#### Contruction de l'image - Back ####

vi Dockerfile

Ajout des lignes suivante : 

FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/ProjetFilRouge-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

#### On modifier les IP dans les appli ####

/home/hydcloud/Docker/TestFilrouge/ProjetFilRouge/src/main/java/com/aston/filrouge/controller
EmployeeController.java 
ProduitController.java

#### On construit l'image ####

Ajouter la variable d'environnement sur la VM Cloud :
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
mvn clean package

#### On build l'image ####

sudo docker build -t image-back .

#### Containerisation de l'image ####

sudo docker run --name container-back -d -p 4200:4200 -t image-back

#### Push de l'image àartir du Docker Hub et montage (back) ####

Dans le path adéat (Front ou Back) :
sudo docker build -t hydcloud/fil-rouge:image-back .
sudo docker push hydcloud/fil-rouge:image-back
sudo docker pull hydcloud/fil-rouge:image-back
sudo docker run --name container-back -d -p 8080:8080 -t "id de l'image"

#### Push de l'image àartir du Docker Hub et montage (front) ####

Dans le path adéat (Front ou Back) :
sudo docker build -t hydcloud/fil-rouge:image-front .
sudo docker push hydcloud/fil-rouge:image-front
sudo docker pull hydcloud/fil-rouge:image-front
sudo docker run --name container-front -d -p 4200:4200 -t "id de l'image"

#### Nettoyer les build Docker #### 

sudo docker system prune --all --force --volumes

#### Redérrer automatiquement le container aprèreboot ####

docker run --restart=always -p 8080:8080
    --name v2-mirror -v /var/lib/docker-registry:/var/lib/registry \
    --detach registry:2 serve /var/lib/registry/config.yml

************************ Jenkins Installation ************************

docker pull jenkins/jenkins

docker run -p 18080:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts

mot de passe : 2495fe861c064c2faa1e8b05cd523e93

************************ ELK Installation/Tests ************************

#### Elasticsearch Installation ####

wget -qO - https://artifacts.elastic.co/GPG-KEY-elasticsearch | sudo apt-key add -

sudo apt-get install apt-transport-https

echo "deb https://artifacts.elastic.co/packages/7.x/apt stable main" | sudo tee -a /etc/apt/sources.list.d/elastic-7.x.list

sudo apt-get update -y && sudo apt-get install elasticsearch

#### Elasticsearch Lancement ####

sudo systemctl start elasticsearch

curl localhost:9200

Résultat : 

{
  "name" : "aston-fil-rouge-cicd-n1",
  "cluster_name" : "elasticsearch",
  "cluster_uuid" : "SI6HLQR1QzCQyQ3pPLmX9g",
  "version" : {
    "number" : "7.12.1",
    "build_flavor" : "default",
    "build_type" : "deb",
    "build_hash" : "3186837139b9c6b6d23c3200870651f10d3343b7",
    "build_date" : "2021-04-20T20:56:39.040728659Z",
    "build_snapshot" : false,
    "lucene_version" : "8.8.0",
    "minimum_wire_compatibility_version" : "6.8.0",
    "minimum_index_compatibility_version" : "6.0.0-beta1"
  },
  "tagline" : "You Know, for Search"
}

sudo systemctl enable elasticsearch

#### Kibana ####

#### Kibana Installation ####

sudo apt-get install kibana

Dans le fichier /etc/kibana/kibana.yml

Décommenter la ligne :
elasticsearch.hosts: ["http://localhost:9200"]

#### Kibana Lancement ####

sudo systemctl start kibana

#### Logstash ####

#### Logstash Installation ####

sudo apt-get install logstash

Dans le fichier /etc/logstash/logstash.yml

#### Logstash Lancement ####

sudo systemctl start logstash

sudo systemctl enable logstash

#### Ajouter les dépendances Java ####

Éditer le fichier /home/ubuntu/Projet-Fil-Rouge/Fil-Rouge-Aston/ProjetFilRouge-Back/pom.xml

<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

<dependency>
   <groupId>net.logstash.logback</groupId>
   <artifactId>logstash-logback-encoder</artifactId>
   <version>6.3</version>
</dependency>

<dependency>
   <groupId>org.projectlombok</groupId>
   <artifactId>lombok</artifactId>
   <optional>true</optional>
</dependency>

#### Configurer les logs ####

Éditer les fichiers :
/home/ubuntu/Projet-Fil-Rouge/Fil-Rouge-Aston/ProjetFilRouge-Back/target/classes/logback.xml
/home/ubuntu/Projet-Fil-Rouge/Fil-Rouge-Aston/ProjetFilRouge-Back/src/main/resources/logback.xml

<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <property scope="context" name="log.fileExtension" value="log"/>
    <property scope="context" name="log.directory" value="/home/ubuntu/logs"/>
    <property scope="context" name="log.fileName" value="filrouge-elk"/>

    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <layout class="ch.qos.logback.classic.PatternLayout">
            <pattern>[%d{yyyy-MM-dd HH:mm:ss.SSS}] [${HOSTNAME}] [%thread] %level %logger{36}@%method:%line - %msg%n</pattern>
        </layout>
    </appender>

    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${log.directory}/${log.fileName}.%d{yyyy-MM-dd}.${log.fileExtension}</fileNamePattern>
        </rollingPolicy>
        <encoder>
            <pattern>[%d{yyyy-MM-dd HH:mm:ss.SSS}] [${HOSTNAME}] [%thread] %level %logger{36}@%method:%line - %msg%n</pattern>
        </encoder>
        </appender>

    <appender name="STASH" class="net.logstash.logback.appender.LogstashTcpSocketAppender">
        <destination>172.81.181.47:5000</destination>
        <!-- encoder is required -->
        <encoder class="net.logstash.logback.encoder.LogstashEncoder"/>
        <keepAliveDuration>10 minutes</keepAliveDuration>
    </appender>

    <root level="INFO">
        <appender-ref ref="STDOUT"/>
        <appender-ref ref="STASH"/>
        <appender-ref ref="FILE"/>
    </root>
</configuration>

#### Logstash Configuration ####

Créer et éditer le fichier /etc/logstash/conf.d/logstash-file.conf

input {
    file {
        path => "/home/ubuntu/logs.log"
        codec => "plain"
        type => "logback"
    }
}

filter {
  if [message] =~ "\tat" {
    grok {
      match => ["message", "^(\tat)"]
      add_tag => ["stacktrace"]
    }
  }

  grok {
    match => [ "message",
               "(?m)\[%{TIMESTAMP_ISO8601:timestamp}\] \[%{HOSTNAME:host}\] \[%{DATA:thread}\] %{LOGLEVEL:logLevel} %{DATA:class}@%{DATA:method}:%{DATA:line} \- %{GREEDYDATA:msg}"
             ]
  }

  date {
    match => [ "timestamp" , "yyyy-MM-dd HH:mm:ss.SSS" ]
  }
  mutate {
    remove_field => ["message"]
  }
}

output {
    stdout { codec => rubydebug }
    elasticsearch {
        hosts => ["localhost:9200"]
        index => "filrouge-elk-file-%{+YYYY.MM.dd}"
        #user => "elastic"
        #password => "changeme"
    }
}

Créer et éditer le fichier /etc/logstash/conf.d/logstash-tcp.conf

input {
    tcp {
        port => "5000"
        codec => json_lines
    }
}

output {
    stdout {}
    elasticsearch {
        hosts => ["http://localhost:9200"]
        index => "filrouge-elk-tcp-%{+YYYY.MM.dd}"
  }
}

In

#### Logstash Injecter la configuration ####

En super administrateur (sudo su)

Dans la directory /usr/share/logstash/bin/

Modifier la variable d'enrironnement jdk : export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/

Injecter le code :

./logstash -f /etc/logstash/conf.d/logstash-file.conf
./logstash -f /etc/logstash/conf.d/logstash-tcp.conf

Redémarrer la machine

#### Interface Kibana ####

Management => Stack Management => Data => Index Management => Ajouter l'index filrouge-elk-file-****.**.**

Management => Stack Management => Kibana => Index Patterns => Create index pattern => Sélectionner l'index filrouge-elk-file-****.**.**

Analytics => Discover => Ajouter les filtres 






