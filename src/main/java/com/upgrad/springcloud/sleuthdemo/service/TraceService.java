package com.upgrad.springcloud.sleuthdemo.service;

import brave.Span;
import brave.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Vishwa  12th June 2021
 */
@Service
public class TraceService {

  @Autowired
  Tracer tracer ;
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  public void work() throws InterruptedException {
    logger.info("Doing some work");
    Thread.sleep(2000L);

  }

  public void doSomeCustomSpan() {

    logger.info("I'm in the same span");

    Span span = tracer.nextSpan().name("newCustomSpan").start();
    try (Tracer.SpanInScope ws = tracer.withSpanInScope(span.start())) {
      Thread.sleep(1000L);
      logger.info("Going inside another span");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      span.finish();
    }

    logger.info("I'm in the original span");
  }
}
