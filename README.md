# Resource Order

Esse sistema ilustra a construção de aplicações nativas para nuvem que utilizam arquitetura hexagonal
e são orquestradas como microserviços.

Os recursos nativos do Kubernetes são utilizados para aplicar técnicas de Service Discovery na comunicação
inter-servicos, API Gateway com ingress para expor externamente os endpoints do sistema e uso de ConfigMaps e
Secrets para fornecer variáveis de ambiente. Tais recursos se tornam possíveis graças ao Spring Cloud Kubernetes
que se integra com a API do cluster.

![Arquitetura](https://github.com/m4ndr4ck/resource-order/blob/master/src/main/resources/microservicos-hexagonal-v2.png?raw=true) 

## Configurando ambiente de desenvolvimento

```
minikube start --vm-driver=virtualbox --memory='2500mb'
```
Habilite o ingress do Nginx:
```
minikube addons enable ingress
```

Rode o comando abaixo e adicione no arquivo hosts com o IP da VM onde está seu Minikube:

**{ip.do.minikube} services.oss.redecorp**

No Linux
```
minikube ssh ifconfig | grep eth1 -A1 | grep "inet addr" | cut -d: -f2| awk '{ print $1 }'
```
No Windows
```
minikube ssh ifconfig  | findstr "inet addr" | findstr 192
```

Ajuste o Docker para construir as imagens utilizando o ambiente do Minikube:
```
eval $(minikube docker-env)
```

Instale **primeiro** os objetos do Resource Order Infra, depois rode **mvn clean package** e o **docker build** em cada aplicação:
```
kubectl apply -f ingress.yaml
kubectl apply -f mongodb-configmap.yaml
kubectl apply -f mongodb-secret.yaml
kubectl apply -f mongodb-deployment.yaml
kubectl apply -f serviceaccount-rbac.yaml
kubectl apply -f k8s/deployment.yaml
```

## Arquitetura

- **[Resource Order](https://github.com/m4ndr4ck/resource-order)** - Responsável pela execução de ordens que contenham componentes de rede ativos.
Para realizar isso existe uma integração com o Resource Order Orchestration.
- **[Resource Order Orchestration](https://github.com/m4ndr4ck/resource-order-orchestration)** - Permite a criação de
componentes de rede como vFirewall ou vRouter que são utilizados na criação de novas ordens. 
- **[Resource Order Gateway](https://github.com/m4ndr4ck/resource-order-gateway)** - Gateway do Swagger que agrega as APIs de todos os microserviços do sistema.
- **[Resource Order Infra](https://github.com/m4ndr4ck/resource-order-infra)** - Contém os objetos do Kubernetes para instalação do MongoDB e do Ingress como API Gateway.



## Para rodar o Resource Order

```
mvn clean package
```

```
docker build . -t oss/resource-order:1.0 
```

```
kubectl apply -f k8s/deployment.yaml 
```

Exemplo de payload (utilize o IDs gerado pelo Resource Order Orchestration):

```
curl --header "Content-Type: application/json" --request POST --data '[{"id":"5e057c40cc5ed83c81faa3c4"},{"id":"5e057c57cc5ed83c81faa3c5"}]' http://services.oss.redecorp/resource-order/v1/createorder
```

Resposta:
```
{
  "id": "5e057c9f9762581ce6d8aeb4",
  "networkComponent": [
    {
      "id": "5e057c40cc5ed83c81faa3c4",
      "name": "vRouter",
      "status": "ATIVADO"
    },
    {
      "id": "5e057c57cc5ed83c81faa3c5",
      "name": "vFirewall",
      "status": "ATIVADO"
    }
  ]
}
```

