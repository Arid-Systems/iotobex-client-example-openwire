# iotobex-client-example-openwire
Example of client implementation demonstrating a method of connection to the Real Time Service, in this case an OpenWire connection implemented in Java and utilizing Java Message Service (JMS).

OpenWire is a binary protocol designed for working with message-oriented middleware, and is the native wire format of the Iotobex message broker (ActiveMQ). It an ideal choice for communication with so-called native clients usually written in Java, C, or C#. As the native wire protocol, it will generally offer the best performance. However the inherent efficiency and performance of the OpenWire protocol comes at a cost, being the complexity of implementation on the client side.

You will need a Real Time Service user account. Be sure to configure the credentials and access URI in ClientExampleOpenWire.java as per the Credentials and OpenWire specifics as provided through the Real Time POC Console. 

Note that the stream name utilized in the example must be configured for the user whose credentials are specified. To do this, configure the stream name as both a publishable and subscribed stream by means of the Real Time POC Console.
