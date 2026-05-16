/**
 * ============================================================
 *  TOPIC: Annotations in Java
 * ============================================================
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW:
 *  - Annotation = metadata attached to code elements
 *  - Do NOT directly affect program logic (but tools/frameworks use them)
 *  - Declared with @interface keyword
 *
 *  Built-in Annotations:
 *    @Override        → method overrides parent; compiler verifies
 *    @Deprecated      → marks as outdated; compiler warns on use
 *    @SuppressWarnings→ suppresses specific compiler warnings
 *    @FunctionalInterface → marks single-abstract-method interface
 *    @SafeVarargs     → suppresses unchecked varargs warning
 *
 *  Meta-Annotations (on annotations):
 *    @Target      → where annotation can be applied (class, method, field...)
 *    @Retention   → how long annotation is retained:
 *                   SOURCE (discarded by compiler)
 *                   CLASS  (in .class file, not at runtime) — default
 *                   RUNTIME(available via reflection)
 *    @Documented  → include in Javadoc
 *    @Inherited   → subclasses inherit annotation
 *    @Repeatable  → annotation can be used multiple times
 *
 *  Annotation elements:
 *  - Can have default values
 *  - element named 'value' can be used without key: @Ann("x")
 *  - Types allowed: primitives, String, Class, enum, annotation, arrays
 * ╚══════════════════════════════════════════════════════════╝
 */

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

public class Annotations {

    // ─── 1. Custom Annotations ───────────────────────────────

