Setup the mysql container:
```
docker run -d -p 3306:3306 --name=docker-mysql --env="MYSQL_ROOT_PASSWORD=test1234" --env="MYSQL_DATABASE=mydb" -v=/Users/aambooken/sourcetree/couponservice-helm/sql:/docker-entrypoint-initdb.d mysql

docker exec -it docker-mysql bash
```
After entering the terminal inside mysql docker container
```
mysql -uroot -p
Enter password: test1234

mysql> show databases;
mysql> use mydb;
mysql> show tables;
```


Launch the Application Containers:
```
docker build -f Dockerfile -t coupon-app .
docker run -t --name=coupon-app --link docker-mysql:mysql -p 9091:9091 coupon-app

docker build -f Dockerfile -t product_app .
docker run -t --link docker-mysql:mysql -p 9090:9090 product_app
docker run -t --link docker-mysql:mysql --link coupon-app:coupon_app -p 9090:9090 product_app
```

Testing: 
http://localhost:9091/couponapiapi
http://localhost:9090/productapi
The --link command will allow the Containers