# MessageGears Java SDK

MessageGears Java SDK allows you to quickly and easily integrate with MessageGears.

## Quick Start Example

    import com.messagegears.sdk.v3_1.TransactionalJobSubmitResponse;
    import com.messagegears.sdk.MessageGearsClient;
    import com.messagegears.sdk.MessageGearsProperties;
    import com.messagegears.sdk.model.request.TransactionalJobSubmitRequest;
    import com.messagegears.sdk.output.ScreenWriter;

    public class TransactionalJobSubmit {

        public static void main(String[] args) {
            // Create the properties object containing the necessary properties
            MessageGearsProperties properties = new MessageGearsProperties();
            properties.setMyMessageGearsAccountId("place your MessageGears account id here");
            properties.setMyMessageGearsApiKey("place your MessageGears api key here");

            // Create the main client object
            MessageGearsClient client = new MessageGearsClient(properties);

            // Create a transactional job request
            TransactionalJobSubmitRequest request = new TransactionalJobSubmitRequest();

            // Create the XML that represents the email recipient
            // (the Recipient and EmailAddress elements are required)
            request.setRecipientXml("<Recipient><EmailAddress>" +
                "place your email address here" + "</EmailAddress><FirstName>You</FirstName></Recipient>");

            // Set the message content
            request.setFromName("MessageGears");
            request.setFromAddress("no-reply@messagegears.com");
            request.setSubjectLine("MessageGears - Reliable Message Delivery");

            // Set the HTML part of the message (using some simple personalization)
            request.setHtmlTemplate("Hello, ${Recipient.FirstName}!");

            // Execute the request
            TransactionalJobSubmitResponse response = client.transactionalJobSubmit(request);

            // Print the result (success or failure)
            ScreenWriter.printResponse(response);
        }

    }


## Documentation

 * [Official documentation](http://messagegears.com/docs/java-sdk)

## Maven

    <dependency>
      <groupId>com.messagegears</groupId>
      <artifactId>messagegears-java-sdk</artifactId>
      <version>PUT VERSION NUMBER HERE</version>
    </dependency>

