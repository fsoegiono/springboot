# springboot
Run the following command in the root directory:

`docker-compose up --build`

To test

`curl http://localhost:8080/`

`Hello, World!`

Run Kubernetes locally using the following command:

`minikube start`

`kubectl apply -f`

Monitor deployment

`minikube dashboard`

NOTE: error might occur in Kafka or App itself. Please restart Kafka and then restart the App.

Get URL

`minikube service spring-boot-app-service --url`

Spring App is served in Kubernetes local machine
