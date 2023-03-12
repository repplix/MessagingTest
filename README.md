[![Maven Test Build](https://github.com/repplix/MessagingTest/actions/workflows/mavenBuild.yml/badge.svg)](https://github.com/repplix/MessagingTest/actions/workflows/mavenBuild.yml)
[![New Release](https://github.com/repplix/MessagingTest/actions/workflows/newRelease.yml/badge.svg)](https://github.com/repplix/MessagingTest/actions/workflows/newRelease.yml)

# MessagingTest
Validates the new TransactionOutboxSender and IdempotentReceive in a long term test  

## Requirements

*   Java 17+ installed
*   IDE with maven support 
*   [Optional] Docker or Kubernetes if you want to run your application in a container. See [here](README-CICD.md) for more information.   
*   [Optional] A locally running [developer stack](deploy/developerStack.yml) providing a Postgres database, ActiveMQ broker, and Swagger-UI 

## Build the Project

*   Checkout the new project in your favorite IDE

*   Without running [developer stack](deploy/developerStack.yml):
    ```shell
    mvn clean install -P '!integrationTests'

    java -jar "-Dio.jexxa.config.import=src/test/resources/jexxa-local.properties" target/jexxatemplate-jar-with-dependencies.jar
    ```

*   [Optional] **With** running [developer stack](deploy/developerStack.yml):
    ```shell
    mvn clean install
    
    java -jar "-Dio.jexxa.config.import=src/test/resources/jexxa-test.properties" target/jexxatemplate-jar-with-dependencies.jar
    ```