    @Target(ElementType.TYPE)                 // applies to classes only
    @Retention(RetentionPolicy.RUNTIME)       // available at runtime via reflection
    @Documented
    @interface Entity {
        String tableName() default "";
        boolean auditable() default false;
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Column {
        String name() default "";
        boolean nullable() default true;
        int maxLength() default 255;
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Id {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Transactional {
        boolean readOnly() default false;
        int timeout() default 30;
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Validate {}

    // ─── 2. Repeatable Annotation ───────────────────────────
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(Roles.class)
    @interface Role { String value(); }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Roles { Role[] value(); }

    // ─── 3. Annotated classes ───────────────────────────────
    @Entity(tableName = "users", auditable = true)
    static class User {
        @Id
        @Column(name = "user_id", nullable = false)
        private int id;

        @Column(name = "username", nullable = false, maxLength = 50)
        private String username;

        @Column(name = "email", maxLength = 100)
        private String email;

        private String password;  // no @Column — not mapped

        public User(int id, String username, String email) {
            this.id = id; this.username = username; this.email = email;
        }

        @Transactional
        @Validate
        public void save() {
            System.out.println("Saving user: " + username);
        }

        @Transactional(readOnly = true, timeout = 5)
        public User findById(int id) {
            System.out.println("Finding user by id: " + id);
            return this;
        }

        @Role("ADMIN")
        @Role("MANAGER")
        @Role("USER")
        public void sensitiveOperation() {
            System.out.println("Sensitive operation by " + username);
        }

        public int    getId()       { return id; }
        public String getUsername() { return username; }
    }

    // ─── 4. Mini ORM using Reflection + Annotations ─────────
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  This demonstrates how frameworks like Hibernate/JPA
     *  use annotations + reflection to generate SQL,
     *  validate fields, and manage transactions.
     * ╚══════════════════════════════════════════════════════╝
     */
    static class MiniORM {
        public static String generateInsertSQL(Object obj) throws Exception {
            Class<?> clazz = obj.getClass();

            if (!clazz.isAnnotationPresent(Entity.class))
                throw new RuntimeException("Not an @Entity class");

            Entity entity = clazz.getAnnotation(Entity.class);
            String table  = entity.tableName().isEmpty()
                ? clazz.getSimpleName().toLowerCase()
                : entity.tableName();

            StringBuilder cols = new StringBuilder();
            StringBuilder vals = new StringBuilder();

            for (Field f : clazz.getDeclaredFields()) {
                if (!f.isAnnotationPresent(Column.class)) continue;
                Column col = f.getAnnotation(Column.class);
                String colName = col.name().isEmpty() ? f.getName() : col.name();

                f.setAccessible(true);
                Object value = f.get(obj);

                if (cols.length() > 0) { cols.append(", "); vals.append(", "); }
                cols.append(colName);
                vals.append(value instanceof String ? "'" + value + "'" : value);
            }

            return "INSERT INTO " + table + " (" + cols + ") VALUES (" + vals + ");";
        }

        public static void printEntityInfo(Class<?> clazz) {
            System.out.println("\n[MiniORM] Inspecting: " + clazz.getSimpleName());
            Entity entity = clazz.getAnnotation(Entity.class);
            if (entity != null) {
                System.out.println("  Table: " + entity.tableName() +
                    " | Auditable: " + entity.auditable());
            }

            for (Field f : clazz.getDeclaredFields()) {
                if (f.isAnnotationPresent(Id.class))
                    System.out.println("  @Id field: " + f.getName());
                if (f.isAnnotationPresent(Column.class)) {
                    Column col = f.getAnnotation(Column.class);
                    System.out.printf("  @Column: %s → %s (nullable=%b, maxLen=%d)%n",
                        f.getName(), col.name(), col.nullable(), col.maxLength());
                }
            }
        }

        public static void executeTransactional(Object obj, String methodName) throws Exception {
            Method m = obj.getClass().getDeclaredMethod(methodName);
            if (m.isAnnotationPresent(Transactional.class)) {
                Transactional tx = m.getAnnotation(Transactional.class);
                System.out.println("[Txn] BEGIN (readOnly=" + tx.readOnly()
                    + ", timeout=" + tx.timeout() + "s)");
                try {
                    m.invoke(obj);
                    System.out.println("[Txn] COMMIT");
                } catch (Exception e) {
                    System.out.println("[Txn] ROLLBACK: " + e.getMessage());
                }
            } else {
                m.invoke(obj);
            }
        }

        public static void checkRoles(Object obj, String methodName, String userRole) throws Exception {
            Method m = obj.getClass().getDeclaredMethod(methodName);
            Role[] roles = m.getAnnotationsByType(Role.class);
            boolean hasAccess = Arrays.stream(roles).anyMatch(r -> r.value().equals(userRole));
            System.out.println("Role check [" + userRole + "] → " + (hasAccess ? "ALLOWED" : "DENIED"));
            if (hasAccess) m.invoke(obj);
        }
    }

    // ─── 5. Built-in annotations ────────────────────────────
    static class BuiltinDemo {
        @Deprecated
        public void oldMethod() { System.out.println("Old method (deprecated)"); }

        @Override
        public String toString() { return "BuiltinDemo object"; }

        @SuppressWarnings("unchecked")
        public List<String> uncheckedOperation() {
            List raw = new ArrayList();   // raw type warning suppressed
            raw.add("item");
            return raw;
        }
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) throws Exception {
        System.out.println("===== Annotations =====\n");

        User user = new User(1, "alice", "alice@example.com");

        // --- Annotation processing via reflection ---
        System.out.println("--- 1. ORM: Entity Info ---");
        MiniORM.printEntityInfo(User.class);

        System.out.println("\n--- 2. ORM: Generate SQL ---");
        System.out.println(MiniORM.generateInsertSQL(user));

        System.out.println("\n--- 3. Transactional ---");
        MiniORM.executeTransactional(user, "save");

        System.out.println("\n--- 4. Role-based Access ---");
        MiniORM.checkRoles(user, "sensitiveOperation", "ADMIN");
        MiniORM.checkRoles(user, "sensitiveOperation", "GUEST");

        // --- Built-in annotations ---
        System.out.println("\n--- 5. Built-in Annotations ---");
        BuiltinDemo bd = new BuiltinDemo();
        bd.oldMethod();  // compiler warning about @Deprecated usage
        System.out.println(bd);
        System.out.println(bd.uncheckedOperation());
    }
}
