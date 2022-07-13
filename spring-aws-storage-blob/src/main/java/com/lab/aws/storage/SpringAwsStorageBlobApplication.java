package com.lab.aws.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

@SpringBootApplication
public class SpringAwsStorageBlobApplication {
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
			System.out.println(availableBuckets.getName());
		}
	}

}
