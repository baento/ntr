package fr.uphf.etu.merchant.clients;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import fr.uphf.etu.gateway.wsdl.TransactionRequest;
import fr.uphf.etu.gateway.wsdl.TransactionResponse;

public class TransactionClient extends WebServiceGatewaySupport {

  public TransactionResponse doTransaction(Integer id, String code, Double amount) {
    TransactionRequest request = new TransactionRequest();
    request.setId(id);
    request.setCode(code);
    request.setAmount(amount);
    request.setMerchant("Merchant 1");

    TransactionResponse response = (TransactionResponse) getWebServiceTemplate()
        .marshalSendAndReceive("http://localhost:8090/gateway-0/ws", request);

    return response;
  }
}