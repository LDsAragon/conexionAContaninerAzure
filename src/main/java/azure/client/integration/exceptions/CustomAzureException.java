package azure.client.integration.exceptions;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter

public class CustomAzureException extends RuntimeException {

  private final String actionType;
  private final String statusCode;
  private final String message;


  public CustomAzureException(String actionType, String statusCode, String message) {
    this.actionType = actionType;
    this.statusCode = statusCode;
    this.message = message;
  }
}
