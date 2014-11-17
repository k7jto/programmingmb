// Variables for input
// char readChar;                    // char buffer for input
const int NUMBER_OF_FIELDS = 2;   // how many comma-sep fields expected
int fieldIndex = 0;              // the current field being received
int values[NUMBER_OF_FIELDS];    // array to hold the values

void setup() {
  Serial.begin(9600);
  Serial.println("Math Test");
  Serial.println("Enter two numbers, separated by commas, and an operator.");
  Serial.println("The first two numbers are the numbers to be operated against.");
  Serial.println("The operator specifies the operation:");
  Serial.println("  + Addition");
  Serial.println("  - Subtraction");
  Serial.println("  x Multiplication");
  Serial.println("  / Division");
  Serial.println("For example: 1,4+");
}

void loop() {
  // Get string from user
  while (Serial.available()) {
    // Expect user to input comma-sep string of 2 numeric values terminated with an operator
    // Copied from "Arduino Cookbook" (Margolis, Michael. OReilly Media, Inc. 2011), p 98-99
    char readChar = Serial.read();  //gets one byte from serial buffer
    delay(5);
    
    // Read in string and make sure these are ASCII digits between 0 and 9
    if(readChar >= '0' && readChar <= '9')  
    {
      // yes, accumulate the value
      values[fieldIndex] = (values[fieldIndex] * 10) + (readChar - '0');
      Serial.println(values[fieldIndex]);
    }
    // Or check if it's a comma
    else if (readChar == ',')
    {
      // Move to the next field
      if(fieldIndex < NUMBER_OF_FIELDS-1)
      {
        fieldIndex++;  // increment field index
      }
    }
    else
    // Everything else terminates input
    {
      // Only do work if we have values
      if (values[0] > 0 && values[1] > 0 && readChar > 0)
      {
        // Everything has to happen here
        
        // First create variables
        // When you change to doubles, change the following 5 instances of "int":
        int answer = 0;
        int y = int(values[0]);
        int z = int(values[1]);
        
        // Next perform math based on the supplied operator
        switch(readChar)
        {
          case '+':
            Serial.print("The sum of ");
            Serial.print(values[0]);
            Serial.print(" and ");
            Serial.print(values[1]);
            Serial.print(" is ");
            answer = doAddition(y, z);
            break;
          case '-':
            Serial.print("The difference of ");
            Serial.print(values[0]);
            Serial.print(" and ");
            Serial.print(values[1]);
            Serial.print(" is ");
            answer = doSubtraction(y, z);
            Serial.println(answer);
            break;
          case 'x':
            Serial.print("The product of ");
            Serial.print(values[0]);
            Serial.print(" and ");
            Serial.print(values[1]);
            Serial.print(" is ");
            answer = doMultiplication(y, z);
            break;
          case '/':
            Serial.print("The quotient of ");
            Serial.print(values[0]);
            Serial.print(" and ");
            Serial.print(values[1]);
            Serial.print(" is ");
            answer = doDivision(y, z);
            break;
        }
        
        Serial.println(answer);
        delay(100);
      } // Closing if statement
      
      // Clean up
      for(int i=0; i<=fieldIndex; i++)
      {
        values[i] = 0; // set to 0 for next message
      }
      fieldIndex = 0; // ready to start over
    }
  }  // close while (Serial.available)
  
} // close loop

int doAddition(int a, int b)
{
  int sum = a + b;
  return sum;
}

int doSubtraction(int a, int b)
{
  int difference = a - b;
  return difference;
}

int doMultiplication(int a, int b)
{
  int product = a * b;
  return product;
}

int doDivision(int a, int b)
{
  int quotient = a / b;
  return quotient;
}
