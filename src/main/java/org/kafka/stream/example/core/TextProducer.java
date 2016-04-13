package org.kafka.stream.example.core;

import com.google.common.base.Joiner;
import org.apache.commons.io.IOUtils;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by osboxes on 3/1/16.
 */
public class TextProducer {

    private final KafkaProducer<String, String> producer;
    private final String topic;
    private final String filePath;
    private final Properties props = new Properties();
    private Random rnd;

    private List<String> fileContent;

    /**
     * Instantiates a new kafka producer.
     *
     * @param topic the topic
     * @param filePath the file to sample from
     */
    public TextProducer(String topic, String filePath) throws IOException {
        this(topic, filePath, KafkaProperties.kafkaServerPort);
    }

    public TextProducer(String topic, String directoryPath, int port) throws IOException {
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("bootstrap.servers", KafkaProperties.kafkaServerURL + ":" + port);
        producer = new KafkaProducer<String, String>(props);
        this.topic = topic;
        this.filePath = directoryPath;
        rnd = new Random();

        fileContent = loadContent();
    }

    private List<String> loadContent() throws IOException {
        return Arrays.asList(new String(IOUtils.toByteArray(new FileInputStream(new File(filePath))), "UTF-8").split("\\n+"));
    }

    public void run() {
        while(true){
            int start = rnd.nextInt(fileContent.size()-1);
            pushFileContent(fileContent.get(rnd.nextInt(fileContent.size())));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {}
        }
    }

    /**
     * Read file content.
     *
     * @param episode content to be pushed
     */
    private void pushFileContent(String episode){
        producer.send(new ProducerRecord<String, String>(topic, rnd.nextInt(100) + "", episode));
        System.out.println("Producer - content consumed");
    }
}
