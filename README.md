Installations sur la VM ************************

#### On identifie les MAJ ànstaller ####

sudo apt-get update

#### On met àour les déndance ####

sudo apt-get upgrade

#### On modifie le mot de passe root ####

sudo passwd root

#### On se connecte en mode root ####

su root 

#### On accorde les privilès root aux users ####

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
nmp install

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

mvn clean package

#### On build l'image ####

sudo docker build -t image-back .

#### Containerisation de l'image ####

sudo docker run --name container-back -d -p 4200:4200 -t image-back

#### Nettoyer les build Docker #### 

sudo docker system prune --all --force --volumes
