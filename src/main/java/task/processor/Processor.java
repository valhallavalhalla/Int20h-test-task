package task.processor;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.model.ovvatv.Channel;
import task.model.ovvatv.Language;
import task.model.ovvatv.Program;
import task.model.ovvatv.ProgramsData;
import task.service.OvvaTvService;
import task.service.VkService;
import task.util.ImageGenerator;

import java.io.File;
import java.sql.Timestamp;
import java.time.*;
import java.util.Date;

@Service
public class Processor {

    @Autowired
    private OvvaTvService ovvaTvService;
    @Autowired
    private VkService vkService;

    public void generateAndUploadTvProgram(String code) throws ClientException, ApiException {
        ProgramsData data = ovvaTvService.
                sendProgramsDataRequest(Language.UA, Channel.ONE_PLUS_ONE, LocalDate.now());

        String textProgram;
        textProgram = data.getData().getDate();
        for (Program program : data.getData().getPrograms()) {
            Date date = new Timestamp(program.getRealtimeBegin());
            Instant instant = Instant.ofEpochSecond(date.getTime());
            LocalTime res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
            String programTimeStart = res.toString();
            textProgram += "\n" + programTimeStart + "\t" + program.getTitle();
        }
        File file = ImageGenerator.imageFromText(textProgram);

        vkService.postImage(file, code);
    }
}
