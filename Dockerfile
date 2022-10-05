FROM java:8
ADD target/couponservice-0.0.1-SNAPSHOT.jar couponservice.jar
ENTRYPOINT [ "java","-jar","couponservice.jar" ]