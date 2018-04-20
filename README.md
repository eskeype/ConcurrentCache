# Concurrent Cache

This is an implementation of a key/value cache designed for concurrency and user configuration

Traditional caches are commonly designed to employ popular key replacement policies, such as LRU, MRU, LFU, etc. Certain cacheing policies are favored in some situations, but are considered suboptimal in others - Caches that use the LRU policy are used by operating systems for page replacement, and a variety of other tasks, but according to some exxperts, "When a file is being repeatedly scanned in a [Looping Sequential] reference pattern, MRU is the best replacement algorithm."

Several of the popular caching policies, including LRU and LFU, can be implemented very efficiently, but in a way that requires the caches to be locked for all reads and writes. This cache aims to allow for users to employ their own cache replacement strategies, while allowing for concurrent cache writing and access in some cases.

## Implementation Details

This is done using a tiered approach. The ConcurrentCache will contain a list of N (specified by the user) subcaches, each with their own capacities. Each subcache will employ a particular cache replacement policy (LRU, by default). Users can design their own cache replacement policies using the Evictor interface or the Cache interface (see documentation). Key/value pairs will be mapped in to a particular subcache according to the hashcode of the key. Each subcache is locked for reads and writes, to allow for efficient implementations of various replacement algorithms, but the ConcurrentCache will not be locked for reads and writes.

So, in a sense, this implementation is meant to be a compromise that allows for effective replacement strategies to be utilized in some way, while avoiding a sequential bottleneck for cache usage.


## Build

This project was written in Java 1.8, using JUnit 4.8.2 for unit tests, and Maven 3.5.2 for build automation

To build this project, clone or download it, then enter the project directory, and run

`$ mvn clean install`

For build and usage specifics, see the documentation