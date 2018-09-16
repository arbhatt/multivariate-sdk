#Preface 
Products frequently need to conduct multivariate testing. There are many needs for this. They may be any of the following but not limited to - 
1. Conception of different capabilities to achieve a certain product goal. However due to market forces, don’t have a determination of which is the best. This brings in the need for testing those features in live environment so that they can be analytically measured and determined
2. A legacy feature is being sunset in favor of a rising feature. Instead of a hard rollout, a soft ramp up is preferred to be able to monitor certain metrics and allow reaction time if there is any additional support needed. For example, training the support groups, business users and so on. 
3. A feature is built and rolled out incrementally and made available to alpha test groups and then at a later time to beta test groups and finally slowly ramping up to customers. 

There is often a service capability in the organization which allows defining of such features and their variation. If allows ramping up and down, defining groups and so on. However, the implementing products often lack an abstraction which allows them to access these remote APIs in a seamless fashion. This is where the multivariate SDK comes into play.

#About Product
Given the feature name and user group identifier, call the service to determine which feature group to be invoked.

##API: 
Request: 
```
GET /mvservice/feature/{feature-name}/callerId/{id}
```

Response: 

Sample JSON:

````
{
	“featureName” : “FY19-ACA-Compliance”,
	“featureGroupId” : “Nash”,
	“featureTestExpireInDays” : 60
}
````


Response Schema

````
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "type": "object",
  "properties": {
    "featureName": {
      "type": "string"
    },
    "featureGroupId": {
      "type": "string"
    },
    "featureTestExpireInDays": {
      "type": "integer"
    }
  },
  "required": [
    "featureName",
    "featureGroupId",
    "featureTestExpireInDays"
  ]
}
````

Request: 
```
GET /mvservice/feature/{feature-name}/callerGroupId/{id}
```

Response: 
````
{
	featureName: “Workday URL Retrieval”,
	featureGroupId : “LegacyCollection”,
	featureTestExpireInDays : 60
}
````

Response Schema

````
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "type": "object",
  "properties": {
    "featureName": {
      "type": "string"
    },
    "featureGroupId": {
      "type": "string"
    },
    "featureTestExpireInDays": {
      "type": "integer"
    }
  },
  "required": [
    "featureName",
    "featureGroupId",
    "featureTestExpireInDays"
  ]
}
````