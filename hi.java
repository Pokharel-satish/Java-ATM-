import java.sql.*;

public class hi {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    // JDBC variables for opening, closing and managing connection
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void connect() {
        try {
            // Load and register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection with the database
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            System.out.println("Connected to the database.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading JDBC driver: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }

    // Method to close the database connection
    public static void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
                System.out.println("Disconnected from the database.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing database resources: " + e.getMessage());
        }
    }

    // Method to execute a query and return the result set
    public static ResultSet executeQuery(String query) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
            return null;
        }
    }

    // Method to execute an update (insert, update, delete) operation
    public static int executeUpdate(String query) {
        try {
            statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("Error executing update: " + e.getMessage());
            return -1;
        }
    }

    // Example method for inserting data into a table
    public static void insertData(String accountNumber, String pin, double balance) {
        String query = "INSERT INTO accounts (account_number, pin, balance) VALUES ('" + accountNumber + "', '" + pin + "', " + balance + ")";
        int rowsAffected = executeUpdate(query);
        if (rowsAffected != -1) {
            System.out.println("Data inserted successfully.");
        } else {
            System.out.println("Error inserting data.");
        }
    }

    // Example method for reading data from a table
    public static void readData() {
        String query = "SELECT * FROM accounts";
        ResultSet resultSet = executeQuery(query);
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    String accountNumber = resultSet.getString("account_number");
                    String pin = resultSet.getString("pin");
                    double balance = resultSet.getDouble("balance");
                    System.out.println("Account Number: " + accountNumber + ", PIN: " + pin + ", Balance: $" + balance);
                }
            } catch (SQLException e) {
                System.err.println("Error reading data: " + e.getMessage());
            }
        } else {
            System.out.println("No data available.");
        }
    }

    // Example method for updating data in a table
    public static void updateData(String accountNumber, double newBalance) {
        String query = "UPDATE accounts SET balance = " + newBalance + " WHERE account_number = '" + accountNumber + "'";
        int rowsAffected = executeUpdate(query);
        if (rowsAffected != -1) {
            System.out.println("Data updated successfully.");
        } else {
            System.out.println("Error updating data.");
        }
    }

    // Example method for deleting data from a table
    public static void deleteData(String accountNumber) {
        String query = "DELETE FROM accounts WHERE account_number = '" + accountNumber + "'";
        int rowsAffected = executeUpdate(query);
        if (rowsAffected != -1) {
            System.out.println("Data deleted successfully.");
        } else {
            System.out.println("Error deleting data.");
        }
    }

    public static void main(String[] args) {
        // Connect to the database
        connect();

        // Example usage: Insert data
        insertData("123456789", "1234", 1000);

        // Example usage: Read data
        readData();

        // Example usage: Update data
        updateData("123456789", 1500);

        // Example usage: Read data after update
        readData();

        // Example usage: Delete data
        deleteData("123456789");

        // Example usage: Read data after delete
        readData();

        // Close the database connection
        close();
    }
}
