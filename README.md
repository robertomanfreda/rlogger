# What is RLogger

RLogger is an advanced logging library that allows you to easily obfuscate and manipulate sensitive data.   
Built on top of SLF4J and Logback.  
All operations (logging and masking) are executed on a dedicated thread in order to not slow down the Main thread.

---

# How it works

The core of Rlogger extends this class 'ch.qos.logback.classic.PatternLayout', every log messages passes through a
specific method into that class:  
```public String doLayout(final ILoggingEvent event);```  
Overriding this method is possible to control and manipulate ALL logs.
All these operations are executed on a separated threads who is capable to async enque and deque messages (using 
Runnables).  
The logs order is respected because the queue doesn't mix up the Runnable Objects but is capable to deque in a good
manner.

---

# Setup

In order to import RLogger in your project you have to do 3 simple steps:
  - Compile the project ```./mvnw -U clean package``` and import the jar in your project
  - Create a logback.xml that uses ```RLoggerMaskingLayout``` as main logback layout
  - Create a rlogger.yaml in your resource folder in order to explicit the key that you want to obfuscate


#### *logback.xml example*
```xml
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
            <layout class="com.robertomanfreda.rlogger.core.RLoggerMaskingLayout">
                <pattern>%d{HH:mm:ss.SSS} %-5level %logger{36} - %msg%n</pattern>
            </layout>
        </encoder>
    </appender>

    <root level="DEBUG">
        <appender-ref ref="STDOUT"/>
    </root>
</configuration>
```


#### *rlogger.yaml example*
```yaml
masks:
  - foo
  - bar
  - baz
```

You can see the src/test folder to get a better idea.