package azure.client.integration.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * Exception created to be launched from classes extending the [ErrorManager].
 */
@Setter
@Getter
public class CustomAzureException extends RuntimeException {

  private final String actionType;
  private final String statusCode;
  private final String message;

  /**
   * Constructor for the CustomAzureException.
   *
   * @param actionType A general description of the action being performed.
   * @param statusCode Error code if any, sometimes the containerClient throws html rest codes.
   * @param message The message of the exception.
   */
  public CustomAzureException(String actionType, String statusCode, String message) {
    this.actionType = actionType;
    this.statusCode = statusCode;
    this.message = message;
  }
}
