# JNBT - Java Library

Jack's Named Binary Tree is a specification, format and java module for efficently saving data to a computer, or sending it over the network! JNBT easily and simply converts its data structure down to the minimal viable bytes to keep file sizes small, whilst providing a robust named map like tree structure to access and manage it programaticly. 

> [!IMPORTANT]
> JNBT (Jack's Named Binary Tree) is not currently cross compatible with NBT (Notchian). Please see the specifications for full details.

## Specification

| ID : 1  (ByteTag)          |        |            |             |         |         |
|----------------------------|--------|------------|-------------|---------|---------|
| Size (bytes)               | 1      | 2 (short)  | ? (dynamic) | 1       |
| Field name                 | TAG ID | KEY LENGTH | KEY         | PAYLOAD |
| -------------------        |--------|------------|-------------|----------------|-------------|
| ID : 2  (ShortTag)         |        |            |            |         |
| Size (bytes)               | 1      | 2 (short)  | ? (dynamic) | 2       |
| Field name                 | TAG ID | KEY LENGTH | KEY        | PAYLOAD |
| -------------------        |--------|------------|-------------|----------------|-------------|
| ID : 3  (IntegerTag)       |        |            |            |         |
| Size (bytes)               | 1      | 2 (short)  | ? (dynamic) | 4       |
| Field name                 | TAG ID | KEY LENGTH | KEY        | PAYLOAD |
| -------------------        |--------|------------|-------------|----------------|-------------|
| ID : 4  (LongTag)          |        |            |            |         |
| Size (bytes)               | 1      | 2 (short)  | ? (dynamic) | 8       |
| Field name                 | TAG ID | KEY LENGTH | KEY        | PAYLOAD |
| -------------------        |--------|------------|-------------|----------------|-------------|
| ID : 5  (FloatTag)         |        |            |            |         |
| Size (bytes)               | 1      | 2 (short)  | ? (dynamic) | 4       |
| Field name                 | TAG ID | KEY LENGTH | KEY        | PAYLOAD |
| -------------------        |--------|------------|-------------|----------------|-------------|
| ID : 6  (DoubleTag)        |        |            |            |         |
| Size (bytes)               | 1      | 2 (short)  | ? (dynamic) | 8       |
| Field name                 | TAG ID | KEY LENGTH | KEY        | PAYLOAD |
| -------------------        |--------|------------|-------------|----------------|-------------|
| ID : 7  (ByteArray)        |        |            |             |                |             |
| Size (bytes)               | 1      | 2 (short)  | ? (dynamic) | 4 (int)        | ? (dynamic) |
| Field name                 | TAG ID | KEY LENGTH | KEY         | PAYLOAD LENGTH | PAYLOAD     |
| -------------------        |--------|------------|-------------|----------------|-------------|
| ID : 8  (StringTag)        |        |            |             |                |             |
| Size (bytes)               | 1      | 2 (short)  | ? (dynamic) | 2 (short)      | ? (dynamic) |
| Field name                 | TAG ID | KEY LENGTH | KEY         | PAYLOAD LENGTH | PAYLOAD     |
| -------------------        |--------|------------|-------------|----------------|-------------|
| ID : 9  (ListTag)          |        |            |             |                |             |
| Size (bytes)               | 1      | 2 (short)  | ? (dynamic) | 4 (int)        | ? (dynamic) |
| Field name                 | TAG ID | KEY LENGTH | KEY         | PAYLOAD LENGTH | PAYLOAD     |
| -------------------        |--------|------------|-------------|----------------|-------------|
| ID : 10  (Compound)        |        |            |             |                |             |
| Size (bytes)               | 1      | 2 (short)  | ? (dynamic) | 4 (int)        | ? (dynamic) |
| Field name                 | TAG ID | KEY LENGTH | KEY         | PAYLOAD LENGTH | PAYLOAD     |
| -------------------        |--------|------------|-------------|----------------|-------------|
| ID : 11  (IntegerArrayTag) |        |            |             |                |             |
| Size (bytes)               | 1      | 2 (short)  | ? (dynamic) | 4 (int)        | ? (dynamic) |
| Field name                 | TAG ID | KEY LENGTH | KEY         | PAYLOAD LENGTH | PAYLOAD     |
| -------------------        |--------|------------|-------------|----------------|-------------|
| ID : 12  (FloatArrayTag)   |        |            |             |                |             |
| Size (bytes)               | 1      | 2 (short)  | ? (dynamic) | 4 (int)        | ? (dynamic) |
| Field name                 | TAG ID | KEY LENGTH | KEY         | PAYLOAD LENGTH | PAYLOAD     |
