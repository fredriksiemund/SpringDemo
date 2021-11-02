package com.example.springdemo.azure;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.example.springdemo.config.LoadDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class Controller {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final BlobServiceClient blobServiceClient;

    public Controller() {
        log.info("Azure Blob Storage v12 sample");

        String connectStr = System.getenv("AZURE_STORAGE_CONNECTION_STRING");
        log.info("Connection string: " + connectStr);

        // Create a BlobServiceClient object which will be used to create a container client
        this.blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();
    }

    @PostMapping("v1/create-container")
    public ResponseEntity<String> createContainer(@RequestBody Map<String, String> body) {
        String containerName = body.get("containerName");
        if (containerName == null)
            return new ResponseEntity<>("Body must have a 'containerName' key/pair", HttpStatus.BAD_REQUEST);

        //Create a unique name for the container
        containerName = containerName + java.util.UUID.randomUUID();

        // Create the container and return a container client object
        blobServiceClient.createBlobContainer(containerName);

        return new ResponseEntity<>(containerName, HttpStatus.CREATED);
    }

    // Send file in form-data, key: "file" value: [file]
    @PostMapping("v1/{containerName}/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String containerName) {
        try {
            BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);

            // Save file
            BlobClient blobClient = containerClient.getBlobClient(containerName);
            log.info("Uploading to Blob storage as blob:\t" + blobClient.getBlobUrl());
            blobClient.upload(file.getInputStream(), file.getSize());

            String message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            String message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
}
