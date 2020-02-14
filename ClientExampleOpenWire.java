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

/**
 * ClientExampleOpenWire
 *
 * Example of a Java program to demonstrate the use of Java Message Service to publish/subscribe
 * to the OpenWire interface for Iotobex. This program publishes a single message then waits
 * momentarily for reception of that message before terminating.
 */

public class ClientExampleOpenWire {

    static JMSConnectionManager jmsConnectionManager;
    static JMSSessionManager jmsSessionManager;

    // Configure the following as per the Credentials and OpenWire specifics
    // as detailed in the Iotobex Client Console.
    final static String USERNAME = "client-username";
    final static String PASSWORD = "client-password";
    final static String ENDPOINT = "OpenWire-endpoint";


    public static void main(String[] args) {
        System.out.println("======== ClientExampleOpenWire ========");

        // The stream name below must be configured for the user whose credentials are
        // entered above. By means of the Iotobex Client Console, configure the stream
        // name as both a publishable and subscribed stream.
        String SAMPLE_STREAM_NAME = "com.example.sample.topic";
        String MESSAGE_TEXT = "Sample message";

        try {
            // Set up JMS connection and session.
            jmsConnectionManager = new JMSConnectionManager();
            jmsSessionManager = new JMSSessionManager();

            // Set up JMS message producer and consumer consumer.
            JMSMessageProducer jmsMessageProducer = new JMSMessageProducer();
            JMSMessageConsumer jmsMessageConsumer = new JMSMessageConsumer(SAMPLE_STREAM_NAME);

            // Set up a listener for incoming messages (topic = SAMPLE_STREAM_NAME).
            //JMSMessageListener jmsMessageListener = new JMSMessageListener(SAMPLE_STREAM_NAME);

            // Publish a single message.
            jmsMessageProducer.publish(SAMPLE_STREAM_NAME, MESSAGE_TEXT);

            // Wait for 2 seconds - a response is expected since the client has subscribed
            // to same stream). Should appear at console.
            Thread.sleep(2000); // Wait two second to provide sufficient time to receive and process message.

            // Clean up before finishing.
            jmsMessageConsumer.close();
            jmsMessageProducer.close();
            jmsSessionManager.close();
            jmsConnectionManager.close();
        } catch (Exception exc) {
                exc.printStackTrace();
        }
        System.out.println("================= End =================");
        System.exit(0);
    }


}