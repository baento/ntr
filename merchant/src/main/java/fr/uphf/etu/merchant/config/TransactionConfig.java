package fr.uphf.etu.merchant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import fr.uphf.etu.merchant.clients.TransactionClient;

@Configuration
public class TransactionConfig {
  @Bean
  public Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setContextPath("fr.uphf.etu.gateway.wsdl");
    return marshaller;
  }

  @Bean
  public TransactionClient transactionClient(Jaxb2Marshaller marshaller) {
    TransactionClient client = new TransactionClient();
    client.setDefaultUri("http://localhost:8081/ws");
    client.setMarshaller(marshaller);
    client.setUnmarshaller(marshaller);
    return client;
  }
}
