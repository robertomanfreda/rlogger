# What is RLogger

RLogger is an advanced logging library that allows you to easily obfuscate and manipulate sensitive data.   
Built on top of SLF4J and Logback.  
All operations (logging and masking) are executed on a dedicated thread in order to not slow down the Main thread.

---

# How it works

The core of Rlogger extends this class ```ch.qos.logback.classic.PatternLayout```, every log messages passes through a
specific method into that class:  
```public String doLayout(final ILoggingEvent event);```  
Overriding this method is possible to control and manipulate ALL logs. All these operations are executed on a separated
threads who is capable to asynchronously enque and deque messages
(using Runnables).    
The logs order will be respected because the queue doesn't mix up the Runnable Objects but is capable to deque in a good
manner.

---

# Setting up

In order to import RLogger in your project you have to do 3 simple steps:

- Compile the project ```./mvnw -U clean package``` and import the jar in your project
- Create a **logback.xml** (default file not provided by the library, you have total control about it) that uses
  ```RLoggerMaskingLayout``` as main logback layout
- Create a **rlogger.yaml** (default file not provided by the library, you have total control about it) in your resource
  folder in order to explicit the key that you want to obfuscate

A package distributed via Maven central will be available as soon as possible! Just be patient.

---

# Creating loggers

To create an instance of RLogger capable to log messages at different logging levels (all SLF4J's logging levels are
supported because RLogger basically is a custom implementation of `org.slf4j.Logger` class) you just need to use one of
the following syntax:

- The classic way  
  `private static final RLogger logger = new Rlogger();`
- For builder lovers   
  `private static final RLogger logger = RLogger.builder.build();`
- RLogger has also native support for lombok annotations  
  `@Slf4j` at class level. I love it.

# SAMPLES

Assuming we are logging a json payload like this:

```json
{
  "glossary": {
    "title": "example glossary",
    "GlossDiv": {
      "title": "S",
      "GlossList": {
        "GlossEntry": {
          "ID": "SGML",
          "SortAs": "SGML",
          "GlossTerm": "Standard Generalized Markup Language",
          "Acronym": "SGML",
          "Abbrev": "ISO 8879:1986",
          "GlossDef": {
            "para": "A meta-markup \"language\", used to create markup languages such as DocBook.",
            "GlossSeeAlso": [
              "GML",
              "XML"
            ]
          },
          "GlossSee": "markup"
        }
      }
    }
  }
}
```

We want to obfuscate some values, but values most of the time are dynamic, so for do that we need to refer to their
keys.    
For do that in RLogger we need to create a simple file, in our `src/main/resources` folder, called **rlogger.yaml**.  
Referring to the json above, suppose we want to obfuscate the value referred to *Acronym* and the array named
*GlossSeeAlso* we will use a file like this:

**rlogger.yaml**

```yaml
config:
  json:
    enabled: true
    indentFactor: 4
    placeholder: '*** This value has been obfuscated by RLogger ***'

masks:
  - Acronym
  - GlossSeeAlso
```

This will be the result in our logs:

```
23:01:05.852 DEBUG c.r.rlogger.core.RLoggerTest - 
{"glossary": {
    "title": "example glossary",
    "GlossDiv": {
        "GlossList": {"GlossEntry": {
            "GlossTerm": "Standard Generalized Markup Language",
            "GlossSee": "markup",
            "SortAs": "SGML",
            "GlossDef": {
                "para": "A meta-markup "language", used to create markup languages such as DocBook.",
                "GlossSeeAlso": "*** This value has been obfuscated by RLogger ***"
            },
            "ID": "SGML",
            "Acronym": "*** This value has been obfuscated by RLogger ***",
            "Abbrev": "ISO 8879:1986"
        }},
        "title": "S"
    }
}}
```

The same stuff will happen if you are logging an "ugly" (all on the same line) json payload like this:      
```{"glossary": {"title": "example glossary","GlossDiv": {"title": "S","GlossList": {"GlossEntry": {"ID": "SGML","SortAs": "SGML","GlossTerm": "Standard Generalized Markup Language","Acronym": "SGML","Abbrev": "ISO 8879:1986","GlossDef": {"para": "A meta-markup \"language\", used to create markup languages such as DocBook.","GlossSeeAlso": ["GML","XML"]},"GlossSee": "markup"}}}}}```

**Please note:**     
Choosing an indentFactor value > 0 will automatically beautify the json payload across your logs.   
At the opposite side choosing the 0 value RLogger will not interfer with indentation.