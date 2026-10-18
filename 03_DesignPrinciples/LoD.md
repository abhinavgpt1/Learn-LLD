source: https://algomaster.io/learn/lld/lod

Law of Demeter (LoD), also known as the Principle of Least Knowledge, is a design guideline in software development that promotes loose coupling between components.
The core idea behind LoD is that an object should only communicate with its immediate collaborators and not with distant or unrelated objects.
By adhering to the LoD principle, developers can create more modular, maintainable, and flexible codebases, reducing dependencies and enhancing the overall design of the system.

------------------

Bad example: An Order accesses the customer's address and then the city's name through a chain of objects: 
```
order.getCustomer().getAddress().getCity().
```
This creates tight coupling because Order depends on the internal structure of Customer and Address. If that structure changes, Order may also need modification.

Better example: Let Customer provide a method such as getCity(), which internally retrieves the city from its Address. Then Order only calls 
```
order.getCustomer().getCity()
```
reducing its knowledge of the internal object structure.

------------------