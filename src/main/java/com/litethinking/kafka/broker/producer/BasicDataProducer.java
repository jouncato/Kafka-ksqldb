package com.litethinking.kafka.broker.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.litethinking.kafka.broker.message.BasicDataCountryMessage;
import com.litethinking.kafka.broker.message.BasicDataFiveMessage;
import com.litethinking.kafka.broker.message.BasicDataFourMessage;
import com.litethinking.kafka.broker.message.BasicDataOneMessage;
import com.litethinking.kafka.broker.message.BasicDataPersonMessage;
import com.litethinking.kafka.broker.message.BasicDataThreeMessage;
import com.litethinking.kafka.broker.message.BasicDataTwoMessage;

@Service
public class BasicDataProducer {

	@Autowired
	private KafkaTemplate<Object, Object> kafkaTemplate;

	public void sendBasicDataCountry(BasicDataCountryMessage message) {
		kafkaTemplate.send("topic-ksql-basic-data-country", message);
	}

	public void sendBasicDataCountryWithNullValue(String countryName) {
		kafkaTemplate.send("topic-ksql-basic-data-country", countryName, null);
		kafkaTemplate.send("tbl-basic-data-country", countryName, null);
	}

	public void sendBasicDataFive(BasicDataFiveMessage message) {
		kafkaTemplate.send("topic-ksql-basic-data-five", message);
	}

	public void sendBasicDataFour(BasicDataFourMessage message) {
		kafkaTemplate.send("topic-ksql-basic-data-four", message);
	}

	public void sendBasicDataOne(BasicDataOneMessage message) {
		kafkaTemplate.send("topic-ksql-basic-data-one", message);
	}

	public void sendBasicDataPerson(BasicDataPersonMessage message) {
		kafkaTemplate.send("topic-ksql-basic-data-person", message);
	}

	public void sendBasicDataThree(BasicDataThreeMessage message) {
		kafkaTemplate.send("topic-ksql-basic-data-three", message);
	}

	public void sendBasicDataTwo(BasicDataTwoMessage message) {
		kafkaTemplate.send("topic-ksql-basic-data-two", message);
	}

}
