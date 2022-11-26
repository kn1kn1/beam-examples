# Example TextIO.ReadableFile workaround

An example pipeline code for properly getting lastModifiedMillis of specified file.

## NG case

```
$ mvn compile exec:java -Dexec.mainClass=org.example.ReadableFileExampleNG
```

outputs:
```
*** name: kinglear.txt
*** lastModifiedMillis: 0
*** lastModified: 1970-01-01T00:00:00.000Z
*** sizeBytes: 157283
```


## OK case

```
$ mvn compile exec:java -Dexec.mainClass=org.example.ReadableFileExample
```

outputs:
```
*** name: kinglear.txt
*** lastModifiedMillis: 1472084114722
*** lastModified: 2016-08-25T00:15:14.722Z
*** sizeBytes: 157283
```
