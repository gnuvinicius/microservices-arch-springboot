# Microservices-arch-springboot



#### comandos do kubectl

-A todos namespaces  
-n <namespace-name> kube-system (default do k8s)

```
$ kubectl get all
$ kubectl get pods -A -o wide
$ kubectl rollout restart deploy <pod-name>
$ kubectl describe pods -n <namespace> <pod-name>
```


#### comandos do minikube

```
minikube service auth-api --url

minikube start --nodes 2 -p k8s-garage474
$ Done! kubectl is now configured to use "k8s-garage474" cluster and "default" namespace by default

minikube tunnel
minikube service auth-api --url
minikube dashboard -p k8s-garage474


minikube ip //pegar IP do node
```