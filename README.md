<div align="center">

<br/>

```
  ██████╗  ██████╗ ██████╗ ███████╗
 ██╔═══██╗██╔═══██╗██╔══██╗██╔════╝
 ██║   ██║██║   ██║██████╔╝███████╗
 ██║   ██║██║   ██║██╔═══╝ ╚════██║
 ╚██████╔╝╚██████╔╝██║     ███████║
  ╚═════╝  ╚═════╝ ╚═╝     ╚══════╝
             in  J A V A
```

### *Object-Oriented Programming — Architected, Not Just Written*

<br/>

![Java](https://img.shields.io/badge/Java-SE%2023-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![IDE](https://img.shields.io/badge/Eclipse-IDE-2C2255?style=for-the-badge&logo=eclipse&logoColor=white)
![OOP](https://img.shields.io/badge/Paradigm-OOP-0057B8?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Active-2ea44f?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

<br/>

> *"Programs must be written for people to read, and only incidentally for machines to execute."*
> — Harold Abelson

<br/>

</div>

---

## ◈ What Is This?

This repository is a **curated, hands-on reference** for Object-Oriented Programming in Java — built from real source code, not slides. Every concept is demonstrated through working `.java` files organized to take you from `class` declarations all the way to polymorphic hierarchies, abstract contracts, and structural design patterns.

Built in **Eclipse IDE** targeting **Java SE 23**, this project follows standard Java project conventions (`src/` for sources, `bin/` for compiled bytecode).

---

## ◈ The Four Pillars — Implemented

```
┌─────────────────────────────────────────────────────────────┐
│                                                             │
│   🔒 ENCAPSULATION      🧬 INHERITANCE                      │
│   ─────────────────     ──────────────                      │
│   Data hiding via       "Is-A" hierarchies via              │
│   access modifiers,     extends keyword,                    │
│   getters & setters,    method overriding,                  │
│   tight class design.   super() chaining.                   │
│                                                             │
│   🎭 POLYMORPHISM       🪟 ABSTRACTION                      │
│   ────────────────      ─────────────                       │
│   One interface,        Abstract classes,                   │
│   many forms. Runtime   interfaces, hiding                  │
│   dispatch, method      impl details behind                 │
│   overloading.          clean contracts.                    │
│                                                             │
└─────────────────────────────────────────────────────────────┘
```

---

## ◈ Repository Structure

```
OOPS/
│
├── src/                        ← All Java source files
│   ├── [classes & objects]     ← Blueprint basics, constructors, `this`
│   ├── [encapsulation]         ← Access modifiers, getters/setters
│   ├── [inheritance]           ← Single, multilevel, hierarchical
│   ├── [polymorphism]          ← Overloading, overriding, dynamic dispatch
│   ├── [abstraction]           ← abstract classes, interfaces
│   └── [design patterns]       ← Structural patterns & real-world models
│
├── bin/                        ← Compiled .class bytecode (Eclipse output)
├── .settings/                  ← Eclipse workspace settings
├── .classpath                  ← Eclipse classpath (JavaSE-23)
└── .project                    ← Eclipse project descriptor
```

---

## ◈ Core Concepts Covered

### 🧱 Classes & Objects
- Class declaration, instance variables, and methods
- Constructors — default, parameterized, copy
- `this` keyword and constructor chaining
- Static vs instance members
- Object lifecycle and memory model (Stack & Heap)

### 🔒 Encapsulation
- `private`, `protected`, `public`, and package-private access
- Getters and setters — JavaBean convention
- Immutable objects and defensive copying
- Why encapsulation is the backbone of maintainable code

### 🧬 Inheritance
- `extends` — single inheritance in Java
- Method overriding (`@Override`)
- `super` keyword — accessing parent members
- Constructor chaining with `super()`
- Multilevel and hierarchical inheritance trees

### 🎭 Polymorphism
- **Compile-time**: Method overloading (same name, different signatures)
- **Runtime**: Dynamic method dispatch via reference upcasting
- `instanceof` checks and safe downcasting
- Covariant return types

### 🪟 Abstraction
- `abstract` classes — partial implementation contracts
- `interface` — pure behavioral contracts
- Default and static methods in interfaces (Java 8+)
- Functional interfaces and the road to lambdas
- Abstract class vs Interface — when to use which

### 🏛️ Design Patterns (OOP Applied)
- **Singleton** — controlled single-instance creation
- **Factory** — object creation without exposing logic
- **Builder** — step-by-step complex object construction
- **Strategy** — encapsulating interchangeable algorithms
- **Observer** — event-driven communication between objects

---

## ◈ Java OOP Quick-Reference Cheat Sheet

```java
// ── Encapsulation ──────────────────────────────────────────
public class BankAccount {
    private double balance;           // hidden state

    public double getBalance() { return balance; }
    public void deposit(double amt) {
        if (amt > 0) balance += amt;  // guarded mutation
    }
}

// ── Inheritance ────────────────────────────────────────────
class Animal {
    String name;
    void speak() { System.out.println("..."); }
}

class Dog extends Animal {
    @Override
    void speak() { System.out.println("Woof!"); } // override
}

// ── Polymorphism ───────────────────────────────────────────
Animal a = new Dog();   // upcast — runtime type is Dog
a.speak();              // → "Woof!" (dynamic dispatch)

// ── Abstraction ────────────────────────────────────────────
interface Drawable {
    void draw();                     // contract
    default void print() {           // Java 8+ default method
        System.out.println("Printing...");
    }
}

abstract class Shape implements Drawable {
    abstract double area();          // partial contract
}
```

---

## ◈ Key Java OOP Concepts — Deep Dive

### The Object Memory Model

```
Stack (method frames)          Heap (object data)
┌──────────────────┐          ┌──────────────────────────┐
│  main()          │          │                          │
│  ┌────────────┐  │          │   ┌──────────────────┐   │
│  │ dog → ─────┼──┼─────────▶│   │  Dog object      │   │
│  └────────────┘  │          │   │  name: "Bruno"   │   │
│                  │          │   │  breed: "Lab"    │   │
│  speak()         │          │   └──────────────────┘   │
│  ┌────────────┐  │          │                          │
│  │ this → ────┼──┼─────────▶│   (same object)          │
│  └────────────┘  │          │                          │
└──────────────────┘          └──────────────────────────┘
```

### Abstract Class vs Interface

| Feature                  | `abstract class`          | `interface`                     |
|--------------------------|---------------------------|---------------------------------|
| Instantiation            | ❌ Cannot instantiate      | ❌ Cannot instantiate            |
| State (fields)           | ✅ Yes                     | ⚠️ Only `public static final`   |
| Constructor              | ✅ Yes                     | ❌ No                            |
| Multiple inheritance     | ❌ Single only             | ✅ Multiple allowed              |
| Default methods          | ✅ Any method              | ✅ Since Java 8                  |
| Use when                 | Sharing code + contract   | Pure behavioral contract        |

### Method Overloading vs Overriding

| Aspect              | Overloading              | Overriding                    |
|---------------------|--------------------------|-------------------------------|
| Resolved at         | Compile-time             | Runtime                       |
| Location            | Same class               | Parent → Child class          |
| Signature           | Must differ              | Must match exactly            |
| `@Override`         | Not applicable           | Best practice to use          |
| Return type         | Can differ               | Must be same or covariant     |

---

## ◈ Getting Started

### Prerequisites

- **Java SE 23** or later — [Download JDK](https://www.oracle.com/java/technologies/downloads/)
- **Eclipse IDE** — [Download Eclipse](https://www.eclipse.org/downloads/) *(recommended)*
- OR any Java IDE: IntelliJ IDEA, VS Code with Java Extension Pack

### Clone the Repository

```bash
git clone https://github.com/sahastraWin/OOPS.git
cd OOPS
```

### Open in Eclipse

```
1. File → Open Projects from File System
2. Select the cloned OOPS/ directory
3. Eclipse auto-detects .project and .classpath
4. Right-click any .java file → Run As → Java Application
```

### Compile & Run via Terminal

```bash
# Compile all sources
javac -d bin src/**/*.java

# Run a specific class (e.g., Main)
java -cp bin Main
```

---

## ◈ Learning Path

Follow this sequence for maximum comprehension:

```
① Classes & Objects          →  Understand blueprints and instances
       ↓
② Encapsulation              →  Learn to hide and protect state
       ↓
③ Inheritance                →  Build "is-a" hierarchies
       ↓
④ Polymorphism               →  Write flexible, extensible code
       ↓
⑤ Abstraction                →  Design clean contracts
       ↓
⑥ Design Patterns            →  Apply OOP to real-world architecture
```

---

## ◈ SOLID Principles Connection

This codebase naturally demonstrates the **SOLID** principles that emerge from good OOP design:

| Principle | Meaning | How it appears here |
|-----------|---------|---------------------|
| **S**ingle Responsibility | One class, one job | Focused, small classes |
| **O**pen/Closed | Open for extension, closed for modification | Abstract classes, interfaces |
| **L**iskov Substitution | Subtypes must be substitutable | Correct `@Override` usage |
| **I**nterface Segregation | Specific interfaces over fat ones | Lean interface definitions |
| **D**ependency Inversion | Depend on abstractions, not concretions | Interface-based design |

---

## ◈ Tech Stack

| Technology | Version | Role |
|------------|---------|------|
| Java | SE 23 | Language |
| Eclipse IDE | 2024+ | Development Environment |
| JDK | OpenJDK 23 | Compilation & Runtime |

---

## ◈ Contributing

Contributions that expand or sharpen the OOP coverage are welcome.

```bash
# Fork → Clone → Branch → Code → PR

git checkout -b feature/your-concept
git commit -m "Add: [concept name] with example"
git push origin feature/your-concept
```

**Good contribution ideas:**
- Additional design pattern examples (Decorator, Composite, Command)
- Java 17+ OOP features (sealed classes, records, pattern matching)
- Unit tests using JUnit 5
- Javadoc improvements

---

## ◈ Author

**sahastraWin**

> *Building understanding, one class at a time.*

[![GitHub](https://img.shields.io/badge/GitHub-sahastraWin-181717?style=flat-square&logo=github)](https://github.com/sahastraWin)

---

## ◈ License

This project is open-source under the [MIT License](LICENSE).
Free to use, study, modify, and share.

---

<div align="center">

<br/>

*"Complexity is the enemy of reliability. OOP, done right, tames both."*

<br/>

**⭐ Star this repo if it helped you think in objects.**

<br/>

`class README extends Markdown implements Exquisite { }`

</div>
