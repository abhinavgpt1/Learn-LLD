SOLID Principles 
- Def: The SOLID principles are a set of five design principles aimed at making software designs more understandable, flexible, and maintainable. 
They were introduced by Robert C. Martin (Uncle Bob) and are widely adopted in object-oriented programming.
ref: https://medium.com/backticks-tildes/the-s-o-l-i-d-principles-in-pictures-b34ce2f1e898

## S — Single Responsibility Principle (SRP)

A class should have only one reason to change, meaning it should have one clearly defined responsibility (!= methods)
This reduces coupling/interdependence between behaviours. If one responsibility changes, it won't accidentally break another.

Goal: Keep behaviour separated so changes don't introduce unexpected bugs.

## O — Open/Closed Principle (OCP)

Classes should be open for extension but closed for modification.
Instead of editing existing code when adding features, you extend behaviour using new components/classes.

Goal: Add new functionality without altering existing, stable code — reducing risk of new bugs.

## L — Liskov Substitution Principle (LSP)

Objects of a subclass should be usable wherever objects of its superclass are expected, without causing unexpected errors or behavior.
If a parent can do X, the child should also be able to do X (or a compatible version of it).

Goal: Ensure consistent and predictable behaviour when using inheritance.

## I — Interface Segregation Principle (ISP)

Clients should not be forced to depend on methods they don't use.
It's better to have multiple small, specific interfaces rather than one large, all-purpose interface.

Goal: Ensure classes implement only what they need, reducing clutter and bugs from unused functionality.

## D — Dependency Inversion Principle (DIP)

High-level modules should depend on abstractions, not concrete low-level implementations.
Both high-level and low-level components depend on interfaces, not on each other.

Goal: Reduce tight coupling and make systems easier to modify, test, and extend.

------------------

#### QQ - Isn't ISP followed automatically by SRP? If not, what's the difference?
#### Ans - Consider this example:

```
interface Machine {
    void print();
    void scan();
    void fax();
}

class BasicPrinter implements Machine {
    public void print() {
        System.out.println("Printing");
    }

    public void scan() {
        throw new UnsupportedOperationException();
    }

    public void fax() {
        throw new UnsupportedOperationException();
    }
}
```

BasicPrinter has one clear responsibility: printing. It can satisfy SRP.
However, it must implement scan() and fax() even though it cannot perform those operations. That violates ISP.
The problem is not that the interface has multiple methods. The problem is that the interface forces clients or implementations to depend on capabilities they don't need.

> #### Difference

SRP — Class-level concern
Is this class handling multiple independent reasons to change?
Focus: organizing responsibilities among classes.


ISP — Interface-level concern
Is this interface forcing an implementation or client to depend on methods it doesn't need?
Focus: organizing interfaces around the needs of their clients.

> Soln: Fix it by splitting the large Machine interface into smaller, capability-specific interfaces

```
interface Printer {
    void print();
}

interface Scanner {
    void scan();
}

interface Fax {
    void fax();
}

class BasicPrinter implements Printer {
    public void print() {
        System.out.println("Printing...");
    }
}

class MultiFunctionPrinter implements Printer, Scanner, Fax {
    public void print() {
        System.out.println("Printing...");
    }

    public void scan() {
        System.out.println("Scanning...");
    }

    public void fax() {
        System.out.println("Faxing...");
    }
}
```
------------------