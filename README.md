# N-Way Set Associative Cache

This is my implementation of an N-Way Set Associative cache

An N-way set associative cache is a cache data structure that combines the policies of a Directly Mapped cache with the policies of a Fully Associative cache. When a key value pair is added to to this cache, it is “directly mapped” to one of N sets with fixed capacity, and each of the N sets act as Fully Associative caches, which could incorporate an arbitrary replacement policy, like LRU, MRU, or any other. N-way set associative caches are particularly useful because they can be thread-safe while potentially avoiding a significant serial bottleneck.

This project was written using Java 1.8, JUnit 4.8.2, and Maven 3.5.2. See the documentation for build instructions as well as usage and implementation details.