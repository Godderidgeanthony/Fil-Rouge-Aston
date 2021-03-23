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

Pour le front :
mkdir /home/hydcloud/Docker/fil-rouge-front/Package
touch Dockerfile
Pour le back :
mkdir /home/hydcloud/Docker/fil-rouge-back/Package
touch Dockerfile

#### On ajoute l'ensemble des fichiers dans le dossier Package ####



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

#### Nettoyer les build Docker #### 

docker system prune --all --force --volumes
