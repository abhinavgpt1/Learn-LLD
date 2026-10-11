SOLID Principles 
- Def: The SOLID principles are a set of five design principles aimed at making software designs more understandable, flexible, and maintainable. 
They were introduced by Robert C. Martin (Uncle Bob) and are widely adopted in object-oriented programming.
ref: https://medium.com/backticks-tildes/the-s-o-l-i-d-principles-in-pictures-b34ce2f1e898

## S — Single Responsibility Principle (SRP)

A class should have one responsibility and only one reason to change.
This reduces coupling/interdependence between behaviours. If one responsibility changes, it won’t accidentally break another.

Goal: Keep behaviour separated so changes don’t introduce unexpected bugs.

## O — Open/Closed Principle (OCP)

Classes should be open for extension but closed for modification.
Instead of editing existing code when adding features, you extend behaviour using new components/classes.

Goal: Add new functionality without altering existing, stable code — reducing risk of new bugs.

## L — Liskov Substitution Principle (LSP)

Subclasses should be usable in place of their parent classes without altering program correctness.
If a parent can do X, the child should also be able to do X (or a compatible version of it).

Goal: Ensure consistent and predictable behaviour when using inheritance.

## I — Interface Segregation Principle (ISP)

Clients should not be forced to depend on methods they don’t use.
It’s better to have multiple small, specific interfaces rather than one large, all-purpose interface.

Goal: Ensure classes implement only what they need, reducing clutter and bugs from unused functionality.

## D — Dependency Inversion Principle (DIP)

High-level modules should depend on abstractions, not concrete low-level implementations.
Both high-level and low-level components depend on interfaces, not on each other.

Goal: Reduce tight coupling and make systems easier to modify, test, and extend.