package fr.uphf.etu.gateway.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import fr.uphf.etu.gateway.util.ApiResponse;
import fr.uphf.etu.gateway.wsdl.TransactionRequest;
import fr.uphf.etu.gateway.wsdl.TransactionResponse;

@Endpoint
public class TransactionEndpoint {
    private static final String NAMESPACE_URI = "http://etu.uphf.fr/gateway/wsdl";
    private static final String BANK_OPERATION_URI = "http://localhost:8080/bank-0/rest/operations";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "transactionRequest")
    @ResponsePayload
    public TransactionResponse operation(@RequestPayload TransactionRequest request) {
        TransactionResponse response = new TransactionResponse();

        try {
            RestTemplate template = new RestTemplate();
            template.postForObject(BANK_OPERATION_URI, request, Void.class);
            response.setSuccess(true);
        } catch (RestClientResponseException e) {
            response.setSuccess(false);
            ObjectMapper xmlMapper = new XmlMapper();
            try {
                ApiResponse apiResponse = xmlMapper.readValue(e.getResponseBodyAsString(), ApiResponse.class);
                response.setMessage(apiResponse.getMessage());
            } catch (JsonProcessingException e1) {
                response.setMessage("Internal error");
            }
        }

        return response;
    }
}
