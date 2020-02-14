package com.aridsys.clientexampleopenwire;

/*
 * Copyright 2020 Arid Systems Pty Ltd.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

/*
 * JMSTopicProducer
 */

import javax.jms.*;

public class TopicProducer{
    private Session producerSession = null;
    private MessageProducer producer;
    private Destination producerDestination = null;
    private String producerTopic;

    public TopicProducer(Session producerSession, String topic) {
        try {
            System.out.println("Initiating the topic producer for topic: " + topic);
            this.producerSession = producerSession;
            this.producerTopic = topic;  // Save to report when closing.

            producerDestination = producerSession.createTopic(topic); // Create a topic.
            producer = producerSession.createProducer(producerDestination); // Create a producer from the session to the topic.
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        }
        catch (JMSException exc) {
            exc.printStackTrace();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        // Send messages to topic (as defined in initiateProducer)
        try {
            TextMessage producerMessage;
            producerMessage = producerSession.createTextMessage(message);
            producer.send(producerMessage);
        }
        catch (JMSException exc) {
            exc.printStackTrace();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void close(){
        // Clean up the consumer.
        try {
            System.out.println("Closing producer for topic: " + producerTopic);
            producer.close();
        }
        catch (JMSException exc) {
            exc.printStackTrace();
        }
    }
}