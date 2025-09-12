FROM amazonlinux:2

# Install dependencies
RUN yum install -y java-21-amazon-corretto-headless-21.0.8+9-1.amzn2.1 procps telnet net-tools

COPY delivery-api-carloseduardoeugenio/target/delivery-api-carloseduardoeugenio*.jar /opt/delivery-api/delivery-api-carloseduardoeugenio.jar

ENTRYPOINT ["/usr/bin/java","-jar","/opt/delivery-api/delivery-api-carloseduardoeugenio.jar"]