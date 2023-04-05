- Start using jshell: `jshell` (Java version >= 9)
```
|  Welcome to JShell -- Version 11.0.16.1
|  For an introduction type: /help intro
```
- `/help intro`
```
|                                   intro
|                                   =====
|  
|  The jshell tool allows you to execute Java code, getting immediate results.
|  You can enter a Java definition (variable, method, class, etc), like:  int x = 8
|  or a Java expression, like:  x + x
|  or a Java statement or import.
|  These little chunks of Java code are called 'snippets'.
|  
|  There are also the jshell tool commands that allow you to understand and
|  control what you are doing, like:  /list
|  
|  For a list of commands: /help
```
- Example:
```
jshell> String x = "4";
x ==> "4"
jshell> String y = "5";
y ==> "5"
jshell> System.out.println(x = 5)
|  Error:
|  incompatible types: int cannot be converted to java.lang.String
|  System.out.println(x = 5)
|                         ^
jshell> System.out.println(x = y)
5
jshell>
```
Reference: [Introduction to jshell](https://docs.oracle.com/javase/9/jshell/introduction-jshell.htm#JSHEL-GUID-465BA4F5-E77D-456F-BCB7-D826AC1E18AE)