package com.example.springboot.controller;

import com.example.springboot.service.KafkaProducer;
import com.example.springboot.service.KafkaConsumerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/kafka")
public class KafkaController {
	
	@Autowired
  private KafkaProducer kafkaProducer;
	
	@Autowired
	private KafkaConsumerService kafkaConsumerService;

	
	@GetMapping("/send")
  public String sendMessage(@RequestParam("key") String key, @RequestParam("message") String message) {
      kafkaProducer.sendMessage(key, message);
      return "Message sent to Kafka topic cart_checkout_topic and group ID my-group!";
  }
	
	@GetMapping("/consume")
  public String consumeFromOffset(@RequestParam("topic") String topic, @RequestParam("partition") String partition, @RequestParam("offset") String offset) {
      kafkaConsumerService.consumeFromOffset(topic, Integer.parseInt(partition), Long.parseLong(offset));
      return "Consuming messages from offset " + offset + " on partition " + partition + " of topic " + topic;
  }
	
}
