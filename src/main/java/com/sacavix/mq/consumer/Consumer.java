package com.sacavix.mq.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.DBObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.ExecutableUpdateOperation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import pe.com.isesystem.gpservice.dto.SemanaDto;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Slf4j
@Component
public class Consumer {
	@Autowired
	private MongoTemplate mongoTemplate;

	@RabbitListener(queues = { "${sacavix.queue.name}" })
	public void receive(@Payload SemanaDto semana){
		//Actualizo todos los gastos de embarcacion
		Query miQuery = new Query();
		miQuery.addCriteria(where("semana.id").is( semana.getId() ));

		Update update = new Update();
		update.set("semana.estado", semana.getEstado());
		mongoTemplate.updateMulti(miQuery, update, "gastos-embarcacion");
		makeSlow();

		//Actualizo todos los gastos descarga

	}

	private void makeSlow() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
