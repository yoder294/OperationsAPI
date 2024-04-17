package operation.api.transactions.app.services.rest;


import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import operation.api.transactions.app.configuration.CustomFeignClientConfiguration;
import operation.api.transactions.app.dtos.restclient.ClientBasicInfoResponseDto;
import operation.api.transactions.app.exceptions.SuccessResponseTemp;

@RefreshScope
@FeignClient(name = "service-users", url= "${user.client.path}", configuration =  CustomFeignClientConfiguration.class)
public interface ClientFeignClientRest {
	
	@GetMapping(path = "/identificationNumber/{idt}")
	SuccessResponseTemp<ClientBasicInfoResponseDto>  findByIdentificationNumber(@PathVariable(required = true) String idt);

}
