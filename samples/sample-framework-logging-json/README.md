# JSON-format logging

Demonstrates how to log in JSON format.

MDC-attributes are appended automatically only if present. Makes non-functional appenders more reusable.

This reduces verbosity significantly compared to key/value-text-based format.

Example output:
```json
{"timestamp":"2019-02-08T13:40:57.691+01:00","mdc":{"trace_id":"bf3a2312-b8e9-427e-b751-57315b8b054b","http_uri":"/ping","http_method":"GET","type":"REQUEST_IN"},"logger":"COMMUNICATION","message":""}
{"timestamp":"2019-02-08T13:40:57.706+01:00","level":"INFO","thread":"http-nio-8080-exec-1","mdc":{"trace_id":"bf3a2312-b8e9-427e-b751-57315b8b054b"},"logger":"org.springplayground.samples.loggingjsonapplication.PingResource","message":"Received ping request"}
{"timestamp":"2019-02-08T13:40:57.895+01:00","mdc":{"trace_id":"bf3a2312-b8e9-427e-b751-57315b8b054b","http_uri":"https://www.vg.no","http_method":"GET","type":"REQUEST_OUT"},"logger":"COMMUNICATION","message":""}
{"timestamp":"2019-02-08T13:40:58.121+01:00","mdc":{"trace_id":"bf3a2312-b8e9-427e-b751-57315b8b054b","elapsed_time_ms":"225","http_uri":"https://www.vg.no","http_method":"GET","type":"REQUEST_OUT"},"logger":"PERFORMANCE","message":""}
{"timestamp":"2019-02-08T13:40:58.121+01:00","mdc":{"trace_id":"bf3a2312-b8e9-427e-b751-57315b8b054b","http_status":"200 OK","http_uri":"https://www.vg.no","type":"RESPONSE_IN"},"logger":"COMMUNICATION","message":""}
{"timestamp":"2019-02-08T13:40:58.166+01:00","mdc":{"trace_id":"bf3a2312-b8e9-427e-b751-57315b8b054b","elapsed_time_ms":"451","type":"METHOD","method_sign":"org.springplayground.samples.loggingjsonapplication.PingService#ping"},"logger":"PERFORMANCE","message":""}
{"timestamp":"2019-02-08T13:40:58.179+01:00","mdc":{"trace_id":"bf3a2312-b8e9-427e-b751-57315b8b054b","elapsed_time_ms":"487","http_uri":"/ping","http_method":"GET","type":"REQUEST_IN"},"logger":"PERFORMANCE","message":""}
{"timestamp":"2019-02-08T13:40:58.180+01:00","mdc":{"trace_id":"bf3a2312-b8e9-427e-b751-57315b8b054b","http_status":"200","http_uri":"/ping","http_method":"GET","type":"RESPONSE_OUT"},"logger":"COMMUNICATION","message":""}
``` 