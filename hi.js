
const accountDetails = {
    accountNumber: "123456789",
    pin: "1234",
    balance: 1000 // Initial balance
};

function login() {
    const accountNumberInput = document.getElementById("accountNumber").value;
    const pinInput = document.getElementById("pin").value;

    if (accountNumberInput === accountDetails.accountNumber && pinInput === accountDetails.pin) {
        displayMessage("Login successful.");
        showOperations();
    } else {
        displayMessage("Invalid account number or PIN.");
    }
}

function checkBalance() {
    displayMessage(`Balance: $${accountDetails.balance.toFixed(2)}`);
}

function deposit() {
    const amount = parseFloat(document.getElementById("amount").value);
    if (isNaN(amount) || amount <= 0) {
        displayMessage("Invalid amount.");
    } else {
        accountDetails.balance += amount;
        displayMessage(`Deposited $${amount.toFixed(2)}.`);
        checkBalance();
    }
}

function withdraw() {
    const amount = parseFloat(document.getElementById("amount").value);
    if (isNaN(amount) || amount <= 0 || amount > accountDetails.balance) {
        displayMessage("Invalid amount or insufficient balance.");
    } else {
        accountDetails.balance -= amount;
        displayMessage(`Withdrawn $${amount.toFixed(2)}.`);
        checkBalance();
    }
}

function displayMessage(message) {
    document.getElementById("message").textContent = message;
}

function showOperations() {
    document.querySelector(".login").style.display = "none";
    document.querySelector(".operations").style.display = "block";
}
