Setup the mysql container:
```
docker run -d -p 6666:3306 --name=docker-mysql -- env="MYSQL_ROOT_PASSWORD=test1234" --env="MYSQL_DATABASE=mydb" mysql

docker exec -it docker-mysql bash
```

```
# mysql -uroot -p
test1234
mysql> show databases;
mysql> show tables;
```


Another Terminal:
```
docker exec -i docker-mysql mysql -uroot -ptest1234 mydb <tables.sql
```

Launch the Application Containers:
```
docker build -f Dockerfile -t coupon_app .
docker run -t --name=coupon-app --link docker-mysql:mysql -p 9091:9091 coupon_app
docker build -f Dockerfile -t product_app .
docker run -t --link docker-mysql:mysql -p 9090:9090 product_app
docker run -t --link docker-mysql:mysql --link coupon-app:coupon_app -p 9090:9090 product_app
```

Testing: 
http://localhost:9091/couponapiapi
http://localhost:9090/productapi
The --link command will allow the Containers