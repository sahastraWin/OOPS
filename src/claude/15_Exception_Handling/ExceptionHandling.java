/**
 * ============================================================
 *  TOPIC: Exception Handling in Java
 * ============================================================
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW:
 *
 *  Exception Hierarchy:
 *         Throwable
 *        /         \
 *     Error       Exception
 *                /         \
 *     RuntimeException   (Checked Exceptions)
 *       (Unchecked)
 *
 *  Checked Exceptions:
 *  - Compile-time: compiler forces you to handle or declare them
 *  - Must use try-catch OR declare with throws
 *  - Examples: IOException, SQLException, ClassNotFoundException
 *
 *  Unchecked Exceptions (RuntimeException and subclasses):
 *  - Not checked at compile time; occur at runtime
 *  - Examples: NullPointerException, ArrayIndexOutOfBoundsException,
 *    ClassCastException, ArithmeticException, IllegalArgumentException
 *
 *  Error: serious JVM problems (not meant to be caught)
 *  - Examples: OutOfMemoryError, StackOverflowError
 *
 *  Keywords: try | catch | finally | throw | throws
 *
 *  finally:
 *  - ALWAYS executes (even if exception thrown or return used)
 *  - Exception: System.exit() or JVM crash prevents finally
 *  - Used for cleanup: closing resources
 *  - If both catch and finally return/throw, finally wins!
 *
 *  try-with-resources (Java 7+):
 *  - Auto-closes resources implementing AutoCloseable
 *  - Replaces finally block for closing resources
 *  - Suppressed exceptions: if body throws AND close() throws,
 *    the body exception is primary, close() exception is suppressed
 *
 *  Rethrowing exceptions:
 *  - throw e          → rethrows preserving original stack trace
 *  - throw new Ex(e)  → wraps original as cause (exception chaining)
 * ╚══════════════════════════════════════════════════════════╝
 */

import java.io.*;

public class ExceptionHandling {

    // ═══════════════════════════════════════════════════════
    // 1. CUSTOM EXCEPTION HIERARCHY
    // ═══════════════════════════════════════════════════════
    static class AppException extends Exception {
        private final int errorCode;

        public AppException(String message, int errorCode) {
            super(message);
            this.errorCode = errorCode;
        }

        public AppException(String message, int errorCode, Throwable cause) {
            super(message, cause);   // exception chaining
            this.errorCode = errorCode;
        }

        public int getErrorCode() { return errorCode; }

        @Override public String toString() {
            return "AppException[" + errorCode + "]: " + getMessage();
        }
    }

    static class ValidationException extends AppException {
        private final String field;

        public ValidationException(String field, String message) {
            super(message, 400);
            this.field = field;
        }

        public String getField() { return field; }

        @Override public String toString() {
            return "ValidationException[field=" + field + "]: " + getMessage();
        }
    }

    static class DatabaseException extends AppException {
        private final String query;

        public DatabaseException(String message, String query, Throwable cause) {
            super(message, 500, cause);
            this.query = query;
        }

        public String getQuery() { return query; }
    }

    // ═══════════════════════════════════════════════════════
    // 2. try / catch / finally
    // ═══════════════════════════════════════════════════════
    static int divide(int a, int b) {
        try {
            int result = a / b;
            System.out.println("Result: " + result);
            return result;
        } catch (ArithmeticException e) {
            System.out.println("Caught: " + e.getMessage());
            return -1;
        } finally {
            /*
             * ╔══════════════════════════════════════════╗
             *  finally ALWAYS executes.
             *  Even if catch has 'return', finally runs first.
             *  This is ideal for resource cleanup.
             * ╚══════════════════════════════════════════╝
             */
            System.out.println("[finally] divide() complete.");
        }
    }

