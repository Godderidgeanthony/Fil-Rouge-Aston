#### Optionnel : Créer une branche Git ####

Dans le dossier à synchroniser
git init
git checkout -b "Nom de la branche"
git remote add "Nom de la branche" "URL"
git add .
git commit -m "commentaire"
git push -u "Nom de la branche déclarée dans le Checkout" "Nom de la branche de destination"
 
#### On crée notre premier environnement local pour la construction de l'image ####

mkdir /home/hydcloud/Docker/fil-rouge-front
touch Dockerfile

#### Contruction de l'image ####

vi Dockerfile

Ajout des lignes suivante : 

FROM scratch
MAINTAINER Stephane Gilbart < stephane.gilbart [ at ] social.aston-ecole.com >

On test un premier build :

docker build https://hub.docker.com/repository/docker/hydcloud/fil-rouge-front.git#hydcloud:docker