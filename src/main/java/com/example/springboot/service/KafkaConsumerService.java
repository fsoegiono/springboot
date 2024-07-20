package com.example.springboot.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;

import java.util.Properties;
import java.time.Duration;
import java.util.Collections;

@Service
public class KafkaConsumerService {
	
	@KafkaListener(topics = "cart_checkout_topic", groupId = "my-group")
	public void consume(String message) {
		System.out.println(message);
	}
	
	@KafkaListener(topics = "cart_checkout_topic", groupId = "my-group")
  public void listen(ConsumerRecord<String, String> record) {
      String key = record.key();
      String message = record.value();
      
      System.out.println("record keys: " + record.toString());
      
      System.out.println("Received Message: " + message + " with key: " + key);
      
      // Process the message based on the key
      if ("biore".equals(key)) {
          processMessageWithSpecificKey(message);
      }
  }

  private void processMessageWithSpecificKey(String message) {
      // Add your specific key processing logic here
      System.out.println("Processing message with specific key: " + message);
  }
  public void consumeFromOffset(String topic, int partition, long offset) {
    Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:9092");
    props.put("group.id", "my-group");
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

    Consumer<String, String> consumer = new KafkaConsumer<>(props);
    TopicPartition topicPartition = new TopicPartition(topic, partition);
    consumer.assign(Collections.singletonList(topicPartition));
    consumer.seek(topicPartition, offset);

    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
    for (ConsumerRecord<String, String> record : records) {
        System.out.printf("Consumed message: key = %s, value = %s, partition = %d, offset = %d%n",
                record.key(), record.value(), record.partition(), record.offset());
    }

    consumer.close();
  }
}
