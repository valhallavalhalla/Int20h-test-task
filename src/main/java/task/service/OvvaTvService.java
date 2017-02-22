package task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import task.model.ovvatv.Channel;
import task.model.ovvatv.Language;
import task.model.ovvatv.ProgramsData;

import java.time.LocalDate;

@Service
public class OvvaTvService {

    @Autowired
    private RestTemplate restTemplate;

    public ProgramsData sendProgramsDataRequest(Language language, Channel channel, LocalDate localDate) {
        String url = "https://api.ovva.tv/v2/" + language.getValue() + "/tvguide/"
                + channel.getName() + "/" + localDate.toString();
        ResponseEntity<ProgramsData> responseEntity = restTemplate.getForEntity(url, ProgramsData.class);
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("Can't get data! Request to API failed!");
        }
    }

}