    // ═══════════════════════════════════════════════════════
    // 3. MULTI-CATCH (Java 7+)
    // ═══════════════════════════════════════════════════════
    static void multiCatchDemo(int choice) {
        try {
            switch (choice) {
                case 1: throw new NullPointerException("null deref");
                case 2: throw new ArrayIndexOutOfBoundsException("bad index");
                case 3: throw new ClassCastException("bad cast");
                case 4: {
                    int[] arr = new int[3];
                    int x = arr[10];  // ArrayIndexOutOfBoundsException
                    break;
                }
            }
        } catch (NullPointerException | ClassCastException e) {
            /*
             * ╔══════════════════════════════════════════╗
             *  MULTI-CATCH (Java 7): catch multiple exception
             *  types in a single catch block.
             *  The exception variable 'e' is implicitly final here.
             * ╚══════════════════════════════════════════╝
             */
            System.out.println("[Multi-catch NPE|CCE] " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("[AIOOBE] " + e.getMessage());
        }
    }

    // ═══════════════════════════════════════════════════════
    // 4. CHECKED EXCEPTION — throws declaration
    // ═══════════════════════════════════════════════════════
    static void processUser(String name, int age) throws ValidationException {
        if (name == null || name.isBlank())
            throw new ValidationException("name", "Name cannot be blank");
        if (age < 0 || age > 150)
            throw new ValidationException("age", "Age must be 0–150");
        System.out.println("User validated: " + name + ", age=" + age);
    }

    static void simulateDbQuery(String query) throws DatabaseException {
        if (query == null || query.isEmpty())
            throw new DatabaseException("Empty query",
                query, new IllegalArgumentException("query is null"));
        System.out.println("DB query executed: " + query);
    }

    // ═══════════════════════════════════════════════════════
    // 5. try-with-resources (AutoCloseable)
    // ═══════════════════════════════════════════════════════
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  try-with-resources (Java 7+):
     *  Resources declared in try() are automatically closed
     *  after the try block, in REVERSE order of declaration.
     *  Resource class must implement AutoCloseable.
     *  close() is called even if an exception is thrown.
     * ╚══════════════════════════════════════════════════════╝
     */
    static class FakeConnection implements AutoCloseable {
        private final String name;
        public FakeConnection(String name) {
            this.name = name;
            System.out.println("[" + name + "] Connection opened.");
        }
        public void query(String sql) { System.out.println("[" + name + "] Query: " + sql); }
        @Override public void close() { System.out.println("[" + name + "] Connection closed."); }
    }

    static class FakeTransaction implements AutoCloseable {
        public FakeTransaction() { System.out.println("[Txn] Transaction started."); }
        public void commit()    { System.out.println("[Txn] Committed."); }
        @Override public void close() { System.out.println("[Txn] Transaction closed/rolled back."); }
    }

    // ═══════════════════════════════════════════════════════
    // 6. EXCEPTION CHAINING (wrapping cause)
    // ═══════════════════════════════════════════════════════
    static void connectDatabase() throws AppException {
        try {
            throw new IOException("Connection refused: port 3306");
        } catch (IOException e) {
            /*
             * ╔══════════════════════════════════════════╗
             *  EXCEPTION CHAINING: wrap the original exception
             *  as the 'cause'. Preserves root cause.
             *  getCause() retrieves the wrapped exception.
             * ╚══════════════════════════════════════════╝
             */
            throw new AppException("Database unavailable", 503, e);
        }
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== Exception Handling =====\n");

        // --- try/catch/finally ---
        System.out.println("--- 1. try / catch / finally ---");
        divide(10, 2);
        divide(10, 0);

        // --- multi-catch ---
        System.out.println("\n--- 2. Multi-catch (Java 7) ---");
        multiCatchDemo(1);
        multiCatchDemo(2);
        multiCatchDemo(3);

        // --- Custom checked exceptions ---
        System.out.println("\n--- 3. Custom Checked Exceptions ---");
        try {
            processUser("Alice", 25);
            processUser("", 25);           // throws ValidationException
        } catch (ValidationException e) {
            System.out.println("Validation failed: field=" + e.getField()
                + " msg=" + e.getMessage() + " code=" + e.getErrorCode());
        }

        try {
            simulateDbQuery("SELECT * FROM users");
            simulateDbQuery("");
        } catch (DatabaseException e) {
            System.out.println("DB error: " + e.getMessage());
            System.out.println("Cause: " + e.getCause().getMessage());
        }

        // --- try-with-resources ---
        System.out.println("\n--- 4. try-with-resources ---");
        try (FakeConnection conn = new FakeConnection("primary");
             FakeTransaction txn = new FakeTransaction()) {
            conn.query("INSERT INTO orders VALUES (...)");
            txn.commit();
            // conn and txn auto-closed in reverse order after this block
        }

        // Even on exception, resources are closed
        System.out.println();
        try (FakeConnection conn = new FakeConnection("secondary")) {
            conn.query("SELECT * FROM users");
            if (true) throw new RuntimeException("Simulated failure");
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage() + " (but conn was still closed)");
        }

        // --- Exception chaining ---
        System.out.println("\n--- 5. Exception Chaining ---");
        try {
            connectDatabase();
        } catch (AppException e) {
            System.out.println("Top-level: " + e.getMessage());
            System.out.println("Root cause: " + e.getCause().getMessage());
            System.out.println("Stack depth: cause is " + e.getCause().getClass().getSimpleName());
        }

        // --- finally vs return (tricky) ---
        System.out.println("\n--- 6. finally always runs (even with return) ---");
        System.out.println("divide(20, 4) returned: " + divide(20, 4));
    }
}
