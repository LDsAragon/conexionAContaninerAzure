package azure.client.integration.mains;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BalancedBraces {


  public static void main(String[] args) {

    List<String> bracesTest = new ArrayList();
    bracesTest.add("()[]{}") ;
    bracesTest.add("()[(]{}") ;
    bracesTest.add("()[]{)}") ;
    bracesTest.add("([{}])") ;

    List<String> strings = balancedBraces(bracesTest);

    System.out.println(strings.toString());

  }

  private static List<String> balancedBraces(List<String> bracesTest) {

    final String YES = "YES";
    final String NO = "NO";
    final Character OPEN_BRACE_1 = '(';
    final Character CLOSE_BRACE_1 = ')';
    final Character OPEN_BRACE_2 = '{';
    final Character CLOSE_BRACE_2 = '}';
    final Character OPEN_BRACE_3 = '[';
    final Character CLOSE_BRACE_3 = ']';
    String result = NO;

    Stack<Character> bracesVault = new Stack<>();
    List<String> balancedBracesDescription = new ArrayList<>();

    for (String braceText : bracesTest) {

      bracesVault.removeAllElements();

      List<Character> chars = new ArrayList<>();
      for (Character character : braceText.toCharArray()) {
        chars.add(character);
      }

      for (Character brace : chars) {

        if (brace.compareTo(OPEN_BRACE_1) == 0
            || brace.compareTo(OPEN_BRACE_2) == 0
            || brace.compareTo(OPEN_BRACE_3) == 0) {

          bracesVault.add(brace);

        } else {

          if (brace.equals(CLOSE_BRACE_1) && bracesVault.contains(OPEN_BRACE_1)) {
            bracesVault.remove(OPEN_BRACE_1);
          }else if(brace.equals(CLOSE_BRACE_1) && !bracesVault.contains(OPEN_BRACE_1)) {
            bracesVault.add(brace);
          }
          if (brace.equals(CLOSE_BRACE_2) && bracesVault.contains(OPEN_BRACE_2)) {
            bracesVault.remove(OPEN_BRACE_2);
          } else if (brace.equals(CLOSE_BRACE_2) && !bracesVault.contains(OPEN_BRACE_2)){
            bracesVault.add(brace);
          }
          if (brace.equals(CLOSE_BRACE_3) && bracesVault.contains(OPEN_BRACE_3) ) {
            bracesVault.remove(OPEN_BRACE_3);
          } else if ((brace.equals(CLOSE_BRACE_3) && !bracesVault.contains(OPEN_BRACE_3))) {
            bracesVault.add(brace);
          }
        }

      }

      if (bracesVault.isEmpty()) {
        result = YES;
      } else {
        result = NO;
      }

      balancedBracesDescription.add(result);

    }

    return balancedBracesDescription;

  }

}




