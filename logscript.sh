while true
do
    sudo docker logs -f -t container-back > /home/ubuntu/logs/test.log
    java -jar /home/ubuntu/ProjetFilRouge/FilRouge/Azurecontainer/target/Azurecontainer*.jar
    sleep 750
done 
