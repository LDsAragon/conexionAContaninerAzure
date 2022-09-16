package azureClientIntegration.Exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomAzureException extends RuntimeException  {

    private final String actionType;
    private final String statusCode;
    private final String message;


}
