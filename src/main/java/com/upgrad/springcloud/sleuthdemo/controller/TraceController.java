package com.upgrad.springcloud.sleuthdemo.controller;

import com.upgrad.springcloud.sleuthdemo.service.TraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Vishwa  12th June 2021
 */
@RestController
public class TraceController {
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private TraceService traceService;

  @GetMapping("/")
  public String helloSleuth() throws InterruptedException {
    logger.info("Hello Logging");
    traceService.work();
    return "success";
  }

  @GetMapping("/custom-span")
  public String helloSleuthNewSpan() {
    logger.info("Custom Span");
    traceService.doSomeCustomSpan();
    return "success";
  }

}
