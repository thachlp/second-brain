`JOL` (Java Object Layout) is the toolbox to analyze object layout in JVMs
#### General VM detail
```text
System.out.println(VM.current().details());
```
```text
# VM mode: 64 bits
# Compressed references (oops): 3-bit shift
# Compressed class pointers: 3-bit shift
# Object alignment: 8 bytes (*)
#                       ref, bool, byte, char, shrt,  int,  flt,  lng,  dbl
# Field sizes:            4,    1,    1,    2,    2,    4,    4,    8,    8
# Array element sizes:    4,    1,    1,    2,    2,    4,    4,    8,    8
# Array base offsets:    16,   16,   16,   16,   16,   16,   16,   16,   16
```
#### String layout
```text
System.out.println(ClassLayout.parseClass(String.class).toPrintable());
```
```text
java.lang.String object internals:
OFF  SZ      TYPE DESCRIPTION               VALUE
  0   8           (object header: mark)     N/A
  8   4           (object header: class)    N/A
 12   4       int String.hash               N/A
 16   1      byte String.coder              N/A
 17   1   boolean String.hashIsZero         N/A
 18   2           (alignment/padding gap)   
 20   4    byte[] String.value              N/A
Instance size: 24 bytes
Space losses: 2 bytes internal + 0 bytes external = 2 bytes total
```
`*` Alignment = 8 mean total bytes of object must be multiples of 8
#### Object layout
```text
System.out.println(ClassLayout.parseClass(Cat.class).toPrintable());
```
```text
OFF  SZ               TYPE DESCRIPTION               VALUE
  0   8                    (object header: mark)     N/A
  8   4                    (object header: class)    N/A
 12   4                int Pet.age                   N/A
 16   4              float Pet.weight                N/A
 20   4   java.lang.String Pet.name                  N/A
Instance size: 24 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
```
#### Object footprint (deep size)
```text
System.out.println(GraphLayout.parseInstance(cat).toFootprint());
```
```text
thach.le.Cat@6b09bb57d(**) footprint:
     COUNT       AVG       SUM   DESCRIPTION
         1        32        32   [B
         1        24        24   java.lang.String
         1        24        24   thach.le.Cat
         3                  80   (total)
```
`**` `thach.le.Cat@6b09bb57d`: `6b09bb57d` is hexadecimal version of object
To get decimal version of object and address memory, using:
```text
System.out.println(cat.hashCode());
System.out.println(VM.current().addressOf(cat));
```
```text
1795799895
30328814656
```
Reference: 
- [jol](https://github.com/openjdk/jol)
- [jvm object size](https://www.baeldung.com/jvm-measuring-object-sizes)
