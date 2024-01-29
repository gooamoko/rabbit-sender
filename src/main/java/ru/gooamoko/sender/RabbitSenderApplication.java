package ru.gooamoko.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.gooamoko.sender.config.ExchangeConfigProperties;
import ru.gooamoko.sender.config.QueueConfigProperties;
import ru.gooamoko.sender.model.SmsMessage;
import ru.gooamoko.sender.rabbit.NotificationService;

@SpringBootApplication
@EnableConfigurationProperties({ExchangeConfigProperties.class, QueueConfigProperties.class})
public class RabbitSenderApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(RabbitSenderApplication.class);

	private final NotificationService producer;


	public RabbitSenderApplication(NotificationService producer) {
		this.producer = producer;
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 1; i <= 10; i++) {
			String message = "test message " + i;
			String phone = "9132231234";
			SmsMessage smsMessage = new SmsMessage();
			smsMessage.setPhone(phone);
			smsMessage.setText(message);
			smsMessage.setUser("user");
			smsMessage.setService("rabbit-sender");
			producer.sendSmsNotification(smsMessage);
			producer.sendEmailNotification("test@gmail.com", "test", message);
			log.info("sending message {} to phone {}.", message, phone);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitSenderApplication.class, args);
	}
}
