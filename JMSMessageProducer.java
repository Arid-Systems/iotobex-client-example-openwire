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
 * JMSMessageProducer
 */

import javax.jms.Session;

import static com.aridsys.clientexampleopenwire.ClientExampleOpenWire.*;

public class JMSMessageProducer {

    private TopicProducer topicProducer;


    // Publish a single message.
    public void publish(String topic, String messageText) {

        Session producerSession;
        try {
            System.out.println("Publishing to topic: " + topic + " - message: " + messageText);
            producerSession = jmsSessionManager.getProducerSession();
            topicProducer = new TopicProducer(producerSession, topic);
            topicProducer.sendMessage(messageText);    // Send the sample message
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    // Close producers, consumers etc.
    public void close() {

        try {
            topicProducer.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

}
