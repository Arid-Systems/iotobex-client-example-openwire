# iotobex-client-example-openwire
Example of client implementation demonstrating a method of connection to the Iotobex service, in this case an OpenWire connection utilizing Java Message Service (JMS).

Iotobex is an information exchange for the Internet of Things. It enables:
- The dissemination of, and easy access to, “public” Internet of Things (IoT) information.
- The sharing of IoT-derived “private” information between associated providers and subscribers, as authorised.

OpenWire is a binary protocol designed for working with message-oriented middleware, and is the native wire format of the Iotobex message broker (ActiveMQ). It an ideal choice for communication with so-called native clients usually written in Java, C, or C#. As the native wire protocol, it will generally offer the best performance. However the inherent efficiency and performance of the OpenWire protocol comes at a cost, being the complexity of implementation on the client side.
