import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("****************************************");
        System.out.println("          Welcome to Our Game");
        ArrayList<String> allNumbers = populateAllNumbers();
        System.out.println("****************************************");
        String firstGuess = "123456";
        System.out.println("               "+firstGuess);
        System.out.println("****************************************");

        int attempts = 1;
        while (true) {
            String inputText = getInput(firstGuess);
            if (inputText.charAt(0) - '0' == 6) {
                System.out.println("****************************************");
                System.out.println("Your number is : " + firstGuess);
                System.out.println("Number of attempts  : " + attempts);
                System.out.println("****************************************");

                System.out.println("            GAME OVER");
                System.out.println("****************************************");

                break;
            }
            allNumbers = checkArray(allNumbers, inputText, firstGuess);
            firstGuess = firstGuessGenerate(allNumbers);
            System.out.println("****************************************");
            System.out.println("               "+firstGuess);
            System.out.println("****************************************");
            attempts++;
        }




    }
    public static ArrayList<String> populateAllNumbers() {
        ArrayList<String> allNumbers = new ArrayList<>();
        for (int a = 0; a < 10; a++) {
            for (int b = 0; b < 10; b++) {
                for (int c = 0; c < 10; c++) {
                    for (int d = 0; d < 10; d++) {
                        for (int e = 0; e < 10; e++) {
                            for (int f = 0; f < 10; f++) {
                                String number = String.valueOf(a) + String.valueOf(b) + String.valueOf(c)
                                        + String.valueOf(d) + String.valueOf(e) + String.valueOf(f);
                                allNumbers.add(number);

                            }
                        }
                    }
                }
            }
        }
        return allNumbers;
    }
    public static String firstGuessGenerate(ArrayList<String> arrayOfSolutions) {
        if (arrayOfSolutions.size() == 0) {
            throw  new IllegalArgumentException("There is no possible solution. Your feedbacks are wrong!");
        } else {
            return arrayOfSolutions.get((int) (Math.random() * arrayOfSolutions.size()));
        }
    }
    public static String getInput(String firstGuess) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the feedback : ");
        String inputText = scanner.nextLine();
        if (inputText.length() != 3 || (inputText.charAt(0) - '0' + inputText.charAt(2) - '0') > 6 || inputText.charAt(1) != ',') {
            System.out.println("****************************************");
            System.out.println("The feedback is invalid. Please control it, then try again!");
            System.out.println("****************************************");
            System.out.println("               "+firstGuess);
            return getInput(firstGuess);
        } else {
            return inputText;
        }
    }
    public static ArrayList<String> checkArray(ArrayList<String> arrayOfSolutions, String inputText, String firstGuess) {
        char direct = inputText.charAt(0);
        char indirect = inputText.charAt(2);
        int directHit = direct - '0';
        int indirectHit = indirect - '0';
        ArrayList<String> newArrayOfSolutions = new ArrayList<>();

        for (String solutionText : arrayOfSolutions) {
            int directcontrol = 0;
            int indirectcontrol = 0;
            char[] firstGuessChars = new char[6];
            char[] solutionTextChars = new char[6];
            for (int indexOfChar = 0; indexOfChar < firstGuessChars.length; indexOfChar++) {
                firstGuessChars[indexOfChar] = firstGuess.charAt(indexOfChar);
                solutionTextChars[indexOfChar] = solutionText.charAt(indexOfChar);
            }

            for (int firstGuessCharControl = 0; firstGuessCharControl < firstGuessChars.length; firstGuessCharControl++) {
                for (int possibleSolutionCharControl = 0; possibleSolutionCharControl < solutionTextChars.length; possibleSolutionCharControl++) {
                    if (firstGuessCharControl == possibleSolutionCharControl) {
                        if (firstGuessChars[firstGuessCharControl] == solutionTextChars[possibleSolutionCharControl]) {
                            directcontrol++;
                            solutionTextChars[possibleSolutionCharControl] = 'a';
                            break;
                        }
                    } else if (firstGuessChars[firstGuessCharControl] == solutionTextChars[possibleSolutionCharControl]) {
                        indirectcontrol++;
                        solutionTextChars[possibleSolutionCharControl] = 'a';
                        break;
                    }
                }
            }
            if ((directHit + indirectHit) == (directcontrol + indirectcontrol)) {
                newArrayOfSolutions.add(solutionText);
            }

        }
        return checkDirectSpot(inputText, firstGuess, newArrayOfSolutions);
    }
    public static ArrayList<String> checkDirectSpot(String inputText, String firstGuess, ArrayList<String> newArrayOfSolutions) {
        ArrayList<String> controlledOfDirectSpotArrayOfSolutions = new ArrayList<>();
        char direct = inputText.charAt(0);
        int directHit = direct - '0';

        for (String solutionText : newArrayOfSolutions) {
            int directcontrol = 0;
            char[] firstGuessChars = new char[6];
            char[] solutionTextChars = new char[6];
            for (int indexOfChar = 0; indexOfChar < firstGuessChars.length; indexOfChar++) {
                firstGuessChars[indexOfChar] = firstGuess.charAt(indexOfChar);
                solutionTextChars[indexOfChar] = solutionText.charAt(indexOfChar);
            }

            for (int firstGuessCharControl = 0; firstGuessCharControl < firstGuessChars.length; firstGuessCharControl++) {
                for (int possibleSolutionCharControl = 0; possibleSolutionCharControl < solutionText.length(); possibleSolutionCharControl++) {
                    if (firstGuessCharControl == possibleSolutionCharControl && firstGuessChars[firstGuessCharControl] == solutionTextChars[possibleSolutionCharControl]) {
                        directcontrol++;
                        break;
                    }
                }
            }
            if (directcontrol == directHit) {
                controlledOfDirectSpotArrayOfSolutions.add(solutionText);
            }
        }
        return controlledOfDirectSpotArrayOfSolutions;
    }
}