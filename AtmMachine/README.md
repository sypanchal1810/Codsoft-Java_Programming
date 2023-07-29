# ATM Interface

This project is a simple ATM (Automated Teller Machine) interface implemented in
Java. It allows users to interact with a virtual ATM to perform basic banking
operations such as depositing money, withdrawing money, and checking account
balance.

## Usage

1. Clone the Repository or download the source code.

2. Compile the Java Code:

   ```
   javac ATM.java
   ```

3. Run the ATM:

   ```
   java ATM
   ```

4. Follow the On-Screen Instructions:

   - Enter your PIN when prompted.
   - Choose the desired operation from the menu:
     - Type `1` to withdraw money.
     - Type `2` to deposit money.
     - Type `3` to check your account balance.
     - Type `4` to exit the ATM.

   Note: The default PIN is set to `4321`. You can modify the `PIN` constant in
   the `BankAccount` class if you want to use a different PIN.

## Features

- **Security**: Users are required to enter a PIN before they can access the ATM
  functionalities. The PIN is stored as a constant in the `BankAccount` class.

- **Withdrawal**: Users can withdraw money from their bank account. The ATM will
  check if the account balance is sufficient before processing the withdrawal.

- **Deposit**: Users can deposit money into their bank account.

- **Check Balance**: Users can check their account balance at any time.

---

_This ATM simulation project is for educational purposes only and should not be
used in real-world applications without proper security measures and rigorous
testing._
