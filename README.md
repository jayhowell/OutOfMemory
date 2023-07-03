**Java/OpenShift Out of memory simulator**

This project launches a Quarkus Webservice that has the following endpoints.
~~~
/hello/status - just verifies the service is up and working
/hello/oom - creates an OOM condition.
~~~
This project also has health checks in it.  The reason for this test is to show Operators how to deal with OOM error conditions within
Openshift.
Instructions:
1.  Add the following to the Java arguments in the container. Most likely it will be defined as environment variables.
~~~
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=/my/mount/path
~~~
3.  Deploy this application using S2i, by going to the developer perspective, create a project, click add and add via github repo.(choose this repo)
4.  Verify your web service works https://insert-your-route-here-from-openshift/hello/status
5.  Set up  your healthchecks are set up in openshift.
-- This project already has health checks built in.  You just have to configure the liveliness checks.
6.  go to
```
https://insert-your-route-here-from-openshift/hello/oom
```
***BOOM!***
