`jcmd` is a useful tool to diagnose `Java Application` and `JVM`.

- Check running application:

```
> jcmd
64598 jdk.jcmd/sun.tools.jcmd.JCmd
60598 thach.le.til.payroll.SpringbootApplication
76968 org.gradle.launcher.daemon.bootstrap.GradleDaemon 6.8.3
```
- Get system properties
```
> jcmd 60598 VM.system_properties
60598:
#Thu Apr 06 16:49:00 ICT 2023
java.specification.version=17
sun.jnu.encoding=UTF-8
java.class.path="..."
java.vm.vendor=Amazon.com Inc.
sun.arch.data.model=64
catalina.useNaming=false
```
- Get flags
```
> jcmd 60598 VM.flags            
60598:
-XX:CICompilerCount=4 -XX:ConcGCThreads=2 -XX:G1ConcRefinementThreads=8 -XX:G1EagerReclaimRemSetThreshold=16 -XX:G1HeapRegionSize=2097152 -XX:GCDrainStackTargetSize=64 -XX:InitialHeapSize=268435456 -XX:+ManagementServer -XX:MarkStackSize=4194304 -XX:MaxHeapSize=4294967296 -XX:MaxNewSize=2575302656 -XX:MinHeapDeltaBytes=2097152 -XX:MinHeapSize=8388608 -XX:NonProfiledCodeHeapSize=0 -XX:-ProfileInterpreter -XX:ProfiledCodeHeapSize=0 -XX:SoftMaxHeapSize=4294967296 -XX:TieredStopAtLevel=1 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseFastUnorderedTimeStamps -XX:+UseG1GC -XX:-UseNUMA -XX:-UseNUMAInterleaving 
```
- Get heap info
```
> jcmd 60598 GC.heap_info      
60598:
 garbage-first heap   total 96256K, used 23246K [0x0000000700000000, 0x0000000800000000)
  region size 2048K, 1 young (2048K), 0 survivors (0K)
 Metaspace       used 51997K, committed 52416K, reserved 1097728K
  class space    used 7518K, committed 7744K, reserved 1048576K
```
- Get all available command 
```
> jcmd 60598 help           
60598:
The following commands are available:
Compiler.CodeHeap_Analytics
Compiler.codecache
Compiler.codelist
Compiler.directives_add
Compiler.directives_clear
Compiler.directives_print
Compiler.directives_remove
Compiler.queue
GC.class_histogram
GC.finalizer_info
GC.heap_dump
GC.heap_info
GC.run
GC.run_finalization
JFR.check
JFR.configure
JFR.dump
JFR.start
JFR.stop
JVMTI.agent_load
JVMTI.data_dump
ManagementAgent.start
ManagementAgent.start_local
ManagementAgent.status
ManagementAgent.stop
Thread.print
VM.cds
VM.class_hierarchy
VM.classloader_stats
VM.classloaders
VM.command_line
VM.dynlibs
VM.events
VM.flags
VM.info
VM.log
VM.metaspace
VM.native_memory
VM.print_touched_methods
VM.set_flag
VM.stringtable
VM.symboltable
VM.system_properties
VM.systemdictionary
VM.uptime
VM.version
help
```

Reference: [The jcmd Utility](https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/tooldescr006.html#BABEHABG)
