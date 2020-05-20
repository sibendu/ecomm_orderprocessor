# ecomm_orderprocessor

OrderProcessor Service. Listens for incoming message at input queue. Processes the incoming order payload, pauses for an amount of time, and simulates a response from backend ERP system in the response queue. Input / output queues, backend ERO identifier and delays are configurable. 

1> Build 
docker build -t sibendudas/orderprocessor .

2> Run the container with host network
docker run -p 8080:8080  --network host  --name orderprocessor  -it sibendudas/orderprocessor "--INPUT_QUEUE=input12"  "--OUTPUT_QUEUE=output12" 

ActiveMQ must be running on localhost (port 8161 and 616161)

3> To call test REST endpoint:
docker exec -it orderprocessor  sh -c "wget http://localhost:8080/api/myorder1111"

4> To simulate processing for multiple ERP backends, deploy multiple instances e.g.

kubectl apply -f deploy_mp1processor.yaml
kubectl apply -f deploy_prdprocessor.yaml
 