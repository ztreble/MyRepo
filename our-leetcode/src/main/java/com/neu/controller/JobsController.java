package com.neu.controller;



import com.neu.common.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author: treblez
 * @className: JobsController
 * @description:职位模块控制器
 * @data: 2020-04-05
 **/

@RestController
@RequestMapping("/jobs")
public class JobsController {

    @GetMapping("/")
    public RedirectView homePage(@PathVariable("code") int code){
        RedirectView red = new RedirectView("/jobs/all",true);
//        if (code == 301){
//            red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
//        }
        return red;
    }

    @GetMapping("/all")
    public Response homePage(){
        return new Response(0,"我很可爱，请给我钱");
//        return new ModelAndView("jobs");
    }


}
