package task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

import task.processor.Processor;
import task.service.VkService;
@Controller
public class VkController {

    @Autowired
    private VkService vkService;
    @Autowired
    private Processor processor;

    @RequestMapping("/")
    private String index() {
        return "index";
    }

    @RequestMapping("/upload")
    private String authorization() {
        return "redirect:" + vkService.buildGetCodeUrl();
    }

    @RequestMapping("/post")
    private String uploadTvProgram(@RequestParam String code, Model model) {
        try {
            processor.generateAndUploadTvProgram(code);
            model.addAttribute("result", "Success");
        } catch (ClientException | ApiException e) {
            e.printStackTrace();
            model.addAttribute("result", "Error");
        }
        return "result";
    }

}
