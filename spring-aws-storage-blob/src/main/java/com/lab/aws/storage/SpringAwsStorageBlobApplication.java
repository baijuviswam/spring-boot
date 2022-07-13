package com.lab.aws.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

@SpringBootApplication
public class SpringAwsStorageBlobApplication {
	private static final Logger logger = LoggerFactory.getLogger(SpringAwsStorageBlobApplication.class);
	private final AmazonS3Client amazonS3Client;

	public static void main(String[] args) {
		SpringApplication.run(SpringAwsStorageBlobApplication.class, args);
	}

	public SpringAwsStorageBlobApplication(AmazonS3Client amazonS3Client) {
		this.amazonS3Client = amazonS3Client;
	}

	@EventListener(classes = ApplicationReadyEvent.class)
	public void onApplicationReadyEvent(ApplicationReadyEvent event) {
		for (Bucket availableBuckets : amazonS3Client.listBuckets()) {
			logger.info(availableBuckets.getName());
		}
	}

}
